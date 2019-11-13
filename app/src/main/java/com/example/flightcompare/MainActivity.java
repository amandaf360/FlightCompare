package com.example.flightcompare;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.flightcompare.FlightsTab.SearchFlights;

public class MainActivity extends AppCompatActivity implements SearchFlights.OnSearchFlightsSelectedListener{

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // start by loading the "search for flights" fragment
//        if (savedInstanceState == null) {
//            LoginFragment loginFragment = new LoginFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frame, loginFragment)
//                    .commit();
//        }

        toolbar = getSupportActionBar();

        // load the search for flights fragment by default
        toolbar.setTitle("Search for Flights");
        loadFragment(SearchFlights.newInstance());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_flights:
                    // need to figure out which fragment to show (flights or search)
                    toolbar.setTitle("Search for Flights");
                    fragment = SearchFlights.newInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_saved:
                    toolbar.setTitle("Saved Flights");
                    //fragment = new GiftsFragment();
                    //loadFragment(fragment);
                    return true;
                case R.id.navigation_compare:
                    toolbar.setTitle("Compare Flights");
//                    fragment = new CartFragment();
//                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
