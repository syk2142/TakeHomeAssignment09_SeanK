package com.example.takehomeassignment10_seank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dishRef = database.getReference("Dish");

    private TextView displayText;
    private ArrayList<Dish> dishList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = (TextView)findViewById(R.id.textView);
        dishRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                dishList.add(dataSnapshot.getValue(Dish.class));
                displayDish();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(MainActivity.this,dataSnapshot.getValue(Dish.class)+ "has changed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this,dataSnapshot.getValue(Dish.class)+ "has been removed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayDish(){
        String text = "";
        for (Dish dish :dishList)
            text += dish+ "\n";
        displayText.setText(text);
    }
    public void addDishes(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}