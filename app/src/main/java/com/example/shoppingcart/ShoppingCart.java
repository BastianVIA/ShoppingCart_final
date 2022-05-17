package com.example.shoppingcart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class ShoppingCart {

    @PrimaryKey
    private int cartId;
    private boolean isAvailable;
    private String reservedBy;

    public ShoppingCart(int cartId) {
        this.cartId = cartId;
        this.isAvailable = true;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }
}
