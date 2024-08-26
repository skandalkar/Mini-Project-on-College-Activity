package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button signIN;
    EditText mobileNo, upiId, password;
    TextView forgot, signUP;
    ImageView googleIN, faceboobkIN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // EditText
        mobileNo = findViewById(R.id.mobileNO);
        upiId = findViewById(R.id.UPIid);
        password = findViewById(R.id.passWord);

        //TextView
        forgot = findViewById(R.id.forgotPass);
        signUP = findViewById(R.id.signUp);

        //Button
        signIN = findViewById(R.id.SignIn);

        //ImageView
        googleIN = findViewById(R.id.googleIn);
        faceboobkIN = findViewById(R.id.facebookIn);

        //for textDecoration = "underlined" /* Underlined text */
        forgot.setText(Html.fromHtml("<u>Forgot Password?</u>"));



        // if old user continuing
        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, OtpVerifyActivity.class);
                startActivity(i);
            }
        });



        // if new user
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}