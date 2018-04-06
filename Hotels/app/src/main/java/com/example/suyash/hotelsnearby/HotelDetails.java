package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HotelDetails extends AppCompatActivity {

    TextView name, vicinity, distance;
    Button navigate;

    private String API_KEY_MAP = "AIzaSyA_RPVEMIKVLeNAgyv5sPo28igOajTIy48";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        name = (TextView) findViewById(R.id.name);
        vicinity = (TextView) findViewById(R.id.vicinity);
        distance = (TextView) findViewById(R.id.distance);

        navigate = (Button) findViewById(R.id.navigate);

        Intent intent = getIntent();

        final Hotel hotel = (Hotel) intent.getSerializableExtra("Hotel");

        name.setText(hotel.getName());
        vicinity.setText(hotel.getVicinity());
        distance.setText(hotel.getDistance());

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + hotel.getLatitude() + "," + hotel.getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
}
