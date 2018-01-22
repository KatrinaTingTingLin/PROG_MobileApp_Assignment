package com.example.tlin7877.assignment_1.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tlin7877.assignment_1.EmailPersister;
import com.example.tlin7877.assignment_1.adapter.OrderAdapter;
import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private AppDatabase db;
    private EmailPersister ep;
    private OrderAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ep = new EmailPersister(this);
        db = AppDatabase.getAppDatabase(this);

        setNavigator();

        displayListView();
    }

    public void setNavigator(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_order);
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
                Intent intent = new Intent(OrderActivity.this, ProfileActivity.class).addFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    private void displayListView() {

        List<Integer> drinkIDs = db.orderDao().getAllDrinkIDs(ep.getUserEmail());
        List<String> drinkNames = new ArrayList<String>();
        for (int i=0;i<drinkIDs.size();i++){
            drinkNames.add(db.drinkDao().getDrinkName(drinkIDs.get(i)));
        }

        List<Integer> quantitys = db.orderDao().getAllQuantitys(ep.getUserEmail());
        adapter = new OrderAdapter(this, drinkNames,quantitys);

        listView = (ListView) findViewById(R.id.listView_Order);
        listView.setAdapter(adapter);
        int num = adapter.getCount();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_order);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_order);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
