package com.datwhite.passwordmanagertest.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.datwhite.passwordmanagertest.App;
import com.datwhite.passwordmanagertest.R;
import com.datwhite.passwordmanagertest.model.Password;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import static com.datwhite.passwordmanagertest.crypto.AES.*;

public class PasswordAddActivity extends AppCompatActivity {

    private static final String EXTRA_PASS = "EXTRA_PASS";

    private Password password;

    private EditText login_input, email_input, pass_input, website_input;

    public static void start(Activity caller, Password password) {
        Intent intent = new Intent(caller, PasswordAddActivity.class);
        if (password != null) {
            //Прикрепить заметку к интенту
            intent.putExtra(EXTRA_PASS, password);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pass_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Создание кнопки "назад"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        setTitleColor(0);
        setTitle(getString(R.string.pass_add_title));

        login_input = findViewById(R.id.login_input);
        email_input = findViewById(R.id.email_input);
        pass_input = findViewById(R.id.pass_input);
        website_input = findViewById(R.id.website_input);

        if (getIntent().hasExtra(EXTRA_PASS)) {
            password = getIntent().getParcelableExtra(EXTRA_PASS);
            login_input.setText(password.getLogin());
            email_input.setText(password.getEmail());
            pass_input.setText(password.getText());
            website_input.setText(password.getWebsite());

        } else {
            password = new Password();
        }


    }

    //Создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Обработка нажатий кнопок в меню
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Нажатие на стрелку назад <-
            case android.R.id.home:
                finish();
                break;
            //Нажатие на кнопку сохранения
            case R.id.action_save:
                if (login_input.getText().length() > 0 &&
                        email_input.getText().length() > 0 &&
                        pass_input.getText().length() > 0 &&
                        website_input.getText().length() > 0) {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            password.setLogin(login_input.getText().toString());
                            password.setEmail(email_input.getText().toString());

                            try {
                                String algorithm = "AES";
                                String input = pass_input.getText().toString();
                                String inputPassword = App.getGlobalPass();
                                String salt = "GfH31Z5a";
                                SecretKey key = getKeyFromPassword(inputPassword, salt);
                                String encrypted = encrypt(algorithm, input, key);

                                password.setText(encrypted);
                                password.setWebsite(website_input.getText().toString());
                                password.setTimestamp(System.currentTimeMillis());

                                App.getInstance().getPasswordDao().insert(password);
                            } catch (NoSuchPaddingException e) {
                                e.printStackTrace();
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidAlgorithmParameterException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (BadPaddingException e) {
                                e.printStackTrace();
                            } catch (IllegalBlockSizeException e) {
                                e.printStackTrace();
                            } catch (InvalidKeySpecException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    Thread thread = new Thread(runnable);
                    thread.start();
                    finish();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
