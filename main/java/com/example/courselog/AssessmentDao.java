package com.example.courselog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface AssessmentDao {
    @Query("select exists(select 1 from assessment where userID=:uid and courseName=:cn)")
    int checkAssessmentData(int uid,String cn);
    @Query("delete from assessment where userID=:uid and courseName=:cname")
    void deleteAssessment(int uid,String cname);
    @Insert
    void insertData(Assessment assessment);
    @Query("select * from assessment where userID=:uid and courseName=:cn")
    Assessment getAssessment(int uid,String cn);
}