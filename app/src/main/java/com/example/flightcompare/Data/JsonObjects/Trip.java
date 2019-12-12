package com.example.flightcompare.Data.JsonObjects;

import java.util.Objects;

public class Trip {
    private Integer Price;
    private FlightLeg OutboundLeg;
    private FlightLeg InboundLeg;
    private boolean saved;

    public Trip(Integer price, FlightLeg outboundLeg, FlightLeg inboundLeg) {
        Price = price;
        OutboundLeg = outboundLeg;
        outboundLeg.setFlightNumber(outboundLeg.generateRandomFlightNumber());
        InboundLeg = inboundLeg;
        inboundLeg.setFlightNumber(inboundLeg.generateRandomFlightNumber());
        saved = false;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public FlightLeg getOutboundLeg() {
        return OutboundLeg;
    }

    public void setOutboundLeg(FlightLeg outboundLeg) {
        OutboundLeg = outboundLeg;
    }

    public FlightLeg getInboundLeg() {
        return InboundLeg;
    }

    public void setInboundLeg(FlightLeg inboundLeg) {
        InboundLeg = inboundLeg;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Price=" + Price +
                ", OutboundLeg=" + OutboundLeg +
                ", InboundLeg=" + InboundLeg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip flight = (Trip) o;
        return Price.equals(flight.Price) &&
                OutboundLeg.equals(flight.OutboundLeg) &&
                Objects.equals(InboundLeg, flight.InboundLeg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Price, OutboundLeg, InboundLeg);
    }
}
