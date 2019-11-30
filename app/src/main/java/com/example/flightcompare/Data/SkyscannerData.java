package com.example.flightcompare.Data;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SkyscannerData {
    public SkyscannerData(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/SFO-sky/ORD-sky/2019-12-01?inboundpartialdate=2019-12-11")
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2ec393d59cmsh5062190befbdb3bp196e5cjsnb23409bd4256")
                .build();

        try {
            Response response = client.newCall(request).execute();
        }
        catch (IOException e){
            Log.e("Exception on Skyscanner request", e.getMessage());
        }
    }
}
