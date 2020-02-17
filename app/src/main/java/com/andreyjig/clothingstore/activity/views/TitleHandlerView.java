package com.andreyjig.clothingstore.activity.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface TitleHandlerView extends MvpView{
    @StateStrategyType(AddToEndStrategy.class)
    void getTitle(int id);
    @StateStrategyType(AddToEndStrategy.class)
    void getTitle(String title);
    @StateStrategyType(SingleStateStrategy.class)
    void updateTitle(String title);
}
