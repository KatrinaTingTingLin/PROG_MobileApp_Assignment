package com.example.tlin7877.assignment_1;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DBHandler db;
    private AccountPersister ap;
    private DrinksAdapter adapter;
    private ArrayList<Drink> drinkList;
    private ListView listView;
    String [] drinkName = {
            "Featured Dark Roast",
            "Nitro Teavana Peach Tea",
            "Salted Caramel Mocha Frappuccino"
    };
    Integer[] imgid ={
            R.drawable.dark_roasted,
            R.drawable.nitro_peach_tea,
            R.drawable.salted_caramel_frap
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvUserEmail = (TextView) findViewById(R.id.userEmail);


        //Generate ListView from SQLite Database
        displayListView();
        //http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem= drinkName[+position];
                int picutreID = imgid[+position];
                Intent intent = new Intent(HomeActivity.this, DrinkDetailActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("Drink_Name",selectedItem);
                intent.putExtra("Drink_Pic_ID",picutreID);
                startActivity(intent);
            }
        });
    }

    private void displayListView() {
        adapter = new DrinksAdapter(this, drinkName,imgid);

        listView = (ListView) findViewById(R.id.listView_Drink);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
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
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_cards) {
            Intent intent = new Intent(HomeActivity.this, CardActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_order) {
            Intent intent = new Intent(HomeActivity.this, OrderActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else if (id == R.id.nav_history) {

        }
        else if (id == R.id.nav_logout) {
             ap = new AccountPersister();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
