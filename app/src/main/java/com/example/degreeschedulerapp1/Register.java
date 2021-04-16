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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, signupUser;
    private EditText editemail, editusername, editpassword, editpasswordconf;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        signupUser = (Button) findViewById(R.id.signUp);
        signupUser.setOnClickListener(this);

        editemail = (EditText) findViewById(R.id.emailId);
        editusername = (EditText) findViewById(R.id.etUsername);
        editpassword = (EditText) findViewById(R.id.etPassword);
        editpasswordconf = (EditText) findViewById(R.id.passwordConfirm);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.signUp:
                signUpUser();
                break;
        }


    }

    private void signUpUser() {
        String email = editemail.getText().toString().trim();
        String username = editusername.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        String passwordconf = editpasswordconf.getText().toString().trim();

        if(email.isEmpty()){
            editemail.setError("Enter WSU email address");
            editemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("Invalid WSU Email");
            editemail.requestFocus();
            return;
        }

        if(username.isEmpty()){
            editusername.setError("Enter user name");
            editusername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editpassword.setError("Enter a password");
            editpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editpassword.setError("Password should be at least 6 characters");
            editpassword.requestFocus();
            return;
        }

        if(passwordconf.isEmpty()){
            editpasswordconf.setError("Enter password again to confirm");
            editpasswordconf.requestFocus();
            return;
        }

        if(!passwordconf.matches(password)){
            editpasswordconf.setError("Password does not match. Try Again!");
            editpasswordconf.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            UserStorage user = new UserStorage(email, username, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        FirebaseUser verifyuser = FirebaseAuth.getInstance().getCurrentUser();

                                        verifyuser.sendEmailVerification();
                                        Toast.makeText(Register.this, "Success! Check your email to verify account", Toast.LENGTH_LONG).show();

                                    }
                                    else
                                    {
                                        Toast.makeText(Register.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Failed to register. Try Again!", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
}