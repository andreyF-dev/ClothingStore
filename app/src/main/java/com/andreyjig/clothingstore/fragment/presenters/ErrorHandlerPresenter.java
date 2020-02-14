package com.andreyjig.clothingstore.fragment.presenters;

import android.view.View;

import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ErrorHandlerPresenter extends MvpPresenter<ErrorHandlerView> {

    public void onClick(){
        getViewState().hideErrorDialog();
    }

    public void setErrorDialog(ErrorHandlerInterface listener){
        getViewState().setErrorDialog(listener);
    }
}
