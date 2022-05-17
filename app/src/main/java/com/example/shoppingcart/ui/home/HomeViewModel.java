package com.example.shoppingcart.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to the SoppingCart app!\n\nYou can unlock your shopping cart by tapping the unlock button in the left hand menu.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}