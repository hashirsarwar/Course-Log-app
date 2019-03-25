package com.example.courselog;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class home extends AppCompatActivity {
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        flag=false;
        SpannableString ss = new SpannableString("Marks shown here are calculated absolutes based on the information added in the Assessments and Weightages");
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), assesment_activity.class);
                i.putExtra("userID", getIntent().getIntExtra("userID", -1));
                i.putExtra("courseName", getIntent().getStringExtra("courseName"));
                startActivityForResult(i, 2);
            }
        };
        ss.setSpan(cs, 80, 91, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan cs2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i = new Intent(getApplicationContext(), weightage_activity.class);
                i.putExtra("userID", getIntent().getIntExtra("userID", -1));
                i.putExtra("courseName", getIntent().getStringExtra("courseName"));
                startActivity(i);
            }
        };
        ss.setSpan(cs2, 96, 106, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView homeInfo = findViewById(R.id.textView15);
        homeInfo.setText(ss);
        homeInfo.setMovementMethod(LinkMovementMethod.getInstance());
        new myAsync().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAsync2 obj = new myAsync2();
        obj.execute();
    }

    private class myAsync2 extends AsyncTask<Void, Void, Wrapper> {
        @Override
        protected void onPostExecute(Wrapper wrapper) {
            if (wrapper.weightage != null) {
                TextView tv = findViewById(R.id.textView60);
                tv.setText(String.valueOf(wrapper.weightage.getAssignments()));
                tv = findViewById(R.id.textView64);
                tv.setText(String.valueOf(wrapper.weightage.getProject()));
                tv = findViewById(R.id.textView67);
                tv.setText(String.valueOf(wrapper.weightage.getMid1()));
                tv = findViewById(R.id.textView68);
                tv.setText(String.valueOf(wrapper.weightage.getMid2()));
                tv = findViewById(R.id.textView69);
                tv.setText(String.valueOf(wrapper.weightage.getFinalExam()));
                if (wrapper.assessment != null) {
                    float assignment = wrapper.assessment.getA1_obt() + wrapper.assessment.getA2_obt() + wrapper.assessment.getA3_obt();
                    assignment = assignment / (float) (wrapper.assessment.getA1_tot() + wrapper.assessment.getA2_tot() + wrapper.assessment.getA3_tot());
                    assignment = assignment * (float) wrapper.weightage.getAssignments();
                    float project = (float) wrapper.assessment.getProj_obt() / (float) wrapper.assessment.getProj_tot();
                    project = project * (float) wrapper.weightage.getProject();
                    float mid1 = (float) wrapper.assessment.getMid1_obt() / (float) wrapper.assessment.getMid1_tot();
                    mid1 = mid1 * (float) wrapper.weightage.getMid1();
                    float mid2 = (float) wrapper.assessment.getMid2_obt() / (float) wrapper.assessment.getMid2_tot();
                    mid2 = mid2 * (float) wrapper.weightage.getMid2();
                    float Final = (float) wrapper.assessment.getFinal_obt() / (float) wrapper.assessment.getFinal_tot();
                    Final = Final * (float) wrapper.weightage.getFinalExam();
                    tv = findViewById(R.id.textView48);
                    tv.setText(String.valueOf((int)assignment));
                    tv = findViewById(R.id.textView62);
                    tv.setText(String.valueOf((int)project));
                    tv = findViewById(R.id.textView57);
                    tv.setText(String.valueOf((int)Final));
                    tv = findViewById(R.id.textView65);
                    tv.setText(String.valueOf((int)mid1));
                    tv = findViewById(R.id.textView54);
                    tv.setText(String.valueOf((int)mid2));
                    if(flag==true)
                    {
                        myAsync3 obj = new myAsync3();
                        obj.execute(assignment,project,mid1,mid2,Final);
                        flag=false;
                    }
                }
            }
        }

        @Override
        protected Wrapper doInBackground(Void... voids) {
            int uid = getIntent().getIntExtra("userID", -1);
            String cn = getIntent().getStringExtra("courseName");
            CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
            Wrapper w = new Wrapper();
            WeightageDao weightageDao = courseLogDB.weightageModel();
            w.weightage = weightageDao.getWeightage(uid, cn);
            AssessmentDao assessmentDao = courseLogDB.assessmentModel();
            w.assessment = assessmentDao.getAssessment(uid, cn);
            return w;
        }
    }
    private class myAsync3 extends AsyncTask<Float, Void, Void>
    {
        @Override
        protected Void doInBackground(Float... params) {
            CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
            CourseDao courseDao = courseLogDB.courseModel();
            int uid = getIntent().getIntExtra("userID", -1);
            String cname = getIntent().getStringExtra("courseName");
            Course course = new Course(cname, uid, params[0]+params[1]+params[2]+params[3]+params[4], 100);
            courseDao.deleteCourse(uid,cname);
            courseDao.insertCourse(course);
            return null;
        }
    }
    private class myAsync extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... voids) {
            CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
            UserDao u = courseLogDB.userModel();
            User obj;
            obj = u.getUser(getIntent().getIntExtra("userID", -1));
            return obj;
        }

        @Override
        protected void onPostExecute(User user) {
            TextView info = findViewById(R.id.textView14);
            info.setText(getIntent().getStringExtra("courseName"));
            info = findViewById(R.id.textView44);
            info.setText(user.getName());
            info = findViewById(R.id.textView45);
            info.setText(user.getRoll_no());
            info = findViewById(R.id.textView46);
            info.setText(user.getBatch());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==2 && resultCode==RESULT_OK)
        {
            flag=true;
        }
    }
}