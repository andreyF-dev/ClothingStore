package com.andreyjig.clothingstore.fragment.views;

import android.view.View;

import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.andreyjig.clothingstore.fragment.presenters.ErrorHandlerPresenter;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ErrorHandlerView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void getErrorDialog(ErrorHandlerInterface listener);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setErrorDialog(ErrorHandlerInterface listener);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void getHideErrorDialog();
    @StateStrategyType(SingleStateStrategy.class)
    void setHideErrorDialog();
}
