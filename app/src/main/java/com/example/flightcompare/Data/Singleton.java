package com.example.flightcompare.Data;

import com.example.flightcompare.Data.CollectionObjects.Airport;

import java.util.List;

public class Singleton {
    List<Airport> airports;
    public Singleton(){}

    public void addAirport(Airport airport){
        airports.add(airport);
    }
}
