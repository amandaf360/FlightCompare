package com.example.flightcompare.Data;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.flightcompare.Data.JsonObjects.*;
import com.example.flightcompare.Services.JsonEncoderService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class JsonData {
    ArrayList<Trip> possibleFlights;
    ArrayList<Airline> possibleAirlines;
    public JsonData() {
        possibleFlights =new ArrayList<>();
        possibleAirlines = new ArrayList<>();
    }

    public void readJson(){
        JsonParser parser = new JsonParser();
        JsonObject flightJson;
        JsonObject airlineJson;
        JsonEncoderService encoder = new JsonEncoderService();
        Log.d("JSON Data", "Trying to read json file");
        try {
//            Object json = parser.parse(new FileReader("c:\\Users/Colin/AndroidStudioProjects/FlightCompare/app/src/main/assets"));
            //String jsonString = encoder.fileToString("/Users/Colin/AndroidStudioProjects/FlightCompare/app/json/flights.json");
            flightJson = (JsonObject) parser.parse(JsonStrings.flightJsonData);
            airlineJson = (JsonObject) parser.parse(JsonStrings.airlineJsonData);
        }
        catch (Exception e){
            Log.d("JSON Data", "Reading json file failed " + e.getMessage());
            return;
        }
        JsonArray flights = flightJson.getAsJsonArray("flights");

        for(int i = 0; i < flights.size(); i++){
            Log.d("JSON parsing flights", "Parsing object " + i + " " + flights.get(i).toString());
            JsonObject object = flights.get(i).getAsJsonObject();
            String price = object.get("Price").getAsString();
            Log.d("JSON outbound", object.get("OutboundLeg").toString());
            Log.d("JSON inbound", object.get("InboundLeg").toString());
            FlightLeg outbound = (FlightLeg) encoder.stringToObject(object.get("OutboundLeg").toString(), FlightLeg.class);
            FlightLeg inbound = (FlightLeg) encoder.stringToObject(object.get("InboundLeg").toString(), FlightLeg.class);
            Trip newRoute = new Trip(Integer.parseInt(price), outbound, inbound);
            possibleFlights.add(newRoute);
            Singleton.addTrip(newRoute);
        }
        Log.d("JSON flights done", "Possible routes size: " + possibleFlights.size());

        JsonArray airlines = airlineJson.getAsJsonArray("airlines");

        for(int i = 0; i < airlines.size(); i++){
            Log.d("JSON parsing airlines", "Parsing object " + i + " " + airlines.get(i).toString());
            JsonObject object = airlines.get(i).getAsJsonObject();
            String name = object.get("Name").getAsString();
            ArrayList<Integer> bagPrices = (ArrayList<Integer>) encoder.stringToObject(object.get("BagPrice").toString(), ArrayList.class);
            Airline airline = new Airline(name, bagPrices);
            possibleAirlines.add(airline);
            Singleton.addAirline(airline);
        }
        Log.d("JSON airlines done", "Possible airlines size: " + possibleAirlines.size());
    }

}
