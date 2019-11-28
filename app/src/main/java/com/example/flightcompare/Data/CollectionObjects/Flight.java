package com.example.flightcompare.Data.CollectionObjects;

import android.util.Pair;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.HashMap;

public class Flight implements Serializable {
    String airline;
    Timestamp arrive_time;
    Timestamp depart_time;
    Airport from_location;
    Airport to_location;
    String flight_num;
    HashMap<String, Integer> layovers;
    HashMap<Integer, Integer> bag_price;
    Long bag_num;
    Double flytime;


    public Flight(String airline, Timestamp arrive_time, Timestamp depart_time, Airport from_location, Airport to_location, String flight_num,
            /*HashMap<String, Integer> layovers, HashMap<Integer, Integer> bag_price,*/ Long bag_num, Double flytime) {
        this.airline = airline;
        this.arrive_time = arrive_time;
        this.depart_time = depart_time;
        this.from_location = from_location;
        this.to_location = to_location;
        this.flight_num = flight_num;
        /*this.layovers = layovers;
        this.bag_price = bag_price;*/
        this.bag_num = bag_num;
        this.flytime = flytime;
    }

    public String getAirline() {
        return airline;
    }

    public Timestamp getArrive_time() {
        return arrive_time;
    }

    public Timestamp getDepart_time() {
        return depart_time;
    }

    public Airport getFrom_location() {
        return from_location;
    }

    public Airport getTo_location() {
        return to_location;
    }

    public String getFlight_num() {
        return flight_num;
    }

    public HashMap<String, Integer> getLayovers() {
        return layovers;
    }

    public HashMap<Integer, Integer> getBag_price() {
        return bag_price;
    }

    public Long getBag_num() {
        return bag_num;
    }

    public Double getFlytime() {
        return flytime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airline='" + airline + '\'' +
                ", arrive_time='" + arrive_time + '\'' +
                ", depart_time='" + depart_time + '\'' +
                ", from_location='" + from_location + '\'' +
                ", to_location='" + to_location + '\'' +
                ", flight_num='" + flight_num + '\'' +
                ", layovers=" + layovers +
                ", bag_price=" + bag_price +
                ", bag_num=" + bag_num +
                ", flytime=" + flytime +
                '}';
    }
}
