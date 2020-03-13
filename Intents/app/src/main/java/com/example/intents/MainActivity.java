package com.example.intents;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    final int ACTIVITY3=3;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText=(EditText)findViewById(R.id.textId);
        Button btn2=(Button)findViewById(R.id.btn2);
        Button btn3=(Button)findViewById(R.id.btn3);
        textView=(TextView)findViewById(R.id.txtViewId);
        textView.setVisibility(View.INVISIBLE);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Enter All The Fields!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name=editText.getText().toString().trim();
                    Intent intent=new Intent(MainActivity.this, com.example.intents.ActivityTwo.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.intents.ActivityThree.class);
//                Starting activity for result purposes. Also we need to pass unique code so that if there are
//                multiple activities sending the data to the main activity then we need to identify it.
                startActivityForResult(intent,ACTIVITY3);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ACTIVITY3){
            textView.setVisibility(View.VISIBLE);
            if(resultCode==RESULT_OK){
                textView.setText(data.getStringExtra("surname"));
            }
            if(resultCode==RESULT_CANCELED){
                textView.setText("No Data Received!");
            }
        }
    }
}