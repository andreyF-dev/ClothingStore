package com.andreyjig.clothingstore.fragment.views;

import android.view.View;

import com.andreyjig.clothingstore.model.Cart;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface CartView extends MvpView{
    @StateStrategyType(SingleStateStrategy.class)
    void setCart(Cart cart);
    @StateStrategyType(AddToEndStrategy.class)
    void progressBarVisibility();
    @StateStrategyType(SingleStateStrategy.class)
    void progressBarInvisible();
}
