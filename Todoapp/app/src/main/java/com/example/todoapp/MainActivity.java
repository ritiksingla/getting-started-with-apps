package com.example.todoapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    TextView menu, tvQuantity,summary;
    Button btnIncrease, btnDecrease, btnOrder;
    CheckBox cbTopping,cbChocolate;
    EditText etName;
    int quantity = 0;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (TextView) findViewById(R.id.tvMenu);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnOrder = findViewById(R.id.btnOrder);
        tvQuantity = findViewById(R.id.quantityView);
        etName = (EditText) findViewById(R.id.etName);
        summary = (TextView) findViewById(R.id.tvSummary);
        cbTopping=findViewById(R.id.cbTopping);
        cbChocolate=findViewById(R.id.cbChocolate);

        menu.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity < 10) {
                    quantity = quantity + 1;
                    tvQuantity.setText("" + quantity);
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot go above Ten!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity = quantity - 1;
                    tvQuantity.setText("" + quantity);
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot go below One!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etName.getText().toString().trim();
                if(name.length()==0)
                {
                    summary.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Please Enter Your Name!",Toast.LENGTH_SHORT).show();
                }
                else{
                    String text="Order Summary :\n";
                    text+="Name :"+name+"\n";
                    double price=0;
                    if(cbTopping.isChecked()){
                        text+="Topping: Yes";
                        price+=(2.99)*quantity;
                    }
                    else
                        text+="Topping: No";
                    if(cbChocolate.isChecked()){
                        text+="\n Chocolate: Yes\n";
                        price+=(3.99)*quantity;}
                    else
                        text+="\n Chocolate: No\n";
                    text+="Total Price :$"+(price+(5.99)*quantity);
                    summary.setVisibility(View.VISIBLE);
                    summary.setText(text);
                }
            }
        });
    }
}