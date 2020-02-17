package com.andreyjig.clothingstore.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface TitleHandlerView extends MvpView{
    void setTitle(int id);
    void setTitle(String title);
    @StateStrategyType(SingleStateStrategy.class)
    void updateTitle(String title);
}
