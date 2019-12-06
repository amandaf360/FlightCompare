package com.example.flightcompare.Data;

import android.util.Log;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.Data.JsonObjects.Airline;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.SkyscannerObjects.Quote;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Singleton {
    static Singleton data;
    private ArrayList<Airport> airports;

    private ArrayList<Flight> flights;
    private ArrayList<Flight> savedFlights;
    private ArrayList<Flight> comparedFlights;
    private ArrayList<Trip> trips;
    private ArrayList<Airline> airlines;
    private ArrayList<Quote> searchedQuotes;
    private LinkedHashMap<String, Boolean> comparators;

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
        comparators = new LinkedHashMap<>();
        trips = new ArrayList<>();
        airlines = new ArrayList<>();
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

    public static void addAirline(Airline airline){
        data._addAirline(airline);
    }

    private void _addAirline(Airline airline){
        airlines.add(airline);
        Log.d("Singleton", "Airlines size: " + airlines.size());
        Log.d("ADD trip", airline.toString());
    }

    public static ArrayList<Airline> getAirlines(){
        return data._getAirlines();
    }

    private ArrayList<Airline> _getAirlines(){
        return airlines;
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

    public static void addSearchedQuotes(Quote quote){
        data._addSearchedQuotes(quote);
    }

    private void _addSearchedQuotes(Quote quote){
        data.searchedQuotes.add(quote);
    }

    public static void addTrip(Trip trip){
        data._addTrip(trip);
    }

    private void _addTrip(Trip trip){
        trips.add(trip);
        Log.d("Singleton", "Trips size: " + trips.size());
        Log.d("ADD trip", trip.toString());
    }

    public static ArrayList<Trip> getTrips(){
        return data._getTrips();
    }

    private ArrayList<Trip> _getTrips(){
        return trips;
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

    //**********************//
    // COMPARE RESULTS PAGE //
    //**********************//

    public static void setComparators(String comparator, Boolean value){
        data._setComparators(comparator, value);
    }

    private void _setComparators(String comparator, Boolean value){
        comparators.put(comparator, value);
    }

    public static LinkedHashMap<String, Boolean> getComparators(){
        return data._getComparators();
    }

    private LinkedHashMap<String, Boolean> _getComparators(){
        return comparators;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
