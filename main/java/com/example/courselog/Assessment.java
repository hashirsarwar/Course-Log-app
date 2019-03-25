package com.example.courselog;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userID;
    private String courseName;
    private int a1_obt;
    private int a1_tot;
    private int a2_obt;
    private int a2_tot;
    private int a3_obt;
    private int a3_tot;
    private int proj_obt;
    private int proj_tot;
    private int mid1_obt;
    private int mid1_tot;
    private int mid2_obt;
    private int mid2_tot;
    private int final_obt;
    private int final_tot;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getA1_obt() {
        return a1_obt;
    }

    public void setA1_obt(int a1_obt) {
        this.a1_obt = a1_obt;
    }

    public int getA1_tot() {
        return a1_tot;
    }

    public void setA1_tot(int a1_tot) {
        this.a1_tot = a1_tot;
    }

    public int getA2_obt() {
        return a2_obt;
    }

    public void setA2_obt(int a2_obt) {
        this.a2_obt = a2_obt;
    }

    public int getA2_tot() {
        return a2_tot;
    }

    public void setA2_tot(int a2_tot) {
        this.a2_tot = a2_tot;
    }

    public int getA3_obt() {
        return a3_obt;
    }

    public void setA3_obt(int a3_obt) {
        this.a3_obt = a3_obt;
    }

    public int getA3_tot() {
        return a3_tot;
    }

    public void setA3_tot(int a3_tot) {
        this.a3_tot = a3_tot;
    }

    public int getProj_obt() {
        return proj_obt;
    }

    public void setProj_obt(int proj_obt) {
        this.proj_obt = proj_obt;
    }

    public int getProj_tot() {
        return proj_tot;
    }

    public void setProj_tot(int proj_tot) {
        this.proj_tot = proj_tot;
    }

    public int getMid1_obt() {
        return mid1_obt;
    }

    public void setMid1_obt(int mid1_obt) {
        this.mid1_obt = mid1_obt;
    }

    public int getMid1_tot() {
        return mid1_tot;
    }

    public void setMid1_tot(int mid1_tot) {
        this.mid1_tot = mid1_tot;
    }

    public int getMid2_obt() {
        return mid2_obt;
    }

    public void setMid2_obt(int mid2_obt) {
        this.mid2_obt = mid2_obt;
    }

    public int getMid2_tot() {
        return mid2_tot;
    }

    public void setMid2_tot(int mid2_tot) {
        this.mid2_tot = mid2_tot;
    }

    public int getFinal_obt() {
        return final_obt;
    }

    public void setFinal_obt(int final_obt) {
        this.final_obt = final_obt;
    }

    public int getFinal_tot() {
        return final_tot;
    }

    public void setFinal_tot(int final_tot) {
        this.final_tot = final_tot;
    }
}
