package com.andreyjig.clothingstore.ui.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseHandlerView extends MvpView{

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "Error")
    void setShowErrorDialog(int errorMessageId);
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Error")
    void showErrorDialog(int errorMessageId);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "Error")
    void setHideErrorDialog();
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Error")
    void hideErrorDialog();
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "Title")
    void setTitle(int id);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "Title")
    void setTitle(String title);
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "Title")
    void updateTitle(String title);
}
