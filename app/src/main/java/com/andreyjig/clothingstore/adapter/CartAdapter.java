package com.andreyjig.clothingstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.adapter.holder.ProductCardHolder;
import com.andreyjig.clothingstore.adapter.holder.PlaceOrderHolder;
import com.andreyjig.clothingstore.adapter.holder.PriceHolder;
import com.andreyjig.clothingstore.adapter.model.PlaceOrder;
import com.andreyjig.clothingstore.adapter.model.TotalPrice;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.ItemCard;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_CARD = 0;
    private final int TYPE_TOTAL_PRICE = 1;
    private final int TYPE_PLACE_ORDER = 2;
    private Context context;
    private ArrayList<Object> adapterList;
    private ProductCardHolder.CardHolderCallback callback;

    public CartAdapter(Context context, Cart cart, ProductCardHolder.CardHolderCallback callback) {
        this.context = context;
        this.callback = callback;
        createAdapterList(cart);
    }

    private void createAdapterList(Cart cart){
        if (cart != null && cart.getItems().size() > 0){
            adapterList = new ArrayList<>();
            adapterList.addAll(cart.getItems());
            adapterList.add(new TotalPrice(cart.getTotalPrice()));
            adapterList.add(new PlaceOrder());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_CARD:
                view = LayoutInflater.from(context).inflate(R.layout.item_purchase_card, parent, false);
                return new ProductCardHolder(view, context, callback);
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
                ((ProductCardHolder)holder).bind((ItemCard)adapterList.get(position));
                break;
            case TYPE_TOTAL_PRICE:
                ((PriceHolder)holder).bind((TotalPrice)adapterList.get(position));
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
        Object object = adapterList.get(position);
        if (object instanceof ItemCard){
            return TYPE_CARD;
        } else if (object instanceof TotalPrice){
            return TYPE_TOTAL_PRICE;
        } else if (object instanceof PlaceOrder){
            return TYPE_PLACE_ORDER;
        }
        return 0;
    }
}
