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
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Trip> savedList;
    private ArrayList<MaterialCardView> savedCards;
//    private ArrayList<MaterialCardView> checkedCards;
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
//        for(int i = 0; i < 3; i++) {
//            Singleton.addSavedTrip(resultsList.get(i));
//        }

        // get all saved flights

//        searchAgainButton = view.findViewById(R.id.search_again_button);
//        searchAgainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSearchClicked();
//            }
//        });

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

    public void checkForMaxCardsSelected() {
        if(cardsChecked == 3) {
            for(MaterialCardView m : savedCards) {
                if(!m.isChecked()) {
                    m.setCheckable(false);
                }
            }
        }
        else if(cardsChecked < 3) {
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
        for(Trip t : savedList) {
            inflateTripCard(t);
        }
    }

    public void inflateTripCard(final Trip t) {
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        final MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.saved_flights_list_item, cardContainer,false);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                if(cardView.isChecked()) {
                    cardView.setChecked(false);
                    cardsChecked--;
                }
                else if(cardsChecked < 3) {
                    cardView.setChecked(true);
                    cardsChecked++;
                    Singleton.addComparedTrip(t);
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
                deleteTrip(view, t);
            }
        });
//            if(cardView.getParent() != null) {
//                System.out.println("PARENT: " + cardView.getParent());
//                ((ViewGroup)cardView.getParent()).removeView(cardView); // <- fix
//                System.out.println("ERROR HERE");
//
//            }
//            else {
//                System.out.println("PARENT: " + cardView.getParent());
//            }
        savedCards.add(cardView);
        cardContainer.addView(cardView);
    }

    private void deleteTrip(View v, Trip t) {
        // remove the flight from the view
        cardContainer.removeView(v);
        // need to actually remove it from the saved list though
        Singleton.removeSavedTrip(t);
        reInflateList();
    }

}
