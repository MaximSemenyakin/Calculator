package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator = new CalculatorModel();
    private TextView inputTextView;
    private Button buttonReset;

    private static final String FIRST_ARG = "first_arg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextView = findViewById(R.id.result_show_text_view);
        buttonReset = findViewById(R.id.digit_button_reset);

        if (savedInstanceState != null && savedInstanceState.containsKey(FIRST_ARG)) {
            calculator = savedInstanceState.getParcelable(FIRST_ARG);
            inputTextView.setText(calculator.getText());
        }

        setTextCalculator();

        buttonReset.setOnClickListener(v -> {
            calculator.reset();
            inputTextView.setText(calculator.getText()); });

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

        for (int i = 0; i < numberButtons.length; i++) {
            findViewById(numberButtons[i]).setOnClickListener(numberButtonOnClick);
        }

        for (int i = 0; i < operationButtons.length; i++) {
            findViewById(operationButtons[i]).setOnClickListener(operationButtonOnClick);
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

}