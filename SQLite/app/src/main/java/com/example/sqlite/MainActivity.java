package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText etName,etCell;
    Button btnSubmit,btnShow,btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        etCell=findViewById(R.id.etCell);
        btnDelete=findViewById(R.id.btnDelete);
        btnEdit=findViewById(R.id.btnEdit);
        btnShow=findViewById(R.id.btnShow);
        btnSubmit=findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString().trim();
                String cell=etCell.getText().toString().trim();
                    ContactsDB db=new ContactsDB(MainActivity.this);
                    db.open();
                    db.createEntry(name,cell);
                    db.close();
                    Toast.makeText(MainActivity.this,"Successfully Saved!",Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etCell.setText("");

            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Data.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ContactsDB db=new ContactsDB(MainActivity.this);
                    db.open();
                    db.updateEntry("1","Ritik Singla","8448408104");
                    db.close();
                    Toast.makeText(MainActivity.this,"Successfully Updated!",Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsDB db=new ContactsDB(MainActivity.this);
                db.open();
                db.deleteEntry("1");
                db.close();
                Toast.makeText(MainActivity.this,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
