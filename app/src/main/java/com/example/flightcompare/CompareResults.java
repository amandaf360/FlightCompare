package com.example.flightcompare;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
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

import static android.view.View.VISIBLE;
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

    ImageView delete1;
    ImageView delete2;
    ImageView delete3;
    HashMap<String, MaterialCardView> cardViewMap;
    HashMap<String, Integer> logoMap;

    TextView airlineLabel1;
    TextView airlineLabel2;
    TextView airlineLabel3;

    TextView flightnum1;
    TextView flightnum2;
    TextView flightnum3;

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

        delete1 = v.findViewById(R.id.delete1);
        delete2 = v.findViewById(R.id.delete2);
        delete3 = v.findViewById(R.id.delete3);

        airlineLabel1 = v.findViewById(R.id.airline1);
        airlineLabel2 = v.findViewById(R.id.airline2);
        airlineLabel3 = v.findViewById(R.id.airline3);

        flightnum1 = v.findViewById(R.id.flightnum1);
        flightnum2 = v.findViewById(R.id.flightnum2);
        flightnum3 = v.findViewById(R.id.flightnum3);

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

        final Fragment compareFragment = this;

        if(trip1 != null) {
            String carrierName = trip1.getOutboundLeg().getCarrier();
            logo1.setImageResource(Singleton.getAirlineImage(carrierName));
            airlineLabel1.setText(carrierName);

            String returnFlightNum = "";
            if(trip1.getInboundLeg().getCarrier() != null){
                returnFlightNum = "/" + trip1.getInboundLeg().getFlightNumber();
            }
            String flightNum = trip1.getOutboundLeg().getFlightNumber() + returnFlightNum;

            flightnum1.setText(flightNum);
            delete1.setVisibility(View.VISIBLE);
        }else {
//            logo1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Add flight", "Click 1");
//                    SavedFlights saveFragment= new SavedFlights();
//                    getFragmentManager().beginTransaction().detach(compareFragment).attach(saveFragment).commit();
//                }
//            });
//            logo1.setImageResource(R.drawable.ic_add_24px);
//            airlineLabel1.setText("SELECT FLIGHT");
            logo1.setImageResource(R.drawable.select_text);
            airlineLabel1.setText("");
            flightnum1.setText("");
            delete1.setVisibility(View.INVISIBLE);
        }

        if(trip2 != null) {
            String carrierName = trip2.getOutboundLeg().getCarrier();
            logo2.setImageResource(Singleton.getAirlineImage(carrierName));
            airlineLabel2.setText(carrierName);

            String returnFlightNum = "";
            if(trip2.getInboundLeg().getCarrier() != null){
                returnFlightNum = "/" + trip2.getInboundLeg().getFlightNumber();
            }
            String flightNum = trip2.getOutboundLeg().getFlightNumber() + returnFlightNum;

            flightnum2.setText(flightNum);
            delete2.setVisibility(View.VISIBLE);
        }else {
//            logo2.setImageResource(R.drawable.ic_add_24px);
//            logo2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Add flight", "Click 2");
//                    SavedFlights saveFragment= new SavedFlights();
//                    getFragmentManager().beginTransaction().detach(compareFragment).attach(saveFragment).commit();
//                }
//            });
//            airlineLabel2.setText("SELECT FLIGHT");
            logo2.setImageResource(R.drawable.select_text);
            airlineLabel2.setText("");
            flightnum2.setText("");
            delete2.setVisibility(View.INVISIBLE);
        }

        if(trip3 != null) {
            String carrierName = trip3.getOutboundLeg().getCarrier();
            logo3.setImageResource(Singleton.getAirlineImage(carrierName));
            airlineLabel3.setText(carrierName);

            String returnFlightNum = "";
            if(trip3.getInboundLeg().getCarrier() != null){
                returnFlightNum = "/" + trip3.getInboundLeg().getFlightNumber();
            }
            String flightNum = trip3.getOutboundLeg().getFlightNumber() + returnFlightNum;

            flightnum3.setText(flightNum);
            delete3.setVisibility(View.VISIBLE);
        }else {
//            logo3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Add flight", "Click 3");
//                    SavedFlights saveFragment= new SavedFlights();
//                    getFragmentManager().beginTransaction().detach(compareFragment).attach(saveFragment).commit();
//
//                }
//            });
//            logo3.setImageResource(R.drawable.ic_add_24px);
//            airlineLabel3.setText("SELECT FLIGHT");
            logo3.setImageResource(R.drawable.select_text);
            airlineLabel3.setText("");
            flightnum3.setText("");
            delete3.setVisibility(View.INVISIBLE);
        }

        inflateAllCards();
        compareByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ASDF", "POPUP");
                showCompList(v);
            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getVisibility() == VISIBLE){
                    Singleton.removeComparedTrip(Singleton.getComparedTrips().get(0));
                    getFragmentManager().beginTransaction().detach(compareFragment).attach(compareFragment).commit();
                }
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getVisibility() == VISIBLE){
                    Singleton.removeComparedTrip(Singleton.getComparedTrips().get(1));
                    getFragmentManager().beginTransaction().detach(compareFragment).attach(compareFragment).commit();
                }
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getVisibility() == VISIBLE){
                    Singleton.removeComparedTrip(Singleton.getComparedTrips().get(2));
                    getFragmentManager().beginTransaction().detach(compareFragment).attach(compareFragment).commit();
                }
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

        final Fragment compareFragment = this;
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                List<Trip> comparedTrips = Singleton.getComparedTrips();
                String data1 = null;
                String data2 = null;
                String data3 = null;
                String data1a = null;
                String data2a = null;
                String data3a = null;
                switch(item.getItemId()){
                    case R.id.price:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.price), item.isChecked());
                        if(item.isChecked()) {
                            switch (comparedTrips.size()){
                                case 3:
                                    data3 = getString(R.string.price_text, comparedTrips.get(2).getPrice());
                                case 2:
                                    data2 = getString(R.string.price_text, comparedTrips.get(1).getPrice());
                                case 1:
                                    data1 = getString(R.string.price_text, comparedTrips.get(0).getPrice());
                            }
                            inflateRoundComparatorCard(getString(R.string.price), new Pair<>(data1, data1a),
                                                                new Pair<>(data2, data2a),
                                                                new Pair<>(data3, data3a));
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.price)));
                        }
                        break;
                    case R.id.bags:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.bags), item.isChecked());
                        if(item.isChecked()) {
                            switch (comparedTrips.size()){
                                case 3:
                                    data3 = "4, $50";
                                case 2:
                                    data2 = "3, $50";
                                case 1:
                                    data1 = "1, $50";
                            }
                            inflateRoundComparatorCard(getString(R.string.bags), new Pair<>(data1, "1, $50"),
                                                                                    new Pair<>(data2, "4, $50"),
                                                                                    new Pair<>(data3, "1, $50"));
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.bags)));
                        }
                        break;
                    case R.id.layovertime:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.layovertime), item.isChecked());
                        if(item.isChecked()) {
                            switch (comparedTrips.size()){
                                case 3:
                                    data3 = "Direct";
                                case 2:
                                    data2 = "Direct";
                                case 1:
                                    data1 = "Direct";
                            }
                            inflateRoundComparatorCard(getString(R.string.layovertime), new Pair<>(data1, data1a),
                                                                                new Pair<>(data2, data2a),
                                                                                new Pair<>(data3, data3a));
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.layovertime)));
                        }
                        break;
                    case R.id.flytime:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.flytime), item.isChecked());
                        if(item.isChecked()) {
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
                            inflateRoundComparatorCard(getString(R.string.flytime), new Pair<>(data1, data1a),
                                                                                new Pair<>(data2, data2a),
                                                                                new Pair<>(data3, data3a));
                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.flytime)));
                        }
                        break;
                    case R.id.to_from:
                        item.setChecked(!item.isChecked());
                        Singleton.setComparators(getString(R.string.to_from), item.isChecked());
                        if(item.isChecked()) {
                            switch (comparedTrips.size()){
                                case 3:
                                    data3 = comparedTrips.get(2).getOutboundLeg().getDestinationId();
                                    if(comparedTrips.get(2).getInboundLeg() != null){
                                        data3a = comparedTrips.get(2).getOutboundLeg().getOriginId();
                                    }
                                case 2:
                                    data2 = comparedTrips.get(1).getOutboundLeg().getDestinationId();
                                    if(comparedTrips.get(1).getInboundLeg() != null){
                                        data2a = comparedTrips.get(1).getOutboundLeg().getOriginId();
                                    }
                                case 1:
                                    data1 = comparedTrips.get(0).getOutboundLeg().getDestinationId();
                                    if(comparedTrips.get(0).getInboundLeg() != null){
                                        data1a = comparedTrips.get(0).getOutboundLeg().getOriginId();
                                    }
                            }
                            inflateRoundComparatorCard(getString(R.string.to_from), new Pair<>(data1, data1a),
                                                                      new Pair<>(data2, data2a),
                                                                      new Pair<>(data3, data3a));
                                                                        }
                        else{
                            deleteCard(cardViewMap.get(getString(R.string.to_from)));
                        }
                        break;
                    default:
                        Log.e("Comparator menu", "Not a valid menu item");
                }
                // Not ideal to reload, could be faster
                // getFragmentManager().beginTransaction().detach(compareFragment).attach(compareFragment).commit();
                reloadAllCards();
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

    private void inflateRoundComparatorCard(String labelText, Pair<String, String> data1, Pair<String, String> data2, Pair<String, String> data3){
        LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
        final MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card_round, cardContainer,false);
        cardView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                Log.d("Dragging", "OnDrag");
                final int action = event.getAction();
                switch(action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d("Dragging", "Drag started");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d("Dragging", "Drag exited");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d("Dragging", "Drag entered");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d("Dragging", "Drag dropped");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d("Dragging", "Drag ended");
                        break;
                    default:
                        Log.d("Dragging", "hit default");
                        break;
                }
                return true;
            }
        });
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Dragging", "OnLongClick");
                ClipData data = ClipData.newPlainText("Dragging", "start now");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(cardView);
                v.startDrag(data, shadow, null, 0);
                return false;
            }
        });

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
            comparison1.setText("-");
            comparison1a.setLayoutParams(invisiParam);
        }
        else{
            comparison1.setText(data1.first);
            if(data1.second != null) {
                comparison1a.setText(data1.second);
                if (data1.second.equals("empty")){
                    cardView.findViewById(R.id.arrow_f).setVisibility(View.INVISIBLE);
                    cardView.findViewById(R.id.arrow_b).setVisibility(View.INVISIBLE);
                    comparison1a.setLayoutParams(invisiParam);
                }
            }
            else{
                comparison1a.setLayoutParams(invisiParam);
            }
        }

        if(data2.first == null){
            comparison2.setText("-");
            comparison2a.setLayoutParams(invisiParam);
        }
        else{
            comparison2.setText(data2.first);
            if(data2.second != null) {
                comparison2a.setText(data2.second);
                if (data2.second.equals("empty")){
                    cardView.findViewById(R.id.arrow_f).setVisibility(View.INVISIBLE);
                    cardView.findViewById(R.id.arrow_b).setVisibility(View.INVISIBLE);
                    comparison2a.setLayoutParams(invisiParam);
                }
            }
            else{
                comparison2a.setLayoutParams(invisiParam);
            }
        }

        if(data3.first == null){
            comparison3.setText("-");
            comparison3a.setLayoutParams(invisiParam);
        }
        else{
            comparison3.setText(data3.first);
            if(data3.second != null) {
                comparison3a.setText(data3.second);
                if (data3.second.equals("empty")){
                    cardView.findViewById(R.id.arrow_f).setVisibility(View.INVISIBLE);
                    cardView.findViewById(R.id.arrow_b).setVisibility(View.INVISIBLE);
                    comparison3a.setLayoutParams(invisiParam);
                }
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
        Log.d("Inflate all cards", "comparedTrips size: " + comparedTrips.size());

        String labelString;
        for(Map.Entry<String, Boolean> e : Singleton.getComparators().entrySet()){
            Log.d("Comparators", "Entry: " + e.toString());
            String data1 = null;
            String data2 = null;
            String data3 = null;
            String data1a = null;
            String data2a = null;
            String data3a = null;
            if(e.getValue()) {
                switch (e.getKey()) {
                    case "PRICE":
                        labelString = getString(R.string.price);
                        switch (comparedTrips.size()) {
                            case 3:
                                data3 = getString(R.string.price_text, comparedTrips.get(2).getPrice());
                                data3a = "empty";
                            case 2:
                                data2 = getString(R.string.price_text, comparedTrips.get(1).getPrice());
                                data2a = "empty";
                            case 1:
                                data1 = getString(R.string.price_text, comparedTrips.get(0).getPrice());
                                data1a = "empty";
                        }
                        break;
                    case "FLYTIME":
                        labelString = getString(R.string.flytime);
                        switch (comparedTrips.size()) {
                            case 3:
                                data3 = comparedTrips.get(2).getOutboundLeg().getFlightDuration();
                                if (comparedTrips.get(2).getInboundLeg() != null) {
                                    data3a = comparedTrips.get(2).getInboundLeg().getFlightDuration();
                                }
                            case 2:
                                data2 = comparedTrips.get(1).getOutboundLeg().getFlightDuration();
                                if (comparedTrips.get(1).getInboundLeg() != null) {
                                    data2a = comparedTrips.get(1).getInboundLeg().getFlightDuration();
                                }
                            case 1:
                                data1 = comparedTrips.get(0).getOutboundLeg().getFlightDuration();
                                if (comparedTrips.get(0).getInboundLeg() != null) {
                                    data1a = comparedTrips.get(0).getInboundLeg().getFlightDuration();
                                }
                        }
                        break;
                    case "CHECKED BAGS":
                        labelString = getString(R.string.bags);
                        switch (comparedTrips.size()) {
                            case 3:
                                data3 = Singleton.getBagString(comparedTrips.get(2).getOutboundLeg().getCarrier());
                                data3a = null;
                            case 2:
                                data2 = Singleton.getBagString(comparedTrips.get(1).getOutboundLeg().getCarrier());;
                                data2a = null;
                            case 1:
                                data1 = Singleton.getBagString(comparedTrips.get(0).getOutboundLeg().getCarrier());;
                                data1a = null;
                        }
                        break;
                    case "LAYOVER TIME":
                        labelString = getString(R.string.layovertime);
                        switch (comparedTrips.size()) {
                            case 3:
                                data3 = "Direct";
                                if(comparedTrips.get(2).getInboundLeg().getOriginId() == null) {
                                    data3a = "empty";
                                }
                                else{
                                    data3a = "Direct";
                                }
                            case 2:
                                data2 = "Direct";
                                if(comparedTrips.get(1).getInboundLeg().getOriginId() == null) {
                                    data2a = "empty";
                                }
                                else{
                                    data2a = "Direct";
                                }
                            case 1:
                                data1 = "Direct";
                                if(comparedTrips.get(0).getInboundLeg().getOriginId() == null) {
                                    data1a = "empty";
                                }
                                else{
                                    data1a = "Direct";
                                }
                        }
                        break;
                    case "DEST/ORIGIN":
                        labelString = getString(R.string.to_from);
                        switch (comparedTrips.size()) {
                            case 3:
                                data3 = comparedTrips.get(2).getOutboundLeg().getDestinationId();
                                if (comparedTrips.get(2).getInboundLeg() != null) {
                                    data3a = comparedTrips.get(2).getOutboundLeg().getOriginId();
                                }
                            case 2:
                                data2 = comparedTrips.get(1).getOutboundLeg().getDestinationId();
                                if (comparedTrips.get(1).getInboundLeg() != null) {
                                    data2a = comparedTrips.get(1).getOutboundLeg().getOriginId();
                                }
                            case 1:
                                data1 = comparedTrips.get(0).getOutboundLeg().getDestinationId();
                                if (comparedTrips.get(0).getInboundLeg() != null) {
                                    data1a = comparedTrips.get(0).getOutboundLeg().getOriginId();
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
    }

    public void deleteAllCards(){
        for(Map.Entry<String, Boolean> e : Singleton.getComparators().entrySet()) {
            if(e.getValue()){
                deleteCard(cardViewMap.get(e.getKey()));
            }
        }
    }

    public void reloadAllCards(){
        deleteAllCards();
        inflateAllCards();
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
