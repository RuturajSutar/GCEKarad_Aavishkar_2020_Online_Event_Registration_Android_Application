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

public class UserLoginPage extends AppCompatActivity {

    Button userLogin;
    EditText username;
    EditText password;
    EditText teamNumber;

    DatabaseReference mRef;
    ProgressBar progressBar;
    CheckBox showpassword;

    //d
    FirebaseAuth mFirebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_page);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);



        //d
        mFirebaseAuth=FirebaseAuth.getInstance();

        userLogin=(Button)findViewById(R.id.button);
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



        userLogin.setOnClickListener(new View.OnClickListener() {
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
                else if(ausername.isEmpty() && apassword.isEmpty()){
                    Toast.makeText(UserLoginPage.this, "Both Fields are Empty", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(ausername.isEmpty() && apassword.isEmpty())){

                    //d
                    mFirebaseAuth.signInWithEmailAndPassword(ausername,apassword).addOnCompleteListener(UserLoginPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            mRef=FirebaseDatabase.getInstance().getReference().child("Users").child(removeAt);
                            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String teamno=dataSnapshot.child("TeamNumber").getValue().toString();
                                    if (task.isSuccessful() && ateamnumber.equals(teamno)) {
                                        Toast.makeText(UserLoginPage.this, "Successfully Logged IN", Toast.LENGTH_SHORT).show();
                                        Intent intent=MainRegPage.makeIntent(UserLoginPage.this);
                                        intent.putExtra("Name",ausername);
                                        intent.putExtra("TeamNo",ateamnumber);
                                        startActivity(intent);
                                        username.setText("");
                                        password.setText("");
                                        teamNumber.setText("");
                                        progressBar.setVisibility(View.INVISIBLE);

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(UserLoginPage.this, "Invalid Username or Password or team Number", Toast.LENGTH_SHORT).show();
                                        username.setText("");
                                        password.setText("");
                                        teamNumber.setText("");
                                        progressBar.setVisibility(View.INVISIBLE);

                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                    Toast.makeText(UserLoginPage.this, "Please Enter Valid Team Number", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    });







                    /*mRef= FirebaseDatabase.getInstance().getReference().child("Users");
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name=dataSnapshot.child(ausername).child("Name").getValue().toString();
                            String pass=dataSnapshot.child(ausername).child("Password").getValue().toString();
                            Log.i("info","Name : "+name);
                            Log.i("info","Pass : "+pass);
                            if(ausername.equals(name) && apassword.equals(pass)){
                                Intent intent=new Intent(UserLoginPage.this,MainRegPage.class);
                                intent.putExtra("Name",ausername);
                                startActivity(intent);
                                username.setText("");
                                password.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(UserLoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    }); */





                }
                else {
                    Toast.makeText(UserLoginPage.this, "Error in Login user", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}
