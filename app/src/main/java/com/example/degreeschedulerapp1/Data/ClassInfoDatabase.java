package com.example.degreeschedulerapp1.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This creates a database for the class information
 * see examples to activate this database
 * Ryan K
 */



@Database(entities = {ClassInfo.class}, version = 1, exportSchema = false)
public abstract class ClassInfoDatabase extends RoomDatabase {
    public abstract ClassInfoDao getClassInfoDao();

    //New singleton example!!
    private static volatile ClassInfoDatabase instance;

    static ClassInfoDatabase getInstance(final Context context){
        if(instance == null){
            synchronized (ClassInfoDatabase.class){
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ClassInfoDatabase.class, "Class_INFO.db")
                            .build();
                }
            }
        }
        return instance;
    }
}
