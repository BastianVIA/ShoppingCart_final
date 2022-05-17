package com.example.shoppingcart.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppingcart.ShoppingCart;

@Database(entities = {ShoppingCart.class}, version = 2)
public abstract class CartDatabase extends RoomDatabase {

    private static CartDatabase instance;
    public abstract  CartDAO cartDAO();

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CartDatabase.class, "cart_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
