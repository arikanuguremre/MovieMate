package com.uguremrearikan.moviemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText moviName,year;
    Button btnSave;



    int[] images={
           R.drawable.zero,
           R.drawable.one,
           R.drawable.two,
           R.drawable.three,
           R.drawable.four,
           R.drawable.five,
           R.drawable.six,
           R.drawable.seven,
           R.drawable.eight,
           R.drawable.nine,
           R.drawable.ten
    };

    int selectedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add);

        moviName = findViewById(R.id.txtMovieName);
        year = findViewById(R.id.txtYear);
        btnSave = findViewById(R.id.btnSave);



        //Custom Spinner Score
        Spinner spin = findViewById(R.id.spinner);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),images);
        spin.setAdapter(customAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedScore=position;
                //Toast.makeText(AddActivity.this, "Your score is "+selectedScore, Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //button click listener
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int initposition=0;
                long res;

                if(moviName.getText().toString().trim().length()>0 && year.getText().toString().trim().length()>0){
                    MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                    res=myDB.addMovie(
                            moviName.getText().toString().trim(),
                            year.getText().toString().trim(),
                            selectedScore
                    );
                    //insertion başarılı ise
                    if(res!=-1){
                        moviName.setText("");
                        year.setText("");
                        spin.setSelection(initposition);

                        Intent intent = new Intent(AddActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(AddActivity.this, "Please Make Sure that all fields are full", Toast.LENGTH_SHORT).show();
                }



            }

        });




    }
}