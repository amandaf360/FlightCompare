package com.example.flightcompare.Data.JsonObjects;

import android.widget.Filter;

import com.example.flightcompare.Data.Singleton;

import java.util.Locale;
import java.util.Objects;

public class FlightLeg {
    private String OriginId;
    private String DestinationId;
    private String Carrier;
    private String DepartureDate;
    private String ArrivalDate;
    private String FlightDuration;
    private String FlightNumber;

    public FlightLeg(String originId, String destinationId, String carrier, String departureDate, String arrivalDate, String flightDuration) {
        OriginId = originId;
        DestinationId = destinationId;
        Carrier = carrier;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
        FlightDuration = flightDuration;
        setFlightNumber(generateRandomFlightNumber());
        System.out.println(getFlightNumber());
    }

    public String getOriginId() {
        return OriginId;
    }

    public void setOriginId(String originId) {
        OriginId = originId;
    }

    public String getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(String destinationId) {
        DestinationId = destinationId;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getFlightDuration() {
        return FlightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        FlightDuration = flightDuration;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public String generateRandomFlightNumber() {
        String prefix = Singleton.getFlightNumPrefix(getCarrier());

        int randomNum = (int)(Math.random() * ((1000 - 1) + 1)) + 1;
        return(prefix + " " + String.format(Locale.US, "%03d", randomNum));
    }

    @Override
    public String toString() {
        return "FlightLeg{" +
                "OriginId='" + OriginId + '\'' +
                ", DestinationId='" + DestinationId + '\'' +
                ", Carrier='" + Carrier + '\'' +
                ", DepartureDate='" + DepartureDate + '\'' +
                ", ArrivalDate='" + ArrivalDate + '\'' +
                ", FlightDuration='" + FlightDuration + '\'' +
                ", FlightNumber='" + FlightNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightLeg)) return false;
        FlightLeg flightLeg = (FlightLeg) o;
        return Objects.equals(OriginId, flightLeg.OriginId) &&
                Objects.equals(DestinationId, flightLeg.DestinationId) &&
                Objects.equals(Carrier, flightLeg.Carrier) &&
                Objects.equals(DepartureDate, flightLeg.DepartureDate) &&
                Objects.equals(ArrivalDate, flightLeg.ArrivalDate) &&
                Objects.equals(FlightDuration, flightLeg.FlightDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OriginId, DestinationId, Carrier, DepartureDate, ArrivalDate, FlightDuration);
    }
}
