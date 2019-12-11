package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.Data.JsonObjects.FlightLeg;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.Singleton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Objects;

public class SavedFlights extends Fragment {
    public final int MAX_TO_COMPARE = 3;
    //    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Trip> savedList;
    private ArrayList<MaterialCardView> savedCards;
    LinearLayout cardContainer;
    private int cardsChecked;

    private TextView noSavedFlightsText;
    private TextView selectSavedFlightsText;
    View divider;

    public SavedFlights() {}

    public static SavedFlights newInstance() {
        SavedFlights fragment = new SavedFlights();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedCards = new ArrayList<>();
        cardsChecked = 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_flights, container, false);

        noSavedFlightsText = view.findViewById(R.id.textview_no_saved_flights);
        selectSavedFlightsText = view.findViewById(R.id.textview_select_saved_flights);
        cardContainer = view.findViewById(R.id.savedCardContainer);
        divider = view.findViewById(R.id.saved_flights_divider);

        ArrayList<Trip> resultsList = Singleton.getTrips();
        System.out.println("Results list size: " + resultsList.size());

        reInflateList();

        /* RECYCLER VIEW STUFF
        noSavedFlightsText = view.findViewById(R.id.textview_no_saved_flights);

        mRecyclerView = view.findViewById(R.id.saved_flights_recycler_view);
        savedList = new ArrayList<>();

        // maybe set the list of results by making an API call?
        // will just add dummy data right now
        if(savedList.size() != 0) {
            noSavedFlightsText.setVisibility(View.INVISIBLE);
        }
        else {
            noSavedFlightsText.setVisibility(View.VISIBLE);
        }


        mLayoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyFlightResultRecyclerViewAdapter(savedList);
        mRecyclerView.setAdapter(mAdapter);
         */

