package com.andreyjig.clothingstore.activity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.activity.presenters.TitleHandlerPresenter;
import com.andreyjig.clothingstore.activity.views.TitleHandlerView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements TitleHandlerView {

    @InjectPresenter
    TitleHandlerPresenter titleHandlerPresenter;

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
    public void getTitle(int id) {
        titleHandlerPresenter.getTitle(getString(id));
    }

    @Override
    public void getTitle(String title) {
        titleHandlerPresenter.getTitle(title);
    }

    @Override
    public void setToolbarTitle(String title) {
        setTitle(title);
    }
}
