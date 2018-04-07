package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OwnerHotelDetails extends AppCompatActivity {

    OwnerDetails hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_hotel_details);

        Intent intent = getIntent();

        hotel = (OwnerDetails) intent.getSerializableExtra("Hotel");

//        Toast.makeText(this, hotel.getName(), Toast.LENGTH_SHORT).show();

        

    }
}
