package com.example.expensetracker;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OtpVerifyActivity extends AppCompatActivity
{
    Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verify);

        verify = findViewById(R.id.verifyButton);

        verify.setOnClickListener(v -> {
            Intent intent = new Intent(OtpVerifyActivity.this, SurveyActivity.class);
            startActivity(intent);
        });

    }
}