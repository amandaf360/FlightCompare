package com.example.flightcompare.Data.JsonObjects;

import java.util.ArrayList;
import java.util.Objects;

public class Airline {
    String Name;
    ArrayList<Integer> BagPrice;

    public Airline(String name, ArrayList<Integer> bagPrice) {
        Name = name;
        BagPrice = bagPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Integer> getBagPrice() {
        return BagPrice;
    }

    public void setBagPrice(ArrayList<Integer> bagPrice) {
        BagPrice = bagPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline)) return false;
        Airline airline = (Airline) o;
        return Name.equals(airline.Name) &&
                Objects.equals(BagPrice, airline.BagPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, BagPrice);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "Name='" + Name + '\'' +
                ", BagPrice=" + BagPrice +
                '}';
    }
}
