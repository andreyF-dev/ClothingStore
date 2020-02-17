package com.andreyjig.clothingstore.views;

import com.andreyjig.clothingstore.model.Cart;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface CartView extends MvpView, ErrorHandlerView, TitleHandlerView{
    @StateStrategyType(SingleStateStrategy.class)
    void setCart(Cart cart);
    @StateStrategyType(AddToEndStrategy.class)
    void progressBarVisibility();
    @StateStrategyType(SingleStateStrategy.class)
    void progressBarInvisible();
}
