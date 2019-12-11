package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.Singleton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Objects;

public class SavedFlights extends Fragment {
    public final int MAX_TO_COMPARE = 2;
    //    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Trip> savedList;
    private ArrayList<MaterialCardView> savedCards;
    LinearLayout cardContainer;
    private int cardsChecked;

    private TextView noSavedFlightsText;
    private TextView selectSavedFlightsText;

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

        ArrayList<Trip> resultsList = Singleton.getTrips();
        System.out.println("Results list size: " + resultsList.size());

        reInflateList();
        // save some of the flights
        for(int i = 0; i < 3; i++) {
            Singleton.addSavedTrip(resultsList.get(i));
        }

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
        final MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.saved_flights_list_item, cardContainer,false);

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
//        TextView outgoingLegAirline = cardView.findViewById(R.id.depart_flight_airline_img);
        TextView outgoingLegDepartureAirport = cardView.findViewById(R.id.saved_flights_depart_airport_text);
        TextView outgoingLegDestinationAirport = cardView.findViewById(R.id.saved_flights_dest_airport_text);
        TextView outgoingLegDepartTime;
        TextView outgoingLegDuration;
        TextView outgoingLegDate;

        TextView incomingLegAirline;
        TextView incomingLegDepartureAirport;
        TextView incomingLegDestinationAirport;
        TextView incomingLegDepartTime;
        TextView incomingLegDuration;
        TextView incomingLegDate;

        TextView price = cardView.findViewById(R.id.saved_flights_price_text);

//        String outgoingLegAirline;
//        String outgoingLegDepartureAirport;
//        String outgoingLegDestinationAirport;
//        String outgoingLegDepartTime;
//        String outgoingLegDuration;
//        String outgoingLegDate;
//
//        String incomingLegAirline;
//        String incomingLegDepartureAirport;
//        String incomingLegDestinationAirport;
//        String incomingLegDepartTime;
//        String incomingLegDuration;
//        String incomingLegDate;
//
//        int price;

        System.out.println("ADDING A SAVED CARD");
        outgoingLegDepartureAirport.setText(t.getOutboundLeg().getOriginId());
        outgoingLegDestinationAirport.setText(t.getOutboundLeg().getDestinationId());
//            outgoingLegAirline = f.getAirline();
//            outgoingLegDepartureAirport = f.getFrom_location().getAirport_code();
//            outgoingLegDestinationAirport = f.getTo_location().getAirport_code();
//            outgoingLegDepartTime = f.getDepart_time().toString();
//            outgoingLegDuration = f.getFlytime().toString();
////            outgoingLegDate = f.getDate().toString();
//
//            incomingLegAirline = f.getAirline();
//            incomingLegDepartureAirport = f.getFrom_location().getAirport_code();
//            incomingLegDestinationAirport = f.getTo_location().getAirport_code();
//            incomingLegDepartTime = f.getDepart_time().toString();
//            incomingLegDuration = f.getFlytime().toString();
////            incomingLegDate = f.getDate().toString();
//
        price.setText("$" + t.getPrice().toString());

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

}
