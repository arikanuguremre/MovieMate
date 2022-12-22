package com.uguremrearikan.moviemate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="MovieMate.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="my_movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "movie_title";
    private static final String COLUMN_YEAR = "movie_year";
    private static final String COLUMN_MYPOINT = "movie_mypoint";








    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE --> SQL command
        String query =  "CREATE TABLE "+ TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_YEAR + " TEXT, " +
                        COLUMN_MYPOINT +" INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    long addMovie(String movieName,String year,int score){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,movieName);
        cv.put(COLUMN_YEAR,year);
        cv.put(COLUMN_MYPOINT,score);

       long result = db.insert(TABLE_NAME,null,cv);
       
       if(result == -1){
           Toast.makeText(context, "Insertion Failed", Toast.LENGTH_SHORT).show();
           return result;
       }else{
           Toast.makeText(context, "Movie added succesfully", Toast.LENGTH_SHORT).show();
           return result;
       }

    }

    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
           cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

    void updateData(String row_id,String movieName,String year,int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,movieName);
        cv.put(COLUMN_YEAR,year);
        cv.put(COLUMN_MYPOINT,score);

       long res= db.update(TABLE_NAME,cv,"_id=?", new String[]{row_id});
       if (res == -1){
           Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context, "Update succesfull", Toast.LENGTH_SHORT).show();
       }

    }

    long deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res =db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(res == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
            return res;
        }else{
            Toast.makeText(context, "Movie Deleted !", Toast.LENGTH_SHORT).show();
            return res;
        }
    }

}
