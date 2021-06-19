package com.sanchit.intestify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class TestActivity extends AppCompatActivity {

    String Subject,Time;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    Button button1,button2,button3;
    TextView textView1,textView2,textView3,textView4;
    DatabaseReference databaseReference;
    ArrayList<Questions> questions = new ArrayList<>();
    ArrayList<Integer> list,selected;
    ArrayList<String> answers = new ArrayList<String>();
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Subject=getIntent().getExtras().getString("Subject");
        Time = getIntent().getExtras().getString("Time");

        list = new ArrayList<Integer>();
        selected = new ArrayList<Integer>();

        /*ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<11; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            System.out.println(list.get(i));
        }*/

        FirebaseDatabase.getInstance().getReference().child("Questions").child(Subject).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int childrenCount = (int) dataSnapshot.getChildrenCount();
                for(int j=0;j<childrenCount;j++)
                {
                    list.add(new Integer(j));
                }
                Collections.shuffle(list);
                for(int k=0;k<10;k++)
                {
                    selected.add(list.get(k));
                    answers.add("0");
                }
                answers.add("0");
                Collections.sort(selected);
                selected.add(new Integer(childrenCount+5));
                int i=0;
                int k=0;
                int j=0;
                for(DataSnapshot snap : dataSnapshot.getChildren())
                {
                    if(k==selected.get(j))
                    {
                        Questions newquestion = snap.getValue(Questions.class);
                        //Toast.makeText(TestActivity.this,newquestion.getQuestion(),Toast.LENGTH_SHORT).show();
                        questions.add(newquestion);
                        //Toast.makeText(TestActivity.this, "Hi" + (int) k,Toast.LENGTH_LONG).show();
                        j++;
                    }
                    k++;
                }
                Toast.makeText(TestActivity.this, "Hi" + (int) childrenCount,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        radioButton1 = findViewById(R.id.option1);
        radioButton2 = findViewById(R.id.option2);
        radioButton3 = findViewById(R.id.option3);
        radioButton4 = findViewById(R.id.option4);


        button1 = findViewById(R.id.Skip_button);
        button2 = findViewById(R.id.Continue_button);
        button3 = findViewById(R.id.submit_button);

        textView1 = findViewById(R.id.Timer);
        textView2 = findViewById(R.id.Subject);
        textView3 = findViewById(R.id.QuestionNumber);
        textView4 = findViewById(R.id.Question);



        final int[] i = {0};

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i[0] ==10)
                {
                    i[0] =0;
                }

                Questions question = questions.get(i[0]);

                textView1.setText(Time);
                textView3.setText("Q."+(i[0]+1));
                textView4.setText(question.getQuestion());

                radioButton1.setText(question.getOption1());
                radioButton2.setText(question.getOption2());
                radioButton3.setText(question.getOption3());
                radioButton4.setText(question.getOption4());

                if(answers.get(i[0]).equals("0"))
                {
                    /*radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);*/
                }
                else
                {
                    if(answers.get(i[0]).equals("1"))
                    {
                        /*radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(false);
                        radioButton1.setChecked(true);*/
                    }
                    else if(answers.get(i[0]).equals("2"))
                    {
                        /*radioButton1.setChecked(false);
                        radioButton2.setChecked(true);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(false);*/
                    }
                    else if(answers.get(i[0]).equals("3"))
                    {
                        /*radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(true);
                        radioButton4.setChecked(false);*/
                    }
                    else if(answers.get(i[0]).equals("4"))
                    {
                        /*radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(true);*/
                    }
                }

                if(radioButton1.isChecked())
                {
                    answers.set(i[0],"1");
                }
                else if(radioButton2.isChecked())
                {
                    answers.set(i[0],"2");
                }
                else if(radioButton3.isChecked())
                {
                    answers.set(i[0],"3");
                }
                else if(radioButton4.isChecked())
                {
                    answers.set(i[0],"4");
                }
                else
                {
                    Toast.makeText(TestActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                    answers.set(i[0],"0");
                }
                \
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                radioButton4.setChecked(false);
                i[0]++;

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score=0;
                int counter=0;
                for(counter=0;counter<10;counter++)
                {
                    Questions question1 = questions.get(counter);
                    //Toast.makeText(TestActivity.this,"answer"+question1.getCorrectAnswer(),Toast.LENGTH_SHORT).show();
                        if(answers.get(counter).equals("0"))
                        {
                            Toast.makeText(TestActivity.this,"answer"+answers.get(counter),Toast.LENGTH_SHORT).show();
                        }
                        else if(answers.get(counter).equals(question1.getCorrectAnswer())) {
                            //Toast.makeText(TestActivity.this,"QuestionNo"+counter,Toast.LENGTH_SHORT).show();
                            score++;
                        }
                }
                Toast.makeText(TestActivity.this,"Score="+score,Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}