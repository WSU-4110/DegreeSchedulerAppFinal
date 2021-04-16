package com.example.degreeschedulerapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {

    private EditText editResetemail;
    private Button resetPassbutton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        editResetemail = (EditText) findViewById(R.id.emailId);
        resetPassbutton = (Button) findViewById(R.id.resetLink);

        auth = FirebaseAuth.getInstance();

        resetPassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
                return;
            }
        });

    }

    private void resetPassword() {
        String email = editResetemail.getText().toString().trim();

        if(email.isEmpty())
        {
            editResetemail.setError("Enter your email");
            editResetemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editResetemail.setError("Invalid WSU Email");
            editResetemail.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(PasswordReset.this, "Password Reset link sent to your email!", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(PasswordReset.this, "Failed to send link. Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}