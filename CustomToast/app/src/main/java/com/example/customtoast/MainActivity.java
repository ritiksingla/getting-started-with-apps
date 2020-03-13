package com.example.customtoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnToast=(Button)findViewById(R.id.btnToast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Button Clicked!");
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setIcon(R.mipmap.beach);
        actionBar.setTitle(" WELCOME");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
    }
    public void showToast(String message)
    {
        View toastView=getLayoutInflater().inflate(R.layout.toast,(ViewGroup)findViewById(R.id.linlay));
        TextView tvToast=(TextView)toastView.findViewById(R.id.tvToast);
        tvToast.setText(message);
        Toast toast=new Toast(MainActivity.this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.download:
                Toast.makeText(this,"Download Clicked!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh:
                Toast.makeText(this,"Refresh Clicked!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.send:
//                Toast.makeText(this,"Send Clicked!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}