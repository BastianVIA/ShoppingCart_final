package com.example.shoppingcart.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppingcart.ShoppingCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShoppingCart shoppingCart);

    @Update
    void update(ShoppingCart shoppingCart);

    @Delete
    void delete(ShoppingCart shoppingCart);

    @Query("SELECT * FROM cart_table")
    LiveData<List<ShoppingCart>> getAllCarts();

    @Query("Delete FROM cart_table")
    void deleteAllCarts();
}
