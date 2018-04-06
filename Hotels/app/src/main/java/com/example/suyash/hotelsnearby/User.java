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
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.Manifest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User extends AppCompatActivity implements LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    final String TAG = "GPS";
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    GoogleApiClient gac;
    LocationRequest locationRequest;

    private String API_KEY = "AIzaSyDGhLYLcHRH-Hpt0WfoVn9vdKXrnKkDPd4";
    private String latitude, longitude;

    int index = 0;

    ArrayList<Hotel> hotelsArray;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        hotelsArray = new ArrayList<>();

        isGooglePlayServicesAvailable();

        if (!isLocationEnabled())
            showAlert();

        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        gac = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

//        sendLocation();

        // setting up the array adapter

    }

    public void sendLocation()
    {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=500&type=restaurant&key=" + API_KEY;
        Log.v("BBBBBBBBB", latitude + "XXX" + longitude);
//        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=13.0066525,74.7966876&radius=20000&type=restaurant&key=AIzaSyDGhLYLcHRH-Hpt0WfoVn9vdKXrnKkDPd4";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("AAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA");
                        Log.d("Response", response.toString());
                        try {
                            JSONArray obj = response.getJSONArray("results");
                            Log.v("CCCCCCCCCCC", obj.toString());
                            String plc1, plc2;
                            Log.v("Length", obj.length() + "");
                            for(int i = 0; i < obj.length(); i++)
                            {
                                JSONObject obj1 = obj.getJSONObject(i);
                                String name, place_id, vicinity;
                                int price_level, rating;
                                Boolean open_now;
                                name = obj1.getString("name");
                                place_id = obj1.getString("place_id");
                                vicinity = obj1.getString("vicinity");

                                JSONObject geometry = obj1.getJSONObject("geometry");
                                JSONObject location = geometry.getJSONObject("location");
                                String lat = location.getString("lat");
                                String lng = location.getString("lng");
//                                Log.v("DDDDDDDDDDDDD", latitude + "XXXXXX" + longitude);

                                double earthRadius = 3958.75;
                                double dLat = Math.toRadians(Double.parseDouble(lat) - Double.parseDouble(latitude));
                                double dLng = Math.toRadians(Double.parseDouble(lng) - Double.parseDouble(longitude));
                                double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                                        Math.cos(Math.toRadians(Double.parseDouble(latitude))) * Math.cos(Math.toRadians(Double.parseDouble(lat))) *
                                                Math.sin(dLng/2) * Math.sin(dLng/2);
                                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                                double dist = earthRadius * c;
                                Log.v("DDDDDDDDDDDDD", dist + "");

                                Hotel hotel = new Hotel(name, place_id, vicinity, lat, lng, dist);
                                hotelsArray.add(hotel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        HotelAdapter itemsAdapter= new HotelAdapter(User.this, hotelsArray);

                        // finding the listView and setting the adapter to it
                        ListView listView = (ListView) findViewById(R.id.hotel_list);
                        listView.setAdapter(itemsAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                                Log.v("SSSSSSSSSSSSSS", "SSSSSSSSSSSS");
                                Hotel hotel = (Hotel) parent.getItemAtPosition(position);

                                Intent intent = new Intent(User.this, HotelDetails.class);
                                intent.putExtra("Hotel", hotel);
                                startActivity(intent);
                            }
                        });

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );
        SingletonRequestQueue.getInstance(User.this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onStart() {
        gac.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        gac.disconnect();
        index = 0;
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            updateUI(location);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(User.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }
        Log.d(TAG, "onConnected");

        Location ll = LocationServices.FusedLocationApi.getLastLocation(gac);
        Log.d(TAG, "LastLocation: " + (ll == null ? "NO LastLocation" : ll.toString()));

        LocationServices.FusedLocationApi.requestLocationUpdates(gac, locationRequest, this);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(User.this, "Permission was granted!", Toast.LENGTH_LONG).show();
                    try {
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                gac, locationRequest, this);
                    } catch (SecurityException e) {
                        Toast.makeText(User.this, "SecurityException:\n" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(User.this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(User.this, "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
        Log.d("DDD", connectionResult.toString());
    }

    private void updateUI(Location loc) {
        index++;
        Log.d(TAG, "updateUI");
        latitude = Double.toString(loc.getLatitude());
        longitude = Double.toString(loc.getLongitude());
        if(index == 1)
            sendLocation();
//        Toast.makeText(User.this, Double.toString(loc.getLatitude()) + "  " + Double.toString(loc.getLongitude()), Toast.LENGTH_LONG).show();
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isGooglePlayServicesAvailable() {
        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.d(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        Log.d(TAG, "This device is supported.");
        return true;
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        MainActivity.mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(User.this, "Logged Out", Toast.LENGTH_SHORT).show();
                        Intent AfterLoginIntent = new Intent(User.this, MainActivity.class);
                        startActivity(AfterLoginIntent);
                        finish();
                    }
                });
    }
}
