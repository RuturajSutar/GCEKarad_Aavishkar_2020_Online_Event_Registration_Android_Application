package com.example.gcekaavishkarregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView aavishkarLogo;
    private Button adminLoginButton;
    private Button userLoginButton;
    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {

        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast=Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime=System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!isConnected()){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Internet Connection Alert")
                    .setMessage("Please Check Your Internet Connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();

        }
        else {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }


        aavishkarLogo=(ImageView)findViewById(R.id.imageView);
        aavishkarLogo.animate().alpha(1).setDuration(2000);
        adminLoginButton=(Button)findViewById(R.id.button1);
        userLoginButton=(Button)findViewById(R.id.button2);
        adminLoginButton.setTranslationX(-1500);
        userLoginButton.setTranslationX(-1500);
        adminLoginButton.animate().translationXBy(1500).setDuration(2000);
        userLoginButton.animate().translationXBy(1500).setDuration(2000);
        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin=new Intent(MainActivity.this,AdminLoginPage.class);
                startActivity(admin);
            }
        });
        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user=new Intent(MainActivity.this,UserLoginPage.class);
                startActivity(user);
            }
        });



    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
