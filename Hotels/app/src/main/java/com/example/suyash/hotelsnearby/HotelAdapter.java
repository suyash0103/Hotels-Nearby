package com.example.suyash.hotelsnearby;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelAdapter extends ArrayAdapter<Hotel> {

    public HotelAdapter(Activity context, ArrayList<Hotel> hotels) {
        super(context, 0, hotels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_list, parent, false);
        }

        Hotel hotel = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(hotel.getName());

        TextView vicinityTextView = (TextView) listItemView.findViewById(R.id.vicinity);
        vicinityTextView.setText(hotel.getVicinity());

        return listItemView;
    }

}
