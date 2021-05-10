package com.example.gcekaavishkarregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventParList extends AppCompatActivity {

    Button Auction;
    Button Assemblohunt;
    Button Box_Cricket;
    Button Bridge_O_Mania;
    Button Codigo;
    Button Code_Puzzle;
    Button Cadroit;
    Button Dazzle_Coding;
    Button Dhishan;
    Button Electrotrinity;
    Button Gcek_Idol;
    Button Hire_Me;
    Button Ideathon;
    Button Quizzer_Blast;
    Button Robothon;
    Button Technotric;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_par_list);

        Auction=(Button)findViewById(R.id.button1);
        Assemblohunt=(Button)findViewById(R.id.button20);
        Box_Cricket=(Button)findViewById(R.id.button2);
        Bridge_O_Mania=(Button)findViewById(R.id.button3);
        Codigo=(Button)findViewById(R.id.button4);
        Code_Puzzle=(Button)findViewById(R.id.button5);
        Cadroit=(Button)findViewById(R.id.button6);
        Dazzle_Coding=(Button)findViewById(R.id.button7);
        Dhishan=(Button)findViewById(R.id.button30);
        Electrotrinity=(Button)findViewById(R.id.button8);
        Gcek_Idol=(Button)findViewById(R.id.button9);
        Hire_Me=(Button)findViewById(R.id.button10);
        Ideathon=(Button)findViewById(R.id.button12);
        Quizzer_Blast=(Button)findViewById(R.id.button13);
        Robothon=(Button)findViewById(R.id.button14);
        Technotric=(Button)findViewById(R.id.button15);
        Auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Auction";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Assemblohunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Assemblohunt";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Box_Cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Box_Cricket";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Bridge_O_Mania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Bridge_O_Mania";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Codigo";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Code_Puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Code_Puzzle";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Cadroit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Cadroit";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Dazzle_Coding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Dazzle_Coding";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Dhishan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Dazzle_Coding";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Electrotrinity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Electrotrinity";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Gcek_Idol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Gcek_Idol";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Hire_Me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Hire_Me";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });

        Ideathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Ideathon";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Quizzer_Blast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Quizzer_Blast";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Robothon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Robothon";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });
        Technotric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName="Technotric";
                Intent intent=new Intent(EventParList.this,ParticipantList.class);
                intent.putExtra("EventName",eventName);
                startActivity(intent);
            }
        });

    }
}
