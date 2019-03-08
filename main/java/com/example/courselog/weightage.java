package com.example.courselog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class weightage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightage);
    }
    public void onUpdateClick(View view)
    {
        EditText et1 = (EditText) findViewById(R.id.editText9);

        EditText et2=(EditText)findViewById(R.id.editText10);

        EditText et3=(EditText)findViewById(R.id.editText11);

        EditText et4=(EditText)findViewById(R.id.editText12);

        EditText et5=(EditText)findViewById(R.id.editText13);



        if(TextUtils.isEmpty(et1.getText().toString()) || TextUtils.isEmpty(et2.getText().toString()) || TextUtils.isEmpty(et3.getText().toString()) || TextUtils.isEmpty(et4.getText().toString()) || TextUtils.isEmpty(et5.getText().toString()))
        {
            TextView tv = (TextView) findViewById(R.id.textView47);
            tv.setText("Enter all fields");
            return;
        }

        int finals = parseInt(et1.getText().toString());
        int projects = parseInt(et2.getText().toString());
        int mid1 = parseInt(et3.getText().toString());
        int mid2 = parseInt(et4.getText().toString());
        int assignments = parseInt(et5.getText().toString());
        if(finals+projects+mid1+mid2+assignments != 100)
        {
            TextView tv = (TextView) findViewById(R.id.textView47);
            tv.setText("Invalid weightage. Sum should be equal to 100");
            return;
        }

        Intent i = new Intent();
        i.putExtra("final_w",finals);
        i.putExtra("project_w",projects);
        i.putExtra("mid1_w",mid1);
        i.putExtra("mid2_w",mid2);
        i.putExtra("assignment_w",assignments);
        setResult(RESULT_OK,i);
        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
        finish();

    }
}
