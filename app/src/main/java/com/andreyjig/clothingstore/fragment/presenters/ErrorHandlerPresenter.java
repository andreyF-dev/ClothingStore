package com.andreyjig.clothingstore.fragment.presenters;

import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ErrorHandlerPresenter extends MvpPresenter<ErrorHandlerView> {

    public void onClick(){
        getViewState().setHideErrorDialog();
    }

    public void setErrorDialog(ErrorHandlerInterface listener){
        getViewState().setErrorDialog(listener);
    }

    public void setHideErrorDialog(){
        getViewState().setHideErrorDialog();
    }
}
