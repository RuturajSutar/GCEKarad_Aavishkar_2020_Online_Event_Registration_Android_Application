package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class AdminLoginPage extends AppCompatActivity {

    Button adminLoginButton;
    EditText username;
    EditText password;
    DatabaseReference mRef;
    CheckBox showpassword;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef1;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        adminLoginButton=(Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);

        showpassword=(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });





        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progressBar.setVisibility(View.VISIBLE);


                String ausername=username.getText().toString();
                String apassword=password.getText().toString();

                if(ausername.isEmpty()){
                    username.setError("Please Enter Username");
                    username.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (apassword.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(ausername.isEmpty() && apassword.isEmpty()){
                    Toast.makeText(AdminLoginPage.this, "Both Fields are Empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(ausername.isEmpty() && apassword.isEmpty())){

                    mRef=FirebaseDatabase.getInstance().getReference().child("AdminLogin");
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name=dataSnapshot.child("AdminUsername").getValue().toString();
                            String pass=dataSnapshot.child("AdminPassword").getValue().toString();
                            Log.i("info","Name : "+name);
                            Log.i("info","Pass : "+pass);
                            if(ausername.equals(name) && apassword.equals(pass)){
                                Intent intent=AdminLoginFunctions.makeIntent(AdminLoginPage.this);
                                startActivity(intent);
                                username.setText("");
                                password.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(AdminLoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else {
                    Toast.makeText(AdminLoginPage.this, "Login Error", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }


}
