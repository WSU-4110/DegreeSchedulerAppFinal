package com.example.degreeschedulerapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    private Button createSched, viewDownSched, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        createSched = (Button) findViewById(R.id.btnCreate);
        createSched.setOnClickListener(this);

        viewDownSched = (Button) findViewById(R.id.btnDownView);
        viewDownSched.setOnClickListener(this);

        contact = (Button) findViewById(R.id.btnShare);
        contact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                startActivity(new Intent(this, Create.class));
                break;

        }
    }
}