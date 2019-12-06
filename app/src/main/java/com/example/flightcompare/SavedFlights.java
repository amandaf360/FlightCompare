package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.Singleton;
import com.example.flightcompare.FlightsTab.MyFlightResultRecyclerViewAdapter;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class SavedFlights extends Fragment {
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Trip> savedList;
    private ArrayList<MaterialCardView> savedCards;
    LinearLayout cardContainer;

    private TextView noSavedFlightsText;
    private TextView selectSavedFlightsText;

    // data members
//    String fromAirport;
//    String destAirport;
//    String departDate;
//    String returnDate;
//    Boolean roundTrip;

    public SavedFlights() {}

    public static SavedFlights newInstance() {
        SavedFlights fragment = new SavedFlights();
//        Bundle bundle = new Bundle();
//        bundle.putString("fromAirport", from);
//        bundle.putString("toAirport", to);
//        bundle.putString("departDate", departDate);
//        bundle.putString("returnDate", returnDate);
//        bundle.putBoolean("roundTrip", roundTrip);
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            fromAirport = getArguments().getString("fromAirport");
//            destAirport = getArguments().getString("toAirport");
//            departDate = getArguments().getString("departDate");
//            returnDate = getArguments().getString("returnDate");
//            roundTrip = getArguments().getBoolean("roundTrip");
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnCompareResultsSelectedListener) {
//            mListener = (OnCompareResultsSelectedListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnCompareResultsSelectedListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_flights, container, false);

        noSavedFlightsText = view.findViewById(R.id.textview_no_saved_flights);
        selectSavedFlightsText = view.findViewById(R.id.textview_select_saved_flights);
        cardContainer = view.findViewById(R.id.savedCardContainer);

        //resultsList = Singleton.getTripsForSearch(fromAirport, destAirport, departDate, returnDate);
        ArrayList<Trip> resultsList = Singleton.getTrips();
        System.out.println("Results list size: " + resultsList.size());

        // save some of the flights
        for(int i = 0; i < 3; i++) {
            Singleton.addSavedTrip(resultsList.get(i));
        }

        // get all saved flights
        savedList = Singleton.getSavedTrips();
        System.out.println("Saved list size: " + savedList.size());

        if(savedList.size() != 0) {
            noSavedFlightsText.setVisibility(View.INVISIBLE);
            selectSavedFlightsText.setVisibility(View.VISIBLE);
        }
        else {
            noSavedFlightsText.setVisibility(View.VISIBLE);
            selectSavedFlightsText.setVisibility(View.INVISIBLE);
        }

        inflateSavedList();
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

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MyFlightResultRecyclerViewAdapter(DummyContent.ITEMS, mListener));
//        }
        return view;
    }

    public void inflateSavedList() {
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.fragment_saved_flights_list_item, cardContainer,false);

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

        TextView price;

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

        for(final Trip t : savedList) {
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
//            price = f.getPrice().intValue();

            removeSavedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // need to remove the flight from the saved list
                    deleteTrip(view, t);
                }
            });
            if(cardView.getParent() != null) {
                ((ViewGroup)cardView.getParent()).removeView(cardView); // <- fix
            }
            cardContainer.addView(cardView);
        }

    }

    private void deleteTrip(View v, Trip t) {
        // remove the flight from the view
        cardContainer.removeView(v);
        // need to actually remove it from the saved list though
        Singleton.removeSavedTrip(t);
    }

}
