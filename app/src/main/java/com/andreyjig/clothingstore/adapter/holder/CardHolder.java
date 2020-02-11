package com.andreyjig.clothingstore.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.andreyjig.clothingstore.R;
import com.andreyjig.clothingstore.fragment.CartFragmentDirections;
import com.andreyjig.clothingstore.model.ItemCard;
import com.andreyjig.clothingstore.utils.ColorDrawer;
import com.squareup.picasso.Picasso;

public class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView textViewName;
    private TextView textViewCount;
    private TextView textViewPrice;
    private TextView textViewManufacturer;
    private FrameLayout frameLayout;

    private Context context;
    private ItemCard card;


    public CardHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_card_image);
        textViewName = itemView.findViewById(R.id.item_card_text_name);
        textViewCount = itemView.findViewById(R.id.item_card_text_count);
        textViewPrice = itemView.findViewById(R.id.item_card_text_price);
        textViewManufacturer = itemView.findViewById(R.id.item_card_text_manufacturer);
        frameLayout = itemView.findViewById(R.id.item_card_layout_for_color);

        this.context = context;

        itemView.setOnClickListener(this);
    }

    public void setHolder (ItemCard card){

        this.card = card;

        textViewName.setText(card.getProductVariant().getName());
        String countText = String.format(context.getString(R.string.count), card.getCount());
        textViewCount.setText(countText);
        String priceText = String.format(context.getString(R.string.price),
                card.getProductVariant().getPrice());
        textViewPrice.setText(priceText);
        textViewManufacturer.setText(card.getProduct().getManufacturer().getName());
        try {
            String imageUrl = card.getProductVariant().getPhotos().get(0).getSmall();
            Picasso.get()
                    .load(imageUrl)
                    .into(imageView);
        } catch (Exception e){
            e.printStackTrace();
        }

        String color = card.getProductVariant().getColor().getHashCode();
        frameLayout.addView(new ColorDrawer(context, color));
    }

    @Override
    public void onClick(View v) {

        CartFragmentDirections.ActionCartFragmentToProductFragment action =
                CartFragmentDirections.actionCartFragmentToProductFragment();
        action.setProductId(card.getProductId())
                .setVariantId(card.getProductVariantId())
                .setName(card.getProduct().getName());
        Navigation.findNavController(v).navigate(action);

    }

}