package com.example.courselog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }

    public void createProfileClick(View view)
    {
        EditText username = (EditText) findViewById(R.id.editText);
        EditText passwd = (EditText) findViewById(R.id.editText4);
        EditText rt_passwd = (EditText) findViewById(R.id.editText5);
        EditText name = (EditText) findViewById(R.id.editText6);
        EditText batch = (EditText) findViewById(R.id.editText7);
        EditText roll_no = (EditText) findViewById(R.id.editText8);
        final TextView error = (TextView) findViewById(R.id.textView42);
        if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(passwd.getText().toString()) || TextUtils.isEmpty(rt_passwd.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(batch.getText().toString()) || TextUtils.isEmpty(roll_no.getText().toString()) )
        {
            error.setText("Error: Enter all fields");
            return;
        }
        if(username.getText().toString().length() < 4)
        {
            error.setText("Error: Username should contain at least 4 characters");
            return;
        }
        if(passwd.getText().toString().length()<6)
        {
            error.setText("Error: Password should contain at lest 6 characters");
            return;
        }
        if(!passwd.getText().toString().equals(rt_passwd.getText().toString()))
        {
            error.setText("Error: Passwords entered are not same");
            return;
        }
        if(!name.getText().toString().contains(" "))
        {
            error.setText("Error: Enter your lastname too");
            return;
        }
        if(batch.getText().toString().length()!=4 || Integer.parseInt(batch.getText().toString()) < 1990 || Integer.parseInt(batch.getText().toString()) > 2019 )
        {
            error.setText("Error: Enter valid batch");
            return;
        }
        if(roll_no.getText().toString().length() != 7 || roll_no.getText().toString().charAt(2) != '-')
        {
            error.setText("Error: Enter valid roll number");
            return;
        }
        new AsyncTask<String,Void,String>()
        {
            @Override
            protected void onPostExecute(String username) {
                if(username==null)
                    error.setText("Username already exists");
                else {
                    Toast.makeText(getApplicationContext(),"Profile Created",Toast.LENGTH_SHORT).show();
                    //save username to shared preferences
                    SharedPreferences sp = getSharedPreferences("usernameInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",username);
                    editor.apply();
                    finish();
                }
            }
            @Override
            protected String doInBackground(String... params) {
                CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                UserDao u = courseLogDB.userModel();
                int check = u.findUser(params[1]);
                if(check==0) {
                    User user = new User();
                    user.setName(params[0]);
                    user.setUsername(params[1]);
                    user.setBatch(params[2]);
                    user.setPassword(params[3]);
                    user.setRoll_no(params[4]);
                    u.addUser(user);
                    return params[1];
                }
                else return null;
            }
        }.execute(name.getText().toString(),username.getText().toString(),batch.getText().toString(),passwd.getText().toString(),roll_no.getText().toString());
    }
}
