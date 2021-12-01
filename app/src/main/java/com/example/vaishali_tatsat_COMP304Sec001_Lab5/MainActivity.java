package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnStart_Click(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, CuisineActivity.class);
        startActivity(myIntent);
    }
}