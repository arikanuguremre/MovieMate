package com.uguremrearikan.moviemate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText txtMovie2,txtYear2;
    Spinner spinner2;
    Button btnUpdate,btnDelete;
    String id,name,year,score;
    AlertDialog alertDialog;

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

    int selectedScore,initposition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update);

        txtYear2=findViewById(R.id.txtYear2);
        spinner2=findViewById(R.id.spinner2);

        btnUpdate=findViewById(R.id.btnUpdate);
        txtMovie2=findViewById(R.id.edtTxtName);
        btnDelete=findViewById(R.id.btnDelete);




        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),images);
        spinner2.setAdapter(customAdapter);

        //STEP 1
        //First we call this
        getAndSetIntentData();


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedScore=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //STEP 2
                //And only then we call this
                if(txtMovie2.getText().toString().trim().length()>0 && txtYear2.getText().toString().trim().length()>0){

                    MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);

                    myDB.updateData(id,txtMovie2.getText().toString().trim(),
                            txtYear2.getText().toString().trim(),
                            selectedScore
                    );
                    Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(UpdateActivity.this, "Please Make Sure that all fields are full", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });







    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("name")&& getIntent().hasExtra("year")&& getIntent().hasExtra("score")){

            //getting data from intent
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            year=getIntent().getStringExtra("year");
            score=getIntent().getStringExtra("score");


            //setting data from intent
            txtMovie2.setText(name);
            txtYear2.setText(year);
            spinner2.setSelection(Integer.parseInt(score));


        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete "+name+" ?");
        builder.setMessage("Are you sure you want to delete "+name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long res;
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                res = myDB.deleteOneRow(id);
                //when delete is success
                if(res!=-1){
                    txtMovie2.setText("");
                    txtYear2.setText("");
                    spinner2.setSelection(initposition);
                    Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog=builder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1000,1500);//Controlling width and height.


    }
}