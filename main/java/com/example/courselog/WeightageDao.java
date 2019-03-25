package com.example.courselog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface WeightageDao {
    @Query("select exists(select 1 from weightage where userID=:uid and courseName=:cn)")
    int checkWeightageData(int uid,String cn);
    @Query("delete from weightage where userID=:uid and courseName=:cname")
    void deleteWeightage(int uid,String cname);
    @Insert
    void insertData(Weightage weightage);
    @Query("select * from weightage where userID=:uid and courseName=:cn")
    Weightage getWeightage(int uid,String cn);
}
