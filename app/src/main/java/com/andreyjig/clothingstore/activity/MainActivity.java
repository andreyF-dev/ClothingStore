package com.andreyjig.clothingstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.fragment.CartFragment;
import com.andreyjig.clothingstore.fragment.ProductFragment;

public class MainActivity extends AppCompatActivity implements CartFragment.CartFragmentCallback {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                titleSetup();
            }
        });

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_activity_container, CartFragment.newInstance());
        ft.commit();
    }

    @Override
    public void startDetail(int id) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.main_activity_container, ProductFragment.newInstance(id));
        ft.commit();
    }

    private void titleSetup() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            setTitle(R.string.cart);
        }
    }
}
