package com.andreyjig.clothingstore.presenter;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.entity.handler.CartHandler;
import com.andreyjig.clothingstore.model.CartModel;
import com.andreyjig.clothingstore.view.CartView;
import com.andreyjig.clothingstore.entity.Cart;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartPresenter extends MvpPresenter<CartView>{

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setTitle(R.string.cart);
        getCart();
    }

    private void getCart () {
        getViewState().showProgressBar();
        CartHandler cartHandler = new CartHandler() {
            @Override
            public void setDownloadedCart(Cart cart) {
                setCart(cart);
            }
            @Override
            public void setErrorDownloaded(int errorStringId) {
                setError(errorStringId);
            }
        };
        CartModel.getInstance().getCart(cartHandler);
    }

    public void setCart( Cart cart){
        getViewState().hideProgressBar();
        getViewState().updateCart(cart);

    }

    private void setError(int errorStringId){
        getViewState().setShowErrorDialog(errorStringId);
    }

    public void errorDialogOnClick(){
        getCart();
    }
}
