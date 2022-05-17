package com.example.shoppingcart.ui.information;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.persistence.CartRepository;

import java.util.List;

public class InformationViewModel extends AndroidViewModel {


    private final MutableLiveData<String> openingHours;
    private final MutableLiveData<String> openingHoursTitle;
    private final MutableLiveData<String> rush;
    private CartRepository repository;

    public InformationViewModel(Application application) {
        super(application);
        repository = CartRepository.getInstance(application);
        openingHoursTitle = new MutableLiveData<>();
        openingHoursTitle.setValue("Opening Hours");
        openingHours = new MutableLiveData<>();
        openingHours.setValue(
                "Monday: 08:00 - 20:00\n" +
                "Tuesday: 08:00 - 20:00\n" +
                "Wednesday: 08:00 - 20:00\n" +
                "Thursday: 08:00 - 20:00\n" +
                "Friday: 08:00 - 20:00\n" +
                "Saturday: 10:00 - 15:00\n" +
                "Sunday: Closed");
        rush = new MutableLiveData<>();
    }

    public LiveData<String> getOpeningHoursTitle() {
        return openingHoursTitle;
    }

    public LiveData<String> getOpeningHours() {
        return openingHours;
    }

    public LiveData<List<ShoppingCart>> getAllCarts() {
        LiveData<List<ShoppingCart>> shoppingCarts = repository.getAllCarts();

        return shoppingCarts;
    }
}