package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {

    Button changePassword;
    EditText username;
    EditText password;
    EditText newpassword;
    EditText newpasswordagain;
    DatabaseReference mRef;
    ProgressBar progressBar;
    CheckBox showpassword;

    public static Intent makeIntent(Context context){
        return new Intent(context,ChangePassword.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        changePassword=(Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);
        newpassword=(EditText)findViewById(R.id.editText3);
        newpasswordagain=(EditText)findViewById(R.id.editText4);

        showpassword=(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpasswordagain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpasswordagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String ausername=username.getText().toString();
                String apassword=password.getText().toString();
                String npass=newpassword.getText().toString();
                String npassag=newpasswordagain.getText().toString();

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
                else if (npass.isEmpty()){
                    newpassword.setError("Please Enter Password");
                    newpassword.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (npassag.isEmpty()){
                    newpasswordagain.setError("Please Enter Password");
                    newpasswordagain.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(ausername.isEmpty() && apassword.isEmpty() && npass.isEmpty() && npassag.isEmpty()  ){
                    Toast.makeText(ChangePassword.this, "All Fields are Empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(ausername.isEmpty() && apassword.isEmpty() && npass.isEmpty() && npassag.isEmpty())){
                    mRef= FirebaseDatabase.getInstance().getReference().child("AdminLogin");
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name=dataSnapshot.child("AdminUsername").getValue().toString();
                            String pass=dataSnapshot.child("AdminPassword").getValue().toString();

                            Log.i("info","Name : "+name);
                            Log.i("info","Pass : "+pass);
                            if(ausername.equals(name) && apassword.equals(pass) && npass.equals(npassag)){
                                Map<String,Object> updateValue=new HashMap<>();
                                updateValue.put("/AdminPassword",npass);
                                mRef.updateChildren(updateValue);
                                Toast.makeText(ChangePassword.this, "Password Changed Successfuly", Toast.LENGTH_SHORT).show();
                                finish();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(ChangePassword.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(ChangePassword.this, "Login Error", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
