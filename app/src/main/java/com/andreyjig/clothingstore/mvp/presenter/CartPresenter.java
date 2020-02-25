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
        setTitle(R.string.cart);
        model = new CartModel();
        setPreview();
        getData();
    }

    private void setPreview() {
        Cart cart = model.getCachedData();
        if (cart != null){
            this.cart = cart;
            getViewState().showPreviewCart(cart);
        }
    }

    @Override
    public void getData() {
        getViewState().showProgressBar();
        DataHandler<Cart> handler = new DataHandler<Cart>() {
            @Override
            public void setDownloadedData(Cart data) {
                setCart(data);
            }
            @Override
            public void setErrorDownloaded(int errorStringId) {
                setErrorDialog(errorStringId);
            }
        };
        model.downloadData(handler);
    }

    public void setCart(Cart cart){
        this.cart = cart;
        getViewState().hideProgressBar();
        getViewState().showCart(cart);
    }

    @Override
    public void setErrorDialog(int errorStringId){
        super.setErrorDialog(errorStringId);
        if (cart != null){
            setCart(cart);
        }
    }
}
