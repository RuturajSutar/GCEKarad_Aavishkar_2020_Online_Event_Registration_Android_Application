package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfterReg extends AppCompatActivity {

    private Button regagain;
    TextView name;
    TextView regid;

    private DatabaseReference mRef;

    public static Intent makeIntent(Context context){
        return new Intent(context,AfterReg.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_reg);

        Intent intent=getIntent();
        String myName=intent.getStringExtra("Name");
        String myPushId=intent.getStringExtra("PushKey");
        String eName=intent.getStringExtra("EventName");

        name=(TextView)findViewById(R.id.textView1);
        name.setText(myName);
        name.setVisibility(View.VISIBLE);



        mRef=FirebaseDatabase.getInstance().getReference().child("Events").child(eName).child(myPushId);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String registrationId=dataSnapshot.child("Registration_ID").getValue().toString();
                regid=(TextView)findViewById(R.id.textView3);
                regid.setText(registrationId);
                regid.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        regagain=(Button)findViewById(R.id.button);
        regagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
