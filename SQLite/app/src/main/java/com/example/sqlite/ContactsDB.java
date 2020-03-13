package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDB {
//    Columns names
    public final static String KEY_ROWID="_id";
    public final static String KEY_NAME="person_name";
    public final static String KEY_CELL="_cell";
//    Database Name
    private final String DATABASE_NAME="ContactsDB";
//    TableName in ContactsDB
    private final String DATABASE_TABLE="ContactsTable";
//    Database Version
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatbase;
    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context)
        {

            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        //Only run whe Database is not created previously.
        @Override
        public void onCreate(SQLiteDatabase db) {
            /*
            * CREATE TABLE ContactsTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, person_name TEXT NOT NULL, _cell TEXT NOT NULL);
            * */
            String sqlCode="CREATE TABLE "+DATABASE_TABLE+" ("+
                    KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_NAME+" TEXT NOT NULL, "+
                    KEY_CELL+" TEXT NOT NULL);";
            db.execSQL(sqlCode);

        }
//        Only run when stored version number is different from new version
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
        }
    }
    public ContactsDB(Context context)
    {
        ourContext=context;
    }
    public ContactsDB open() throws SQLException
    {
        ourHelper=new DBHelper(ourContext);
        ourDatbase=ourHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        ourHelper.close();

    }
    public long createEntry(String name,String cell)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_CELL,cell);
        return ourDatbase.insert(DATABASE_TABLE,null,cv);
//        return number of values inserted.
    }
    public String getData()
    {
        String [] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_CELL};
        Cursor cursor=ourDatbase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRowId=cursor.getColumnIndex(KEY_ROWID);
        int iName=cursor.getColumnIndex(KEY_NAME);
        int iCell=cursor.getColumnIndex(KEY_CELL);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            result+=cursor.getString(iRowId)+": "+cursor.getString(iName)+": "+cursor.getString(iCell)+"\n";

        }
        cursor.close();
        return result;
    }
    public long deleteEntry(String rowId)
    {
        return ourDatbase.delete(DATABASE_TABLE,KEY_ROWID+"=?",new String[]{rowId});

    }
    public long updateEntry(String rowid,String name,String cell)
    {
        ContentValues c=new ContentValues();
        c.put(KEY_NAME,name);
        c.put(KEY_ROWID,rowid);
        c.put(KEY_CELL,cell);
        return ourDatbase.update(DATABASE_TABLE,c,KEY_ROWID+"=?",new String[]{rowid});
    }
}