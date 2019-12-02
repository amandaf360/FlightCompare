package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.flightcompare.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SearchFlights extends Fragment {

    // UI elements
    TextInputEditText fromAirportEdit;
    TextInputEditText toAirportEdit;
    TextInputEditText departDateEdit;
    TextInputEditText returnDateEdit;

    RadioButton roundtripRadio;
    RadioButton onewayRadio;

    MaterialButton searchButton;


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
        View v = inflater.inflate(R.layout.fragment_search_flights, container, false);

        fromAirportEdit = v.findViewById(R.id.from_airport_edit);
        toAirportEdit = v.findViewById(R.id.to_airport_edit);
        departDateEdit = v.findViewById(R.id.depart_date_edit);
        returnDateEdit = v.findViewById(R.id.return_date_edit);
        roundtripRadio = v.findViewById(R.id.roundtrip_radio);
        onewayRadio = v.findViewById(R.id.oneway_radio);
        searchButton = v.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClicked();
            }
        });

        RadioGroup tripButtonsGroup = v.findViewById(R.id.radio_group);


        searchButton.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // if oneway, disable the "return date" field
                if(onewayRadio.isChecked()) {
                    returnDateEdit.setEnabled(false);
                }
                else {
                    returnDateEdit.setEnabled(true);
                }

                // check to see if all fields are filled out
                if(fromAirportEdit.getText().toString().length() != 0 && toAirportEdit.getText().toString().length() != 0
                    && departDateEdit.getText().toString().length() != 0) {
                    if(roundtripRadio.isChecked() && returnDateEdit.getText().toString().length() != 0) {
                        searchButton.setEnabled(true);
                    }
                    else if(onewayRadio.isChecked()) {
                        searchButton.setEnabled(true);
                    }
                }
                else {
                    searchButton.setEnabled(false);
                }
            }
        };

        fromAirportEdit.addTextChangedListener(watcher);
        toAirportEdit.addTextChangedListener(watcher);
        departDateEdit.addTextChangedListener(watcher);
        returnDateEdit.addTextChangedListener(watcher);
//        roundtripRadio.addTextChangedListener(watcher);
//        onewayRadio.addTextChangedListener(watcher);
//        searchButton.addTextChangedListener(watcher);

        //Listener -- setOnCheckedChangeListener???
        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // if oneway, disable the "return date" field
                if(onewayRadio.isChecked()) {
                    returnDateEdit.setEnabled(false);
                }
                else {
                    returnDateEdit.setEnabled(true);
                }

                // check to see if all fields are filled out
                if(fromAirportEdit.getText().toString().length() != 0 && toAirportEdit.getText().toString().length() != 0
                        && departDateEdit.getText().toString().length() != 0) {
                    if(roundtripRadio.isChecked() && returnDateEdit.getText().toString().length() != 0) {
                        searchButton.setEnabled(true);
                    }
                    else if(onewayRadio.isChecked()) {
                        searchButton.setEnabled(true);
                    }
                }
                else {
                    searchButton.setEnabled(false);
                }
            }
        };
        tripButtonsGroup.setOnCheckedChangeListener(listener);

        return v;
    }

    /*

    TextInputEditText fromAirportEdit;
    TextInputEditText toAirportEdit;
    TextInputEditText departDateEdit;
    TextInputEditText returnDateEdit;

    RadioButton roundtripRadio;
    RadioButton onewayRadio;
     */
    public void onSearchClicked() {
        searchButton.setEnabled(false);

        SearchResults searchResults = SearchResults.newInstance(fromAirportEdit.getText().toString(),
                toAirportEdit.getText().toString(), departDateEdit.getText().toString(),
                returnDateEdit.getText().toString(), roundtripRadio.isChecked());

        switchFragment(searchResults);
    }

    private void switchFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void returnSearchResult() {
//        LoginRequest request = getLoginRequest();
//
//        LoginTask task = new LoginTask();
//        task.setCallback(this);
//        task.setHost(hostEdit.getText().toString());
//        task.setPort(portEdit.getText().toString());
//        task.execute(request);

        // FIND MATCHING THINGS IN THE MOCK DATA
        // SWITCH ACTIVITIES?? - SET THINGS IN THE MODEL TO INDICATE THAT THERE ARE SEARCH RESULTS
    }

    //-----------------------------------------------------------------//

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnSearchFlightsSelectedListener) {
//            mListener = (OnSearchFlightsSelectedListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnSearchFlightsSelectedListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
