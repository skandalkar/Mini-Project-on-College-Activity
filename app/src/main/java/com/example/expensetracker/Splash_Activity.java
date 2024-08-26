package com.example.expensetracker;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        Thread splashTread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(2000);  // 2 seconds delay to load next activity
                    startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        splashTread.start();
    }
}