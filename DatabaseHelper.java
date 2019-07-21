package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "MONEY1";

    private static final String TABLE_NAME = "Money";
    private static final String COL0="DAte";
    private static final String COL1="Amount";
    private static final String COL2="SpentFor";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String createTable="CREATE TABLE"+ TABLE_NAME+"("+COL1+"VARCHAR,"+COL2+"VARCHAR);";*/
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(DAte VARCHAR,Amount VARCHAR,SpentFor VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Money");
        onCreate(db);
    }
    public boolean addData(String DAte,String Amount,String SpentFor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0,DAte);
        contentValues.put(COL1,Amount);
        contentValues.put(COL2,SpentFor);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else
            return true;

    }
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("Select * from "+TABLE_NAME,null);
        return data;

    }
}
