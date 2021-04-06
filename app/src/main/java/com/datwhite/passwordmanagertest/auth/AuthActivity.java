package com.datwhite.passwordmanagertest.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.datwhite.passwordmanagertest.App;
import com.datwhite.passwordmanagertest.R;
import com.datwhite.passwordmanagertest.screens.main.MainActivity;

public class AuthActivity extends AppCompatActivity {


    private TextView inputKey;
    private InputNumber inputNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        int[] numberIds = new int[] {
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine
        };

        inputKey = findViewById(R.id.inputKey);
        inputNumber = new InputNumber();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(view.getId());
                inputKey.setText(inputNumber.onNumPressed(view.getId()));
                if(inputKey.length() == 4) {
                    //Переход на страницу с паролями
                    App.setGlobalPass(inputKey.getText().toString());
                    startActivity(new Intent(AuthActivity.this, MainActivity.class ));
                    finish();


                    //Переход на страницу загрузки
//                    App.setGlobalPass(inputKey.getText().toString());
//                    startActivity(new Intent(AuthActivity.this, LoadingScreenActivity.class ));
//                    finish();
                }
            }
        };

        for (int i = 0; i < numberIds.length; i++) {
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }
    }




}
