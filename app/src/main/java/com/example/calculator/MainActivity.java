package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator;
    private TextView inputTextView;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextView = findViewById(R.id.result_show_text_view);
        buttonReset = findViewById(R.id.button_reset);

        int[] numberButtons = new int[] {
                R.id.button_zero,
                R.id.button_first,
                R.id.button_second,
                R.id.button_three,
                R.id.button_four,
                R.id.button_five,
                R.id.button_six,
                R.id.button_seven,
                R.id.button_eight,
                R.id.button_nine,
        };

        int[] operationButtons = new int[] {
                R.id.button_plural,
                R.id.button_multiply,
                R.id.button_minus,
                R.id.button_division,
                R.id.button_equals
        };

        calculator = new CalculatorModel();


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

        buttonReset.setOnClickListener(v -> inputTextView.setText(""));

    }
}