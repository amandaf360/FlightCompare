package com.example.flightcompare.Data;

import android.util.Log;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.Data.JsonObjects.Airline;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.SkyscannerObjects.Quote;
import com.example.flightcompare.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Singleton {
    static Singleton data;
    private ArrayList<Airport> airports;

    private ArrayList<Flight> flights;
    private ArrayList<Flight> savedFlights;
    private ArrayList<Flight> comparedFlights;
    private ArrayList<Quote> searchedQuotes;
    private LinkedHashMap<String, Boolean> comparators;

    private ArrayList<Trip> trips;
    private ArrayList<Airline> airlines;
    private ArrayList<Trip> searchedTrips;
    private ArrayList<Trip> savedTrips;
    private ArrayList<Trip> comparedTrips;
    private Map<String, Integer> airlineImages;
    private Map<String, String> flightNumPrefix;
    private Map<String, String> airportCodes;

    private ArrayList<String> searchData;

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
        savedTrips = new ArrayList<>();
        comparedTrips = new ArrayList<>();
        searchData = new ArrayList<>();

        // init airline images map
        airlineImages = new HashMap<>();
        airlineImages.put("American Airlines", R.drawable.aa_logo);
        airlineImages.put("Delta Air Lines", R.drawable.delta_logo);
        airlineImages.put("Hawaiian Airlines", R.drawable.hawaii_logo);

        // init airline flight prefixes
        flightNumPrefix = new HashMap<>();
        flightNumPrefix.put("American Airlines", "AA");
        flightNumPrefix.put("Delta Air Lines", "DL");
        flightNumPrefix.put("Hawaiian Airlines", "HA");

        // init airport codes
        airportCodes = new HashMap<>();
        airportCodes.put("Salt Lake City", "SLC");
        airportCodes.put("Los Angeles", "LAX");
        airportCodes.put("San Francisco", "SFO");

        comparators.put("PRICE", true);
        comparators.put("FLYTIME", true);
        comparators.put("BAGS", true);
        comparators.put("LAYOVER TIME", true);
        comparators.put("DEST/ORIGIN", true);
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
        Log.d("Singleton", "Flights size: " + flights.size());
        Log.d("ADD flight", flight.toString());
    }

    public static void addSearchedQuotes(Quote quote){
        data._addSearchedQuotes(quote);
    }

    private void _addSearchedQuotes(Quote quote){
        data.searchedQuotes.add(quote);
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

    private boolean _addComparedFlight(Flight flight) {
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

    //*******************************************************************//
    //                            JSON DATA                              //
    //*******************************************************************//


    // Airlines

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

    // Trips

    public static void addTrip(Trip trip){
        data._addTrip(trip);
    }

    private void _addTrip(Trip trip){
        trips.add(trip);
        Log.d("Singleton", "Trips size: " + trips.size());
        Log.d("ADD trip", trip.toString());
    }

    public static ArrayList<Trip> getTripsForSearch(String originAirportCode, String destAirportCode,
                                                    String departDate, String returnDate) {
        return data._getTripsForSearch(originAirportCode, destAirportCode, departDate, returnDate);
    }

    private ArrayList<Trip> _getTripsForSearch(String originAirportCode, String destAirportCode,
                                               String departDate, String returnDate) {
        System.out.println("GETTING TRIPS FOR SEARCH");
        setSearchData(originAirportCode, destAirportCode, departDate, returnDate);
        ArrayList<Trip> matchList = new ArrayList<>();
        ArrayList<Trip> trips = getTrips();
        System.out.println("Trips size: " + trips.size());
        for(Trip t : trips) {
            // first check the from/to
            System.out.println("outbound leg id: " + t.getOutboundLeg().getOriginId());
            System.out.println("outbound leg search: " + originAirportCode);
            System.out.println("dest leg id: " + t.getInboundLeg().getOriginId());
            System.out.println("dest leg search: " + destAirportCode);
            if(t.getOutboundLeg().getOriginId().equals(originAirportCode) &&
                t.getOutboundLeg().getDestinationId().equals(destAirportCode)) {
                System.out.println("FROM/TO MATCH");
                // check departure date
                String departureDate = t.getOutboundLeg().getDepartureDate();
                if(parseDate(departDate).equals(departureDate.substring(0, departureDate.indexOf("T")))) {
                    System.out.println("DEPART DATE MATCHES");
                    // check return date
                    if(returnDate.length() > 0) {
                        String returningDate = t.getInboundLeg().getDepartureDate();
                        System.out.println("RETURNING DATE MATCHES");
                        if(returningDate != null && parseDate(returnDate).equals(returningDate.substring(0, returningDate.indexOf("T")))) {
                            matchList.add(t);
                        }
                    }
                    // check to see if it's only a one-way flight
                    else if(t.getInboundLeg().getDepartureDate() == null){
                        matchList.add(t);
                    }
                }
            }
        }

        for(Trip t : matchList) {
            System.out.println(t.toString());
        }
        return matchList;
    }

    public static ArrayList<Trip> getTrips(){
        return data._getTrips();
    }

    private ArrayList<Trip> _getTrips(){
        return trips;
    }

    public static void addSavedTrip(Trip trip){
        data._addSavedTrip(trip);
    }

    private void _addSavedTrip(Trip trip){
        savedTrips.add(trip);
        trip.setSaved(true);
        Log.d("ADD saved trip", trip.toString());
    }

    public static void removeSavedTrip(Trip trip){
        data._removeSavedTrip(trip);
    }

    private void _removeSavedTrip(Trip trip){
        savedTrips.remove(trip);
        trip.setSaved(false);
        Log.d("REMOVE saved trip", trip.toString());
    }

    public static ArrayList<Trip> getSavedTrips(){
        return data._getSavedTrips();
    }

    private ArrayList<Trip> _getSavedTrips(){
        return savedTrips;
    }

    public static boolean addComparedTrip(Trip trip){
        return data._addComparedTrip(trip);
    }

    private boolean _addComparedTrip(Trip trip){
        if(comparedTrips.size() < 3) {
            comparedTrips.add(trip);
            Log.d("ADD compared trip", trip.toString());
            return true;
        }
        Log.d("ADD compared trip", "Compared trips full: " + comparedTrips.size());
        return false;
    }

    public static boolean removeComparedTrip(Trip trip){
        return data._removeComparedTrip(trip);
    }

    private boolean _removeComparedTrip(Trip trip){
        comparedTrips.remove(trip);
        Log.d("REMOVE compared trip", trip.toString());
        return false;
    }

    public static ArrayList<Trip> getComparedTrips(){
        return data._getComparedTrips();
    }

    private ArrayList<Trip> _getComparedTrips(){
        return comparedTrips;
    }

    private String parseDate(String date) {
        String day;
        String month;
        String year;
        int beginIndex = 0;
        int currentSpot = date.indexOf("/");

        month = date.substring(beginIndex, currentSpot);
        beginIndex = currentSpot + 1;
        currentSpot = date.indexOf("/", beginIndex);
        day = date.substring(beginIndex, currentSpot);
        beginIndex = currentSpot + 1;
        year = date.substring(beginIndex);

        return(year + "-" + month + "-" + day);

    }

    public static Integer getAirlineImage(String airline){
        return data._getAirlineImage(airline);
    }

    private Integer _getAirlineImage(String airline){
        return airlineImages.get(airline);
    }

    public static String getAirportCode(String airline) {
        return data._getAirportCode(airline);
    }

    public String _getAirportCode(String airline) {
        return airportCodes.get(airline);
    }

    public static String getFlightNumPrefix(String airline){
        return data._getFlightNumPrefix(airline);
    }

    private String _getFlightNumPrefix(String airline){
        return flightNumPrefix.get(airline);
    }

    public static ArrayList<String> getSearchData(){
        return data._getSearchData();
    }

    private ArrayList<String> _getSearchData(){
        return searchData;
    }

    public static void setSearchData(String originAirportCode, String destAirportCode,
                                     String departDate, String returnDate){
        data._setSearchData(originAirportCode, destAirportCode, departDate, returnDate);
    }

    private void _setSearchData(String originAirportCode, String destAirportCode,
                                String departDate, String returnDate){
        searchData.add(originAirportCode);
        searchData.add(destAirportCode);
        searchData.add(departDate);
        searchData.add(returnDate);
    }

    public static void clearSearchData() {
        data._clearSearchData();
    }

    private void _clearSearchData() {
        searchData.clear();
    }
}
