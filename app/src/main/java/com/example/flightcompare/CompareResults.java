package com.example.flightcompare;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightcompare.FlightsTab.SearchFlights;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

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
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.price:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("PRICE");
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.bags:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("BAGS");
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.layovertime:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("LAYOVER TIME");
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.flytime:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("FLYTIME");
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.from:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("FROM");
                            cardContainer.addView(cardView);
                        }
                        break;
                    case R.id.to:
                        item.setChecked(!item.isChecked());
                        if(item.isChecked()) {
                            MaterialCardView cardView = (MaterialCardView) getLayoutInflater().inflate(R.layout.comparator_card, null);
                            TextView label = cardView.findViewById(R.id.comparatorLabel);
                            label.setText("TO");
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
