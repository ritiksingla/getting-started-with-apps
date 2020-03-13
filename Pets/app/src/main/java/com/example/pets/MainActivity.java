package com.example.pets;

import
        androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pets.data.PetContract.PetEntry;
import com.example.pets.data.PetDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private PetDbHelper petDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        petDbHelper=new PetDbHelper(this);

        displayDatabaseInfo();
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {
        PetDbHelper petDbHelper =new PetDbHelper(this);
        SQLiteDatabase db=petDbHelper.getReadableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+ PetEntry.TABLE_NAME,null);
        try {
            TextView displayView=(TextView) findViewById(R.id.text_view_pet);
            displayView.setText("Number of rows in pets database table: "+cursor.getCount());
        }
        finally {
            cursor.close();
        }
    }
    private void insertPet()
    {
        SQLiteDatabase db=petDbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME,"Toto");
        contentValues.put(PetEntry.COLUMN_PET_BREED,"Terrier");
        contentValues.put(PetEntry.COLUMN_PET_GENDER,PetEntry.GENDER_MALE);
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT,7);
        //Returns long integer
       long newRowId= db.insert(PetEntry.TABLE_NAME,null,contentValues);
        Log.v("CatalogActivity","NEW ROW ID"+newRowId);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                // insert fake data
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
