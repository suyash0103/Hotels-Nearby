package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerHotelDetails extends AppCompatActivity {

    OwnerDetails hotel;
    EditText name, address, ot, et;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_hotel_details);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        ot = (EditText) findViewById(R.id.open_time);
        et = (EditText) findViewById(R.id.end_time);
        update = (Button) findViewById(R.id.update);

        Intent intent = getIntent();
        hotel = (OwnerDetails) intent.getSerializableExtra("Hotel");

        final FirebaseDatabase database;
        DatabaseReference hotelRef;

        database = FirebaseDatabase.getInstance();
        hotelRef = database.getReference().child("hotels");

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        Toast.makeText(this, "Details can be updated", Toast.LENGTH_LONG).show();

        name.setText(hotel.getName());
        address.setText(hotel.getHotel_address());
        ot.setText(hotel.getOpening_time());
        et.setText(hotel.getEnding_time());

        name.addTextChangedListener(tw);
        address.addTextChangedListener(tw);
        ot.addTextChangedListener(tw);
        et.addTextChangedListener(tw);



    }
}
