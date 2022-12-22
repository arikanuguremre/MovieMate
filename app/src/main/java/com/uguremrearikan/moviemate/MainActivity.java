package com.uguremrearikan.moviemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity

{

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    private static final String TAG = "MainActivity";
    MyDataBaseHelper myDB;
    ArrayList<String> movie_id,movie_name,year,score;
    CustomRecyclerViewAdapter customRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.AddBtn);




        //when double tap happen in add button new intent comes
        add_button.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Toast.makeText(MainActivity.this, "DOUBLE TAP", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,AddActivity.class);
                    startActivity(intent);

                    return super.onDoubleTap(e);
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        myDB = new MyDataBaseHelper(MainActivity.this);
        movie_id = new ArrayList<>();
        movie_name=new ArrayList<>();
        year=new ArrayList<>();
        score=new ArrayList<>();

        storeDataInArrays();




        customRecyclerViewAdapter=new CustomRecyclerViewAdapter(MainActivity.this,movie_id,movie_name,year,score);
        customRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                movie_id.add(cursor.getString(0));
                movie_name.add(cursor.getString(1));
                year.add(cursor.getString(2));
                score.add(cursor.getString(3));
            }
        }


    }




}