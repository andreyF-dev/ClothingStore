package com.andreyjig.clothingstore.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.andreyjig.clothingstore.adapter.holder.ProductCardHolder;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.presenters.CartPresenter;
import com.andreyjig.clothingstore.views.CartView;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class CartFragment extends BaseHandlerFragment implements
        ProductCardHolder.CardHolderCallback, CartView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @InjectPresenter
    CartPresenter presenter;


    public CartFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.fragment_cart_progress_bar);
        recyclerView = view.findViewById(R.id.fragment_cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setCart(Cart cart) {
        CartAdapter cartAdapter = new CartAdapter(getContext(), cart, CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void progressBarVisibility() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressBarInvisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void productCardHolderClick(ItemCard card) {
        if (getView() != null) {
            CartFragmentDirections.ActionCartFragmentToProductFragment action =
                    CartFragmentDirections.actionCartFragmentToProductFragment();
            action.setProductId(card.getProductId())
                    .setVariantId(card.getProductVariantId())
                    .setName(card.getProduct().getName());
            Navigation.findNavController(getView()).navigate(action);
        }
    }

    @Override
    public void errorDialogOnClick() {
        presenter.errorDialogOnClick();
    }
}
