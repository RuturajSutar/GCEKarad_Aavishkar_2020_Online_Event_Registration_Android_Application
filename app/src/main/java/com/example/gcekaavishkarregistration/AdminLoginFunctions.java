package com.example.gcekaavishkarregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class AdminLoginFunctions extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button createUser;
    private Button changePassword;

    Button deleteUser;

    Button eventList;
    Button userList;



    private Toast backToast;
    private long backPressedTime;
    @Override
    public void onBackPressed() {

        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast=Toast.makeText(this, "Press back again to Logout", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime=System.currentTimeMillis();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context,AdminLoginFunctions.class);
    }

    public void showPopup(View view){
        PopupMenu popup=new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Myaccount", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminLoginFunctions.this,AdminAccount.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_functions);
        createUser=(Button)findViewById(R.id.button1);
        changePassword=(Button)findViewById(R.id.button5);
        eventList=(Button)findViewById(R.id.button3);
        userList=(Button)findViewById(R.id.button4);
        userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginFunctions.this,UserList.class);
                startActivity(intent);
            }
        });
        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminLoginFunctions.this,EventParList.class);
                startActivity(intent);
            }
        });

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=CreateUser.makeIntent(AdminLoginFunctions.this);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=ChangePassword.makeIntent(AdminLoginFunctions.this);
                startActivity(intent);
            }
        });

        deleteUser=(Button)findViewById(R.id.button2);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=DeleteUser.makeIntent(AdminLoginFunctions.this);
                startActivity(intent);
            }
        });
    }
}