        return view;
    }

    public boolean initCheckedCard(Trip t) {
        ArrayList<Trip> toCompare = Singleton.getComparedTrips();
        System.out.println("Compared trips size: " + toCompare.size());
        for(Trip trip : toCompare) {
            if(t.equals(trip)) {
                return true;
            }
        }
        return false;
    }

    public void checkForMaxCardsSelected() {
        if(cardsChecked == MAX_TO_COMPARE) {
            for(MaterialCardView m : savedCards) {
                if(!m.isChecked()) {
                    m.setCheckable(false);
                }
            }
        }
        else if(cardsChecked < MAX_TO_COMPARE) {
            for(MaterialCardView m : savedCards) {
                m.setCheckable(true);
            }
        }
    }

    public void reInflateList() {
        savedList = Singleton.getSavedTrips();
        System.out.println("Saved list size: " + savedList.size());
        for(Trip t : savedList) {
            System.out.println(t.toString());
        }

        if(savedList.size() != 0) {
            noSavedFlightsText.setVisibility(View.INVISIBLE);
            selectSavedFlightsText.setVisibility(View.VISIBLE);
        }
        else {
            noSavedFlightsText.setVisibility(View.VISIBLE);
            selectSavedFlightsText.setVisibility(View.INVISIBLE);
        }

        inflateSavedList();
    }

    public void inflateSavedList() {
        cardContainer.removeAllViews();
        savedCards.clear();
        for(Trip t : savedList) {
            inflateTripCard(t);
        }
    }

    public void inflateTripCard(final Trip t) {
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        final MaterialCardView cardView;
        boolean oneway = false;
        // see if the flight is one way or not
        FlightLeg outboundLeg = t.getOutboundLeg();
        FlightLeg inboundLeg = t.getInboundLeg();
        if(inboundLeg.getDepartureDate() == null) {
            oneway = true;
            System.out.println("\tONE WAY FLIGHT");
            cardView = (MaterialCardView) inflater.inflate(R.layout.saved_flights_list_item_one_way, cardContainer,false);
        }
        else {
            cardView = (MaterialCardView) inflater.inflate(R.layout.saved_flights_list_item, cardContainer,false);
        }

        cardView.setChecked(initCheckedCard(t));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                if(cardView.isChecked()) {
                    cardView.setChecked(false);
                    cardsChecked--;
                    Singleton.removeComparedTrip(t);
                }
                else if(cardsChecked < MAX_TO_COMPARE) {
                    cardView.setChecked(true);
                    cardsChecked++;
                    Singleton.addComparedTrip(t);
                }
                else {
                    Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, "Can only select up to 3", duration);
                    toast.show();
                }
                checkForMaxCardsSelected();
            }
        });
        ImageButton removeSavedButton = cardView.findViewById(R.id.remove_saved_button);

        ImageView outgoingLegAirlineImage = cardView.findViewById(R.id.saved_flights_depart_flight_airline_img);
        TextView outgoingLegDepartureAirport = cardView.findViewById(R.id.saved_flights_depart_airport_text);
        TextView outgoingLegDestinationAirport = cardView.findViewById(R.id.saved_flights_dest_airport_text);
        TextView outgoingLegDepartTime = cardView.findViewById(R.id.saved_flights_depart_time_text);
        TextView outgoingLegDuration = cardView.findViewById(R.id.saved_flights_depart_duration_text);
        TextView outgoingLegDate = cardView.findViewById(R.id.saved_flights_depart_date_text);

        if(!oneway) {
            ImageView incomingLegAirlineImage = cardView.findViewById(R.id.saved_flights_return_flight_airline_img);
            TextView incomingLegDepartureAirport = cardView.findViewById(R.id.saved_flights_return_from_airport_text);
            TextView incomingLegDestinationAirport = cardView.findViewById(R.id.saved_flights_return_to_airport_text);
            TextView incomingLegDepartTime = cardView.findViewById(R.id.saved_flights_return_time_text);
            TextView incomingLegDuration = cardView.findViewById(R.id.saved_flights_return_duration_text);
            TextView incomingLegDate = cardView.findViewById(R.id.saved_flights_return_date_text);

            incomingLegAirlineImage.setImageResource(Singleton.getAirlineImage(inboundLeg.getCarrier()));
            incomingLegDepartureAirport.setText(inboundLeg.getOriginId());
            incomingLegDestinationAirport.setText(inboundLeg.getDestinationId());
            incomingLegDepartTime.setText(parseFlightTime(inboundLeg));
            incomingLegDuration.setText(inboundLeg.getFlightDuration());
            incomingLegDate.setText(parseDate(inboundLeg));
        }

        TextView price = cardView.findViewById(R.id.saved_flights_price_text);

        System.out.println("ADDING A SAVED CARD");
        outgoingLegAirlineImage.setImageResource(Singleton.getAirlineImage(outboundLeg.getCarrier()));
        outgoingLegDepartureAirport.setText(outboundLeg.getOriginId());
        outgoingLegDestinationAirport.setText(outboundLeg.getDestinationId());
        outgoingLegDepartTime.setText(parseFlightTime(outboundLeg));
        outgoingLegDuration.setText(outboundLeg.getFlightDuration());
        outgoingLegDate.setText(parseDate(outboundLeg));

        String priceText = "$" + t.getPrice().toString();
        price.setText(priceText);

        removeSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // need to remove the flight from the saved list
                deleteTrip(view, t, cardView);
            }
        });
        savedCards.add(cardView);
        cardContainer.addView(cardView);
    }

    private void deleteTrip(View v, Trip t, MaterialCardView cardView) {
        // remove the flight from the view
        cardContainer.removeView(v);
        // need to actually remove it from the saved list though
        Singleton.removeSavedTrip(t);
        // and if it was checked, remove from the compare list
        if(cardView.isChecked()) {
            Singleton.removeComparedTrip(t);
            cardsChecked--;
            checkForMaxCardsSelected();
        }
        reInflateList();
    }

    private String parseDate(FlightLeg flightLeg) {
        String departDate = flightLeg.getDepartureDate();
        //yyyy-mm-ddThh:mm:ss
        Integer year = Integer.parseInt(departDate.substring(0, departDate.indexOf("-")));
        Integer month = Integer.parseInt(departDate.substring(departDate.indexOf("-") + 1, departDate.indexOf("-", departDate.indexOf("-") + 1)));
        Integer day = Integer.parseInt(departDate.substring(departDate.indexOf("-", departDate.indexOf("-") + 1), departDate.indexOf("T")));
        return (month.toString() + "/" + day.toString() + "/" + year.toString());
    }

    private String parseFlightTime(FlightLeg flightLeg) {
        String departDate = flightLeg.getDepartureDate();
        String returnDate = flightLeg.getArrivalDate();
        //yyyy-mm-ddThh:mm:ss
        Integer hourOut = Integer.parseInt(departDate.substring(11, departDate.indexOf(":")));
        String minOut = departDate.substring(departDate.indexOf(":") + 1, departDate.indexOf(":", departDate.indexOf(":") + 1));
        boolean outPM = (hourOut > 12);
        Integer hourIn = Integer.parseInt(returnDate.substring(11, departDate.indexOf(":")));
        Integer minIn = Integer.parseInt(returnDate.substring(departDate.indexOf(":") + 1, departDate.indexOf(":", departDate.indexOf(":") + 1)));
        boolean inPM = (hourIn > 12);
        return ((hourOut > 12 ? hourOut - 12 : hourOut) + ":" + minOut + (outPM ? "PM" : "AM") +
                " - " + (hourIn > 12 ? hourIn - 12 : hourIn) + ":" + minIn + (inPM ? "PM" : "AM"));
    }

}
