package com.example.flightcompare.Data.JsonObjects;

import java.util.Objects;

public class FlightLeg {
    private String OriginId;
    private String DestinationId;
    private String Carrier;
    private String DepartureDate;
    private String ArrivalDate;

    public FlightLeg(String originId, String destinationId, String carrier, String departureDate, String arrivalDate) {
        OriginId = originId;
        DestinationId = destinationId;
        Carrier = carrier;
        DepartureDate = departureDate;
        ArrivalDate = arrivalDate;
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

    @Override
    public String toString() {
        return "FlightLeg{" +
                "OriginId='" + OriginId + '\'' +
                ", DestinationId='" + DestinationId + '\'' +
                ", Carrier='" + Carrier + '\'' +
                ", DepartureDate='" + DepartureDate + '\'' +
                ", ArrivalDate='" + ArrivalDate + '\'' +
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
                Objects.equals(ArrivalDate, flightLeg.ArrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OriginId, DestinationId, Carrier, DepartureDate, ArrivalDate);
    }
}
