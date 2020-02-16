package com.andreyjig.clothingstore.activity.presenters;

import android.util.Log;

import com.andreyjig.clothingstore.activity.views.TitleHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class TitleHandlerPresenter extends MvpPresenter<TitleHandlerView> {

    public void getTitle(String title){
        getViewState().setToolbarTitle(title);
    }
}
