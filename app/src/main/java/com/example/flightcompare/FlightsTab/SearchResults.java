package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightcompare.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Object> resultsList;

    // data members
    String fromAirport;
    String destAirport;
    String departDate;
    String returnDate;
    Boolean roundTrip;

    public SearchResults() {
    }

    public static SearchResults newInstance(String from, String to, String departDate, String returnDate, Boolean roundTrip) {
        SearchResults fragment = new SearchResults();
        Bundle bundle = new Bundle();
        bundle.putString("fromAirport", from);
        bundle.putString("toAirport", to);
        bundle.putString("departDate", departDate);
        bundle.putString("returnDate", returnDate);
        bundle.putBoolean("roundTrip", roundTrip);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            fromAirport = getArguments().getString("fromAirport");
            destAirport = getArguments().getString("toAirport");
            departDate = getArguments().getString("departDate");
            returnDate = getArguments().getString("returnDate");
            roundTrip = getArguments().getBoolean("roundTrip");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flightresults, container, false);

        mRecyclerView = getActivity().findViewById(R.id.flight_results_recycler_view);
        resultsList = new ArrayList<>();

        // TODO: set the list of results by making an API call?
        // will just add dummy data right now



        mLayoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyFlightResultRecyclerViewAdapter(resultsList);
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

//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(DummyItem item);
//    }
}
