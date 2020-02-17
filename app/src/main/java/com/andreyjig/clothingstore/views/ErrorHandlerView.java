package com.andreyjig.clothingstore.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ErrorHandlerView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setShowErrorDialog(String errorText);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showErrorDialog(String errorText);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setHideErrorDialog();
    @StateStrategyType(SingleStateStrategy.class)
    void hideErrorDialog();
}
