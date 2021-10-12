package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private MaterialButton backToMainActivityBtn;
    private SwitchMaterial themeSelectedSwitch;

    public static final String MY_PREFERENCES = "my_settings";
    protected static final String KEY_SWITCH_INDEX = "save_switch_index";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();

        loadPreferences();

        setUpSwitchController();


        backToMainActivityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void savePreferences(String key, boolean value) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public void loadPreferences() {

        SharedPreferences preferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        boolean isChosen = preferences.getBoolean(KEY_SWITCH_INDEX, false);
        themeSelectedSwitch.setChecked(isChosen);

    }

    public void initViews() {

        backToMainActivityBtn = findViewById(R.id.back_main_activity_button);
        themeSelectedSwitch = findViewById(R.id.control_dark_theme_switch);

    }

    public void setUpSwitchController() {
        themeSelectedSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            savePreferences(KEY_SWITCH_INDEX, isChecked);
        });
    }

}