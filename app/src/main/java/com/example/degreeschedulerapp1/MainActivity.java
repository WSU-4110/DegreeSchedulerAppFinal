package com.example.degreeschedulerapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signup, forgotpass;
    private EditText editLogemail, editLogpassword;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = (TextView) findViewById(R.id.btnSignUp);
        signup.setOnClickListener(this);

        login = (Button) findViewById(R.id.btnSignIn);
        login.setOnClickListener(this);

        editLogemail = (EditText) findViewById(R.id.emailId);
        editLogpassword = (EditText) findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();

        forgotpass = (TextView) findViewById(R.id.forgotPassword);
        forgotpass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnSignUp:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.btnSignIn:
                loginUser();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, PasswordReset.class));
                break;

        }
    }

    private void loginUser() {

        String email = editLogemail.getText().toString().trim();
        String password = editLogpassword.getText().toString().trim();


        if(email.isEmpty()){
            editLogemail.setError("Enter user name");
            editLogemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editLogemail.setError("Invalid WSU Email");
            editLogemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editLogpassword.setError("Enter password");
            editLogpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editLogpassword.setError("Password must be at least 6 characters");
            editLogpassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser verifyuser = FirebaseAuth.getInstance().getCurrentUser();

                    if(verifyuser.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this, Menu.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Verify your account by checking your email!", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Email and/or password is incorrect. Try Again!", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}