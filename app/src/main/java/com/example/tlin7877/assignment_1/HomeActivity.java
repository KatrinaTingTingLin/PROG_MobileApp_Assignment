package com.example.tlin7877.assignment_1;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DBHandler db;
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

        db = new DBHandler(this);

        TableLayout tableLayout=(TableLayout)findViewById(R.id.drinkTable);
        //https://developer.android.com/guide/topics/ui/layout/listview.html
        try
        {
            List<Drink> drinkList = db.getAllDrinks();
            if(db.getDrinksCount()>0)
            {
                for (Drink drink : drinkList) {
                    // Read columns data
                    String drink_pic = drink.getPicture();
                    String drink_name = drink.getName();
                    int pictureID = getResources().getIdentifier(
                            drink_pic,"drawable",getPackageName());
                    int imageViewID = View.generateViewId();
                    int textViewID = View.generateViewId();
                    // dara rows
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.FILL_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    RelativeLayout relativelayout = new RelativeLayout(this);
                    relativelayout.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.FILL_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    ImageView iv = new ImageView(this);
                    iv.setId(imageViewID);
                    iv.setImageResource(pictureID);
                    iv.getLayoutParams().width = 70;
                    iv.getLayoutParams().height = 70;
                    RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    imageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    iv.setLayoutParams(imageParams);
                    relativelayout.addView(iv);

                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setId(textViewID);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(25);
                    tv.setText(drink_name);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL));
                    RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    textParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, iv.getId());
                    tv.setLayoutParams(textParams);
                    relativelayout.addView(tv);

                    row.addView(relativelayout);
                    tableLayout.addView(row);
                }
            }
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.close();
            // Close database
        }
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
