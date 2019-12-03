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
import com.example.flightcompare.Data.Singleton;
import com.example.flightcompare.FlightsTab.SearchFlights;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

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

        Flight flight1 = Singleton.getComparedFlights().get(0);
        Flight flight2 = Singleton.getComparedFlights().get(1);
        Flight flight3 = Singleton.getComparedFlights().get(2);

        if(flight1 != null) {
            logo1.setImageResource(logoMap.get(flight1.getAirline()));
        }else {
            logo1.setImageResource(R.drawable.ic_home_black_24dp);
        }

        if(flight2 != null) {
            logo1.setImageResource(logoMap.get(flight2.getAirline()));
        }else {
            logo1.setImageResource(R.drawable.ic_home_black_24dp);
        }

        if(flight3 != null) {
            logo1.setImageResource(logoMap.get(flight3.getAirline()));
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
                switch(item.getItemId()){
                    case R.id.price:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.price), item.isChecked());
                        if(item.isChecked()) {
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
                            String data1 = getString(R.string.price_text, comparedFlights.get(0).getPrice());
                            String data2 = getString(R.string.price_text, comparedFlights.get(1).getPrice());
                            String data3 = getString(R.string.price_text, comparedFlights.get(2).getPrice());
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
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
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
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
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
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
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
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(0).getFrom_location());
                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(1).getFrom_location());
                            Log.d(TAG, "Comp flights from: " + comparedFlights.get(2).getFrom_location());
                            String data1 = comparedFlights.get(0).getFrom_location().getAirport_code();
                            String data2 = comparedFlights.get(1).getFrom_location().getAirport_code();
                            String data3 = comparedFlights.get(2).getFrom_location().getAirport_code();
                            inflateComparatorCard(getString(R.string.from), data1, data2, data3);
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.from)));
                        }
                        break;
                    case R.id.to:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.to), item.isChecked());
                        if(item.isChecked()) {
                            List<Flight> comparedFlights = Singleton.getComparedFlights();
                            Log.d(TAG, "Comp flights to: " + comparedFlights.size());
                            String data1 = comparedFlights.get(0).getTo_location().getAirport_code();
                            String data2 = comparedFlights.get(1).getTo_location().getAirport_code();
                            String data3 = comparedFlights.get(2).getTo_location().getAirport_code();
                            inflateComparatorCard(getString(R.string.to), data1, data2, data3);
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

    private void deleteCard(View v) {
        cardContainer.removeView(v);
    }

    private void inflateAllCards(){
        List<Flight> comparedFlights = Singleton.getComparedFlights();
        if(comparedFlights.size() == 0){
            return;
        }
        String labelString;
        String data1 = null;
        String data2 = null;
        String data3 = null;
        for(Map.Entry<String, Boolean> e : Singleton.getComparators().entrySet()){
            switch (e.getKey()){
                case "PRICE":
                    labelString = getString(R.string.price);
                    switch (comparedFlights.size()){
                        case 3:
                            data3 = getString(R.string.price_text, comparedFlights.get(2).getPrice());
                        case 2:
                            data2 = getString(R.string.price_text, comparedFlights.get(1).getPrice());
                        case 1:
                            data1 = getString(R.string.price_text, comparedFlights.get(0).getPrice());
                    }
                    break;
                case "FLYTIME":
                    labelString = getString(R.string.flytime);
                    switch (comparedFlights.size()){
                        case 3:
                            data3 = comparedFlights.get(2).getFlytime().toString();
                        case 2:
                            data2 = comparedFlights.get(1).getFlytime().toString();
                        case 1:
                            data1 = comparedFlights.get(0).getFlytime().toString();
                    }
                    break;
                case "BAGS":
                    labelString = getString(R.string.bags);
                    switch (comparedFlights.size()){
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
                    switch (comparedFlights.size()){
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
                    switch (comparedFlights.size()){
                        case 3:
                            data3 = comparedFlights.get(2).getFrom_location().getAirport_code();
                        case 2:
                            data2 = comparedFlights.get(1).getFrom_location().getAirport_code();
                        case 1:
                            data1 = comparedFlights.get(0).getFrom_location().getAirport_code();;
                    }
                    break;
                case "TO":
                    labelString = getString(R.string.to);
                    switch (comparedFlights.size()){
                        case 3:
                            data3 = comparedFlights.get(2).getTo_location().getAirport_code();
                        case 2:
                            data2 = comparedFlights.get(1).getTo_location().getAirport_code();
                        case 1:
                            data1 = comparedFlights.get(0).getTo_location().getAirport_code();;
                    }
                    break;
                default:
                    Log.e(TAG, "Not a valid menu item");
                    labelString = "NOT AN ITEM";
                    data1 = "INVALID";
                    data2 = "INVALID";
                    data3 = "INVALID";
            }
            inflateComparatorCard(labelString, data1, data2, data3);
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