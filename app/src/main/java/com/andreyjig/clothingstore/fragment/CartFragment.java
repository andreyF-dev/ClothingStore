package com.andreyjig.clothingstore.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andreyjig.clothingstore.utils.NetworkService;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.CartAdapter;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.shell.CartShell;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment implements CartAdapter.CartAdapterCallback {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Cart cart;
    private CartFragmentCallback callback;

    public CartFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CartFragmentCallback) {
            callback = (CartFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
    public static CartFragment newInstance() {
        return new CartFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.fragment_cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (cart == null){
            cart = new Cart();
            getCart();
        } else {
            setCartAdapter();
        }

        return view;
    }

    private void getCart() {
        NetworkService.newInstance()
                .getJSONApi()
                .getCart()
                .enqueue(new Callback<CartShell>() {
                    @Override
                    public void onResponse(Call<CartShell> call, Response<CartShell> response) {
                        cart = response.body().getCart();
                        if (getContext() != null){
                            setCartAdapter();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void setCartAdapter(){
        cartAdapter = new CartAdapter(getContext(), cart, CartFragment.this);
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void getCardDetails(int position) {
        int id = cart.getItems().get(position).getProductId();
        int color = cart.getItems().get(position).getProductVariant().getColorId();
        int size = cart.getItems().get(position).getProductVariant().getSizeId();
        callback.startDetail(id, color, size);
    }

    public interface CartFragmentCallback {
        void startDetail(int id, int color, int size);
    }
}
