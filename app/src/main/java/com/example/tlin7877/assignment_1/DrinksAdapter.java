package com.example.tlin7877.assignment_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tlin7877 on 12/6/2017.
 */

public class DrinksAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public DrinksAdapter(Activity context, String[] itemname, Integer[] imgid){
        super(context, R.layout.content_list_layout,itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content_list_layout,
                    parent, false);
        }
        // Lookup view for data population
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView tvDrinkName = (TextView) view.findViewById(R.id.item);

        // Populate the data into the template view using the data object
        imageView.setImageResource(imgid[position]);
        tvDrinkName.setText(itemname[position]);
        // Return the completed view to render on screen
        return view;
    }
}
