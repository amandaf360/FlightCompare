package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    LinkedHashMap<String, Boolean> comparatorActive;

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
        View v = inflater.inflate(R.layout.fragment_compare, container, false);
        compareByBtn = v.findViewById(R.id.compareByBtn);
        cardContainer = v.findViewById(R.id.cardContainer);
        comparatorActive = new LinkedHashMap<>();
        comparatorActive.put(getString(R.string.price), true);
        comparatorActive.put(getString(R.string.flytime), true);
        comparatorActive.put(getString(R.string.bags), true);
        comparatorActive.put(getString(R.string.layovertime), true);
        comparatorActive.put(getString(R.string.from), true);
        comparatorActive.put(getString(R.string.to), true);
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
        for(Map.Entry<String, Boolean> e : comparatorActive.entrySet()){
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
                        comparatorActive.put(getString(R.string.price), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.price) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            TextView comparison1 = cardView.findViewById(R.id.comparison1);
                            TextView comparison2 = cardView.findViewById(R.id.comparison2);
                            TextView comparison3 = cardView.findViewById(R.id.comparison3);
                            List<Flight> comparedFlights = Singleton.getComparedFlights();

                            comparison1.setText(getString(R.string.price_text, comparedFlights.get(0).getPrice()));
                            comparison2.setText(getString(R.string.price_text, comparedFlights.get(1).getPrice()));
                            comparison3.setText(getString(R.string.price_text, comparedFlights.get(2).getPrice()));

                            label.setText(getString(R.string.price));
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.bags:
                        item.setChecked(!item.isChecked());
                        comparatorActive.put(getString(R.string.bags), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.bags) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText(R.string.bags);
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.layovertime:
                        item.setChecked(!item.isChecked());
                        comparatorActive.put(getString(R.string.layovertime), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.layovertime) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            TextView comparison1 = cardView.findViewById(R.id.comparison1);
                            TextView comparison2 = cardView.findViewById(R.id.comparison2);
                            TextView comparison3 = cardView.findViewById(R.id.comparison3);
                            List<Flight> comparedFlights = Singleton.getComparedFlights();

//                            int hours1 = comparedFlights.get(0).getLayovers();
//                            comparison1.setText(getString(R.string.layovertime_text, comparedFlights.get(0).getPrice()));
//                            comparison2.setText(getString(R.string.layovertime_text, comparedFlights.get(1).getPrice()));
//                            comparison3.setText(getString(R.string.layovertime_text, comparedFlights.get(2).getPrice()));

                            label.setText(getString(R.string.layovertime));
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.flytime:
                        item.setChecked(!item.isChecked());
                        comparatorActive.put(getString(R.string.flytime), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.flytime) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            TextView comparison1 = cardView.findViewById(R.id.comparison1);
                            TextView comparison2 = cardView.findViewById(R.id.comparison2);
                            TextView comparison3 = cardView.findViewById(R.id.comparison3);
                            List<Flight> comparedFlights = Singleton.getComparedFlights();

//                            int flytimeH1 = (Integer) comparedFlights.get(0).getFlytime();

//                            comparison1.setText(getString(R.string.price_text, comparedFlights.get(0).getFlytime()));
//                            comparison2.setText(getString(R.string.price_text, comparedFlights.get(1).getFlytime()));
//                            comparison3.setText(getString(R.string.price_text, comparedFlights.get(2).getFlytime()));
                            label.setText(getString(R.string.flytime));
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.from:
                        item.setChecked(!item.isChecked());
                        comparatorActive.put(getString(R.string.from), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.from) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText(R.string.from);
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.to:
                        item.setChecked(!item.isChecked());
                        comparatorActive.put(getString(R.string.to), item.isChecked());
                        Log.d("ITEM CLICK", getString(R.string.to) + " " + item.isChecked());
                        if(item.isChecked()) {
                            LayoutInflater inflater = LayoutInflater.from(cardContainer.getContext());
                            MaterialCardView cardView = (MaterialCardView) inflater.inflate(R.layout.comparator_card, cardContainer,false);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText(R.string.to);
                            cardContainer.addView(cardView);
                        }
                        break;
                    default:
                        Log.e("Comparator menu", "Not a valid menu item");
                }
                return false;
            }
        });
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
