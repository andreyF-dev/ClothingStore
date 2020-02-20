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

    private CartModel cartModel;
    private Cart cart;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setTitle(R.string.cart);
        cartModel = CartModel.getInstance();
        setPreview();
        getCart();
    }

    private void setPreview() {
        Cart cart = cartModel.getCachedCart();
        if (cart != null){
            this.cart = cart;
            getViewState().showPreviewCart(cart);
        }
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
        cartModel.getCart(cartHandler);
    }

    public void setCart(Cart cart){
        this.cart = cart;
        getViewState().hideProgressBar();
        getViewState().showCart(cart);
        cartModel.setCashedCart(cart);
    }

    private void setError(int errorStringId){
        if (cart != null){
            setCart(cart);
        }
        getViewState().setShowErrorDialog(errorStringId);
    }

    public void errorDialogOnClick(){
        getCart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartModel.closeCartModel();
    }
}
