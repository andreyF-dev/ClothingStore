package com.andreyjig.clothingstore.fragment.views;

import com.andreyjig.clothingstore.model.Cart;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface CartFragmentView extends MvpView{
    @StateStrategyType(SingleStateStrategy.class)
    void setCartAdapter (Cart cart);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void progressBarVisibility (int state);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void getDialogError();
}
