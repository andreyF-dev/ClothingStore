package com.andreyjig.clothingstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.holder.CardHolder;
import com.andreyjig.clothingstore.fragment.CartFragment;
import com.andreyjig.clothingstore.fragment.ProductFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CardHolder.CardHolderCallback {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this::titleSetup);

        if (savedInstanceState == null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_activity_container, CartFragment.newInstance());
            ft.commit();
        }
        titleSetup();
    }

    private void titleSetup() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        } else {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void startProductFragment(int productId, int variantId) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.main_activity_container, ProductFragment.newInstance(productId, variantId));
        ft.commit();
    }
}
