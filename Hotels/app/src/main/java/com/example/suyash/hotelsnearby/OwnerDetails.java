package com.example.suyash.hotelsnearby;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by suyash on 7/4/18.
 */

@IgnoreExtraProperties
public class OwnerDetails {

    public String name;
    public String email;
    public String hotel_name;
    public String hotel_address;
    public String opening_time;
    public String ending_time;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public OwnerDetails()
    {

    }

    public OwnerDetails(String name, String email, String hotel_name, String hotel_address, String opening_time, String ending_time)
    {
        this.name = name;
        this.email = email;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.opening_time = opening_time;
        this.ending_time = ending_time;
    }

}
