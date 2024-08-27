package com.example.expensetracker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText username, usermail, userpassword;
    CheckBox checkBox1;
    TextView terms_conditions, signIN;
    ImageView googleIN, faceboobkIN;
    Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.userName);
        usermail = findViewById(R.id.userEmail);
        userpassword = findViewById(R.id.userPassword);

        checkBox1 = findViewById(R.id.checkbox1);

        terms_conditions = findViewById(R.id.terms);
        signIN = findViewById(R.id.signInButton);

        createAcc = findViewById(R.id.createAccount);

        googleIN = findViewById(R.id.googleIn);
        faceboobkIN = findViewById(R.id.facebookIn);


        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,OtpVerifyActivity.class);
                startActivity(intent);
            }
        });


        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        terms_conditions.setText(Html.fromHtml("<u>Terms & Conditions</u>"));

        /** For terms and conditions simple dialog-box */
        terms_conditions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Dialog dialog = new Dialog(SignUpActivity.this);
                dialog.setContentView(R.layout.alert_dialog);

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.CENTER);

                Button cancelButton = dialog.findViewById(R.id.cancelButton);
                Button allowButton = dialog.findViewById(R.id.allowButton);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                allowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle the allow action
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}