package com.andreyjig.clothingstore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.model.Cart;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.utils.ColorDrawer;
import com.squareup.picasso.Picasso;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_CARD = 0;
    private final int TYPE_TOTAL_PRICE = 1;
    private final int TYPE_PLACE_ORDER = 2;

    private Cart cart;
    private Context context;
    private CartAdapterCallback callback;

    public CartAdapter(Context context, Cart cart, CartAdapterCallback callback) {
        this.context = context;
        this.cart = cart;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
            case TYPE_CARD:
                view = LayoutInflater.from(context).inflate(R.layout.item_purchase_card, parent, false);
                return new CardHolder(view);
            case TYPE_TOTAL_PRICE:
                view = LayoutInflater.from(context).inflate(R.layout.item_price, parent, false);
                return new PriceHolder(view);
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
                ItemCard card = cart.getItems().get(position);
                ((CardHolder)holder).textViewName.setText(card.getProductVariant().getName());
                String countText = String.format(context.getString(R.string.count), card.getCount());
                ((CardHolder)holder).textViewCount.setText(countText);
                String priceText = String.format(context.getString(R.string.price),
                        card.getProductVariant().getPrice());
                ((CardHolder)holder).textViewPrice.setText(priceText);
                ((CardHolder)holder).textViewManufacturer.setText(card.getProduct().getManufacturer().getName());
                try {
                    String imageUrl = card.getProductVariant().getPhotos().get(0).getSmall();
                    Picasso.get()
                            .load(imageUrl)
                            .into(((CardHolder)holder).imageView);
                } catch (Exception e){
                    e.printStackTrace();
                }

                String color = card.getProductVariant().getColor().getHashCode();
                ((CardHolder)holder).frameLayout.addView(new ColorDrawer(context, color));
                break;
            case TYPE_TOTAL_PRICE:
                String text = String.format(context.getString(R.string.total_price), cart.getTotalPrice());
                ((PriceHolder)holder).textView.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        if (cart == null || cart.getItems().size() == 0){
            return 0;
        }
        return (cart.getItems().size() + 2);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < (getItemCount() - 2)){
            Log.d("MyRetrofit", "Type_Card size " + cart.getItems().size() + " size rv " + getItemCount());
            return TYPE_CARD;
        } else if (position == (getItemCount() - 2)){
            Log.d("MyRetrofit", "Type_total_price");
            return TYPE_TOTAL_PRICE;
        } else {
            Log.d("MyRetrofit", "Type_order");
            return TYPE_PLACE_ORDER;
        }
    }

    public class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textViewName;
        TextView textViewCount;
        TextView textViewPrice;
        TextView textViewManufacturer;
        FrameLayout frameLayout;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_card_image);
            textViewName = itemView.findViewById(R.id.item_card_text_name);
            textViewCount = itemView.findViewById(R.id.item_card_text_count);
            textViewPrice = itemView.findViewById(R.id.item_card_text_price);
            textViewManufacturer = itemView.findViewById(R.id.item_card_text_manufacturer);
            frameLayout = itemView.findViewById(R.id.item_card_layout_for_color);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.getCardDetails(getAdapterPosition());
        }
    }

    public class PriceHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PriceHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_price_text);
        }
    }

    public class PlaceOrderHolder extends RecyclerView.ViewHolder {

        public PlaceOrderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface CartAdapterCallback{
        void getCardDetails(int position);
    }
}
