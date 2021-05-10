package com.example.gcekaavishkarregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteUser extends AppCompatActivity {

    Button deleteUser;
    EditText username;
    EditText password;
    DatabaseReference mRef;
    ProgressBar progressBar;
    FirebaseAuth mFirebaseAuth;

    public static Intent makeIntent(Context context){
        return new Intent(context,DeleteUser.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        mFirebaseAuth=FirebaseAuth.getInstance();


        deleteUser=(Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                progressBar.setVisibility(View.VISIBLE);
                String myusername=username.getText().toString();
                String mypassword=password.getText().toString();

                String removeDot=myusername.replace(".","");
                String removeAt=removeDot.replace("@","");

                if (myusername.isEmpty()){
                    username.setError("Enter Username");
                    username.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else if (!(myusername.isEmpty())){


                    mRef= FirebaseDatabase.getInstance().getReference().child("Users");


                    mFirebaseAuth.signInWithEmailAndPassword(myusername,mypassword).addOnCompleteListener(DeleteUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(DeleteUser.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();


                                            Task<Void> mtask=mRef.child(removeAt).removeValue();
                                            mtask.addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(DeleteUser.this, "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    username.setText("");
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(DeleteUser.this, "Unable to delete user from database", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                }
                                            });


                                        }
                                    }
                                });

                                //progressBar.setVisibility(View.INVISIBLE);

                            } else {
                                Toast.makeText(DeleteUser.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                                username.setText("");
                                password.setText("");
                                progressBar.setVisibility(View.INVISIBLE);
                                //progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(DeleteUser.this, "Probleme in deleting user", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
