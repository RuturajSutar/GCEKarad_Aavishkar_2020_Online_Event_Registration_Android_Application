package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParticipantList extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Participant participant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);

        Intent intent=getIntent();
        String event=intent.getStringExtra("EventName");

        participant=new Participant();
        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Events").child(event);
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.participant_list,R.id.participantInfo,list);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    participant=ds.getValue(Participant.class);
                    list.add("*Registration_ID :"+participant.getRegistration_ID().toString() +"\n"+"*Name :"+participant.getName().toString()+"\n"+"*Email :"+participant.getEmail().toString()+"\n"+"*Mobile :"+participant.getMobile().toString()+"\n"+"*College :"+participant.getCollege().toString()+"\n"+"*Participants :"+participant.getParticipants().toString()+"\n"+"*Fees :"+participant.getFees().toString()+"\n"+"*Registered By :"+participant.getRegistered_By().toString()+"\n"+"*Registered By Team :"+participant.getRegistered_By_Team().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

