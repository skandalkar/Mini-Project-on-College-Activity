package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button signIN;
    EditText USERmail, upiId, password;
    TextView forgot, signUP;
    ImageView googleIN, faceboobkIN;

    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // EditText
        USERmail = findViewById(R.id.user_Email);
        upiId = findViewById(R.id.UPIid);
        password = findViewById(R.id.passWord);

        //Button
        signIN = findViewById(R.id.SignIn);

        //TextView
        forgot = findViewById(R.id.forgotPass);
        signUP = findViewById(R.id.signUp);


        //ImageView
        googleIN = findViewById(R.id.googleIn);
        faceboobkIN = findViewById(R.id.facebookIn);

        //for textDecoration = "underlined" /* Underlined text */
        forgot.setText(Html.fromHtml("<u>Forgot Password?</u>"));

        firebaseAuth = FirebaseAuth.getInstance();


        // if old user continuing
        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u_email, u_upi, u_password;

                u_email = USERmail.getText().toString();
                u_upi = upiId.getText().toString();
                u_password = password.getText().toString();

                if (TextUtils.isEmpty(u_email)|| TextUtils.isEmpty(u_upi) || TextUtils.isEmpty(u_password))
                {
                    USERmail.setError("Email No is required!");
                    upiId.setError("Username is required!");
                    password.setError("Password is required!");
                    return;
                }
                if (u_password.length() <= 6)
                {
                    password.setError("Password must be at least 6 characters!");
                    return;
                }

                showProgressDialog();

                firebaseAuth.signInWithEmailAndPassword(u_email,u_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this,"Successfully logged in.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Something went wrong!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
    public void showProgressDialog()
    {
        Dialog progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.signing_progressbar);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}