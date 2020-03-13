package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.itemClick{
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> people;
    Button btnRemove,btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.list);
        btnRemove=findViewById(R.id.btnRemove);
        btnAdd=findViewById(R.id.btnAdd);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(people.size()!=0) {
                    people.remove(people.size() - 1);
                    myAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this,"EMPTY LIST!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people.add(new Person("Ron","Samuel","plane"));
                myAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        people=new ArrayList<>();
        people.add(new Person("Ritik","Singla","bus"));
        people.add(new Person("Ankit","Mishra","plane"));
        people.add(new Person("Samyak","Uttam","bus"));
        people.add(new Person("Ritik","Singla","bus"));
        people.add(new Person("Ankit","Mishra","plane"));
        people.add(new Person("Samyak","Uttam","bus"));
        people.add(new Person("Ritik","Singla","bus"));
        people.add(new Person("Ankit","Mishra","plane"));
        people.add(new Person("Samyak","Uttam","bus"));
        people.remove(people.size()-1);
        myAdapter=new PersonAdapter(this,people);
        recyclerView.setAdapter(myAdapter);
    }
    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this,"NAME :"+people.get(index).getName(),Toast.LENGTH_SHORT).show();
    }
}