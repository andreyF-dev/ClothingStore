package com.andreyjig.clothingstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.utils.SetToolbarNameListener;

public class MainActivity extends AppCompatActivity implements SetToolbarNameListener {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment_container).navigateUp();
    }

    @Override
    public void setNameToolbar(String name) {
        getSupportActionBar().setTitle(name);
    }
}
