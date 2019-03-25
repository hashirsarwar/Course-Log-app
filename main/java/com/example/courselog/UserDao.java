package com.example.courselog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void addUser(User users);
    @Query("select exists(select 1 from users where username=:uname)")
    int findUser(String uname);
    @Query("select id from users where username=:uname and password=:pass")
    int getUserUsingpass(String uname,String pass);
    @Query("select * from users where id=:uid")
    User getUser(int uid);
}
