package com.example.suyash.hotelsnearby;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;

import java.util.ArrayList;

public class Owner extends AppCompatActivity  {

    ArrayList<OwnerDetails> ownerDetails;
    ArrayList<String> arr;
    OwnersAdapter ownersAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        ownerDetails = new ArrayList<>();
        arr = new ArrayList<>();

        listView = (ListView) findViewById(R.id.owner_hotelssss);

        arr.add("A");
        arr.add("A");
        arr.add("A");
        arr.add("A");
        arr.add("A");
        arr.add("A");
        arr.add("A");

//        ListView listView1 = (ListView) findViewById(R.id.owner_hotelssss);
//        ArrayAdapter adapter = new ArrayAdapter(Owner.this, android.R.layout.simple_list_item_1, arr);
//        listView1.setAdapter(adapter);


//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        final FirebaseDatabase database;
        DatabaseReference hotelRef;

        database = FirebaseDatabase.getInstance();
        hotelRef = database.getReference().child("hotels");

//        Log.v("In try", "In try");
//        DatabaseReference ownerRef = hotelRef.child("owner");

        hotelRef.orderByChild("email").equalTo(MainActivity.email_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    try
                    {
                        Log.v("In try", "In try");
                        String name = datas.child("hotel_name").getValue().toString();
                        String hotel_address = datas.child("hotel_address").getValue().toString();
                        String ending_time = datas.child("ending_time").getValue().toString();
                        String opening_time = datas.child("opening_time").getValue().toString();
                        String uid = datas.child("uid").getValue().toString();
//                        Toast.makeText(Owner.this, uid, Toast.LENGTH_LONG).show();
                        OwnerDetails obj = new OwnerDetails(MainActivity.email_id, name, hotel_address, ending_time, opening_time, uid);
                        ownerDetails.add(obj);
                        Log.v("AAAAAAAAAAAAAAAAA", name + "XX" + hotel_address + "XX" + ending_time + "XX" + opening_time + "ZZ" + ownerDetails.size());
                        ownersAdapter = new OwnersAdapter(Owner.this, ownerDetails);
                        listView.setAdapter(ownersAdapter);
                    }
                    catch (Exception e)
                    {
                        Log.v("Exception", e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        Log.v("AAAAAAAAAAAAAAAAA", ownerDetails.size() + "");
//
//        for(int i = 0; i < ownerDetails.size(); i++)
//        {
//            OwnerDetails owner = ownerDetails.get(i);
//            Log.v("QQQQQQQQQQ", owner.getName() + "   " + owner.getHotel_address());
//        }

//        OwnersAdapter ownersAdapter= new OwnersAdapter(Owner.this, ownerDetails);
//
//        // finding the listView and setting the adapter to it
//        ListView listView = (ListView) findViewById(R.id.owner_hotelssss);
//        listView.setAdapter(ownersAdapter);

//        Toast.makeText(Owner.this, ownerDetails.size() + " ", Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Log.v("SSSSSSSSSSSSSS", "SSSSSSSSSSSS");
                OwnerDetails owner = (OwnerDetails) parent.getItemAtPosition(position);

                Intent intent = new Intent(Owner.this, OwnerHotelDetails.class);
                intent.putExtra("Hotel", owner);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.owner_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                signOut();
                return true;
            case R.id.add_hotel:
                Intent intent = new Intent(Owner.this, AddHotel.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        MainActivity.mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Owner.this, "Logged Out", Toast.LENGTH_SHORT).show();
                        Intent AfterLoginIntent = new Intent(Owner.this, MainActivity.class);
                        startActivity(AfterLoginIntent);
                        finish();
                    }
                });
    }
}

