package com.andreyjig.clothingstore.fragment.presenters;

import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.andreyjig.clothingstore.fragment.CartFragmentDirections;
import com.andreyjig.clothingstore.fragment.model.CartFragmentPresenterInterface;
import com.andreyjig.clothingstore.fragment.model.ErrorHandlerInterface;
import com.andreyjig.clothingstore.fragment.views.CartFragmentView;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartFragmentPresenter extends MvpPresenter<CartFragmentView>
implements CartFragmentPresenterInterface {

    private ErrorHandlerView errorHandlerView;

    public CartFragmentPresenter(ErrorHandlerView errorHandlerView) {
        this.errorHandlerView = errorHandlerView;
        getViewState().progressBarVisibility(View.VISIBLE);
        getCart();
    }

    public void getCart () {
        NetworkService.getInstance().getCart(this);
    }

    @Override
    public void setErrorDialog() {
        errorHandlerView.getErrorDialog(() -> getCart());
    }

    @Override
    public void setCart(Cart cart) {
        getViewState().progressBarVisibility(View.INVISIBLE);
        getViewState().setCartAdapter(cart);
    }

    public void itemCardSelected(ItemCard card, View view){
        CartFragmentDirections.ActionCartFragmentToProductFragment action =
                CartFragmentDirections.actionCartFragmentToProductFragment();
        action.setProductId(card.getProductId())
                .setVariantId(card.getProductVariantId())
                .setName(card.getProduct().getName());
        Navigation.findNavController(view).navigate(action);
    }

}
