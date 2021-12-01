package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CuisineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
    }

    public void btnChinese_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "Chinese");
        startActivity(myIntent);
    }
    public void btnIndian_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "Indian");
        startActivity(myIntent);
    }
    public void btnThai_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "Thai");
        startActivity(myIntent);
    }
    public void btnItalian_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "Italian");
        startActivity(myIntent);
    }
    public void btnMiddleEast_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "MiddleEast");
        startActivity(myIntent);
    }
    public void btnMexican_Click(View view){
        Intent myIntent = new Intent(CuisineActivity.this, RestaurantActivity.class);
        myIntent.putExtra(RestaurantActivity.EXTRA_SELECTED_CUISINE, "Mexican");
        startActivity(myIntent);
    }
}