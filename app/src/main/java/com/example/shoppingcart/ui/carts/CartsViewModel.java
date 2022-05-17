package com.example.shoppingcart.ui.carts;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.persistence.CartRepository;

import java.util.List;

public class CartsViewModel extends AndroidViewModel {

    private final CartRepository repository;

    public CartsViewModel(Application application) {
        super(application);
        repository = CartRepository.getInstance(application);
    }

    public LiveData<List<ShoppingCart>> getAllCarts() {
        LiveData<List<ShoppingCart>> shoppingCarts = repository.getAllCarts();

        return shoppingCarts;
    }

    public void createCart(ShoppingCart shoppingCart) {
        repository.insert(shoppingCart);
    }

    public void deleteCarts() {
        repository.delete();
    }
}
