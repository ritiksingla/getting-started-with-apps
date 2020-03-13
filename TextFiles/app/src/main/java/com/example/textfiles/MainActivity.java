package com.example.textfiles;

import
        androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.io.File;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText etName,etSurname;
    Button btnAdd,btnSave;
    TextView tvResults;
    ArrayList<Person>persons;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        etSurname=findViewById(R.id.etSurname);
        btnAdd=findViewById(R.id.btnAdd);
        btnSave=findViewById(R.id.btnSave);
        tvResults=findViewById(R.id.tvResults);

        persons=new ArrayList<>();

        loadData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persons.add(new Person(etName.getText().toString(),etSurname.getText().toString()));
                setTextToTextView();
            }
        });
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream file =openFileOutput("Data.txt",MODE_PRIVATE);
                    OutputStreamWriter outputFile=new OutputStreamWriter(file);
                    for(Person S:persons)
                    {
                        outputFile.write(S.getName()+","+S.getSurname()+"\n");
                    }
                    outputFile.flush();
                    outputFile.close();
                    Toast.makeText(MainActivity.this,"Successfully Saved!",Toast.LENGTH_SHORT).show();
                }
                catch (IOException e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setTextToTextView()
    {
        String text="";
        for(int i=0;i<persons.size();++i){
            text=text+persons.get(i).getName()+" "+persons.get(i).getSurname()+"\n";
        }
        tvResults.setText(text);
    }
    public void loadData()
    {
        persons.clear();
        File file=getApplicationContext().getFileStreamPath("Data.txt");
       String lineFromFile;
       if(file.exists())
       {
           try {
               BufferedReader reader=new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
               while ((lineFromFile=reader.readLine())!=null)
               {
                   StringTokenizer token=new StringTokenizer(lineFromFile,",");
                   Person person=new Person(token.nextToken(),token.nextToken());
                   persons.add(person);
               }
               reader.close();
               setTextToTextView();
           }
           catch (IOException e){
               Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
           }
       }
    }
}