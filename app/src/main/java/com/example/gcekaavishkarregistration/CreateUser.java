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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CreateUser extends AppCompatActivity {

    Button createUser;
    EditText username;
    EditText password;
    EditText teamNumber;
    DatabaseReference mRef;
    CheckBox showpassword;


    FirebaseAuth mFirebaseAuth;

    //d



    ProgressBar progressBar;

    public static Intent makeIntent(Context context){
        return new Intent(context,CreateUser.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        //d

        mFirebaseAuth=FirebaseAuth.getInstance();


        createUser=(Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);
        teamNumber=(EditText)findViewById(R.id.editText3);


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

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);


                String ausername=username.getText().toString();
                String apassword=password.getText().toString();
                String ateamnumber=teamNumber.getText().toString();


                String removeDot=ausername.replace(".","");
                String removeAt=removeDot.replace("@","");
                if(ausername.isEmpty()){
                    username.setError("Please Enter Username");
                    username.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (ateamnumber.isEmpty()){
                    teamNumber.setError("Please Enter Team Number");
                    teamNumber.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (apassword.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(ausername.isEmpty() && ateamnumber.isEmpty() && apassword.isEmpty()){
                    Toast.makeText(CreateUser.this, "All Fields are Empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (apassword.length()<6){

                    password.setError("Password length must be greater than 6");
                    password.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(ausername.isEmpty() && apassword.isEmpty())){
                    //String key=mRef.push().getKey();



                    mFirebaseAuth.createUserWithEmailAndPassword(ausername,apassword).addOnCompleteListener(CreateUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                mRef=FirebaseDatabase.getInstance().getReference().child("Users");
                                Map<String,Object> insertValues=new HashMap<>();
                                insertValues.put("Name",ausername);
                                insertValues.put("TeamNumber",ateamnumber);
                                insertValues.put("Password",apassword);
                                mRef.child(removeAt).setValue(insertValues);
                                Toast.makeText(CreateUser.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                progressBar.setVisibility(View.INVISIBLE);
                            } else {
                                Toast.makeText(CreateUser.this, "Please Enter Valid Username or Password",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });




                }
                else {
                    Toast.makeText(CreateUser.this, "Error in creating user", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
