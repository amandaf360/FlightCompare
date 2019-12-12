package com.example.flightcompare.FlightsTab;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightcompare.Data.Singleton;
import com.example.flightcompare.MainActivity;
import com.example.flightcompare.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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

        // START TEST
        fromAirportEdit.setText("Los Angeles");
        toAirportEdit.setText("Salt Lake City");
        departDateEdit.setText("12/20/2019");
        returnDateEdit.setText("1/20/2019");
        // END TEST

        RadioGroup tripButtonsGroup = v.findViewById(R.id.radio_group);
        tripButtonsGroup.clearCheck();

        searchButton.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            boolean fromAirportGood;
            boolean toAirportGood;
            boolean departDateGood;
            boolean returnDateGood;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchButton.setEnabled(false);
                fromAirportGood = false;
                toAirportGood = false;
                departDateGood = false;
                returnDateGood = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("AFTER TEXT CHANGED");
                // if oneway, disable the "return date" field
                if(onewayRadio.isChecked()) {
                    returnDateEdit.setEnabled(false);
                }
                else {
                    returnDateEdit.setEnabled(true);
                }

                // check to see if the from airport is good
                    // needs to have a length of 3 for the airport code
                //if(editable.equals(fromAirportEdit)) {
                    if(fromAirportEdit.getText().toString().length() > 0) {
                        fromAirportGood = true;
                    }
                    else {
                        fromAirportGood = false;
                        //fromAirportEdit.setError("Need to enter 3 characters for airport code");
                    }
                //}

                // check to see if the to airport is good
                if(toAirportEdit.getText().toString().length() > 0) {
                    toAirportGood = true;
                }
                else {
                    toAirportGood = false;
                    //toAirportEdit.setError("Need to enter 3 characters for airport code");
                }


                // check to see if the depart date is good
                if(departDateEdit.getText().length() > 10 || departDateEdit.getText().length() < 8) {
                    departDateGood = false;
                    //departDateEdit.setError("Need to enter the date in the correct format");
                }
                else {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
                        String date = departDateEdit.getText().toString(); // EditText to check
                        java.util.Date parsedDate = dateFormat.parse(date);
                        java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                        // If the string can be parsed in date, it matches the SimpleDateFormat
                        // Do whatever you want to do if String matches SimpleDateFormat.
                        departDateGood = true;
                    } catch (java.text.ParseException e) {
                        // Else if there's an exception, it doesn't
                        // Do whatever you want to do if it doesn't.
                        departDateGood = false;
                        //departDateEdit.setError("Need to enter valid date");
                    }
                }


                // check to see if the return date is good
//                if(editable.equals(returnDateEdit)) {
                    if(returnDateEdit.getText().length() > 10 || returnDateEdit.getText().length() < 8) {
                        returnDateGood = false;
                        //returnDateEdit.setError("Need to enter the date in the correct format");
                    }
                    else {
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
                            String date = returnDateEdit.getText().toString(); // EditText to check
                            java.util.Date parsedDate = dateFormat.parse(date);
                            java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                            // If the string can be parsed in date, it matches the SimpleDateFormat
                            // Do whatever you want to do if String matches SimpleDateFormat.
                            returnDateGood = true;
                        } catch (java.text.ParseException e) {
                            // Else if there's an exception, it doesn't
                            // Do whatever you want to do if it doesn't.
                            returnDateGood = false;
                            //returnDateEdit.setError("Need to enter valid date");
                        }
                    }
//                }

                System.out.println("fromAirportGood: " + fromAirportGood);
                System.out.println("toAirportGood: " + toAirportGood);
                System.out.println("departDateGood: " + departDateGood);
                System.out.println("returnDateGood: " + returnDateGood);


                // check to see if all fields are filled out
                if(fromAirportGood && toAirportGood && departDateGood) {
                    if(roundtripRadio.isChecked() && returnDateGood) {
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
    private void onSearchClicked() {
        searchButton.setEnabled(false);

        String returnDate = (roundtripRadio.isChecked() ? returnDateEdit.getText().toString() : "");
        String fromAirportCode = Singleton.getAirportCode(fromAirportEdit.getText().toString());
        String toAirportCode = Singleton.getAirportCode(toAirportEdit.getText().toString());

        if(fromAirportCode == null) {
            Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Invalid departure city name", duration);
            toast.show();
        }
        if(toAirportCode == null) {
            Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, "Invalid destination city name", duration);
            toast.show();
        }

        if(fromAirportCode != null && toAirportCode != null) {
            SearchResults searchResults = SearchResults.newInstance(fromAirportCode, toAirportCode,
                    departDateEdit.getText().toString(), returnDate, roundtripRadio.isChecked());

            ((MainActivity) Objects.requireNonNull(getActivity())).loadFragment(searchResults);
        }
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
