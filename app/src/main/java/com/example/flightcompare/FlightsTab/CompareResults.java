package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightcompare.MainActivity;
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
public class CompareResults extends Fragment {

    private OnCompareResultsSelectedListener mListener;

    public CompareResults() {}

    public static CompareResults newInstance() {
        CompareResults fragment = new CompareResults();
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
        if (context instanceof OnCompareResultsSelectedListener) {
            mListener = (OnCompareResultsSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompareResultsSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this.getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.comparator_menu, popup.getMenu());
        popup.show();
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
    public interface OnCompareResultsSelectedListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
        //void printString();
    }
}
