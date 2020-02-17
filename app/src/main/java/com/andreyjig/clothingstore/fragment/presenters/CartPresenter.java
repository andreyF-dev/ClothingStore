package com.andreyjig.clothingstore.fragment.presenters;

import android.view.View;
import androidx.navigation.Navigation;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.fragment.CartFragmentDirections;
import com.andreyjig.clothingstore.fragment.views.CartView;
import com.andreyjig.clothingstore.fragment.views.ErrorHandlerView;
import com.andreyjig.clothingstore.activity.views.TitleHandlerView;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class CartPresenter extends MvpPresenter<CartView>{

    private ErrorHandlerView errorHandlerView;
    private TitleHandlerView titleHandlerView;

    public CartPresenter(ErrorHandlerView errorHandlerView, TitleHandlerView titleHandlerView) {
        this.errorHandlerView = errorHandlerView;
        this.titleHandlerView = titleHandlerView;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().progressBarVisibility();
        getCart();
    }

    @Override
    public void attachView(CartView view) {
        super.attachView(view);
        titleHandlerView.getTitle(R.string.cart);
    }

    private void getCart () {
        NetworkService.getInstance().getCart(this::setCart);
    }

    public void setCart(Cart cart) {
        if (cart != null) {
            getViewState().progressBarInvisible();
            getViewState().setCart(cart);
        } else {
            errorHandlerView.getShowErrorDialog(this::getCart);
        }
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
