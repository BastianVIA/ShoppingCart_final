package com.example.shoppingcart.persistence;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;

import com.example.shoppingcart.ShoppingCart;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartRepository {

    private static CartRepository instance;
    private final CartDAO cartDAO;
    private final LiveData<List<ShoppingCart>> allCarts;
    private final ExecutorService executorService;

    private CartRepository(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDAO = database.cartDAO();
        allCarts = cartDAO.getAllCarts();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized CartRepository getInstance(Application application) {
        if (instance == null)  {
            instance = new CartRepository(application);
        }
        return instance;
    }

    public LiveData<List<ShoppingCart>> getAllCarts() {
        return allCarts;
    }

    public void insert(ShoppingCart shoppingCart)  {
        try {
            executorService.execute(() -> cartDAO.insert(shoppingCart));
        } catch (Exception e) {
            throw new SQLiteConstraintException();
        }

    }

    public void update(ShoppingCart shoppingCart) {
        executorService.execute(() -> cartDAO.update(shoppingCart));
    }

    public void delete() {
        executorService.execute(() -> cartDAO.deleteAllCarts());
    }
}
