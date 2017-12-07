package com.example.tlin7877.assignment_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinkDetailActivity extends AppCompatActivity {
    private DBHandler db;
    private int drinkID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        String drinkName = getIntent().getStringExtra("Drink_Name");
        int drinkPicID = getIntent().getIntExtra("Drink_Pic_ID",0);
        db = new DBHandler(this);
        ArrayList<Drink> drinks = db.getAllDrinks();
        for (Drink tempDrink : drinks){
            if (tempDrink.getName().contains(drinkName)){
                drinkID = tempDrink.getDrinkID();
            }
        }
        Drink drink = db.getDrink(drinkID);

        ImageView ivDrink = (ImageView) findViewById(R.id.drinkPic);
        TextView tvDrinkName = (TextView) findViewById(R.id.drinkName);
        TextView tvDrinkDesc = (TextView) findViewById(R.id.drinkDesc);

        ivDrink.setImageResource(drinkPicID);
        tvDrinkName.setText(drink.getName());
        tvDrinkDesc.setText(drink.getDescription());

        Button btnAddToOrder = (Button) findViewById(R.id.btnAddToOrder);
        btnAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
