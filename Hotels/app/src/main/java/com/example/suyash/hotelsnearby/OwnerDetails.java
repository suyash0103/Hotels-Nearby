package com.example.suyash.hotelsnearby;

/**
 * Created by suyash on 7/4/18.
 */

public class OwnerDetails {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public OwnerDetails() {
    }

    public OwnerDetails(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
