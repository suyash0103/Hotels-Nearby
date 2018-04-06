package com.example.suyash.hotelsnearby;

/**
 * Created by suyash on 6/4/18.
 */

public class Hotel {

    private String place_id;
    private Boolean open_now;
    private int price_level;
    private int rating;
    private String name;
    private String vicinity;

    public Hotel(String name, Boolean open_now, String place_id, int rating)
    {
        this.name = name;
        this.open_now = open_now;
        this.place_id = place_id;
        this.rating = rating;
    }

    public String getName()
    {
        return name;
    }

    public Boolean getOpen_now()
    {
        return open_now;
    }

}
