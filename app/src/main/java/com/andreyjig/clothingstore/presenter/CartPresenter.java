package com.andreyjig.clothingstore.presenter;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.database.RealmHelper;
import com.andreyjig.clothingstore.model.handler.CartHandler;
import com.andreyjig.clothingstore.model.CartModel;
import com.andreyjig.clothingstore.ui.view.CartView;
import com.andreyjig.clothingstore.entity.Cart;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartPresenter extends MvpPresenter<CartView>{

    private CartModel cartModel;
    private Cart cart;
    private RealmHelper realmHelper;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().updateTitle(R.string.cart);
        cartModel = new CartModel();
        realmHelper = RealmHelper.getInstance();
        setPreview();
        getCart();
    }

    private void setPreview() {
        Cart cart = realmHelper.getCachedCart();
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
        realmHelper.setCashedCart(cart);
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
