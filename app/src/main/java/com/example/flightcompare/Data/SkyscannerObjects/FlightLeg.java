package com.example.flightcompare.Data.SkyscannerObjects;

import java.util.ArrayList;
import java.util.Date;

public class FlightLeg {
    ArrayList<Integer> CarrierIds;
    Integer OriginId;
    Integer DestinationId;
    Date DepartureDate;

    public FlightLeg(ArrayList<Integer> carrierIds, Integer originId, Integer destinationId, Date departureDate) {
        CarrierIds = carrierIds;
        OriginId = originId;
        DestinationId = destinationId;
        DepartureDate = departureDate;
    }
}
