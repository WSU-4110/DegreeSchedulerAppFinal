package com.example.degreeschedulerapp1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.degreeschedulerapp1.Data.ClassInfo;
import com.example.degreeschedulerapp1.Data.ClassInfoAdapter;
import com.example.degreeschedulerapp1.Data.ClassInfoDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateSchedule extends AppCompatActivity implements View.OnClickListener
{

    RecyclerView recyclerView;
    ClassInfoAdapter adapter;
    private Button addClass;
    private List<ClassInfo> classInfoList;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);


        addClass = (Button) findViewById(R.id.addClass);
        addClass.setOnClickListener(v -> {
            Intent intent = new Intent(this, schedualCreate.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recycler_view);

        //allow database to use main thread (bad)
        //enables database
        ClassInfoDatabase classInfoDatabase = Room.databaseBuilder(this,
                ClassInfoDatabase.class, "CLASS_INFO.db").allowMainThreadQueries().build();

        //add all classes to this list, this query gives all the information
        classInfoList = classInfoDatabase.getClassInfoDao().getAll();

        //enable recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassInfoAdapter(classInfoList);
        recyclerView.setAdapter(adapter);



        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //filter
                filter(s.toString());

            }
        });


    }
    private void filter(String txt) {
        ArrayList<ClassInfo> classInfosFilteredList = new ArrayList<>();

        for (ClassInfo classInfo : classInfoList){
            if (classInfo.getClassName().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }
            else if (classInfo.getClassNumber().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }

            else if (classInfo.getDays().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }
            else if (classInfo.getDescription().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }

            else if (classInfo.getStartDate().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }
            else if (classInfo.getEndDate().contains(txt)) {
                classInfosFilteredList.add(classInfo);
            }
            else if(classInfo.getCrn().toString().contains(txt)){
                classInfosFilteredList.add(classInfo);
            }
        }
        adapter.filteredList(classInfosFilteredList);
    }

    @Override
    public void onClick(View v) {

    }

}