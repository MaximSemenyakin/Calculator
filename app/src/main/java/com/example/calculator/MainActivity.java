package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator = new CalculatorModel();
    private TextView inputTextView;
    private ImageButton settingsImageButton;
    private Button resetButton;

    private static final String FIRST_ARG = "first_arg";
    SharedPreferences myPreferences;
    public static final String MY_PREFERENCES = "my_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myPreferences = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);
        myPreferences.getBoolean(SettingsActivity.KEY_SWITCH_INDEX, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(FIRST_ARG)) {
            calculator = savedInstanceState.getParcelable(FIRST_ARG);
            inputTextView.setText(calculator.getText());
        }

        settingsImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        setTextCalculator();

        clearTextView();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(FIRST_ARG, calculator);
    }

    public void setTextCalculator() {
        View.OnClickListener numberButtonOnClick = v -> {
            calculator.onNumberPressed(v.getId());
            inputTextView.setText(calculator.getText());
        };

        View.OnClickListener operationButtonOnClick = v -> {
            calculator.onOperationPressed(v.getId());
            inputTextView.setText(calculator.getText());
        };

        for (int numberButton : numberButtons) {
            findViewById(numberButton).setOnClickListener(numberButtonOnClick);
        }

        for (int operationButton : operationButtons) {
            findViewById(operationButton).setOnClickListener(operationButtonOnClick);
        }
    }

    int[] numberButtons = new int[]{
            R.id.digit_button_zero,
            R.id.digit_button_first,
            R.id.digit_button_second,
            R.id.digit_button_three,
            R.id.digit_button_four,
            R.id.digit_button_five,
            R.id.digit_button_six,
            R.id.digit_button_seven,
            R.id.digit_button_eight,
            R.id.digit_button_nine,
    };

    int[] operationButtons = new int[]{
            R.id.digit_button_plural,
            R.id.digit_button_multiply,
            R.id.digit_button_minus,
            R.id.digit_button_division,
            R.id.digit_button_equals
    };

    public void initViews() {

        inputTextView = findViewById(R.id.result_show_text_view);
        resetButton = findViewById(R.id.digit_button_reset);
        settingsImageButton = findViewById(R.id.settings_image_button);

    }

    public void clearTextView() {
        resetButton.setOnClickListener(v -> {
            calculator.reset();
            inputTextView.setText(calculator.getText()); });
    }

}