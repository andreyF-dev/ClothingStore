package com.andreyjig.clothingstore.mvp.presenter;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.mvp.model.CartModel;
import com.andreyjig.clothingstore.mvp.model.handler.DataHandler;
import com.andreyjig.clothingstore.mvp.view.CartView;
import com.andreyjig.clothingstore.entity.Cart;
import com.arellomobile.mvp.InjectViewState;

@InjectViewState
public class CartPresenter extends BasePresenter<CartView>{

    private CartModel model;
    private Cart cart;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().updateTitle(R.string.cart);
        model = new CartModel();
        setPreview();
        getCart();
    }

    private void setPreview() {
        Cart cart = model.getCachedData();
        if (cart != null){
            this.cart = cart;
            getViewState().showPreviewCart(cart);
        }
    }

    private void getCart () {
        getViewState().showProgressBar();
        DataHandler<Cart> handler = new DataHandler<Cart>() {
            @Override
            public void setDownloadedData(Cart data) {
                setCart(data);
            }
            @Override
            public void setErrorDownloaded(int errorStringId) {
                setError(errorStringId);
            }
        };
        model.downloadData(handler);
    }

    public void setCart(Cart cart){
        this.cart = cart;
        getViewState().hideProgressBar();
        getViewState().showCart(cart);
        model.setDataToCache(cart);
    }

    private void setError(int errorStringId){
        if (cart != null){
            setCart(cart);
        }
        getViewState().showErrorDialog(errorStringId);
    }

    public void errorDialogOnClick(){
        getCart();
    }

}
