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
    private String latitude;
    private String longitude;
    private Double distance;

    public Hotel(String name, String place_id, String vicinity, String latitude, String longitude, Double distance)
    {
        this.name = name;
        this.place_id = place_id;
        this.vicinity = vicinity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public String getName()
    {
        return name;
    }

    public String getVicinity()
    {
        return vicinity;
    }

}
