package com.example.suyash.hotelsnearby;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by suyash on 7/4/18.
 */

@IgnoreExtraProperties
public class OwnerDetails implements Serializable {

    public String name;
    public String email;
    public String hotel_name;
    public String hotel_address;
    public String opening_time;
    public String ending_time;
    public String uid;

    public OwnerDetails()
    {

    }

    public OwnerDetails(String email, String hotel_name, String hotel_address, String opening_time, String ending_time, String uid)
    {
        this.email = email;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.opening_time = opening_time;
        this.ending_time = ending_time;
        this.uid = uid;
    }

    public String getName()
    {
        return hotel_name;
    }

    public String getHotel_address()
    {
        return hotel_address;
    }

    public String getOpening_time()
    {
        return opening_time;
    }

    public String getEnding_time()
    {
        return ending_time;
    }

    public String getUid()
    {
        return uid;
    }

}
