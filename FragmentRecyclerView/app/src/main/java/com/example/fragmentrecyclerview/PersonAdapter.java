package com.example.fragmentrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    private ArrayList<Person>people;
    ItemClicked activity;
    public interface ItemClicked{
        void onItemClicked(int index);
    }
    public PersonAdapter(Context context,ArrayList<Person>list){
        people=list;
        activity=(ItemClicked)context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(people.indexOf((Person) v.getTag()));
                }
            });
        }
    }
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(people.get(position));
        holder.tvName.setText(people.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return people.size();
    }
}