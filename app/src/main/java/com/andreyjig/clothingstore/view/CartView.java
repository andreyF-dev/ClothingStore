package com.andreyjig.clothingstore.view;

import com.andreyjig.clothingstore.entity.Cart;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface CartView extends MvpView, BaseHandlerView{
    @StateStrategyType(SingleStateStrategy.class)
    void updateCart(Cart cart);
    @StateStrategyType(AddToEndStrategy.class)
    void showProgressBar();
    @StateStrategyType(SingleStateStrategy.class)
    void hideProgressBar();
}
