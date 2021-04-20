package com.example.degreeschedulerapp1.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * This is the data access object for the classes
 * We can insert, delete, update and importantly query
 * Currently we can query by crn
 * We can add more based on what we want to search by
 * Call getItemByCrn to search by crn
 *
 * Ryan K
 */
@Dao
public interface ClassInfoDao {

    //Useful information: https://developer.android.com/training/data-storage/room/accessing-data

    //Queries:

    @Query("SELECT * FROM course_table")
    List<ClassInfo> getAll();

    @Query("SELECT crn, grade, className, classNumber, instructor, times FROM course_table")
    LiveData<List<ClassInfo>> getBasicClassInformation();

    @Query("SELECT crn, grade, className, classNumber FROM course_table WHERE crn = :crn")
    ClassInfo getItemByCrn(String crn);

    @Query("DELETE FROM course_table")
    void clearTheTable();



    //insert, delete and update functions
    @Insert
    void insert(ClassInfo classInfo);

    @Delete
    void delete(ClassInfo classInfo);

    @Update
    void update(ClassInfo classInfo);



}
