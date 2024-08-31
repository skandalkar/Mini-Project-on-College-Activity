package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity
{
    EditText username, usermail, userpassword, mobile_no, upi_id;
    CheckBox checkBox1;
    TextView terms_conditions, signIN;
    ImageView googleIN, faceboobkIN;
    Button createAcc;

    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.userName);
        usermail = findViewById(R.id.userEmail);
        mobile_no = findViewById(R.id.userMobile);
        upi_id = findViewById(R.id.Userupi);
        userpassword = findViewById(R.id.userPassword);

        checkBox1 = findViewById(R.id.checkbox1);

        terms_conditions = findViewById(R.id.terms);
        signIN = findViewById(R.id.signInButton);

        createAcc = findViewById(R.id.createAccount);

        googleIN = findViewById(R.id.googleIn);
        faceboobkIN = findViewById(R.id.facebookIn);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(SignUpActivity.this,DashboardActivity.class);
            startActivity(intent);
            //Toast.makeText(SignUpActivity.this,"You already have an account!",Toast.LENGTH_SHORT).show();
            //finish();
        }

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname, Umail, Umobile, Uupi, Upassword;

                Uname = username.getText().toString();
                Umail = usermail.getText().toString();
                Umobile = mobile_no.getText().toString();
                Uupi = upi_id.getText().toString();
                Upassword = userpassword.getText().toString();

                if (TextUtils.isEmpty(Uname) && TextUtils.isEmpty(Umail) && TextUtils.isEmpty(Umobile) && TextUtils.isEmpty(Uupi) && TextUtils.isEmpty(Upassword))
                {
                    username.setError("Name is required!");
                    usermail.setError("Email is required!");
                    mobile_no.setError("Mobile No is required!");
                    upi_id.setError("UPI ID is required!");
                    userpassword.setError("Password is required!");
                    return;
                }
                if (Upassword.length() <= 6)
                {
                    userpassword.setError("Password must be at least 6 characters!");
                    return;
                }

                if (firebaseAuth.getCurrentUser() != null)
                {
                    Toast.makeText(SignUpActivity.this,"You already have an account!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                showProgressDialog();
                //progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(Umail,Upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this,"Account created successfully.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Error occurring while creating"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    public void showProgressDialog()
    {
        Dialog progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.dialog_progressbar);
        progressDialog.setCancelable(false);
        progressDialog.show();
        //createAccount(progressDialog);
    }
}