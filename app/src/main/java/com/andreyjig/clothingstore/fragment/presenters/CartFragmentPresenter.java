package com.andreyjig.clothingstore.fragment.presenters;

import android.view.View;

import com.andreyjig.clothingstore.fragment.model.CartFragmentPresenterInterface;
import com.andreyjig.clothingstore.fragment.views.CartFragmentView;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartFragmentPresenter extends MvpPresenter<CartFragmentView>
implements CartFragmentPresenterInterface {

    public CartFragmentPresenter() {
        getViewState().progressBarVisibility(View.VISIBLE);
        getCart();
    }

    public void getCart () {
        NetworkService.getInstance().getCart(this);
    }

    @Override
    public void setErrorDialog() {
        getViewState().getDialogError(v -> getCart());
    }

    @Override
    public void setCart(Cart cart) {
        getViewState().progressBarVisibility(View.INVISIBLE);
        getViewState().setCartAdapter(cart);
    }
}
