package com.datwhite.passwordmanagertest.toast;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.datwhite.passwordmanagertest.App;
import com.datwhite.passwordmanagertest.screens.main.MainActivity;

public class MakeToast extends AppCompatActivity {

    MainActivity mainActivity;
    App app;



    public void showToast() {
        Toast.makeText(app.getApplicationContext(),"Text Copied ",Toast.LENGTH_SHORT).show();
    }
}
