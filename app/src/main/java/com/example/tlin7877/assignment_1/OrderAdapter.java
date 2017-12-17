package com.example.tlin7877.assignment_1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tlin7877 on 12/17/2017.
 */

public class OrderAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] drinkName;
    private final Integer[] quantity;

    public OrderAdapter(Activity context, String[] drinkName, Integer[] quantity){
        super(context, R.layout.content_list_layout,drinkName);
        this.context=context;
        this.drinkName=drinkName;
        this.quantity=quantity;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.content_order_list,
                    parent, false);
        }
        // Lookup view for data population
        TextView tvDrinkName = (TextView) view.findViewById(R.id.drinkName_list);
        TextView tvQuantity = (TextView) view.findViewById(R.id.quantity_list);

        // Populate the data into the template view using the data object
        tvDrinkName.setText(drinkName[position]);
        tvQuantity.setText(quantity[position]);
        // Return the completed view to render on screen
        return view;
    }
}
