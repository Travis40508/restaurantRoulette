package com.campbellapps.christiancampbell.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rodneytressler on 11/17/16.
 */

public class restaurantAdapter extends ArrayAdapter<Restaurant> {

    public restaurantAdapter(Context context, ArrayList<Restaurant> restaurants){

        super(context, 0, restaurants);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        Restaurant restaurant = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.restaurant_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.item_name);
        TextView address = (TextView) convertView.findViewById(R.id.item_address);

        name.setText(restaurant.getName().toUpperCase());
        address.setText(restaurant.getAddress());

        return convertView;


    }

}
