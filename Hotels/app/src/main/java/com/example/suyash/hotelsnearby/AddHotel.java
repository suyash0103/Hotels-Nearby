package com.example.suyash.hotelsnearby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHotel extends AppCompatActivity {

    EditText name, address, open_time, close_time;
    Button save;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("owners");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating new user node, which returns the unique key value
                // new user node would be /users/$userid/
                String userId = databaseReference.push().getKey();
                Log.v("AAAAAAAAAAAAAA", userId);

                // creating user object
                OwnerDetails ownerDetails = new OwnerDetails(MainActivity.email_id, name.getText().toString(), address.getText().toString(), open_time.getText().toString(), close_time.getText().toString());

                // pushing user to 'users' node using the userId
                databaseReference.child(userId).setValue(ownerDetails);
            }
        });

    }
}
