package com.andreyjig.clothingstore.presenters;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.handler.CartHandler;
import com.andreyjig.clothingstore.views.CartView;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartPresenter extends MvpPresenter<CartView>{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().progressBarVisibility();
        getViewState().setTitle(R.string.cart);
        getCart();
    }

    private void getCart () {
        CartHandler cartHandler = new CartHandler() {
            @Override
            public void setDownloadedCart(Cart cart) {
                setCart(cart);
            }
            @Override
            public void setErrorDownloaded(String error) {
                setError(error);
            }
        };
        NetworkService.getInstance().getCart(cartHandler);
    }

    public void setCart(Cart cart) {
        if (cart != null) {
            getViewState().progressBarInvisible();
            getViewState().setCart(cart);
        } else {
            setError("Cart object is null");
        }
    }

    private void setError(String errorText){
        getViewState().setShowErrorDialog(errorText);
    }

    public void errorDialogOnClick(){
        getCart();
    }
}
