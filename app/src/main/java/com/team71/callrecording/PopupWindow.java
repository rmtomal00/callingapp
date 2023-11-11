package com.team71.callrecording;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.team71.callrecording.databinding.ActivityPopupWindowBinding;

public class PopupWindow extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivityPopupWindowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding = ActivityPopupWindowBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.TOP | Gravity.START; // Set the position of the window
        params.x = 0; // X coordinate
        params.y = 0; // Y coordinate
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // Adjust other window properties as needed
        // For instance, you can add animations, set flags, etc.

        getWindow().setAttributes(params);
    }
}