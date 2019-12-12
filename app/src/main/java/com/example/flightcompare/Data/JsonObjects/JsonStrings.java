package com.example.flightcompare.Data.JsonObjects;

import com.google.gson.JsonObject;

public class JsonStrings {
    JsonStrings() {}
    public static String flightJsonData = "{\n" +
            "  \"flights\": [\n" +

            // RT flights from SLC->LAX on 12/20/2019, coming back 1/20/2020
            "    {\n" +
            "      \"Price\": \"250\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"220\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T16:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T08:20:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T10:43:00\",\n" +
            "        \"FlightDuration\": \"2h 23m\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"273\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T11:43:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T13:20:00\",\n" +
            "        \"FlightDuration\": \"1h 37m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"180\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T18:35:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T19:55:00\",\n" +
            "        \"FlightDuration\": \"2h 20m\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"190\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      }\n" +
            "    },\n" +


            // one way flights from SLC->LAX leaving 12/20/2019
            "    {\n" +
            "      \"Price\": \"95\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"195\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"Hawaiian Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T12:35:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T14:35:00\",\n" +
            "        \"FlightDuration\": \"1h 55m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"103\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T5:32:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T6:37:00\",\n" +
            "        \"FlightDuration\": \"2h 5m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"105\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T10:05:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T11:06:00\",\n" +
            "        \"FlightDuration\": \"2h 1m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"145\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T21:02:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T21:47:00\",\n" +
            "        \"FlightDuration\": \"1h 45m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"123\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"LAX\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T11:00:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T11:47:00\",\n" +
            "        \"FlightDuration\": \"1h 47m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +


            // one way flights from LAX->SLC leaving 1/20/2020
            "    {\n" +
            "      \"Price\": \"127\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T11:43:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T14:43:00\",\n" +
            "        \"FlightDuration\": \"2h 0m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"127\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"Hawaiian Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T13:17:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T16:25:00\",\n" +
            "        \"FlightDuration\": \"2h 8m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"87\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"Hawaiian Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T06:10:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T08:55:00\",\n" +
            "        \"FlightDuration\": \"1h 45m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"Price\": \"107\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"LAX\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T20:05:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T22:35:00\",\n" +
            "        \"FlightDuration\": \"1h 30m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +

            // one way flights from SLC->SFO leaving 12/20/2019
            "    {\n" +
            "      \"Price\": \"97\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SLC\",\n" +
            "        \"DestinationId\": \"SFO\",\n" +
            "        \"Carrier\": \"Delta Air Lines\",\n" +
            "        \"DepartureDate\": \"2019-12-20T8:05:00\",\n" +
            "        \"ArrivalDate\": \"2019-12-20T8:30:00\",\n" +
            "        \"FlightDuration\": \"1h 25m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    },\n" +

            // one way flights from SFO->SLC leaving 1/20/2020
            "    {\n" +
            "      \"Price\": \"110\",\n" +
            "      \"OutboundLeg\": {\n" +
            "        \"OriginId\": \"SFO\",\n" +
            "        \"DestinationId\": \"SLC\",\n" +
            "        \"Carrier\": \"American Airlines\",\n" +
            "        \"DepartureDate\": \"2020-1-20T14:35:00\",\n" +
            "        \"ArrivalDate\": \"2020-1-20T16:46:00\",\n" +
            "        \"FlightDuration\": \"1h 11m\"\n" +
            "      },\n" +
            "      \"InboundLeg\": {}\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String airlineJsonData = "{\n" +
            "  \"airlines\": [\n" +
            "    {\n" +
            "      \"Name\": \"American Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        50,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Delta Air Lines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        50,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Alaska Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        50,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Southwest Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        0,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Hawaiian Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        0,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Frontier Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        50,\n" +
            "        50,\n" +
            "        75,\n" +
            "        75\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Spirit Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        40,\n" +
            "        60,\n" +
            "        75\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"JetBlue Airways\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        25,\n" +
            "        50,\n" +
            "        50\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"United Airlines\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        0,\n" +
            "        75,\n" +
            "        75\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"Name\": \"Allegiant Air\",\n" +
            "      \"BagPrice\": [\n" +
            "        0,\n" +
            "        40,\n" +
            "        40,\n" +
            "        40\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
