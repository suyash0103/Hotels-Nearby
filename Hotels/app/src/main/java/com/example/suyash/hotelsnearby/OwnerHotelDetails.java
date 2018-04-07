package com.example.suyash.hotelsnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerHotelDetails extends AppCompatActivity {

    OwnerDetails hotel;
    EditText name, address, ot, et;
    Button update;
    int a = 0;

    Query q;
    DatabaseReference d;

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
        final DatabaseReference hotelRef;

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
                a = 1;
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a == 0) {
                    Toast.makeText(OwnerHotelDetails.this, "No fields changed", Toast.LENGTH_LONG).show();
                } else {
                    Owner.ownerDetails = new ArrayList<>();

                    hotelRef.orderByChild("uid").equalTo(hotel.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String keyid = hotel.getUid();
                            hotelRef.child(keyid).child("hotel_name").setValue(name.getText().toString());
                            hotelRef.child(keyid).child("name").setValue(name.getText().toString());
                            hotelRef.child(keyid).child("hotel_address").setValue(address.getText().toString());
                            hotelRef.child(keyid).child("opening_time").setValue(ot.getText().toString());
                            hotelRef.child(keyid).child("ending_time").setValue(et.getText().toString());
                            hotelRef.child(keyid).child("email").setValue(MainActivity.email_id);
                            hotelRef.child(keyid).child("uid").setValue(keyid);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(OwnerHotelDetails.this, "Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(OwnerHotelDetails.this, Owner.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
