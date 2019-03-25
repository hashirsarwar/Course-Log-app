package com.example.courselog;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Course {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int uid;
    private float obtainedMarks;
    private int totalMarks;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObtainedMarks(float obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public float getObtainedMarks() {
        return obtainedMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public Course(String name, int uid, float obtainedMarks, int totalMarks) {
        this.uid=uid;
        this.name = name;
        this.obtainedMarks = obtainedMarks;
        this.totalMarks = totalMarks;
    }

}
