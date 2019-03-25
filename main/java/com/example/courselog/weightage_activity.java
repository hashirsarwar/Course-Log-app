package com.example.courselog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class weightage_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightage);
    }
    public void onUpdateClick(View view)
    {
        EditText et1 = findViewById(R.id.editText9);

        EditText et2= findViewById(R.id.editText10);

        EditText et3= findViewById(R.id.editText11);

        EditText et4= findViewById(R.id.editText12);

        EditText et5= findViewById(R.id.editText13);

        if(TextUtils.isEmpty(et1.getText().toString()) || TextUtils.isEmpty(et2.getText().toString()) || TextUtils.isEmpty(et3.getText().toString()) || TextUtils.isEmpty(et4.getText().toString()) || TextUtils.isEmpty(et5.getText().toString()))
        {
            TextView tv = findViewById(R.id.textView71);
            tv.setText("Enter all fields");
            return;
        }

        int assignments = parseInt(et1.getText().toString());
        int projects = parseInt(et2.getText().toString());
        int mid1 = parseInt(et3.getText().toString());
        int mid2 = parseInt(et4.getText().toString());
        int finals = parseInt(et5.getText().toString());
        if(finals+projects+mid1+mid2+assignments != 100)
        {
            TextView tv = findViewById(R.id.textView71);
            tv.setText("Invalid weightage. Sum should be equal to 100");
            return;
        }
        new AsyncTask<Integer,Void,Void>()
        {
            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(Integer... integers) {
                CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                WeightageDao weightageDao=courseLogDB.weightageModel();
                int userID= getIntent().getIntExtra("userID",-1);
                String cname = getIntent().getStringExtra("courseName");
                int check =weightageDao.checkWeightageData(userID,cname);
                if(check==1)
                {
                    weightageDao.deleteWeightage(userID,cname);
                }
                Weightage weightage = new Weightage();
                weightage.setUserID(userID);
                weightage.setCourseName(cname);
                weightage.setAssignments(integers[0]);
                weightage.setProject(integers[1]);
                weightage.setMid1(integers[2]);
                weightage.setMid2(integers[3]);
                weightage.setFinalExam(integers[4]);
                weightageDao.insertData(weightage);
                return null;
            }
        }.execute(assignments,projects,mid1,mid2,finals);
        finish();
    }
}
