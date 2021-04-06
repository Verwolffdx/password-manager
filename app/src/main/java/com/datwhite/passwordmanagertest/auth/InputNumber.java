package com.datwhite.passwordmanagertest.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;

import com.datwhite.passwordmanagertest.R;

public class InputNumber extends AppCompatActivity {
    private int firstArg;
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private State state;

    public String onNumPressed(int buttonId) {
        if(inputStr.length() <= 4)
            switch (buttonId) {
                case R.id.zero:
                    inputStr.append("0");
                    return inputStr.toString();

                case R.id.one:
                    inputStr.append("1");
                    return inputStr.toString();

                case R.id.two:
                    inputStr.append("2");
                    return inputStr.toString();

                case R.id.three:
                    inputStr.append("3");
                    return inputStr.toString();

                case R.id.four:
                    inputStr.append("4");
                    return inputStr.toString();

                case R.id.five:
                    inputStr.append("5");
                    return inputStr.toString();

                case R.id.six:
                    inputStr.append("6");
                    return inputStr.toString();

                case R.id.seven:
                    inputStr.append("7");
                    return inputStr.toString();

                case R.id.eight:
                    inputStr.append("8");
                    return inputStr.toString();

                case R.id.nine:
                    inputStr.append("9");
                    return inputStr.toString();

            }
        return inputStr.toString();
    }


}
