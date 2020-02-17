package com.andreyjig.clothingstore.presenters;

import com.andreyjig.clothingstore.views.TitleHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class TitleHandlerPresenter extends MvpPresenter<TitleHandlerView> {

    public void setTitle(String title){
        getViewState().updateTitle(title);
    }
}
