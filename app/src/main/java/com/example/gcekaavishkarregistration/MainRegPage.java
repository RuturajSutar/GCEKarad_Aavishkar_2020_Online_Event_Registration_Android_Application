package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRegPage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    Spinner eventSpinner;
    Button regButton;
    Button btnLogout;
    EditText name;
    Button userAccount;

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


    EditText email;
    EditText mobile;
    EditText college;
    EditText participants;
    EditText fees;
    public String mposition;
    private DatabaseReference mRef;
    ProgressBar progressBar;
    public String thisName,thisTeamNo;



    public static Intent makeIntent(Context context){
        return new Intent(context,MainRegPage.class);
    }




    public void showPopup(View view){
        PopupMenu popup=new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent=getIntent();
        thisName=intent.getStringExtra("Name");
        thisTeamNo=intent.getStringExtra("TeamNo");

        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Myaccount", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(MainRegPage.this,UserAccount.class);
                intent2.putExtra("Name",thisName);
                intent2.putExtra("TeamNo",thisTeamNo);
                startActivity(intent2);
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
        setContentView(R.layout.activity_main_reg_page);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);



        Intent intent=getIntent();
        thisName=intent.getStringExtra("Name");
        thisTeamNo=intent.getStringExtra("TeamNo");





        name=(EditText)findViewById(R.id.editText1);
        email=(EditText)findViewById(R.id.editText2);
        mobile=(EditText)findViewById(R.id.editText3);
        college=(EditText)findViewById(R.id.editText4);
        participants=(EditText)findViewById(R.id.editText5);
        fees=(EditText)findViewById(R.id.editText6);




        eventSpinner=(Spinner)findViewById(R.id.spinner2);

        List<String> list=new ArrayList<String>();
        list.add("Select_Event");
        list.add("Auction");
        list.add("Assemblohunt");
        list.add("Box_Cricket");
        list.add("Bridge_O_Mania");
        list.add("Codigo");
        //
        list.add("Code_Shuffle");
        list.add("Cadroit");
        list.add("Dazzle_Coding");
        list.add("Dhishan");
        list.add("Electrotrinity");
        list.add("Gcek_Idol");
        list.add("Hire_Me");
        //list.add("Hydro_Rocketory");
        list.add("Ideathon");
        list.add("Quizzer_Blast");
        list.add("Robothon");
        list.add("Technotric");
        //list.add("Weapons_Out");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(arrayAdapter);

        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eventSpinner.setSelection(position);
                Log.i("info","Position"+eventSpinner.getItemAtPosition(position).toString());
                mposition=eventSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        regButton=(Button)findViewById(R.id.button);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                Log.i("info","event: "+mposition);
                String myname=name.getText().toString();
                String myemail=email.getText().toString();
                String mymobile=mobile.getText().toString();
                String mycollege=college.getText().toString();
                String myparticipants=participants.getText().toString();
                String myfees=fees.getText().toString();

                TextView errorText = (TextView)eventSpinner.getSelectedView();

                if(myname.isEmpty()){
                    name.setError("Enter Name");
                    name.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (myemail.isEmpty()){
                    email.setError("Enter Email");
                    email.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (mymobile.isEmpty()){
                    mobile.setError("Enter Mobile Number");
                    mobile.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (mycollege.isEmpty()){
                    college.setError("Enter college name");
                    college.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (myparticipants.isEmpty()){
                    participants.setError("Enter number of participants");
                    participants.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if(myfees.isEmpty()){
                    fees.setError("Enter Fees");
                    fees.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (mposition.equals("Select_Event")){
                    errorText.setError("Please Select Event");
                    errorText.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (myname.isEmpty() && myemail.isEmpty() &&  mymobile.isEmpty() && mycollege.isEmpty() && myparticipants.isEmpty() && myfees.isEmpty() && mposition.equals("Select_Event")){
                    Toast.makeText(MainRegPage.this, "Please fill All the fields", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(myname.isEmpty() && myemail.isEmpty() &&  mymobile.isEmpty() && mycollege.isEmpty() && myparticipants.isEmpty() && myfees.isEmpty() && mposition.equals("Select_Event"))){
                    mRef=FirebaseDatabase.getInstance().getReference().child("Events");
                    Map<String,Object> insertValues=new HashMap<>();

                    if (mposition.equals("Auction")){
                        String eventName="Auction";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Auction"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Auction").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        Intent intent1=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
                        progressBar.setVisibility(View.INVISIBLE);



                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);




                    }
                    else if (mposition.equals("Assemblohunt")){

                        String eventName="Assemblohunt";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Assemblohunt"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Assemblohunt").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }


                    else if (mposition.equals("Box_Cricket")){

                        String eventName="Box_Cricket";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Box_Cricket"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Box_Cricket").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Bridge_O_Mania")){

                        String eventName="Bridge_O_Mania";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Bridge_O_Mania"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Bridge_O_Mania").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Codigo")){

                        String eventName="Codigo";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Codigo"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Codigo").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Code_Shuffle")){

                        String eventName="Code_Shuffle";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Code_Shuffle"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Code_Shuffle").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Cadroit")){

                        String eventName="Cadroit";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Cadroit"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Cadroit").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Dazzle_Coding")){

                        String eventName="Dazzle_Coding";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Dazzle_Coding"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Dazzle_Coding").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Dhishan")){

                        String eventName="Dhishan";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Dhishan"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Dhishan").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Electrotrinity")){

                        String eventName="Electrotrinity";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Electrotrinity"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Electrotrinity").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Gcek_Idol")){

                        String eventName="Gcek_Idol";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Gcek_Idol"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Gcek_Idol").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Hire_Me")){

                        String eventName="Hire_Me";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Hire_Me"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Hire_Me").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }

                    else if (mposition.equals("Ideathon")){

                        String eventName="Ideathon";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Ideathon"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Ideathon").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Quizzer_Blast")){

                        String eventName="Quizzer_Blast";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Quizzer_Blast"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Quizzer_Blast").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Robothon")){

                        String eventName="Robothon";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Robothon"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Robothon").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }
                    else if (mposition.equals("Technotric")){

                        String eventName="Technotric";
                        insertValues.put("Name",myname);
                        insertValues.put("Email",myemail);
                        insertValues.put("Mobile",mymobile);
                        insertValues.put("College",mycollege);
                        insertValues.put("Participants",myparticipants);
                        insertValues.put("Fees",myfees);
                        insertValues.put("Registration_ID","Technotric"+mymobile);
                        insertValues.put("Registered_By",thisName);
                        insertValues.put("Registered_By_Team",thisTeamNo);
                        String key=mRef.push().getKey();
                        mRef.child("Technotric").child(key).setValue(insertValues);
                        Toast.makeText(MainRegPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=AfterReg.makeIntent(MainRegPage.this);
                        intent.putExtra("PushKey",key);
                        intent.putExtra("Name",myname);
                        intent.putExtra("EventName",eventName);
                        name.setText("");
                        email.setText("");
                        mobile.setText("");
                        college.setText("");
                        participants.setText("");
                        fees.setText("");
                        //Toast.makeText(MainRegPage.this, "Email :"+myemail, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);


                        //*******
                        sendmail(myname,eventName,mymobile,myparticipants,myfees,myemail);
                    }

                    else {
                        Toast.makeText(MainRegPage.this, "Please select event", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(MainRegPage.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });











    }
    private void sendmail( String myname, String eventName, String mymobile, String myparticipants, String myfees,String myemail){
        String subject="Hi "+myname+", You are successfully registered for event "+eventName+" of Aavishkar'20.";
        String massage="Dear "+myname+" ,\nYour registration ID is "+eventName+mymobile+" .\n The Number of participants in your team is "+myparticipants+" .\nTotal amount you paid is "+myfees+"/-.\n\nThank you for showing your interest in Aavishkar.\n\nSee you on 29th February 2020 at 10am at the reporting desk.\n\nRegards,\nTeam Aavishkar,\nGovernment College of Engineering, Karad.";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{myemail});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,massage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        /*Intent intent=new Intent(Intent.ACTION_VIEW
        ,Uri.parse("mailto:"+myemail));
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,massage);
        startActivity(intent); */



    }

}
