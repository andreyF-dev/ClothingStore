package com.andreyjig.clothingstore.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.andreyjig.clothingstore.ui.adapter.holder.ProductCardHolder;
import com.andreyjig.clothingstore.entity.ItemCard;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.ui.adapter.CartAdapter;
import com.andreyjig.clothingstore.entity.Cart;
import com.andreyjig.clothingstore.mvp.presenter.CartPresenter;
import com.andreyjig.clothingstore.mvp.view.CartView;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class CartFragment extends BaseHandlerFragment implements
        ProductCardHolder.CardHolderCallback, CartView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Boolean disableRecyclerView;

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
        disableRecyclerView = false;
        progressBar = view.findViewById(R.id.fragment_cart_progress_bar);
        recyclerView = view.findViewById(R.id.fragment_cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return disableRecyclerView;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public void showPreviewCart(Cart cart) {
        recyclerView.setAlpha(0.1f);
        disableRecyclerView = true;
        setAdapter(cart);
    }

    @Override
    public void showCart(Cart cart) {
        recyclerView.setAlpha(1f);
        disableRecyclerView = false;
        setAdapter(cart);
    }

    private void setAdapter(Cart cart){
        CartAdapter cartAdapter = new CartAdapter(getContext(), cart, CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
    }
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
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
    protected void onErrorDialogClick() {
        presenter.setErrorActionClick();
    }
}
