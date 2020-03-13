package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        TextView textView=(TextView)findViewById(R.id.txtViewTwo);
        String name=getIntent().getStringExtra("name").trim();
        textView.setText("HELLO "+name+" !");
    }
}
