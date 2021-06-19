package com.sanchit.intestify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends AppCompatActivity {

    Button button;
    String Subject,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Subject = getIntent().getExtras().getString("Subject");
        time = getIntent().getExtras().getString("Time");
        button = findViewById(R.id.start_timer_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Instructions.this,TestActivity.class);
                intent.putExtra("Subject",Subject);
                intent.putExtra("Time",time);
                startActivity(intent);
            }
        });




    }
}