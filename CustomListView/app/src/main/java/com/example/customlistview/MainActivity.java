package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvProducts;
    ArrayList<Product>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvProducts= (ListView) findViewById(R.id.lvProducts);
        list=new ArrayList<Product>();
        Product p1=new Product("Dell Latitude 3500",
                "The world's most secure, most manageable and most reliable buisness-class laptops.",
                "Laptop",
                14500.99,
                true);
        Product p2=new Product("Acer aspire 7",
                "Revolutionary convertible computers that feature powerful innovations and forward-thinking design.",
                "Screen",
                12500.99,
                false);
        Product p3=new Product("SANDISK 16 GB Cruzer",
                "Low-cost, no-nonsense way of storing and transporting files.",
                "Memory",
                299.99,
                false);
        Product p4=new Product("Verbatim 1TB",
                "Verbatim's portable hard drive product offerings are exceptionally reliable and fashionably thin.",
                "HDD",
                1020.99,
                true);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        ProductsAdapter adapter=new ProductsAdapter(this,list);
        lvProducts.setAdapter(adapter);
    }
}
