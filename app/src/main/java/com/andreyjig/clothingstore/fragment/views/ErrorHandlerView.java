package com.andreyjig.clothingstore.fragment.views;

import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ErrorHandlerView extends MvpView{

    @StateStrategyType(AddToEndSingleStrategy.class)
    void getShowErrorDialog(ErrorHandlerInterface listener);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setShowErrorDialog(ErrorHandlerInterface listener);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void getHideErrorDialog();
    @StateStrategyType(SingleStateStrategy.class)
    void setHideErrorDialog();
}
