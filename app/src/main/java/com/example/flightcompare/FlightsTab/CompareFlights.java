package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.flightcompare.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CompareFlights extends Fragment {
    MaterialButton compareByBtn;

    public CompareFlights() {}

    public static CompareFlights newInstance() {
        CompareFlights fragment = new CompareFlights();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_compare, container, false);
        compareByBtn = v.findViewById(R.id.compareByBtn);
        compareByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopup(v);
            }
        });

        return v;
    }



    //-----------------------------------------------------------------//

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
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
    public interface OnCompareFlightsSelectedListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
        //void printString();
    }
}
