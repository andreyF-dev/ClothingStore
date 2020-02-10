package com.andreyjig.clothingstore.fragment;

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
import com.andreyjig.clothingstore.utils.SnackBarHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment{

    private RecyclerView recyclerView;
    private Cart cart;
    private Snackbar snackbar;
    private View.OnClickListener snackBarOnClickListener;

    public CartFragment(){
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

        snackBarOnClickListener = v -> getCart();

        Objects.requireNonNull(getActivity()).setTitle(R.string.cart);
        if (cart == null){
            cart = new Cart();
            getCart();
        } else {
            setCartAdapter();
        }

        return view;
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
                                setCartAdapter();
                            }
                        } else {
                            errorLoading();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartShell> call, Throwable t) {
                        t.printStackTrace();
                        errorLoading();
                    }
                });
    }

    private void setCartAdapter(){
        CartAdapter cartAdapter = new CartAdapter(getContext(), cart);
        recyclerView.setAdapter(cartAdapter);
    }

    private void errorLoading() {

        if (getContext() != null) {
            snackbar = SnackBarHelper.showSnackbar(getContext(), recyclerView, snackBarOnClickListener);
            snackbar.show();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
}
