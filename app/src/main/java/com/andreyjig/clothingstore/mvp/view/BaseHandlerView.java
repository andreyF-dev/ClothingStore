package com.andreyjig.clothingstore.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseHandlerView extends MvpView{

    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Error")
    void showErrorDialog(int errorMessageId);
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Error")
    void hideErrorDialog();
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Title")
    void updateTitle(int id);
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Title")
    void updateTitle(String title);
}
