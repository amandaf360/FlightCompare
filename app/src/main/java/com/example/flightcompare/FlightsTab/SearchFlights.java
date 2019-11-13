package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightcompare.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFlights.OnSearchFlightsSelectedListener
 *} interface
 * to handle interaction events.
 * Use the {@link SearchFlights#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFlights extends Fragment {

    private OnSearchFlightsSelectedListener mListener;

    public SearchFlights() {}

    public static SearchFlights newInstance() {
        SearchFlights fragment = new SearchFlights();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_flights, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchFlightsSelectedListener) {
            mListener = (OnSearchFlightsSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSearchFlightsSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchFlightsSelectedListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
        //void printString();
    }
}
