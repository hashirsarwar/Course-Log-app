package com.example.courselog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class assesment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment);
    }
    public void onUpdateClick(View view)
    {
        EditText et15 = (EditText) findViewById(R.id.editText15);
        EditText et16 = (EditText) findViewById(R.id.editText16);
        EditText et17 = (EditText) findViewById(R.id.editText17);
        EditText et18 = (EditText) findViewById(R.id.editText18);
        EditText et19 = (EditText) findViewById(R.id.editText19);
        EditText et20 = (EditText) findViewById(R.id.editText20);
        EditText et21 = (EditText) findViewById(R.id.editText21);
        EditText et29 = (EditText) findViewById(R.id.editText29);
        EditText et30 = (EditText) findViewById(R.id.editText30);
        EditText et31 = (EditText) findViewById(R.id.editText31);
        EditText et32 = (EditText) findViewById(R.id.editText32);
        EditText et33 = (EditText) findViewById(R.id.editText33);
        EditText et34 = (EditText) findViewById(R.id.editText34);
        EditText et35 = (EditText) findViewById(R.id.editText35);

        float assignment=-9999,project=-9999,finals=-9999,mid1=-9999,mid2=-9999,at=0,ao=0;
        boolean a1=false,a2=false,a3=false,check=false;
        //-9999 in every var

        if (!TextUtils.isEmpty(et21.getText().toString()) && !TextUtils.isEmpty(et29.getText().toString()))
        {
            check=true;
            a1=true;
        }

        if (!TextUtils.isEmpty(et16.getText().toString()) && !TextUtils.isEmpty(et30.getText().toString()))
        {
            check=true;
            a2=true;
        }

        if (!TextUtils.isEmpty(et15.getText().toString()) && !TextUtils.isEmpty(et31.getText().toString()))
        {
            check=true;
            a3=true;
        }

        if (!TextUtils.isEmpty(et17.getText().toString()) && !TextUtils.isEmpty(et32.getText().toString()))
        {
            check=true;
            project = Float.parseFloat(et17.getText().toString()) / Float.parseFloat(et32.getText().toString());
        }

        if (!TextUtils.isEmpty(et18.getText().toString()) && !TextUtils.isEmpty(et33.getText().toString()))
        {
            check=true;
            mid1 = Float.parseFloat(et18.getText().toString()) / Float.parseFloat(et33.getText().toString());
        }

        if (!TextUtils.isEmpty(et19.getText().toString()) && !TextUtils.isEmpty(et34.getText().toString()))
        {
            check=true;
            mid2 = Float.parseFloat(et19.getText().toString()) / Float.parseFloat(et34.getText().toString());
        }

        if (!TextUtils.isEmpty(et20.getText().toString()) && !TextUtils.isEmpty(et35.getText().toString()))
        {
            check=true;
            finals = Float.parseFloat(et20.getText().toString()) / Float.parseFloat(et35.getText().toString());
        }

        if(!check)
        {
            TextView tv = (TextView) findViewById(R.id.textView49);
            tv.setText("Enter valid data");
            return;
        }

        if(a1 || a2 || a3)
        {
            if(a1) {
                ao = ao+Float.parseFloat(et21.getText().toString());
                at = at+Float.parseFloat(et29.getText().toString());
            }

            if(a2) {
                ao = ao+Float.parseFloat(et16.getText().toString());
                at = at+Float.parseFloat(et30.getText().toString());
            }

            if(a3) {
                ao = ao+Float.parseFloat(et15.getText().toString());
                at = at+Float.parseFloat(et31.getText().toString());
            }
            assignment = ao/at;
        }
        Intent i = new Intent();
        i.putExtra("project" ,project);
        i.putExtra("final",finals);
        i.putExtra("assignment",assignment);
        i.putExtra("mid1",mid1);
        i.putExtra("mid2",mid2);
        setResult(RESULT_OK,i);
        Toast.makeText(this,"Updated",Toast.LENGTH_SHORT);
        finish();

    }
}
