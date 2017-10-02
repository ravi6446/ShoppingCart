package com.ravi.practiceapp.activities;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ravi.practiceapp.R;
import com.ravi.practiceapp.fragments.CartFragment;
import com.ravi.practiceapp.fragments.ProductsFragment;

public class ProductsActivity extends AppCompatActivity implements CartFragment.OnFragmentInteractionListener,ProductsFragment.OnFragmentInteractionListener{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment= ProductsFragment.newInstance();
                    getSupportActionBar().setTitle(R.string.title_home);
                    break;
                case R.id.navigation_dashboard:
                    fragment = CartFragment.newInstance();
                    getSupportActionBar().setTitle(R.string.title_dashboard);
                    break;
                default:
                    return false;
            }
            fragmentTransaction.replace(R.id.frag_container,fragment);
            fragmentTransaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
