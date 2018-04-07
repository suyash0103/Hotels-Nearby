package com.example.suyash.hotelsnearby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddHotel extends AppCompatActivity {

    EditText name, address, open_time, close_time;
    Button save;
    String hotel_name, hotel_address, hotel_open_time, hotel_close_time;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        open_time = (EditText) findViewById(R.id.open_time);
        close_time = (EditText) findViewById(R.id.close_time);

        save = (Button) findViewById(R.id.save);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("hotels");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotel_name = name.getText().toString();
                hotel_address = address.getText().toString();
                hotel_open_time = open_time.getText().toString();
                hotel_close_time = close_time.getText().toString();

                DatabaseReference ownerRef = databaseReference.child("owner");
                OwnerDetails ownerDetails = new OwnerDetails(MainActivity.email_id, hotel_name, hotel_address, hotel_open_time, hotel_close_time);
                ownerRef.push().setValue(ownerDetails);
            }
        });

    }
}
