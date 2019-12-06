package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightcompare.Data.CollectionObjects.Flight;
import com.example.flightcompare.Data.JsonObjects.Trip;
import com.example.flightcompare.Data.Singleton;
import com.example.flightcompare.FlightsTab.SearchFlights;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.snapshot.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
    MaterialButton compareByBtn;
    LinearLayout cardContainer;
    ImageView logo1;
    ImageView logo2;
    ImageView logo3;
    HashMap<String, MaterialCardView> cardViewMap;
    HashMap<String, Integer> logoMap;

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
        Log.d("ASDF", "Creating Compare View");
        cardViewMap = new HashMap<>();
        logoMap = new HashMap<>();
        // Add new logos here
        logoMap.put(getString(R.string.american), R.drawable.aa_logo);
        logoMap.put(getString(R.string.hawaiian), R.drawable.hawaii_logo);
        logoMap.put(getString(R.string.delta), R.drawable.delta_logo);

        View v = inflater.inflate(R.layout.fragment_compare, container, false);
        compareByBtn = v.findViewById(R.id.compareByBtn);
        cardContainer = v.findViewById(R.id.cardContainer);

        logo1 = v.findViewById(R.id.logo1);
        logo2 = v.findViewById(R.id.logo2);
        logo3 = v.findViewById(R.id.logo3);

        Trip trip1 = null;
        Trip trip2 = null;
        Trip trip3 = null;

        try {
            trip1 = Singleton.getComparedTrips().get(0);
            trip2 = Singleton.getComparedTrips().get(1);
            trip3 = Singleton.getComparedTrips().get(2);
        }
        catch (IndexOutOfBoundsException ex){
            Log.d("Compared Trips not full", ex.getMessage());
        }

        if(trip1 != null) {
            logo1.setImageResource(logoMap.get(getString(R.string.hawaiian)));
        }else {
            logo1.setImageResource(R.drawable.ic_home_black_24dp);
        }

        if(trip2 != null) {
            logo1.setImageResource(logoMap.get(getString(R.string.hawaiian)));
        }else {
            logo1.setImageResource(R.drawable.ic_home_black_24dp);
        }

        if(trip3 != null) {
            logo1.setImageResource(logoMap.get(getString(R.string.hawaiian)));
        }else {
            logo1.setImageResource(R.drawable.ic_home_black_24dp);
        }

        Singleton.setComparators(getString(R.string.price), true);
        Singleton.setComparators(getString(R.string.flytime), true);
        Singleton.setComparators(getString(R.string.bags), true);
        Singleton.setComparators(getString(R.string.layovertime), true);
        Singleton.setComparators(getString(R.string.from), true);
        Singleton.setComparators(getString(R.string.to), true);
        inflateAllCards();
        compareByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ASDF", "POPUP");
                showCompList(v);
            }
        });

        return v;
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

    public void showCompList(View v) {
        Log.i("ASDF", "Showing List");
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        final MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.comparator_menu, popup.getMenu());

        int i = 0;
        for(Map.Entry<String, Boolean> e : Singleton.getComparators().entrySet()){
            popup.getMenu().getItem(i).setChecked(e.getValue());
            i++;
            Log.d("Comparators!", e.getKey() + " " + e.getValue());
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                List<Trip> comparedTrips = Singleton.getComparedTrips();
                switch(item.getItemId()){
                    case R.id.price:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.price), item.isChecked());
                        if(item.isChecked()) {
                            String data1 = getString(R.string.price_text, comparedTrips.get(0).getPrice());
                            String data2 = getString(R.string.price_text, comparedTrips.get(1).getPrice());
                            String data3 = getString(R.string.price_text, comparedTrips.get(2).getPrice());
                            inflateComparatorCard(getString(R.string.price), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.price)));
                        }
                        break;
                    case R.id.bags:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.bags), item.isChecked());
                        if(item.isChecked()) {
                            String data1 = "1";
                            String data2 = "2";
                            String data3 = "3";
                            inflateComparatorCard(getString(R.string.bags), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.bags)));
                        }
                        break;
                    case R.id.layovertime:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.layovertime), item.isChecked());
                        if(item.isChecked()) {
                            String data1 = "1";
                            String data2 = "2";
                            String data3 = "3";
                            inflateComparatorCard(getString(R.string.layovertime), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.layovertime)));
                        }
                        break;
                    case R.id.flytime:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.flytime), item.isChecked());
                        if(item.isChecked()) {
                            String data1 = "1";
                            String data2 = "2";
                            String data3 = "3";
                            inflateComparatorCard(getString(R.string.flytime), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.flytime)));
                        }
                        break;
                    case R.id.from:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.from), item.isChecked());
                        if(item.isChecked()) {
//                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(0).getFrom_location());
//                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(1).getFrom_location());
//                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(2).getFrom_location());
//                            String data1 = comparedFlights.get(0).getFrom_location().getAirport_code();
//                            String data2 = comparedFlights.get(1).getFrom_location().getAirport_code();
//                            String data3 = comparedFlights.get(2).getFrom_location().getAirport_code();
//                            inflateComparatorCard(getString(R.string.from), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.from)));
                        }
                        break;
                    case R.id.to:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.to), item.isChecked());
                        if(item.isChecked()) {
//                            Log.d(TAG, "Comp flights to: " + comparedFlights.size());
//                            String data1 = comparedFlights.get(0).getTo_location().getAirport_code();
//                            String data2 = comparedFlights.get(1).getTo_location().getAirport_code();
//                            String data3 = comparedFlights.get(2).getTo_location().getAirport_code();
//                            inflateComparatorCard(getString(R.string.to), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.to)));
                        }
                        break;
                    default:
                        Log.e("Comparator menu", "Not a valid menu item");
                }

                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
                item.setActionView(new View(getContext()));
                item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return false;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return false;
                    }
                });

                return false;
            }
        });
        popup.show();
    }

    private void inflateComparatorCard(String labelText, String data1, String data2, String data3){
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
        cardViewMap.put(labelText, cardView);
        TextView label = cardView.findViewById(R.id.comparatorLabel);
        TextView comparison1 = cardView.findViewById(R.id.comparison1);
        TextView comparison2 = cardView.findViewById(R.id.comparison2);
        TextView comparison3 = cardView.findViewById(R.id.comparison3);

        if(data1 == null){
            comparison1.setText("SELECT FLIGHT");
        }
        else{
            comparison1.setText(data1);
        }

        if(data2 == null){
            comparison2.setText("SELECT FLIGHT");
        }
        else{
            comparison2.setText(data2);
        }

        if(data3 == null){
            comparison3.setText("SELECT FLIGHT");
        }
        else{
            comparison3.setText(data3);
        }

        label.setText(labelText);

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        cardContainer.addView(cardView);
    }

    private void inflateRoundComparatorCard(String labelText, Pair<String, String> data1, Pair<String, String> data2, Pair<String, String> data3){
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card_round, cardContainer,false);
        cardViewMap.put(labelText, cardView);
        TextView label = cardView.findViewById(R.id.comparatorLabel);
        TextView comparison1 = cardView.findViewById(R.id.comparison1);
        TextView comparison2 = cardView.findViewById(R.id.comparison2);
        TextView comparison3 = cardView.findViewById(R.id.comparison3);

        TextView comparison1a = cardView.findViewById(R.id.comparison1a);
        TextView comparison2a = cardView.findViewById(R.id.comparison2a);
        TextView comparison3a = cardView.findViewById(R.id.comparison3a);

        LinearLayout.LayoutParams invisiParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                -1.0f
        );
        Log.d("Inflate card d1", "data: " + data1.first);
        Log.d("Inflate card d1", "data: " + data1.second);
        Log.d("Inflate card d2", "data: " + data2.first);
        Log.d("Inflate card d2", "data: " + data2.second);
        Log.d("Inflate card d3", "data: " + data3.first);
        Log.d("Inflate card d3", "data: " + data3.second);
        if(data1.first == null){
            comparison1.setText("SELECT FLIGHT");
        }
        else{
            comparison1.setText(data1.first);
            if(data1.second != null) {
                comparison1a.setText(data1.second);
            }
            else{
                comparison1a.setLayoutParams(invisiParam);
            }
        }

        if(data2.first == null){
            comparison2.setText("SELECT FLIGHT");
        }
        else{
            comparison2.setText(data2.first);
            if(data2.second != null) {
                comparison2a.setText(data2.second);
            }
            else{
                comparison2a.setLayoutParams(invisiParam);
            }
        }

        if(data3.first == null){
            comparison3.setText("SELECT FLIGHT");
        }
        else{
            comparison3.setText(data3.first);
            if(data3.second != null) {
                comparison3a.setText(data3.second);
            }
            else{
                comparison3a.setLayoutParams(invisiParam);
            }
        }

        label.setText(labelText);

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        cardContainer.addView(cardView);
    }

    private void deleteCard(View v) {
        cardContainer.removeView(v);
    }

    private void inflateAllCards(){
        List<Trip> comparedTrips = Singleton.getComparedTrips();
        Log.d("Inflate all cards", "Enter");

        String labelString;
        String data1 = null;
        String data2 = null;
        String data3 = null;
        String data1a = null;
        String data2a = null;
        String data3a = null;
        for(Map.Entry<String, Boolean> e : Singleton.getComparators().entrySet()){
            switch (e.getKey()){
                case "PRICE":
                    labelString = getString(R.string.price);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = getString(R.string.price_text, comparedTrips.get(2).getPrice());
                        case 2:
                            data2 = getString(R.string.price_text, comparedTrips.get(1).getPrice());
                        case 1:
                            data1 = getString(R.string.price_text, comparedTrips.get(0).getPrice());
                    }
                    break;
                case "FLYTIME":
                    labelString = getString(R.string.flytime);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = comparedTrips.get(2).getOutboundLeg().getFlightDuration();
                            if(comparedTrips.get(2).getInboundLeg() != null){
                                data3a = comparedTrips.get(2).getInboundLeg().getFlightDuration();
                            }
                        case 2:
                            data2 = comparedTrips.get(1).getOutboundLeg().getFlightDuration();
                            if(comparedTrips.get(1).getInboundLeg() != null){
                                data2a = comparedTrips.get(1).getInboundLeg().getFlightDuration();
                            }
                        case 1:
                            data1 = comparedTrips.get(0).getOutboundLeg().getFlightDuration();
                            if(comparedTrips.get(0).getInboundLeg() != null){
                                data1a = comparedTrips.get(0).getInboundLeg().getFlightDuration();
                            }
                    }
                    break;
                case "BAGS":
                    labelString = getString(R.string.bags);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = "4, $50";
                        case 2:
                            data2 = "3, $50";
                        case 1:
                            data1 = "1, $50";
                    }
                    break;
                case "LAYOVER TIME":
                    labelString = getString(R.string.layovertime);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = "1";
                        case 2:
                            data2 = "2";
                        case 1:
                            data1 = "3";
                    }
                    break;
                case "FROM":
                    labelString = getString(R.string.from);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = comparedTrips.get(2).getOutboundLeg().getOriginId();
                            if(comparedTrips.get(2).getInboundLeg() != null){
                                data3a = comparedTrips.get(2).getInboundLeg().getOriginId();
                            }
                        case 2:
                            data2 = comparedTrips.get(1).getOutboundLeg().getOriginId();
                            if(comparedTrips.get(1).getInboundLeg() != null){
                                data2a = comparedTrips.get(1).getInboundLeg().getOriginId();
                            }
                        case 1:
                            data1 = comparedTrips.get(0).getOutboundLeg().getOriginId();
                            if(comparedTrips.get(0).getInboundLeg() != null){
                                data1a = comparedTrips.get(0).getInboundLeg().getOriginId();
                            }
                    }
                    break;
                case "TO":
                    labelString = getString(R.string.to);
                    switch (comparedTrips.size()){
                        case 3:
                            data3 = comparedTrips.get(2).getOutboundLeg().getDestinationId();
                            if(comparedTrips.get(2).getInboundLeg() != null){
                                data3a = comparedTrips.get(2).getInboundLeg().getDestinationId();
                            }
                        case 2:
                            data2 = comparedTrips.get(1).getOutboundLeg().getDestinationId();
                            if(comparedTrips.get(1).getInboundLeg() != null){
                                data2a = comparedTrips.get(1).getInboundLeg().getDestinationId();
                            }
                        case 1:
                            data1 = comparedTrips.get(0).getOutboundLeg().getDestinationId();
                            if(comparedTrips.get(0).getInboundLeg() != null){
                                data1a = comparedTrips.get(0).getInboundLeg().getDestinationId();
                            }
                    }
                    break;
                default:
                    Log.e(TAG, "Not a valid menu item");
                    labelString = "NOT AN ITEM";
                    data1 = "INVALID";
                    data2 = "INVALID";
                    data3 = "INVALID";
            }
            Log.d("Inflate all cards", "End and inflate " + comparedTrips.size());
            inflateRoundComparatorCard(labelString, new Pair<>(data1, data1a),
                                                    new Pair<>(data2, data2a),
                                                    new Pair<>(data3, data3a));
        }
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
