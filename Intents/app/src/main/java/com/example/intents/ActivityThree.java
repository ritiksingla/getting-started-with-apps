package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityThree extends AppCompatActivity {
    private EditText editText;
    private Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        editText=(EditText)findViewById(R.id.editText2);
        btn4=(Button)findViewById(R.id.btn4);
        Button btnCancel=(Button)findViewById(R.id.btnCancel);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(ActivityThree.this,"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                }
                else{
                    String surname=editText.getText().toString().trim();
                    Intent intent=new Intent();
                    intent.putExtra("surname",surname);
                    setResult(RESULT_OK,intent);
                    ActivityThree.this.finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                ActivityThree.this.finish();
            }
        });

    }
}