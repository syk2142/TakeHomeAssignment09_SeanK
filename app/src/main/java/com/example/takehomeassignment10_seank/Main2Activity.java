package com.example.takehomeassignment10_seank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText mDishName,mDishPrice;
    CheckBox mIsVegan;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dishRef = database.getReference("Dish");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeViewControls();
    }

    private void initializeViewControls(){
        mDishName = findViewById(R.id.dishName);
        mDishPrice = findViewById(R.id.dishPrice);
        mIsVegan = findViewById(R.id.isVegan);
    }

    public void submitDish(View view) {
        String dishName = mDishName.getText().toString();
        int dishAge = Integer.valueOf(mDishPrice.getText().toString());
        boolean isVegan = mIsVegan.isChecked();

        Dish dish = new Dish(dishName,dishAge,isVegan);
        dishRef.push().setValue(dish);

        Intent data = new Intent(this, MainActivity.class);
        startActivity(data);
    }
}