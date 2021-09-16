package com.example.calculator;

public class CalculatorModel {

    private double firstArg;
    private double secondArg;

    private State state;
    private int actionSelected;

    private StringBuilder strInput = new StringBuilder();

    private enum State {
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    public void onNumberPressed(int numId) {
        if (state == State.resultShow) {
            state = State.firstArgInput;
            strInput.setLength(0);
        }

        if (strInput.length() < 9) {
            switch (numId) {
                case R.id.button_zero:
                    if (strInput.length() != 0) {
                        strInput.append("0");
                    }
                    break;
                case R.id.button_first:
                    strInput.append("1");
                    break;
                case R.id.button_second:
                    strInput.append("2");
                    break;
                case R.id.button_three:
                    strInput.append("3");
                    break;
                case R.id.button_four:
                    strInput.append("4");
                    break;
                case R.id.button_five:
                    strInput.append("5");
                    break;
                case R.id.button_six:
                    strInput.append("6");
                    break;
                case R.id.button_seven:
                    strInput.append("7");
                    break;
                case R.id.button_eight:
                    strInput.append("8");
                    break;
                case R.id.button_nine:
                    strInput.append("9");
                    break;
            }
        }
    }

    public void onOperationPressed(int operationId) {
        if (operationId == R.id.button_equals && state == State.secondArgInput) {
            secondArg = Integer.parseInt(strInput.toString());
            state = State.resultShow;
            strInput.setLength(0);
            switch (actionSelected) {
                case R.id.button_minus:
                    strInput.append(firstArg - secondArg);
                    break;
                case R.id.button_plural:
                    strInput.append(firstArg + secondArg);
                    break;
                case R.id.button_multiply:
                    strInput.append(firstArg * secondArg);
                    break;
                case R.id.button_division:
                    strInput.append(firstArg / secondArg);
                    break;
            }
        } else if (strInput.length() > 0 && state == State.firstArgInput) {
            firstArg = Integer.parseInt(strInput.toString());
            state = State.secondArgInput;
            strInput.setLength(0);
            switch (operationId) {
                case R.id.button_plural:
                    actionSelected = R.id.button_plural;
                    break;
                case R.id.button_multiply:
                    actionSelected = R.id.button_multiply;
                    break;
                case R.id.button_division:
                    actionSelected = R.id.button_division;
                case R.id.button_minus:
                    actionSelected = R.id.button_minus;
                    break;
            }
        }
    }

    public String getText() {
        return strInput.toString();
    }


}
