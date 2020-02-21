package com.andreyjig.clothingstore.presenter;

import com.andreyjig.clothingstore.ui.view.BaseHandlerView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class BaseHandlerPresenter extends MvpPresenter<BaseHandlerView> {

    public void setErrorDialog(int errorMessageId){
        getViewState().showErrorDialog(errorMessageId);
    }

    public void setHideErrorDialog(){
        getViewState().hideErrorDialog();
    }

    public void setTitle(String title){
        getViewState().updateTitle(title);
    }
}
