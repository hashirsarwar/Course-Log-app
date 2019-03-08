package com.example.courselog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class log extends AppCompatActivity {

    int pos=-1;
    ArrayList<Course> alc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        RecyclerView rvCourses = (RecyclerView) findViewById(R.id.rv);


        alc.add(new Course("Software for Mobile Devices", 0, 0));
        alc.add(new Course("Artificial Intelligence", 0, 0));
        alc.add(new Course("Computer Networks", 0, 0));
        alc.add(new Course("Software Engineering", 0, 0));
        alc.add(new Course("Object Oriented Analysis and Design", 0, 0));

        CourseAdapter ca = new CourseAdapter(alc);

        ca.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pos=position;
                Intent i = new Intent(getApplicationContext(),home.class);
                i.putExtra("name",getIntent().getStringExtra("name"));
                i.putExtra("batch",getIntent().getStringExtra("batch"));
                i.putExtra("roll_no",getIntent().getStringExtra("roll_no"));
                i.putExtra("course",alc.get(position).getName());
                startActivityForResult(i,1000);
            }
        });
        rvCourses.setAdapter(ca);

        rvCourses.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==1000)
        {
            Course c = alc.get(pos);

            Course c2 = new Course(c.getName(),data.getFloatExtra("obtained",0),data.getIntExtra("total",0));
            alc.set(pos,c2);
            RecyclerView rvCourses = (RecyclerView) findViewById(R.id.rv);
            rvCourses.getAdapter().notifyDataSetChanged();

        }
    }
}
