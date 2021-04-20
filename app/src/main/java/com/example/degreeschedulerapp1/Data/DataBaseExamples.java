//package com.example.degreeschedulerapp1.Data;

//package com.DegreeSchedulerApp.degreescheduler.Data;
//
//import android.content.Context;
//
//import androidx.room.Room;
//
//import java.util.List;
//
///**
// *This is for examples only
// * comment out to experiment :)
// * The database is located under
// * /data/data/com.example.degreescheduler
//
// */
//public abstract class DataBaseExamples extends Context {
//
//    private DataBaseExamples() {
//
//        //CLASS INFO
//
//        ClassInfoDatabase classInfoDatabase = Room.databaseBuilder(this,
//                ClassInfoDatabase.class, "CLASS_INFO.db").allowMainThreadQueries().build();
//
//        ClassInfoDao classInfoDao = classInfoDatabase.getClassInfoDao();
//
//        ClassInfo classInfo = new ClassInfo();
//
//        classInfo.setCrn(12345);
//        classInfo.setClassName("Software Engineering");
//        classInfo.setClassNumber(4110);
//        classInfo.setStartDate("2021-1-11");
//        classInfo.setEndDate("2021-5-4");
//        classInfo.setDays("Monday and Wednesday");
//        classInfo.setInstructor("Professor Bosu");
//        classInfo.setDescription("SE concepts");
//        classInfo.setGrade(0);
//
//        classInfoDao.insert(classInfo);
//        List<ClassInfo> classInfoList = classInfoDao.getClassInfo();
//
//        System.out.println(classInfoList.toString());
//
//
//
//        //USER
//
//        UserDatabase userDatabase = Room.databaseBuilder
//                (this, UserDatabase.class, "USER_DATABASE.db")
//                .allowMainThreadQueries().build();
//
//        UserDao userDao = userDatabase.getUserDAO();
//
//        //Here is the user
//        User user = new User();
//
//        //This is how you enter information
//        user.setAccessId("gn2289");
//        user.setPassword("12345");
//        user.setEmail("gn2289@wayne.edu");
//        userDao.insert(user);
//        System.out.println("added");
//
//        //accessing the database
//        List<User> userList = userDao.getUser();
//
//        System.out.println(userList.toString());
//
//    }
//}
