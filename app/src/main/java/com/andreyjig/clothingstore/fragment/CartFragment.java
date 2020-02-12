package com.andreyjig.clothingstore.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.andreyjig.clothingstore.adapter.holder.ProductCardHolder;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.shell.CartShell;
import com.andreyjig.clothingstore.utils.SetToolbarNameListener;
import com.andreyjig.clothingstore.utils.FragmentWithErrorHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends FragmentWithErrorHandler implements ProductCardHolder.CardHolderCallback {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Cart cart;

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
        Context context = getContext();
        ((SetToolbarNameListener)context).setNameToolbar(context.getString(R.string.cart));
        if (cart == null){
            cart = new Cart();
            getCart();
            progressBar.setVisibility(View.VISIBLE);
        } else {
            setCartAdapter();
        }
    }

    private void getCart() {
        NetworkService.getInstance()
                .getJSONApi()
                .getCart()
                .enqueue(new Callback<CartShell>() {
                    @Override
                    public void onResponse(Call<CartShell> call, Response<CartShell> response) {

                        if (response.isSuccessful()) {
                            cart = response.body().getCart();
                            if (getContext() != null) {
                                progressBar.setVisibility(View.GONE);
                                setCartAdapter();
                            }
                        } else {
                            getErrorDialog(v -> getCart());
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                        getErrorDialog(v -> getCart());
                    }
                });
    }

    private void setCartAdapter(){
        CartAdapter cartAdapter = new CartAdapter(getContext(), cart, CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void startProductionFragment(ItemCard card) {
        CartFragmentDirections.ActionCartFragmentToProductFragment action =
                CartFragmentDirections.actionCartFragmentToProductFragment();
        action.setProductId(card.getProductId())
                .setVariantId(card.getProductVariantId())
                .setName(card.getProduct().getName());
        Navigation.findNavController(getView()).navigate(action);
    }
}
