package com.example.flightcompare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightcompare.Data.CollectionObjects.Airport;
import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.FlightsTab.MyFlightResultRecyclerViewAdapter;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class SavedFlights extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Object> savedList;

    // data members
//    String fromAirport;
//    String destAirport;
//    String departDate;
//    String returnDate;
//    Boolean roundTrip;

    public SavedFlights() {
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_flights, container, false);

//        searchAgainButton = view.findViewById(R.id.search_again_button);
//        searchAgainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSearchClicked();
//            }
//        });
        mRecyclerView = view.findViewById(R.id.saved_flights_recycler_view);
        savedList = new ArrayList<>();

        // TODO: set the list of results by making an API call?
        // will just add dummy data right now



        mLayoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyFlightResultRecyclerViewAdapter(savedList);
        mRecyclerView.setAdapter(mAdapter);

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

    private void onSearchClicked() {
        // TEMP STUFF
        // Flight(String airline, Long price, Timestamp arrive_time, Timestamp depart_time,
        // Airport from_location, Airport to_location, String flight_num,
        //            / Long bag_num, Double flytime)
        Airport airport = new Airport("Salt Lake City", "West", "USA", "SLC");

        Flight flight = new Flight("Delta", (long)90, Timestamp.now(), Timestamp.now(), airport, airport, "123", (long)1, 120.0);
        Flight flight2 = new Flight("Delta", (long)90, Timestamp.now(), Timestamp.now(), airport, airport, "123", (long)1, 120.0);
        ArrayList<Flight> pair = new ArrayList<>();
        pair.add(flight);
        pair.add(flight2);

        savedList.add(pair);
        mAdapter.notifyDataSetChanged();


        // END OF TEMP STUFF
//        searchAgainButton.setEnabled(false);
//
//        SearchFlights searchFlights = SearchFlights.newInstance();
//        ((MainActivity) Objects.requireNonNull(getActivity())).loadFragment(searchFlights);
    }
}
