package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class CalculatorModel implements Parcelable {

    private int firstArg;
    private int secondArg;

    private State state;
    private int actionSelected;

    private final StringBuilder strInput = new StringBuilder();

    protected CalculatorModel(Parcel in) {
        firstArg = in.readInt();
        secondArg = in.readInt();
        actionSelected = in.readInt();
    }

    public static final Creator<CalculatorModel> CREATOR = new Creator<CalculatorModel>() {
        @Override
        public CalculatorModel createFromParcel(Parcel in) {
            return new CalculatorModel(in);
        }

        @Override
        public CalculatorModel[] newArray(int size) {
            return new CalculatorModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(firstArg);
        dest.writeInt(secondArg);
        dest.writeInt(actionSelected);
    }

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
                case R.id.digit_button_zero:
                    if (strInput.length() != 0) {
                        strInput.append("0");
                    }
                    break;
                case R.id.digit_button_first:
                    strInput.append("1");
                    break;
                case R.id.digit_button_second:
                    strInput.append("2");
                    break;
                case R.id.digit_button_three:
                    strInput.append("3");
                    break;
                case R.id.digit_button_four:
                    strInput.append("4");
                    break;
                case R.id.digit_button_five:
                    strInput.append("5");
                    break;
                case R.id.digit_button_six:
                    strInput.append("6");
                    break;
                case R.id.digit_button_seven:
                    strInput.append("7");
                    break;
                case R.id.digit_button_eight:
                    strInput.append("8");
                    break;
                case R.id.digit_button_nine:
                    strInput.append("9");
                    break;
            }
        }
    }

    public void onOperationPressed(int operationId) {
        if (operationId == R.id.digit_button_equals && state == State.secondArgInput) {
            secondArg = Integer.parseInt(strInput.toString());
            state = State.resultShow;
            strInput.setLength(0);
            switch (actionSelected) {
                case R.id.digit_button_minus:
                    strInput.append(firstArg - secondArg);
                    break;
                case R.id.digit_button_plural:
                    strInput.append(firstArg + secondArg);
                    break;
                case R.id.digit_button_multiply:
                    strInput.append(firstArg * secondArg);
                    break;
                case R.id.digit_button_division:
                    strInput.append(firstArg / secondArg);
                    break;
            }
        } else if (strInput.length() > 0 && state == State.firstArgInput) {
            firstArg = Integer.parseInt(strInput.toString());
            state = State.secondArgInput;
            strInput.setLength(0);
            switch (operationId) {
                case R.id.digit_button_plural:
                    actionSelected = R.id.digit_button_plural;
                    break;
                case R.id.digit_button_multiply:
                    actionSelected = R.id.digit_button_multiply;
                    break;
                case R.id.digit_button_division:
                    actionSelected = R.id.digit_button_division;
                case R.id.digit_button_minus:
                    actionSelected = R.id.digit_button_minus;
                    break;
            }
        }
    }

    public String getText() {
        return strInput.toString();
    }

    public void reset() {
        state = State.firstArgInput;
        strInput.setLength(0);
    }

}
