package com.example.gcekaavishkarregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserAccount extends AppCompatActivity {

    TextView userAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        userAccount=(TextView)findViewById(R.id.textView2);

        Intent intent=getIntent();
        String myName=intent.getStringExtra("Name");
        String myTeamNo=intent.getStringExtra("TeamNo");
        userAccount.setText(myName+"\n"+"Team Number : "+myTeamNo);
    }
}
