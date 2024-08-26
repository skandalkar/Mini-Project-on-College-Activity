package com.example.expensetracker;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int id = item.getItemId();

                if (id == R.id.home)
                {
                    LoadFragment(new FragmentHome(), true); // default home tab active
                }
                else if (id == R.id.stats)
                {
                    LoadFragment(new FragmentStats(), false);
                }
                else if (id == R.id.wallet)
                {
                    LoadFragment(new FragmentWallet(), false);
                }
                else
                {
                    LoadFragment(new FragmentProfile(), false);
                }
                return true;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.home);
    }

    public void LoadFragment(Fragment fragment, boolean flag)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (flag)
        {
            fragmentTransaction.add(R.id.dashboard_container, fragment);
        }
        else
        {
            fragmentTransaction.replace(R.id.dashboard_container, fragment);
        }
        fragmentTransaction.commit();
    }
}