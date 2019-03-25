package com.example.courselog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);
    @Query("delete from course where uid=:uid and name = :cn")
    int deleteCourse(int uid,String cn);
    @Query("select * from course where uid=:uid")
    List<Course> getCourses(int uid);
}
