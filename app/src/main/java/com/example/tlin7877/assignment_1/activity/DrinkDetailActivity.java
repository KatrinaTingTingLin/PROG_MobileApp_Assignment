package com.example.tlin7877.assignment_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tlin7877.assignment_1.EmailPersister;
import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.database.AppDatabase;
import com.example.tlin7877.assignment_1.entity.Drink;
import com.example.tlin7877.assignment_1.entity.Order;

import java.util.List;

public class DrinkDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private AppDatabase db;
    private EmailPersister ep;
    private int drinkID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        ep = new EmailPersister(this);
        db = AppDatabase.getAppDatabase(this);

        setNavigator();

        String drinkName = getIntent().getStringExtra("Drink_Name");
        int drinkPicID = getIntent().getIntExtra("Drink_Pic_ID",0);

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
                db.orderDao().insert(new Order(drinkID,ep.getUserEmail(),1));
                Toast.makeText(DrinkDetailActivity.this,"Added to order", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setNavigator(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_drink_detail);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);

        TextView tvUserName = (TextView) headerLayout.findViewById(R.id.txtUserEmail);

        String userEmail = ep.getUserEmail();
        tvUserName.setText(userEmail);

        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinkDetailActivity.this, ProfileActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_drink_detail);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, HomeActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_cards) {
            Intent intent = new Intent(this, CardActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_download) {
            Intent intent = new Intent(this, DownloadActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(this, OrderActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            ep.logOutUser();
            Intent intent = new Intent(this, MainActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_drink_detail);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
