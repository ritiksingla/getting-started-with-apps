package com.example.earthquakeactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR=" of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake>data)
    {
        super(context,0,data);
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view=convertView;
        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list,parent,false);
        }
        Earthquake currentEarthquake=getItem(position);

        TextView tvMagnitude=view.findViewById(R.id.tvMagnitude);

        GradientDrawable magnitudeCircle=(GradientDrawable)tvMagnitude.getBackground();
        int magnitudeColor=getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);
        tvMagnitude.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        String originalLocation=currentEarthquake.getLocation();
        String primaryLocation,offsetLocation;

        if(originalLocation.contains(LOCATION_SEPARATOR))
        {
         String[]parts=originalLocation.split(LOCATION_SEPARATOR);
         offsetLocation=parts[0]+LOCATION_SEPARATOR;
         primaryLocation=parts[1];
        }
        else{
         offsetLocation=getContext().getString(R.string.near_the);
         primaryLocation=originalLocation;
        }
        TextView tvLocationPrimary=view.findViewById(R.id.tvLocationPrimary);
        TextView tvLocationOffset=view.findViewById(R.id.tvLocationOffset);
        tvLocationPrimary.setText(primaryLocation);
        tvLocationOffset.setText(offsetLocation);

        Date dateObject=new Date(currentEarthquake.getTimeInMilliSeconds());
        String formattedDate=formatDate(dateObject);
        TextView tvDate=view.findViewById(R.id.tvDate);
        tvDate.setText(formattedDate);

        String formattedTime=formatTime(dateObject);
        TextView tvTime=view.findViewById(R.id.tvTime);
        tvTime.setText(formattedTime);
        return view;
    }

    private String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormatter=new SimpleDateFormat("LLL dd,yyyy");
        return dateFormatter.format(dateObject);
    }
    private String formatTime(Date dateObject)
    {
        SimpleDateFormat dateFormatter=new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObject);
    }
    private String formatMagnitude(double magnitude)
    {
        DecimalFormat formatter=new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }
   private int getMagnitudeColor(double magnitude)
   {
       int magnitudeColorResourceId;
       int magnitudeFloor=(int)Math.floor(magnitude);
       switch (magnitudeFloor)
       {
           case 1:
               magnitudeColorResourceId=R.color.magnitude1;
               break;
           case 2:
               magnitudeColorResourceId=R.color.magnitude2;
               break;
           case 3:
               magnitudeColorResourceId=R.color.magnitude3;
               break;
           case 4:
               magnitudeColorResourceId=R.color.magnitude4;
               break;
           case 5:
               magnitudeColorResourceId=R.color.magnitude5;
               break;
           case 6:
               magnitudeColorResourceId=R.color.magnitude6;
               break;
           case 7:
               magnitudeColorResourceId=R.color.magnitude7;
               break;
           case 8:
               magnitudeColorResourceId=R.color.magnitude8;
               break;
           case 9:
               magnitudeColorResourceId=R.color.magnitude9;
               break;
           default:
               magnitudeColorResourceId=R.color.magnitude10plus;
               break;
       }
       return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
   }
}
