package com.example.practilesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBNameNew.db";
    public static final String CONTACTS_TABLE_NAME_AUTHER = "authert";
    public static final String CONTACTS_TABLE_NAME_BOOK = "bookt";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_BOOK = "book";
    public static final String CONTACTS_COLUMN_AUTHER = "auther";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table authert " +
                        "(id integer primary key, auther text)"
        );
        db.execSQL(
                "create table bookt " +
                        "(id integer primary key, book text, auther text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS authert");
        db.execSQL("DROP TABLE IF EXISTS bookt");
        onCreate(db);
    }

    public boolean insertContact (String bo, String aut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book", bo);
        contentValues.put("auther", aut);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public boolean insertAuther (String aut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("auther", aut);
        db.insert("authert", null, contentValues);
        return true;
    }

    public boolean insertAuther (String book, String aut) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book", book);
        contentValues.put("auther", aut);
        db.insert("bookt", null, contentValues);
        return true;
    }

    public boolean insertBook (String book, String auther) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book", book);
        contentValues.put("auther", auther);
        db.insert("bookt", null, contentValues);
        return true;
    }

    public Cursor getData(String auther) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where auther="+auther+"", null );
        return res;
    }

    /*public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }*/

    public boolean updateContact (Integer id, String book, String auther) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book", book);
        contentValues.put("auther", auther);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateAuther (Integer id, String auther) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("auther", auther);
        db.update("authert", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateBook (Integer id, String book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("book", book);
        db.update("bookt", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (String bo) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "book = ? ",
                new String[] { bo });
    }

    public Integer deleteAuther (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("authert",
                "id = ? ",
                new String[] { id });
    }

    public Integer deleteBook (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("bookt",
                "id = ? ",
                new String[] { id });
    }

    /*public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/

    /*where auther="+aut+"*/

    public ArrayList<String> getAllBookold(String aut) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = { "" + aut };
        StringBuffer sbQuery = new StringBuffer("SELECT * from ").append(
                "contacts").append(" where auther=?");
        Cursor res = getReadableDatabase().rawQuery(sbQuery.toString(), args);
        res.moveToFirst();
        /*Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();*/

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_BOOK)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<AutherModel> getAllAuther() {
        ArrayList<AutherModel> array_list = new ArrayList<AutherModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from authert", null );
        res.moveToFirst();
        /*Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();*/

        while(res.isAfterLast() == false){
            AutherModel autherModel = new AutherModel(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)), res.getString(res.getColumnIndex(CONTACTS_COLUMN_AUTHER)));
            array_list.add(autherModel);
            res.moveToNext();
        }

        return array_list;
    }

    public ArrayList<BookModel> getAllBook(String aut) {
        ArrayList<BookModel> array_list = new ArrayList<BookModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = { "" + aut };
        StringBuffer sbQuery = new StringBuffer("SELECT * from ").append(
                "bookt").append(" where auther=?");
        Cursor res = getReadableDatabase().rawQuery(sbQuery.toString(), args);
        res.moveToFirst();
        /*Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();*/

        while(res.isAfterLast() == false){
            BookModel autherModel = new BookModel(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)), res.getString(res.getColumnIndex(CONTACTS_COLUMN_BOOK)), res.getString(res.getColumnIndex(CONTACTS_COLUMN_AUTHER)));
            array_list.add(autherModel);
            res.moveToNext();
        }

        return array_list;
    }

}
