package com.sanchit.intestify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChooseSubject extends AppCompatActivity {

    Button button;
    String Subject;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        radioButton1 = findViewById(R.id.Physics);
        radioButton2 = findViewById(R.id.Chemistry);
        radioButton3 = findViewById(R.id.Maths);
        radioButton4 = findViewById(R.id.Biology);

        button = findViewById(R.id.Continue_subject);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioButton1.isChecked())
                {
                    Subject="Physics";
                }
                else if(radioButton2.isChecked())
                {
                    Subject = "Chemistry";
                }
                else if(radioButton3.isChecked())
                {
                    Subject = "Maths";
                }
                else if(radioButton4.isChecked())
                {
                    Subject = "Biology";
                }
                else
                {
                    Subject="";
                }

                if(Subject.isEmpty())
                {
                    Toast.makeText(ChooseSubject.this,"Please Choose a Subject",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(ChooseSubject.this,ChooseTimeLimit.class);
                    intent.putExtra("Subject",Subject);
                    startActivity(intent);
                }
            }
        });

    }
}