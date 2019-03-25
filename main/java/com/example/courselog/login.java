package com.example.courselog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        printUsername();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView error = findViewById(R.id.textView43);
        error.setText("");
        printUsername();
    }

    private void printUsername()
    {
        SharedPreferences sp = getSharedPreferences("usernameInfo", Context.MODE_PRIVATE);
        String uname = sp.getString("username","");
        EditText u = findViewById(R.id.editText2);
        u.setText(uname);
    }
    public void createAccount(View view)
    {
        Intent i = new Intent(this, createProfile.class);
        startActivity(i);
    }
    public void onLoginClick(View view)
    {
        EditText u,p;
        final TextView error = findViewById(R.id.textView43);
        u=findViewById(R.id.editText2);
        p=findViewById(R.id.editText3);
        String username = u.getText().toString();
        String password = p.getText().toString();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))
        {
            error.setText("Error: Empty field/s");
            return;
        }
        //retrieve data from db
        new AsyncTask<String,Void,Integer>()
        {
            @Override
            protected void onPostExecute(Integer id) {
                if(id==0)
                    error.setText("Invalid credentials");
                else
                {
                    Intent i = new Intent(getApplicationContext(),log.class);
                    i.putExtra("userID",id);
                    startActivity(i);
                    finish();
                }
            }
            @Override
            protected Integer doInBackground(String... params) {
                CourseLogDB courseLogDB = CourseLogDB.getInstance(getApplicationContext());
                UserDao ud = courseLogDB.userModel();
                int id = ud.getUserUsingpass(params[0],params[1]);
                return id;
            }

        }.execute(username,password);
    }
}
