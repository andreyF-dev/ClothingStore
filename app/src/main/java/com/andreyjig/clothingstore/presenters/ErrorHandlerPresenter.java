package com.andreyjig.clothingstore.presenters;

import com.andreyjig.clothingstore.views.ErrorHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ErrorHandlerPresenter extends MvpPresenter<ErrorHandlerView> {

    public void setErrorDialog(String textError){
        getViewState().showErrorDialog(textError);
    }

    public void setHideErrorDialog(){
        getViewState().hideErrorDialog();
    }
}
