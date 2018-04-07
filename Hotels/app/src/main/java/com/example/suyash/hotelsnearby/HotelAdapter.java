package com.example.suyash.hotelsnearby;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by suyash on 6/4/18.
 */

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

        //find the textview in list_item with id default_text_view
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        //gets the default Translation and set it to the text of this textView
        nameTextView.setText(hotel.getName());

        //find the textview in list_item with id miwok_text_view
        TextView vicinityTextView = (TextView) listItemView.findViewById(R.id.vicinity);
        //gets the miwok Translation and set it to the text of this textView
        vicinityTextView.setText(hotel.getVicinity());


        return listItemView;
    }

}
