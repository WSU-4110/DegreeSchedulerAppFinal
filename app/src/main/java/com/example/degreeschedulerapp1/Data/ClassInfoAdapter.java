package com.example.degreeschedulerapp1.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeschedulerapp1.R;

import java.util.ArrayList;
import java.util.List;
/**
 * This is the adapter for the ClassInfo
 * With this, we can display the classes to the app
 *
 *
 * Ryan K
 */

public class ClassInfoAdapter extends RecyclerView.Adapter<ClassInfoAdapter.ViewHolder> {

    //put everything from rooms inth a list

    List<ClassInfo> classInfoList;

    //constructor

    public ClassInfoAdapter(List<ClassInfo> classInfoList) {
        this.classInfoList = classInfoList;
    }

    //viewer, connects to course_row layout

    @NonNull
    @Override
    public ClassInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    //get info and set it up for the xml file
    //snags the data from out rooms db
    public void onBindViewHolder(@NonNull ClassInfoAdapter.ViewHolder holder, int position) {
        holder.crn.setText(classInfoList.get(position).getCrn());
        holder.grade.setText(classInfoList.get(position).getCrn());
        holder.className.setText(classInfoList.get(position).getClassName());
        holder.classNumber.setText(classInfoList.get(position).getClassNumber());
        holder.startDate.setText(classInfoList.get(position).getStartDate());
        holder.endDate.setText(classInfoList.get(position).getEndDate());
        holder.days.setText(classInfoList.get(position).getDays());
        holder.times.setText(classInfoList.get(position).getTimes());
        holder.instructor.setText(classInfoList.get(position).getInstructor());
        holder.description.setText(classInfoList.get(position).getDescription());


    }
    //returns the amount of classes we have
    //probably useless but this method is required
    @Override
    public int getItemCount() {
        return classInfoList.size();
    }

    //This takes all the information from the database and connects it to the layout
    public class ViewHolder extends RecyclerView.ViewHolder {

        //crn, grade, classname, classnumber, startdate, enddate, days, times, instructor, description

        public TextView crn;
        public TextView grade;
        public TextView className;
        public TextView classNumber;
        public TextView startDate;
        public TextView endDate;
        public TextView days;
        public TextView times;
        public TextView instructor;
        public TextView description;


        //connects to layout

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            crn = itemView.findViewById(R.id.crn);
            grade = itemView.findViewById(R.id.grades);
            className = itemView.findViewById(R.id.className);
            classNumber = itemView.findViewById(R.id.classNumber);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            days = itemView.findViewById(R.id.days);
            times = itemView.findViewById(R.id.times);
            instructor = itemView.findViewById(R.id.instructor);
            description = itemView.findViewById(R.id.description);

        }
    }

    public void filteredList(List<ClassInfo> filteredList) {
        classInfoList = filteredList;
        notifyDataSetChanged();

    }

}
