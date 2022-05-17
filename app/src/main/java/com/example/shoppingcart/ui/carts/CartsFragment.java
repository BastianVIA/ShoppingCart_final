package com.example.shoppingcart.ui.carts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.SignInViewModel;
import com.example.shoppingcart.databinding.FragmentCartsBinding;

import java.util.ArrayList;
import java.util.List;


public class CartsFragment extends Fragment {

    private FragmentCartsBinding binding;
    private Button createCartsBtn;
    private Button deleteCartsBtn;
    private CartsViewModel cartsViewModel;
    private SignInViewModel signInViewModel;
    private RecyclerView cartList;
    private CartAdapter cartAdapter;
    private String username;
    private ArrayList<ShoppingCart> carts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartsViewModel = new ViewModelProvider(this).get(CartsViewModel.class);
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = FragmentCartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createCartsBtn = root.findViewById(R.id.button_createCarts);
        deleteCartsBtn = root.findViewById(R.id.button_deleteCarts);

        createCartsBtn.setOnClickListener(v -> createCarts());
        deleteCartsBtn.setOnClickListener(v -> deleteCarts());

        cartList = root.findViewById(R.id.rv);
        cartList.hasFixedSize();
        cartList.setLayoutManager(new LinearLayoutManager(getContext()));

        signInViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            username = user.getDisplayName();
        });

        cartAdapter = new CartAdapter(carts, username);
        cartList.setAdapter(cartAdapter);

        createCarts();

        cartsViewModel.getAllCarts().observe(getViewLifecycleOwner(), new Observer<List<ShoppingCart>>() {
            @Override
            public void onChanged(List<ShoppingCart> shoppingCarts) {
                cartAdapter.setCartsList(carts);
                cartAdapter.setUsername(username);
                cartAdapter.onUpdate();
            }
        });

        cartsViewModel.getAllCarts().observe(getViewLifecycleOwner(), carts -> {
                for (ShoppingCart s : carts) {
                    this.carts.add(s);
                }
        });

        return root;
    }

    private void deleteCarts() {
        carts.clear();
        cartsViewModel.deleteCarts();
    }

    private void createCarts() {
        if (carts.isEmpty()) {
            carts.add(new ShoppingCart(1));
            carts.add(new ShoppingCart(2));
            carts.add(new ShoppingCart(3));
            carts.add(new ShoppingCart(4));
            carts.add(new ShoppingCart(5));
            carts.add(new ShoppingCart(6));
            carts.add(new ShoppingCart(7));
            carts.add(new ShoppingCart(8));
            carts.get(2).setAvailable(false);
            carts.get(6).setAvailable(false);
            carts.get(7).setAvailable(false);

        }
        for (ShoppingCart cart: carts) {
            cartsViewModel.createCart(cart);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
