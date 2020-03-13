package com.example.todoapp;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final ArrayList<Word>words=new ArrayList<Word>();
        words.add(new Word("Coffee",5.99,R.drawable.coffee));
        words.add(new Word("Toppings",2.99,R.drawable.toppings));
        words.add(new Word("Chocolate",3.99,R.drawable.chocolatetoppings));

        WordAdapter adapter=new WordAdapter(this,words,R.color.colorPrimaryLight);
        ListView listView=(ListView)findViewById(R.id.rootView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(MenuActivity.this,"Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
