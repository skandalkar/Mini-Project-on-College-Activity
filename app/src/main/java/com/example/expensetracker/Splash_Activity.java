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
                    /* Where the ideal time of splash screen with progress bar is 3 seconds or 3000 milliseconds. */

                    sleep(2500);  // 2.5 seconds delay to load next activity
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

/***
      This is Completely ok and perfect working
  */