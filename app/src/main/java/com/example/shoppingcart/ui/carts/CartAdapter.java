package com.example.shoppingcart.ui.carts;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.ShoppingCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<ShoppingCart> carts;
    private String username;

    CartAdapter(ArrayList<ShoppingCart> carts, String username) {

        this.carts = carts;
        this.username = username;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cartId.setText("Shopping Cart #" + Integer.toString(carts.get(position).getCartId()));
        if (carts.get(position).isAvailable()) {
            holder.icon.setImageResource(R.drawable.icons8_done_48);
        }
        else {
            holder.icon.setImageResource(R.drawable.icons8_cross_mark_48);
            holder.reservedBy.setText("Reserved by:\n" + username);
        }

    }

    public void onUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return carts.size();
    }

    public void setCartsList(ArrayList<ShoppingCart> carts) {
        this.carts.clear();
        for (ShoppingCart cart: carts) {
            this.carts.add(cart);
        }
        Log.i("CartsInList", Integer.toString(this.carts.size()));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView cartId;
        private final ImageView icon;
        private final TextView reservedBy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartId = itemView.findViewById(R.id.cart_id);
            icon = itemView.findViewById(R.id.cart_icon);
            reservedBy = itemView.findViewById(R.id.text_reserved_by);
        }
    }
}
