package com.example.suyash.hotelsnearby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddHotel extends AppCompatActivity {

    EditText name, address, open_time, close_time;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        open_time = (EditText) findViewById(R.id.open_time);
        close_time = (EditText) findViewById(R.id.close_time);

        save = (Button) findViewById(R.id.save);

    }
}
