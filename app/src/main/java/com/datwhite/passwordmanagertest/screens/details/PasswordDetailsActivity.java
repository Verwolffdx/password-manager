package com.datwhite.passwordmanagertest.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.datwhite.passwordmanagertest.App;
import com.datwhite.passwordmanagertest.R;
import com.datwhite.passwordmanagertest.model.Password;

public class PasswordDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_PASS = "EXTRA_PASS";

    private Password password;

    private TextView login_output, email_output, pass_output, website_output;

    public static void start(Activity caller, Password password) {
        Intent intent = new Intent(caller, PasswordDetailsActivity.class);
        if (password != null) {
            //Прикрепить заметку к интенту
            intent.putExtra(EXTRA_PASS, password);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pass_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Создание кнопки "назад"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.pass_details_title));

        login_output = findViewById(R.id.login_output);
        email_output = findViewById(R.id.email_output);
        pass_output = findViewById(R.id.password_output);
        website_output = findViewById(R.id.website_output);

        password = getIntent().getParcelableExtra(EXTRA_PASS);
        login_output.setText(password.getLogin());
        email_output.setText(password.getEmail());
        pass_output.setText(password.getText());
        website_output.setText(password.getWebsite());
    }

    //Создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Обработка нажатий кнопок в меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Нажатие на стрелку назад <-
            case android.R.id.home:
                finish();
                break;
            //Нажатие на кнопку редактирования
            case R.id.action_edit:
                PasswordAddActivity.start(PasswordDetailsActivity.this, password);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
