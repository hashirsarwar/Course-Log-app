package com.example.courselog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import java.util.List;

public class log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new AsyncTask<Void,Void,List<Course>>()
        {
            @Override
            protected void onPostExecute(List<Course> courses) {
                final List<Course> c =courses;
                RecyclerView rvCourses = findViewById(R.id.rv);
                CourseAdapter ca = new CourseAdapter(c);
                ca.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(),home.class);
                        i.putExtra("userID",getIntent().getIntExtra("userID",-1));
                        i.putExtra("courseName",c.get(position).getName());
                        startActivity(i);
                    }
                });
                rvCourses.setAdapter(ca);
                rvCourses.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            protected List<Course> doInBackground(Void... voids) {
                CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                CourseDao courseDao=courseLogDB.courseModel();
                final List<Course> courses=courseDao.getCourses(getIntent().getIntExtra("userID",-1));
                return courses;
            }
        }.execute();
    }

    public void onAddClick(View view)
    {
        //insert new course in db
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText = new EditText(getApplicationContext());
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        alert.setMessage("Enter course title");
        alert.setView(editText);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String cname = editText.getText().toString();
                new AsyncTask<Void,Void,Void>()
                {
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        onStart();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                        CourseDao courseDao=courseLogDB.courseModel();
                        Course course = new Course(cname,getIntent().getIntExtra("userID",-1),0,0);
                        courseDao.insertCourse(course);
                        return null;
                    }
                }.execute();
            }
        });
        alert.show();
    }
}
