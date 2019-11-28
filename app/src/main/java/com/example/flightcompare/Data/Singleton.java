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
    private ArrayList<Flight> savedFlights;
    private ArrayList<Flight> comparedFlights;

    public static void init(){
        if(data == null){
            data = new Singleton();
            data._init();
        }
    }

    private void _init(){
        airports = new ArrayList<>();
        flights = new ArrayList<>();
        savedFlights = new ArrayList<>();
        comparedFlights = new ArrayList<>();
    }

    //*************//
    // AIRPORT DATA//
    //*************//

    public static void addAirport(Airport airport){
        data._addAirport(airport);
    }

    private void _addAirport(Airport airport){
        airports.add(airport);
        Log.d("Singleton", "Airports size: " + airports.size());
        Log.d("ADD airport", airport.toString());
    }

    public static ArrayList<Airport> getAirports(){
        return data._getAirports();
    }

    private ArrayList<Airport> _getAirports(){
        return airports;
    }

    public static Airport getAirportFromCode(String code){
        return data._getAirportFromCode(code);
    }

    private Airport _getAirportFromCode(String code){
        for(Airport a : airports){
            if(a.getAirport_code().equals(code)){
                return a;
            }
        }
        return null;
    }

    //*************//
    // FLIGHTS DATA//
    //*************//

    public static void addFlight(Flight flight){
        data._addFlight(flight);
    }

    private void _addFlight(Flight flight){
        flights.add(flight);
        data._addComparedFlight(flight);
        Log.d("Singleton", "Flights size: " + flights.size());
        Log.d("ADD flight", flight.toString());
    }

    //*********************//
    // MANAGE SAVED FLIGHTS//
    //*********************//
    public static ArrayList<Flight> getSavedFlights(){
        return data._getSavedFlights();
    }

    private ArrayList<Flight> _getSavedFlights(){
        return savedFlights;
    }

    public static void addSavedFlight(Flight flight){
        data._addSavedFlight(flight);
    }

    private void _addSavedFlight(Flight flight){
        savedFlights.add(flight);
    }

    //************************//
    // MANAGE COMPARED FLIGHTS//
    //************************//
    public static ArrayList<Flight> getComparedFlights(){
        return data._getComparedFlights();
    }

    private ArrayList<Flight> _getComparedFlights(){
        return comparedFlights;
    }

    public static boolean addComparedFlight(Flight flight){
        return data._addComparedFlight(flight);
    }

    private boolean _addComparedFlight(Flight flight){
        if(comparedFlights.size() < 3) {
            comparedFlights.add(flight);
            return true;
        }
        return false;
    }
}
