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

    //Resource id for background color of list
    private int mColorResourceId;

    public HotelAdapter(Activity context, ArrayList<Hotel> hotels) {
        super(context, 0, hotels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_list, parent, false);
        }

        //get the object located at position
        Hotel hotel = getItem(position);

        //find the textview in list_item with id default_text_view
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        //gets the default Translation and set it to the text of this textView
        nameTextView.setText(hotel.getName());

        //find the textview in list_item with id miwok_text_view
        TextView open_nowTextView = (TextView) listItemView.findViewById(R.id.open_now);
        //gets the miwok Translation and set it to the text of this textView
        String open_now = hotel.getOpen_now() + "";
        open_nowTextView.setText(open_now);


        return listItemView;
    }

}
