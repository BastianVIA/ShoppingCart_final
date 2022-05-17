package com.example.shoppingcart.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.databinding.FragmentInformationBinding;

public class InformationFragment extends Fragment {

    private FragmentInformationBinding binding;
    private int cartsInUse = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InformationViewModel informationViewModel =
                new ViewModelProvider(this).get(InformationViewModel.class);

        binding = FragmentInformationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView_openingHoursTitle = binding.textOpeningHoursTitle;
        informationViewModel.getOpeningHoursTitle().observe(getViewLifecycleOwner(), textView_openingHoursTitle::setText);
        final TextView textView_openingHours = binding.textOpeningHours;
        informationViewModel.getOpeningHours().observe(getViewLifecycleOwner(), textView_openingHours::setText);
        final TextView textView_rush = binding.textRush;

        informationViewModel.getAllCarts().observe(getViewLifecycleOwner(), carts -> {
            for (ShoppingCart cart: carts ) {
                if (!cart.isAvailable()) {
                    cartsInUse++;
                }
            }
         double rushScore = (double) cartsInUse/ (double) carts.size();
            if (rushScore >= 0.5) {
                textView_rush.setText(cartsInUse + "/" + carts.size() + " carts in use, the store is busy");
            }else {
                textView_rush.setText(cartsInUse + "/" + carts.size() + " carts in use, the store is not busy, now would be a great time to do your groceries");
            }

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}