package com.andreyjig.clothingstore.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.andreyjig.clothingstore.adapter.holder.ProductCardHolder;
import com.andreyjig.clothingstore.fragment.presenters.CartFragmentPresenter;
import com.andreyjig.clothingstore.fragment.presenters.ProductDescriptionFragmentPresenter;
import com.andreyjig.clothingstore.fragment.views.CartFragmentView;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.utils.SetToolbarNameListener;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

public class CartFragment extends FragmentWithErrorHandler implements ProductCardHolder.CardHolderCallback,
        CartFragmentView{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @InjectPresenter
    CartFragmentPresenter presenter;

    @ProvidePresenter
    public CartFragmentPresenter providePresenter (){
        return new CartFragmentPresenter( this);
    }

    public CartFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        ((SetToolbarNameListener)getContext()).setNameToolbar(getContext().getString(R.string.cart));
    }

    @Override
    public void startProductionFragment(ItemCard card) {
        presenter.itemCardSelected(card, getView());
    }

    @Override
    public void setCartAdapter(Cart cart) {
        CartAdapter cartAdapter = new CartAdapter(getContext(), cart, CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void progressBarVisibility(int state) {
        progressBar.setVisibility(state);
    }
}
