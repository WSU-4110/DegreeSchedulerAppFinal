package com.example.degreeschedulerapp1.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the entity for Class Information
 * Used to create another db for the classes
 *
 * The class is identified by the CRN
 *
 * Ryan K
 */

@Entity(tableName = "course_table")
public class ClassInfo implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "crn")
    @NonNull
    public String crn;
    //private int grade;



    @ColumnInfo(name = "className")
    private String className;

    @ColumnInfo(name = "grade")
    private int grade;

    @ColumnInfo(name = "classNumber")
    private String classNumber;

    @ColumnInfo(name = "start date")
    private String startDate;

    @ColumnInfo(name = "end date")
    private String endDate;

    @ColumnInfo(name = "days")
    private String days;

    @ColumnInfo(name = "times")
    private String times;

    @ColumnInfo(name = "instructor")
    private String instructor;

    @ColumnInfo(name = "description")
    private String description;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "crn=" + crn +
                ", grade=" + grade +
                ", className='" + className + '\'' +
                ", classNumber=" + classNumber +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", days='" + days + '\'' +
                ", times='" + times + '\'' +
                ", instructor='" + instructor + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

