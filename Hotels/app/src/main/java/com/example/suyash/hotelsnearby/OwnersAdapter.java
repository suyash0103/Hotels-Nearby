package com.example.suyash.hotelsnearby;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class OwnersAdapter extends ArrayAdapter<OwnerDetails> {

    public OwnersAdapter(Activity context, ArrayList<OwnerDetails> hotels) {
        super(context, 0, hotels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.owner_hotel_list, parent, false);
        }

        OwnerDetails ownerDetails = getItem(position);

        TextView hotelName = (TextView) listItemView.findViewById(R.id.hotel_name);
        hotelName.setText(ownerDetails.getName());

        return listItemView;
    }

}
