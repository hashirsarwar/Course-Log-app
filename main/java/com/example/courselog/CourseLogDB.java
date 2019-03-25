package com.example.courselog;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class,Assessment.class,Weightage.class,Course.class},version = 1)
public abstract class CourseLogDB extends RoomDatabase {
    private static CourseLogDB instance;
    public abstract UserDao userModel();
    public abstract AssessmentDao assessmentModel();
    public abstract WeightageDao weightageModel();
    public abstract CourseDao courseModel();
    public static synchronized CourseLogDB getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context,CourseLogDB.class,"CourseLogDB").build();
        }
        return instance;
    }
}
