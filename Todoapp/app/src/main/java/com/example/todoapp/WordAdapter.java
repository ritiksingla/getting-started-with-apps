package com.example.todoapp;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word>words,int colorSourceId)
    {
        super(context,0,words);
        mColorResourceId=colorSourceId;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

//        Check if the existing view is being reused, otherwise inflate the view.
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
//        Get the Word object located at this position in the list.
        Word currentWord=getItem(position);

        TextView nameTextView=(TextView)listItemView.findViewById(R.id.itemName);
        nameTextView.setText(currentWord.getmItemName());
        TextView priceTextView=(TextView)listItemView.findViewById(R.id.itemPrice);
        priceTextView.setText(""+currentWord.getmPrice());
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.itemImage);
        imageView.setImageResource(currentWord.getmImageId());

        View textContainer=listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);

        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}