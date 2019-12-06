package com.example.flightcompare.Data.SkyscannerObjects;

public class Place {
    Integer PlaceId;
    String IataCode;
    String Name;
    String Type;
    String SkyscannerCode;
    String CityName;
    String CityId;
    String CountryName;

    public Place(Integer placeId, String iataCode, String name, String type, String skyscannerCode,
                 String cityName, String cityId, String countryName) {
        PlaceId = placeId;
        IataCode = iataCode;
        Name = name;
        Type = type;
        SkyscannerCode = skyscannerCode;
        CityName = cityName;
        CityId = cityId;
        CountryName = countryName;
    }
}
