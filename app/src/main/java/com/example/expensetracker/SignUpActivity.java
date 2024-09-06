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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity
{
    String Uname, Umail, Umobile, Uupi, Upassword;
    DatabaseReference reference;

    EditText user_name, user_mail, user_password, mobile_no, user_Name;
    CheckBox checkBox1;
    TextView terms_conditions, signIN;

    Button createAcc;

    // for SignIN with Google
    ImageView googleIN, faceboobkIN;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    int RS_Sign_In = 20;


    FirebaseAuth firebaseAuth; // email-login

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user_name = findViewById(R.id.userName);
        user_mail = findViewById(R.id.userEmail);
        mobile_no = findViewById(R.id.userMobile);
        user_Name = findViewById(R.id.Username);
        user_password = findViewById(R.id.userPassword);

        checkBox1 = findViewById(R.id.checkbox1);

        terms_conditions = findViewById(R.id.terms);
        signIN = findViewById(R.id.signInButton);

        createAcc = findViewById(R.id.createAccount);

        faceboobkIN = findViewById(R.id.facebookIn);


        /** For Google Sign-In */

        googleIN = findViewById(R.id.googleIn);

        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();


        gsc = GoogleSignIn.getClient(SignUpActivity.this,gso);

        googleIN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signIn();
            }
        });

        /* ------------------------------------------------------------------------------------------------- */

        // for Custom User Mail and pass authentication
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(SignUpActivity.this,DashboardActivity.class);
            startActivity(intent);
            //Toast.makeText(SignUpActivity.this,"You already have an account!",Toast.LENGTH_SHORT).show();
            //finish();
        }

        createAcc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Uname = user_name.getText().toString();
                Umail = user_mail.getText().toString();
                Umobile = mobile_no.getText().toString();
                Uupi = user_Name.getText().toString();
                Upassword = user_password.getText().toString();

                if (TextUtils.isEmpty(Uname) && TextUtils.isEmpty(Umail) && TextUtils.isEmpty(Umobile) &&
                        TextUtils.isEmpty(Uupi) && TextUtils.isEmpty(Upassword))
                {
                    user_name.setError("Name is required!");
                    user_mail.setError("Email is required!");
                    mobile_no.setError("Mobile No is required!");
                    user_Name.setError("UPI ID is required!");
                    user_password.setError("Password is required!");
                    return;
                }
                if (Upassword.length() <= 6)
                {
                    user_password.setError("Password must be at least 6 characters!");
                    return;
                }

                if (firebaseAuth.getCurrentUser() != null)
                {
                    Toast.makeText(SignUpActivity.this,"You already have an account!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                showProgressDialog();
                //progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(Umail,Upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this,"Account created successfully.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);

                            /* for firebase */
                            Users users = new Users(Uname, Umail, Umobile, Uupi, Upassword);
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            //String userId = reference.push().getKey();
                            reference = firebaseDatabase.getReference("Users");

                            reference.child(Uupi).setValue(users);

                            user_name.setText("");
                            user_mail.setText("");
                            mobile_no.setText("");
                            user_Name.setText("");
                            user_password.setText("");
                            /** */

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Error occurring while creating"+task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


        /** */

        // this is signin button
        signIN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        terms_conditions.setText(Html.fromHtml("<u>Terms & Conditions</u>"));

        /** For terms and conditions simple dialog-box */
        terms_conditions.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Dialog dialog = new Dialog(SignUpActivity.this);
                dialog.setContentView(R.layout.alert_dialog);

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.CENTER);

                Button cancelButton = dialog.findViewById(R.id.cancelButton);
                Button allowButton = dialog.findViewById(R.id.allowButton);

                cancelButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });

                allowButton.setOnClickListener(new View.OnClickListener()
                {
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


    // Google SignIN
    private void signIn()
    {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent,RS_Sign_In);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RS_Sign_In)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                fAuth(account.getIdToken());

            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fAuth(String idToken)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user = fAuth.getCurrentUser();
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("id",user.getUid());
                            map.put("name",user.getDisplayName());
                            map.put("profile",user.getPhotoUrl()).toString();
                            firebaseDatabase.getReference().child("userss").child(user.getUid()).setValue(map);

                            Intent intent = new Intent(SignUpActivity.this,DashboardActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                        }
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