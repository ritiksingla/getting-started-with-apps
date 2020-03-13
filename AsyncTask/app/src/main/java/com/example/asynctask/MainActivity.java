package com.example.asynctask;

import
        androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText etDice;
    Button btnDice;
    TextView tvDice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDice=findViewById(R.id.etDice);
        btnDice=findViewById(R.id.btnRoll);
        tvDice=findViewById(R.id.tvDice);

        btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num= Integer.parseInt(etDice.getText().toString().trim());
                new ProcessDiceInBackground().execute(num);
            }
        });
    }
//    <Params,Progress,Result>
    public class ProcessDiceInBackground extends AsyncTask<Integer,Integer,String> {
        ProgressDialog dialog;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(MainActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax( Integer.parseInt(etDice.getText().toString().trim()));
        dialog.show();
    }
    @Override
    protected String doInBackground(Integer... integers) {
        int num= integers[0];
        int one,two,three,four,five,six,random;
        one=two=three=four=five=six=0;
        double currentProgress=0;
        double prevProgress=0;
        for(int i=0;i<num;i++)
        {
            currentProgress=(double) i/num;
            if(currentProgress-prevProgress>=0.02) {
                publishProgress(i);//Calls onProgress update
                prevProgress=currentProgress;
            }
            random=new Random().nextInt(6)+1;
            switch (random)
            {
                case 1:one++;break;
                case 2:two++;break;
                case 3:three++;break;
                case 4:four++;break;
                case 5:five++;break;
                case 6:six++;break;
            }
        }
        String text="Results:\n1: "+one+"\n2: "+two+"\n3: "+three+"\n4: "+four+"\n5: "+five+"\n6: "+six;
//        Toast.makeText(MainActivity.this,"Process Done!",Toast.LENGTH_SHORT).show();
        return text;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setProgress(values[0]);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        tvDice.setText(s);
        Toast.makeText(MainActivity.this,"Process Done!",Toast.LENGTH_SHORT).show();
    }

}
}
