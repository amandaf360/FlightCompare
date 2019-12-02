package com.example.flightcompare.Data;

import android.util.Log;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Singleton {
    static Singleton data;
    private ArrayList<Airport> airports;

    private ArrayList<Flight> flights;

    public static void init(){
        if(data == null){
            data = new Singleton();
            data._init();
        }
    }

    private void _init(){
        airports = new ArrayList<>();
        flights = new ArrayList<>();
    }

    public static void addAirport(Airport airport){
        data._addAirport(airport);
    }

    private void _addAirport(Airport airport){
        airports.add(airport);
        Log.d("Singleton", "Airports size: " + airports.size());
        Log.d("ADD airport", airport.toString());
    }

    public static void addFlight(Flight flight){
        data._addFlight(flight);
    }

    private void _addFlight(Flight flight){
        flights.add(flight);
        Log.d("Singleton", "Flights size: " + flights.size());
        Log.d("ADD flight", flight.toString());
    }

    public static ArrayList<Airport> getAirports(){
        return data._getAirports();
    }

    private ArrayList<Airport> _getAirports(){
        return airports;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
