package com.example.courselog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class login extends AppCompatActivity {

    String username,password,name,batch,roll_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void createAccount(View view)
    {
        Intent i = new Intent(this, createProfile.class);
        startActivityForResult(i,1);

    }
    public void onLoginClick(View view)
    {
        EditText u,p;
        TextView error = (TextView) findViewById(R.id.textView43);
        u=(EditText) findViewById(R.id.editText2);
        p=(EditText) findViewById(R.id.editText3);
        if(TextUtils.isEmpty(u.getText().toString()) || TextUtils.isEmpty(p.getText().toString()))
        {

            error.setText("Error: Empty field/s");
            return;
        }
        if(!(u.getText().toString().equals(username) && p.getText().toString().equals(password)))
        {
            error.setText("Error: Invalid credentials");
            return;
        }
        Intent i = new Intent(this,log.class);
        i.putExtra("name",name);
        i.putExtra("batch",batch);
        i.putExtra("roll_no",roll_no);
        finish();
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK)
        {
            username = data.getStringExtra("username");
            password = data.getStringExtra("password");
            name = data.getStringExtra("name");
            batch = data.getStringExtra("batch");
            roll_no = data.getStringExtra("roll_no");
            EditText et = (EditText) findViewById(R.id.editText2);
            et.setText(username);
        }
    }
}
