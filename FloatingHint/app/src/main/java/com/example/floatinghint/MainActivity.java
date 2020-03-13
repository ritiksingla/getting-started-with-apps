package com.example.floatinghint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView etFirstName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirstName=findViewById(R.id.etFirstName);
        String [] names={"James","John","Jenny","Jenine","Jennifer","Jack","Johnny"};
        Arrays.sort(names);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.custom_design_autocomplete,names);
        etFirstName.setThreshold(1);
        etFirstName.setAdapter(adapter);
    }
}