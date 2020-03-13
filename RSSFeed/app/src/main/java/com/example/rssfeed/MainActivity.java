package com.example.rssfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String>titles;
    ArrayList<String>links;
    ListView lvRss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvRss=findViewById(R.id.lvRss);
        titles=new ArrayList<>();
        links=new ArrayList<>();

        new ProcessInBackground().execute();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri=Uri.parse(links.get(position));
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
    public InputStream getInputStream(URL url)
    {
        try {
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }
    public class ProcessInBackground extends AsyncTask<Integer,Void,Exception> {
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        Exception exception=null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Please Wait.....");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,titles);
            lvRss.setAdapter(adapter);
        }
        @Override
        protected Exception doInBackground(Integer... integers) {

            try{
                URL url=new URL("http://feeds.news24.com/articles/fin24/tech/rss");
                XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
//                Won't support for namespaces
                xmlPullParserFactory.setNamespaceAware(false);
                XmlPullParser xpp=xmlPullParserFactory.newPullParser();
                xpp.setInput(getInputStream(url),null);
                boolean inside=false;
                int eventType=xpp.getEventType();
                while (eventType!=XmlPullParser.END_DOCUMENT)
                {
                    if(eventType==XmlPullParser.START_TAG)
                    {
                        if(xpp.getName().equalsIgnoreCase("item"))
                        {
                            inside=true;

                        }
                        else if(xpp.getName().equalsIgnoreCase("title"))
                        {
                            if(inside)
                            {
                                titles.add(xpp.nextText());
                            }
                        }
                        else if(xpp.getName().equalsIgnoreCase("link"))
                        {
                            if(inside)
                            {
                                links.add(xpp.nextText());
                            }
                        }
                    }
                    else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        inside=false;
                    }
                }
            }
            catch (MalformedURLException e)
            {
                exception=e;
            }
            catch (XmlPullParserException e)
            {
                exception=e;
            }
            catch (IOException e)
            {
                exception=e;
            }


            return exception;
        }
    }
}
