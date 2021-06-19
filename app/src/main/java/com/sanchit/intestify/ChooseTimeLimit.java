package com.sanchit.intestify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChooseTimeLimit extends AppCompatActivity {

    Button button;
    RadioButton radioButton1,radioButton2,radioButton3;
    String time,Subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time_limit);

        Subject = getIntent().getExtras().getString("Subject");
        radioButton1 = findViewById(R.id.option1);
        radioButton2 = findViewById(R.id.option2);
        radioButton3 = findViewById(R.id.option3);

        button = findViewById(R.id.begin_test);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(radioButton1.isChecked())
                {
                    time="45";
                }
                else if(radioButton2.isChecked())
                {
                    time="90";
                }
                else if(radioButton3.isChecked())
                {
                    time="180";
                }
                else
                {
                    time="";
                }

                if(time.isEmpty())
                {
                    Toast.makeText(ChooseTimeLimit.this,"Please Choose a Time Limit",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(ChooseTimeLimit.this,Instructions.class);
                    intent.putExtra("Subject",Subject);
                    intent.putExtra("Time",time);
                    startActivity(intent);
                }

            }

        });


    }
}