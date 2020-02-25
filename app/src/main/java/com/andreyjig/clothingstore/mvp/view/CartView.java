package com.andreyjig.clothingstore.mvp.view;

import com.andreyjig.clothingstore.entity.Cart;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface CartView extends MvpView, BaseHandlerView{
    @StateStrategyType(value = AddToEndStrategy.class, tag = "cart")
    void showPreviewCart(Cart cart);
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "cart")
    void showCart(Cart cart);
    @StateStrategyType(value = AddToEndStrategy.class, tag = "ProgressBar")
    void showProgressBar();
    @StateStrategyType(value = AddToEndSingleTagStrategy.class, tag = "ProgressBar")
    void hideProgressBar();
}
