package com.example.flightcompare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // need to switch fragments
                    mTextMessage.setText(R.string.flights_tab);
                    return true;
                case R.id.navigation_dashboard:
                    // need to switch fragments
                    mTextMessage.setText(R.string.favorites_tab);
                    return true;
                case R.id.navigation_notifications:
                    // need to switch fragments
                    mTextMessage.setText(R.string.compare_tab);
                    return true;
            }
            return false;
        }
    };

}
