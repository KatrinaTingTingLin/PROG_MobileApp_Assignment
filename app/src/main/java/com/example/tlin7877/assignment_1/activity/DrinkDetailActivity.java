package com.example.tlin7877.assignment_1.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.database.AppDatabase;
import com.example.tlin7877.assignment_1.entity.Drink;

import java.util.List;

public class DrinkDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private int drinkID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        String drinkName = getIntent().getStringExtra("Drink_Name");
        int drinkPicID = getIntent().getIntExtra("Drink_Pic_ID",0);
        db = AppDatabase.getAppDatabase(this);
        List<Drink> drinks = db.drinkDao().getAll();
        for (Drink tempDrink : drinks){
            if (tempDrink.getName().contains(drinkName)){
                drinkID = tempDrink.getDrinkID();
            }
        }
        Drink drink = db.drinkDao().findByDrinkID(drinkID);

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
