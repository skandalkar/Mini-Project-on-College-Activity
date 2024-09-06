package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SurveyActivity extends AppCompatActivity
{
    Fragment[] surveyFragments = {new FragmentA(), new FragmentB(), new FragmentC()};

    Button continueBtn;
    TextView skip_2;

    public int result = 0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        continueBtn = findViewById(R.id.button_continue);
        skip_2 = findViewById(R.id.skip2);

        // okay
        skip_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                nextActivity();
//                Intent intent = new Intent(SurveyActivity.this,DashboardActivity.class);
//                startActivity(intent);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        getSupportFragmentManager().beginTransaction().replace(R.id.survey_fragment_container, new FragmentA()).commit();

        continueBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (savedInstanceState == null)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.survey_fragment_container, new FragmentA()).commit();
                }
                */

                loadNextFragment();
            }
        });
    }
    public void loadNextFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (result)
        {
            case 0:
                // First fragment (FragmentA)
                fragmentTransaction.replace(R.id.survey_fragment_container, new FragmentB());
                result = 1;
                break;

            case 1:
                // Second fragment (FragmentB)
                fragmentTransaction.replace(R.id.survey_fragment_container, new FragmentC());
                result = 2;
                break;

            case 2:
                // No more fragment redirected to next activity
               nextActivity(); // call to nextActivity(); for directing to main home screen or dashboard
               return; // by using the return no any affected error or problems
                //break;
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void nextActivity()
    {
        Intent intent = new Intent(SurveyActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

}