package com.example.courselog;

public class Course {

    private String name;
    private float obtainedMarks;
    private int totalMarks;

    public String getName() {
        return name;
    }

    public float getObtainedMarks() {
        return obtainedMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public Course(String name, float obtainedMarks, int totalMarks) {
        this.name = name;
        this.obtainedMarks = obtainedMarks;
        this.totalMarks = totalMarks;
    }

}
