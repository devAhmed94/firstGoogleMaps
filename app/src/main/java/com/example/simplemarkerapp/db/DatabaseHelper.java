package com.example.simplemarkerapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, EntityDataBase.DATABASE_NAME, null, EntityDataBase.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_create = " CREATE TABLE  " + EntityDataBase.DATABASE_TABLE + " ( "
                         +EntityDataBase.COL_ID + " Integer PRIMARY KEY AUTOINCREMENT , "
                         + EntityDataBase.COL_TITLE + " text , "
                         + EntityDataBase.COL_LAT + " text , "
                         +EntityDataBase.COL_LONG +" text ) ;";
        db.execSQL(sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String drop =" DROP  " +EntityDataBase.DATABASE_TABLE ;
        db.execSQL(drop);
        onCreate(db);


    }

    public boolean insert(Model model){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EntityDataBase.COL_LAT,model.getLatitude());
        values.put(EntityDataBase.COL_LONG,model.getLongitude());
        values.put(EntityDataBase.COL_TITLE,model.getTitle());

        long insert = sqLiteDatabase.insert(EntityDataBase.DATABASE_TABLE, null, values);
        return insert>-1;
    }

    public boolean update (Model model){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EntityDataBase.COL_LAT,model.getLatitude());
        values.put(EntityDataBase.COL_LONG,model.getLongitude());
        values.put(EntityDataBase.COL_TITLE,model.getTitle());

        int update = sqLiteDatabase.
                update(EntityDataBase.DATABASE_TABLE, values
                        , EntityDataBase.COL_ID + " =? ", new String[]{model.getId() + ""});
        return update>0;
    }


    public ArrayList<Model> getAllData(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Model> modelArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + EntityDataBase.DATABASE_TABLE, null);
        if(cursor.moveToFirst()){
            do {
                Model newModel = new Model();
                newModel.setId(cursor.getInt(cursor.getColumnIndex(EntityDataBase.COL_ID)));
                newModel.setTitle(cursor.getString(cursor.getColumnIndex(EntityDataBase.COL_TITLE)));
                newModel.setLatitude(cursor.getString(cursor.getColumnIndex(EntityDataBase.COL_LAT)));
                newModel.setLongitude(cursor.getString(cursor.getColumnIndex(EntityDataBase.COL_LONG)));
                modelArrayList.add(newModel);

            }while (cursor.moveToNext());
        }
        return modelArrayList;
    }

    public boolean delete(Model model){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(EntityDataBase.DATABASE_TABLE,
                EntityDataBase.COL_ID + " =? ", new String[]{String.valueOf(model.getId())});
        return delete>0;
    }

    public int getCountRows (Model model){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + EntityDataBase.DATABASE_TABLE, null);
        return cursor.getCount();
    }
}
