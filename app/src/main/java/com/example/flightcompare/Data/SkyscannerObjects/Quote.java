package com.example.flightcompare.Data.SkyscannerObjects;

import java.util.Date;

public class Quote {
    Integer QuoteId;
    Integer MinPrice;
    Boolean Direct;
    FlightLeg OutboundLeg;
    FlightLeg InboundLeg;
    String QuoteDateTime;

    // Round Trip
    public Quote(Integer quoteId, Integer minPrice, Boolean direct, FlightLeg outboundLeg, FlightLeg inboundLeg, String quoteDateTime) {
        QuoteId = quoteId;
        this.MinPrice = minPrice;
        this.Direct = direct;
        OutboundLeg = outboundLeg;
        InboundLeg = inboundLeg;
        QuoteDateTime = quoteDateTime;
    }

    // One-way trip
    public Quote(Integer quoteId, Integer minPrice, Boolean direct, FlightLeg outboundLeg, String quoteDateTime) {
        QuoteId = quoteId;
        this.MinPrice = minPrice;
        this.Direct = direct;
        OutboundLeg = outboundLeg;
        QuoteDateTime = quoteDateTime;
    }


}
