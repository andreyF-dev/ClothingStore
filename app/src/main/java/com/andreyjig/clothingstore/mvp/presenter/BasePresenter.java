package com.andreyjig.clothingstore.mvp.presenter;

import com.andreyjig.clothingstore.mvp.view.BaseHandlerView;
import com.arellomobile.mvp.MvpPresenter;

public abstract class BasePresenter<T extends BaseHandlerView> extends MvpPresenter<T> {

    public void setErrorDialog(int errorStringId) {
        getViewState().showErrorDialog(errorStringId);
    }

    void setTitle(int titleResId){
        getViewState().updateTitle(titleResId);
    }

    void setTitle(String title){
        getViewState().updateTitle(title);
    }

    public void setErrorActionClick(){
        getData();
    };

    public abstract void getData();
}
