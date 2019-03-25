package com.example.courselog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class assesment_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        new myAsync().execute();

    }

    private class myAsync extends AsyncTask<Void,Void,Integer>
    {
        @Override
        protected void onPostExecute(Integer integer) {
            if(integer==0)
            {
                TextView t =findViewById(R.id.textView49);
                Button b = findViewById(R.id.button4);
                t.setText("Set Weightage first");
                b.setEnabled(false);
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int uid = getIntent().getIntExtra("userID",-1);
            String cname = getIntent().getStringExtra("courseName");
            CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
            WeightageDao weightageDao=courseLogDB.weightageModel();
            int wCheck = weightageDao.checkWeightageData(uid,cname);
            return wCheck;
        }
    }
    public void onUpdateClick(View view)
    {
        EditText et15 = findViewById(R.id.editText15);
        EditText et16 = findViewById(R.id.editText16);
        EditText et17 = findViewById(R.id.editText17);
        EditText et18 = findViewById(R.id.editText18);
        EditText et19 = findViewById(R.id.editText19);
        EditText et20 = findViewById(R.id.editText20);
        EditText et21 = findViewById(R.id.editText21);
        EditText et29 = findViewById(R.id.editText29);
        EditText et30 = findViewById(R.id.editText30);
        EditText et31 = findViewById(R.id.editText31);
        EditText et32 = findViewById(R.id.editText32);
        EditText et33 = findViewById(R.id.editText33);
        EditText et34 = findViewById(R.id.editText34);
        EditText et35 = findViewById(R.id.editText35);
        if(TextUtils.isEmpty(et15.getText().toString()) || TextUtils.isEmpty(et16.getText().toString()) ||TextUtils.isEmpty(et17.getText().toString()) ||TextUtils.isEmpty(et18.getText().toString()) ||TextUtils.isEmpty(et19.getText().toString()) ||TextUtils.isEmpty(et20.getText().toString()) ||TextUtils.isEmpty(et21.getText().toString()) ||TextUtils.isEmpty(et29.getText().toString()) ||TextUtils.isEmpty(et30.getText().toString()) ||TextUtils.isEmpty(et31.getText().toString()) ||TextUtils.isEmpty(et32.getText().toString()) ||TextUtils.isEmpty(et33.getText().toString()) ||TextUtils.isEmpty(et34.getText().toString()) ||TextUtils.isEmpty(et35.getText().toString()))
        {
            TextView error = findViewById(R.id.textView49);
            error.setText("Enter all fields");
            return;
        }
        new AsyncTask<String,Void,Void>()
        {
            @Override
            protected void onPostExecute(Void v) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(String... p) {

                int uid = getIntent().getIntExtra("userID",-1);
                String cname = getIntent().getStringExtra("courseName");
                Assessment assessment = new Assessment();
                assessment.setUserID(uid);
                assessment.setCourseName(cname);
                assessment.setA1_obt(Integer.parseInt(p[0]));
                assessment.setA2_obt(Integer.parseInt(p[1]));
                assessment.setA3_obt(Integer.parseInt(p[2]));
                assessment.setProj_obt(Integer.parseInt(p[3]));
                assessment.setMid1_obt(Integer.parseInt(p[4]));
                assessment.setMid2_obt(Integer.parseInt(p[5]));
                assessment.setFinal_obt(Integer.parseInt(p[6]));
                assessment.setA1_tot(Integer.parseInt(p[7]));
                assessment.setA2_tot(Integer.parseInt(p[8]));
                assessment.setA3_tot(Integer.parseInt(p[9]));
                assessment.setProj_tot(Integer.parseInt(p[10]));
                assessment.setMid1_tot(Integer.parseInt(p[11]));
                assessment.setMid2_tot(Integer.parseInt(p[12]));
                assessment.setFinal_tot(Integer.parseInt(p[13]));
                CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                AssessmentDao assessmentDao = courseLogDB.assessmentModel();
                int check = assessmentDao.checkAssessmentData(uid, cname);
                if (check == 1) {
                    assessmentDao.deleteAssessment(uid, cname);
                }
                assessmentDao.insertData(assessment);
                return null;
            }
        }.execute(
                et21.getText().toString(),
                et16.getText().toString(),
                et15.getText().toString(),
                et17.getText().toString(),
                et18.getText().toString(),
                et19.getText().toString(),
                et20.getText().toString(),
                et29.getText().toString(),
                et30.getText().toString(),
                et31.getText().toString(),
                et32.getText().toString(),
                et33.getText().toString(),
                et34.getText().toString(),
                et35.getText().toString()
        );
        setResult(RESULT_OK);
        finish();
    }
}