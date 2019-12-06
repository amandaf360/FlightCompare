package com.example.flightcompare.Data;

import android.util.Log;

import com.example.flightcompare.Data.SkyscannerObjects.Quote;
import com.example.flightcompare.Services.JsonEncoderService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SkyscannerData {
    OkHttpClient client;
    public SkyscannerData(){
        client = new OkHttpClient();
    }

    // outboundDate must be YYYY-MM-DD
    public void browseOneWayTrips(String origin, String destination, String outboundDate){
        ArrayList<Quote> possibleRoutes = new ArrayList<>();
        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/" +
                        origin + "-sky/" + destination + "-sky/" + outboundDate)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2ec393d59cmsh5062190befbdb3bp196e5cjsnb23409bd4256")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.d("SKYSCANNER RESPONSE", "Converting to objects");
            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray quotes = jsonResponse.getJSONArray("Quotes");
            JsonEncoderService encoder = new JsonEncoderService();

            for(int i = 0; i < quotes.length(); i++){
                Log.d("Skyscanner parsing", "Parsing object " + i + " " + quotes.get(i).toString());
                Quote newRoute = (Quote) encoder.stringToObject(quotes.get(i).toString(), Quote.class);
                possibleRoutes.add(newRoute);
                Singleton.addSearchedQuotes(newRoute);
                Log.d("Skyscanner parsed", "Parsed object " + i + " " + newRoute.toString());
            }
            Log.d("Skyscanner done parsing", "Possible routes size: " + possibleRoutes.size());

        }
        catch (Exception e){
            Log.e(TAG, "HTTP Request failed: " + e.getMessage());
        }
    }

    public void browseRoundTrips(String origin, String destination, String outboundDate, String inboundDate){
        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/" +
                        origin + "-sky/" + destination + "-sky/" + outboundDate + "/" + inboundDate)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2ec393d59cmsh5062190befbdb3bp196e5cjsnb23409bd4256")
                .build();

        try {
            Response response = client.newCall(request).execute();
        }
        catch (IOException e){
            Log.e(TAG, "HTTP Request failed");
        }
    }
}
