package com.example.tlin7877.assignment_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tlin7877.assignment_1.R;
import com.example.tlin7877.assignment_1.entity.Order;

import java.util.List;

/**
 * Created by tlin7877 on 12/17/2017.
 */

public class OrderAdapter extends ArrayAdapter<String> {
    private final List<String> drinkNames;
    private final List<Integer> quantitys;

    public OrderAdapter(Context context, List<String> drinkNames, List<Integer> quantitys){
        super(context, R.layout.content_list_layout,drinkNames);
        this.drinkNames=drinkNames;
        this.quantitys=quantitys;
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
        tvDrinkName.setText(drinkNames.get(position));
        tvQuantity.setText(quantitys.get(position).toString());
        // Return the completed view to render on screen
        return view;
    }
}
