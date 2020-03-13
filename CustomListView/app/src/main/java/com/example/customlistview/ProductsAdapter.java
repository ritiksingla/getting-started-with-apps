package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductsAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final ArrayList<Product> values;

    public ProductsAdapter(@NonNull Context context, ArrayList<Product>list) {
        super(context,R.layout.row_layout,list);
        this.context=context;
        this.values=list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_view=inflater.inflate(R.layout.row_layout,parent,false);

        TextView tvProducts=(TextView)row_view.findViewById(R.id.tvProducts);
        TextView tvPrice=(TextView)row_view.findViewById(R.id.tvPrice);
        TextView tvDescription=(TextView)row_view.findViewById(R.id.tvDescription);

        ImageView ivProducts=(ImageView)row_view.findViewById(R.id.ivProduct);
        ImageView ivSale=(ImageView)row_view.findViewById(R.id.ivSale);

        tvProducts.setText(values.get(position).getTitle());
        tvPrice.setText("$"+values.get(position).getPrice());
        tvDescription.setText(values.get(position).getDescription());

        if(values.get(position).isSale())
        {
            ivSale.setImageResource(R.mipmap.sale);
        }
        else{
            ivSale.setImageResource(R.mipmap.best_price);
        }

        if(values.get(position).getType().equals("Laptop"))
        {
            ivProducts.setImageResource(R.mipmap.laptop);
        }
       else if(values.get(position).getType().equals("Memory"))
        {
            ivProducts.setImageResource(R.mipmap.memory);
        }
        else if(values.get(position).getType().equals("Screen"))
        {
            ivProducts.setImageResource(R.mipmap.screen);
        }
        else
        {
            ivProducts.setImageResource(R.mipmap.hdd);
        }
        return row_view;
    }
}
