package com.andreyjig.clothingstore.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseHandlerView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setShowErrorDialog(int errorMessageId);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showErrorDialog(int errorMessageId);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setHideErrorDialog();
    @StateStrategyType(SingleStateStrategy.class)
    void hideErrorDialog();
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setTitle(int id);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setTitle(String title);
    @StateStrategyType(SingleStateStrategy.class)
    void updateTitle(String title);
}
