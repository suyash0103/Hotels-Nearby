package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HotelDetails extends AppCompatActivity {

    TextView name, vicinity, distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        name = (TextView) findViewById(R.id.name);
        vicinity = (TextView) findViewById(R.id.vicinity);
        distance = (TextView) findViewById(R.id.distance);

        Intent intent = getIntent();

        Hotel hotel = (Hotel) intent.getSerializableExtra("Hotel");

        name.setText(hotel.getName());
        vicinity.setText(hotel.getVicinity());
        distance.setText(hotel.getDistance());

    }
}
