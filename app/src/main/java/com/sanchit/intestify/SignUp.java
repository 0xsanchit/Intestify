package com.sanchit.intestify;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Random;

public class SignUp extends AppCompatActivity {

    public EditText emailId, passwd, userName,studclass,studloc;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    DatabaseReference usersRef;
    String Username,goal;
    private RadioButton radioButton1,radioButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.ETemail);
        passwd = findViewById(R.id.ETpassword);
        studclass = findViewById(R.id.ETclass);
        studloc = findViewById(R.id.ETlocation);
        btnSignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.TVSignIn);
        userName = findViewById(R.id.user_name);
        radioButton1 = (RadioButton) findViewById(R.id.radio_jee);
        radioButton2 = (RadioButton) findViewById(R.id.radio_neet);


        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        if(radioButton1.isChecked())
        {
            goal = "JEE";
        }
        if(radioButton2.isChecked())
        {
            goal = "NEET";
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailID = emailId.getText().toString();
                final String EClass=studclass.getText().toString();
                final String Eloc=studloc.getText().toString();
                String paswd = passwd.getText().toString();
                Username = userName.getText().toString();



                if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                }else if(EClass.isEmpty())
                {
                    studclass.setError("Please provide your class first");
                    studclass.requestFocus();
                }
                else if(Eloc.isEmpty())
                {
                    studloc.setError("Please provide your location first");
                    studloc.requestFocus();
                }
                else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignUp.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                firebaseAuth.getCurrentUser().sendEmailVerification();
                                final String userId=firebaseAuth.getCurrentUser().getUid();


                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("email",emailID);
                                userMap.put("isAdmin","false");
                                userMap.put("name",Username);
                                userMap.put("goal",goal);
                                userMap.put("standard",EClass);
                                userMap.put("location",Eloc);
                                userMap.put("uid",userId);
                                userMap.put("image","default");

                                usersRef.child(firebaseAuth.getCurrentUser().getUid()).updateChildren(userMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful())
                                                {
                                                    if(task.isSuccessful())
                                                    { firebaseAuth.signOut();
                                                    Intent intent = new Intent(SignUp.this,MainActivity.class);
                                                    startActivity(intent);
                                                    }
                                                     else {
                                                     firebaseAuth.signOut();
                                                     Intent intent = new Intent(SignUp.this,MainActivity.class);
                                                     startActivity(intent);
                                                     }

                                                }
                                            }
                                        });

                                Toast.makeText(SignUp.this,"Registration successful , please verify your email and Login ",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent I = new Intent(SignUp.this , MainActivity.class);
                startActivity(I);
            }
        });

    }
}
