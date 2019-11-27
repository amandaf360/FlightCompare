package com.example.flightcompare.Data;

import android.util.Log;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirestoreData {
    private FirebaseFirestore db;

    public FirestoreData(){
        db = FirebaseFirestore.getInstance();
    }



    public void queryAirports(){
        db.collection("Airports")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String city = (String) document.get("city");
                                String country = (String) document.get("country");
                                String region = (String) document.get("region");
                                String aiport_code = document.getId();

                                Singleton.addAirport(new Airport(city, region, country, aiport_code));
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void setAirports(){
        db.collection("Airports").document("BWI").set(new Airport("Baltimore", "Maryland", "United States", "BWI"));
        db.collection("Airports").document("BIL").set(new Airport("Billings", "Maryland", "United States", "BWI"));
    }

    public void queryFlights(){
        Log.d("Querying flights", "Were here");
        db.collection("Flights")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("Querying flights", "Was Successful");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Querying flights", "Getting a result");
                                String airline = (String) document.get("airline");
                                String arrive_time = (String) document.get("arrive_time");
                                String depart_time = (String) document.get("depart_time");
                                DocumentReference from_location = (DocumentReference) document.get("from_location");
                                DocumentReference to_location = (DocumentReference) document.get("to_location");
                                String flight_num = (String) document.get("flight_num");
                                Long bag_num = (Long) document.get("bag_num");
                                Double flytime = (Double) document.get("flytime");
                                Flight flight = new Flight(airline, arrive_time, depart_time, from_location.getId(), to_location.getId(),
                                        flight_num, bag_num, flytime);
                                Singleton.addFlight(flight);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
