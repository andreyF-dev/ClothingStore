package com.andreyjig.clothingstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.holder.CardHolder;
import com.andreyjig.clothingstore.adapter.holder.PlaceOrderHolder;
import com.andreyjig.clothingstore.adapter.holder.PriceHolder;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.ItemCard;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_CARD = 0;
    private final int TYPE_TOTAL_PRICE = 1;
    private final int TYPE_PLACE_ORDER = 2;

    private final String PLACE_ORDER = "place_order";

    private Context context;
    private ArrayList<Object> adapterList;

    public CartAdapter(Context context, Cart cart) {
        this.context = context;
        createAdapterList(cart);
    }

    private void createAdapterList(Cart cart){
        if (cart != null && cart.getItems().size() > 0){
            adapterList = new ArrayList<>();
            adapterList.addAll(cart.getItems());
            adapterList.add(cart.getTotalPrice());
            adapterList.add(PLACE_ORDER);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case TYPE_CARD:
                view = LayoutInflater.from(context).inflate(R.layout.item_purchase_card, parent, false);
                return new CardHolder(view, context);
            case TYPE_TOTAL_PRICE:
                view = LayoutInflater.from(context).inflate(R.layout.item_price, parent, false);
                return new PriceHolder(view, context);
            case TYPE_PLACE_ORDER:
                view = LayoutInflater.from(context).inflate(R.layout.item_place_order, parent, false);
                return new PlaceOrderHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_CARD:
                ((CardHolder)holder).setHolder((ItemCard)adapterList.get(position));
                break;
            case TYPE_TOTAL_PRICE:
                ((PriceHolder)holder).setHolder((Integer)adapterList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (adapterList != null){
            return adapterList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (adapterList.get(position) instanceof ItemCard){
            return TYPE_CARD;
        } else if (adapterList.get(position) instanceof Integer){
            return TYPE_TOTAL_PRICE;
        } else {
            return TYPE_PLACE_ORDER;
        }
    }


}
