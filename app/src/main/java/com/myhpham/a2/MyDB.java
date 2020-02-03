package com.myhpham.a2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    //table strings
    private static final String DB_NAME = "image.db";
    private static final String TABLE_NAME = "IMG_DB";
    private static final String ID = "_id";
    private static final String IMG_NAME = "name";
    private static final String IMG_URL = "url";

    Context ctx;
    static int VERSION = 1;


    public MyDB(Context context){
        super(context, DB_NAME, null, VERSION);
        ctx = context;
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableString = "create table " + TABLE_NAME + " (" + ID + " integer primary key autoincrement, " + IMG_NAME + " text, " + IMG_URL + " text);";
        db.execSQL(tableString);
        //Toast.makeText(ctx, "Table " + TABLE_NAME + " was made", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertImage(String imageName, String imageUrl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMG_NAME, imageName);
        cv.put(IMG_URL, imageUrl);
        db.insert(TABLE_NAME, null, cv);
    }

    //displays images
    public Cursor viewAllImages(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    //deletes item from database
    public void deleteImage(String imageid) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "_id = ?", new String[]{imageid});
        viewAllImages();
        //Toast.makeText(ctx, imageName + " is deleted", Toast.LENGTH_SHORT).show();
    }

    public Cursor searchImage(String imageName){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + IMG_NAME + " LIKE '%" + imageName + "%'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void erase() {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, VERSION, VERSION + 1);
    }
}