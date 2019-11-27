package com.example.flightcompare.Data.CollectionObjects;

import java.io.Serializable;

public class Airport implements Serializable {
    String city;
    String region;
    String country;
    String airport_code;

    public Airport(String city, String region, String country, String airport_code){
        this.city = city;
        this.region = region;
        this.country = country;
        this.airport_code = airport_code;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getAirport_code() {
        return airport_code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", airport_code='" + airport_code + '\'' +
                '}';
    }
}
