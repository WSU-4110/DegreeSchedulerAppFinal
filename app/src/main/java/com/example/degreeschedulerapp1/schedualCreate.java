package com.example.degreeschedulerapp1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.degreeschedulerapp1.Data.ClassInfo;
import com.example.degreeschedulerapp1.Data.ClassInfoDao;
import com.example.degreeschedulerapp1.Data.ClassInfoDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class schedualCreate extends AppCompatActivity implements View.OnClickListener {
    String crn;
    EditText textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedualcreate);
        ClassInfoDatabase classInfoDatabase = Room.databaseBuilder(this,
                ClassInfoDatabase.class,
                "CLASS_INFO.db").allowMainThreadQueries().build();
        ClassInfoDao classInfoDao = classInfoDatabase.getClassInfoDao();


        textView = (EditText) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)  {
                crn = textView.getText().toString();
                showToast(crn + " Was the selected Crn");
               /* course.getCrn();
                course.getClassName();
                course.getClassNumber();*/
                ClassInfo course = classInfoDao.getItemByCrn(crn);
                System.out.println(course.getCrn());
                System.out.println(course.getClassName());
                System.out.println(course.getClassNumber());
                try {
                    FileOutputStream fileout=openFileOutput("File.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(course.getClassNumber()+" "+course.getCrn()+" "+course.getClassName());
                    outputWriter.close();
                    //display file saved message
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


    }



    private void showToast(String crn) {
        Toast.makeText(schedualCreate.this,crn,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }



}
