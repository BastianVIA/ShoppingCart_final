package com.example.shoppingcart.ui.unlock;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.UserLiveData;
import com.example.shoppingcart.UserRepository;
import com.example.shoppingcart.persistence.CartRepository;

import java.util.List;

public class UnlockViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private final CartRepository cartRepository;

    public UnlockViewModel(Application application) {
        super(application);
        cartRepository = CartRepository.getInstance(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is unlock fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void unlockCart(ShoppingCart shoppingCart) {
        cartRepository.update(shoppingCart);
    }

    public void createCart(final ShoppingCart shoppingCart) {
        cartRepository.insert(shoppingCart);
    }

    public LiveData<List<ShoppingCart>> getAllCarts() {
        return cartRepository.getAllCarts();
    }
}