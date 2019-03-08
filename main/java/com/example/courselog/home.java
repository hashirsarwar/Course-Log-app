package com.example.courselog;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import static java.sql.Types.NULL;

public class home extends AppCompatActivity {
    int final_w,assignment_w,mid1_w,mid2_w,project_w = NULL;
    float final_a =-9999,assignment_a =-9999,mid1_a=-9999,mid2_a=-9999,project_a =-9999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SpannableString ss=new SpannableString("Marks shown here are calculated absolutes based on the information added in the Assesments and Weightages");
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(),assesment.class),2);
            }
        };
        ss.setSpan(cs,80,90, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan cs2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivityForResult(new Intent(getApplicationContext(),weightage.class),1);
            }
        };
        ss.setSpan(cs2,95,105,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView homeInfo =(TextView) findViewById(R.id.textView15);
        homeInfo.setText(ss);
        homeInfo.setMovementMethod(LinkMovementMethod.getInstance());

        TextView info = (TextView) findViewById(R.id.textView14);
        info.setText(getIntent().getStringExtra("course"));
        info = (TextView) findViewById(R.id.textView44);
        info.setText(getIntent().getStringExtra("name"));
        info = (TextView) findViewById(R.id.textView45);
        info.setText(getIntent().getStringExtra("roll_no"));
        info = (TextView) findViewById(R.id.textView46);
        info.setText(getIntent().getStringExtra("batch"));

    }

    public void onBackClick(View view)
    {
        float obt=0;int tot=0;
        if(final_a!=-9999)
        {
            obt = obt + final_a*final_w;
            tot = tot + final_w;
        }
        if(assignment_a!=-9999)
        {
            obt = obt + assignment_a*assignment_w;
            tot = tot + assignment_w;
        }
        if(project_a!=-9999)
        {
            obt = obt + project_a*project_w;
            tot = tot + project_w;
        }
        if(mid1_a!=-9999)
        {
            obt = obt + mid1_a*mid1_w;
            tot = tot + mid1_w;
        }
        if(mid2_a!=-9999)
        {
            obt = obt + mid2_a*mid2_w;
            tot = tot + mid2_w;
        }

        Intent i = new Intent();

        i.putExtra("obtained",obt);
        i.putExtra("total",tot);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && final_w == NULL && resultCode == RESULT_OK)
        {
            TextView error = (TextView) findViewById(R.id.textView50);
            error.setText("Set weightage first");
            return;
        }


        if(requestCode==1 && resultCode == RESULT_OK) {
            final_w = data.getIntExtra("final_w", 0);
            project_w = data.getIntExtra("project_w", 0);
            mid1_w = data.getIntExtra("mid1_w", 0);
            mid2_w = data.getIntExtra("mid2_w", 0);
            assignment_w = data.getIntExtra("assignment_w", 0);

            TextView tv = (TextView) findViewById(R.id.textView69);
            tv.setText(Integer.toString(final_w));
            tv = (TextView) findViewById(R.id.textView64);
            tv.setText(Integer.toString(project_w));
            tv = (TextView) findViewById(R.id.textView67);
            tv.setText(Integer.toString(mid1_w));
            tv = (TextView) findViewById(R.id.textView68);
            tv.setText(Integer.toString(mid2_w));
            tv = (TextView) findViewById(R.id.textView60);
            tv.setText(Integer.toString(assignment_w));
            tv = (TextView) findViewById(R.id.textView50);
            tv.setText("");

            if(assignment_a!=-9999){
                tv = (TextView) findViewById(R.id.textView48);
                tv.setText(Float.toString(assignment_a*assignment_w));}
            if(project_a!=-9999){
                tv = (TextView) findViewById(R.id.textView62);
                tv.setText(Float.toString(project_a*project_w));}
            if(final_a!=-9999){
                tv = (TextView) findViewById(R.id.textView57);
                tv.setText(Float.toString(final_a*final_w));}
            if(mid1_a!=-9999){
                tv = (TextView) findViewById(R.id.textView65);
                tv.setText(Float.toString(mid1_a*mid1_w));}
            if(mid2_a!=-9999){
                tv = (TextView) findViewById(R.id.textView54);
                tv.setText(Float.toString(mid2_a*mid2_w));}
        }


        if(requestCode==2 && resultCode==RESULT_OK)
        {
            final_a = data.getFloatExtra("final", 0);
            project_a = data.getFloatExtra("project", 0);
            mid1_a = data.getFloatExtra("mid1", 0);
            mid2_a = data.getFloatExtra("mid2", 0);
            assignment_a = data.getFloatExtra("assignment", 0);
            TextView tv;
            if(assignment_a!=-9999) {
                tv = (TextView) findViewById(R.id.textView48);
                tv.setText(Float.toString(assignment_a*assignment_w));
            }
            if(project_a!=-9999) {
                tv = (TextView) findViewById(R.id.textView62);
                tv.setText(Float.toString(project_a * project_w));
            }

            if(final_a!=-9999) {
                tv = (TextView) findViewById(R.id.textView57);
                tv.setText(Float.toString(final_a * final_w));
            }

            if(mid1_a!=-9999) {
                tv = (TextView) findViewById(R.id.textView65);
                tv.setText(Float.toString(mid1_a * mid1_w));
            }

            if(mid2_a!=-9999) {
                tv = (TextView) findViewById(R.id.textView54);
                tv.setText(Float.toString(mid2_a * mid2_w));
            }
        }
    }
}
