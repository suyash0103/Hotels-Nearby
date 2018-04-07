package com.example.suyash.hotelsnearby;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by suyash on 7/4/18.
 */

@IgnoreExtraProperties
public class OwnerDetails {

    private String name;
    private String email;
    private String hotel_name;
    private String hotel_address;
    private String opening_time;
    private String ending_time;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public OwnerDetails()
    {

    }

    public OwnerDetails(String email, String hotel_name, String hotel_address, String opening_time, String ending_time)
    {
        this.email = email;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.opening_time = opening_time;
        this.ending_time = ending_time;
    }

}
