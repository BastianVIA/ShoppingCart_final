package com.example.shoppingcart.ui.unlock;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppingcart.R;
import com.example.shoppingcart.ShoppingCart;
import com.example.shoppingcart.SignInViewModel;
import com.example.shoppingcart.UserLiveData;
import com.example.shoppingcart.databinding.FragmentUnlockBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class UnlockFragment extends Fragment{

    private FragmentUnlockBinding binding;
    private ImageButton scanBtn;
    private String qrResult;
    private UnlockViewModel unlockViewModel;
    private SignInViewModel signInViewModel;
    private String username;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        unlockViewModel = new ViewModelProvider(this).get(UnlockViewModel.class);
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = FragmentUnlockBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        scanBtn = root.findViewById(R.id.qr_button);

        scanBtn.setOnClickListener(v -> scanCode());

        return root;
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        qrLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> qrLauncher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
            qrResult = result.getContents();
            Log.i("QR_READ", qrResult);

            ShoppingCart shoppingCart = new ShoppingCart(Integer.parseInt(qrResult));
            shoppingCart.setAvailable(false);

            signInViewModel.getCurrentUser().observe(this, user -> {
                username = user.getDisplayName();
            });
            shoppingCart.setReservedBy(username);
            unlockViewModel.unlockCart(shoppingCart);
        }
    });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}