package com.datwhite.passwordmanagertest.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

import static com.datwhite.passwordmanagertest.crypto.AES.decrypt;
import static com.datwhite.passwordmanagertest.crypto.AES.getKeyFromPassword;

public class PasswordDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_PASS = "EXTRA_PASS";

    private Password password;

    private TextView login_output, email_output, pass_output, website_output;

    private View look;

    private boolean isLooked = false;

    public static void start(Activity caller, Password password) {
        Intent intent = new Intent(caller, PasswordDetailsActivity.class);
        if (password != null) {
            //Прикрепить заметку к интенту
            intent.putExtra(EXTRA_PASS, password);
        }
        caller.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pass_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Создание кнопки "назад"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("");

        login_output = findViewById(R.id.login_output);
        email_output = findViewById(R.id.email_output);
        pass_output = findViewById(R.id.password_output);
        website_output = findViewById(R.id.website_output);
        look = findViewById(R.id.look);

        password = getIntent().getParcelableExtra(EXTRA_PASS);
        login_output.setText(password.getLogin());
        email_output.setText(password.getEmail());
        website_output.setText(password.getWebsite());
//        pass_output.setInputType(InputType.TYPE_CLASS_TEXT);
        pass_output.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        pass_output.setText("Расшифровка пароля...");


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {


                    String algorithm = "AES";
                    String input = password.getText();
                    String inputPassword = App.getGlobalPass();
                    String salt = "GfH31Z5a";
                    SecretKey key = getKeyFromPassword(inputPassword, salt);
                    String decrypted = decrypt(algorithm, input, key);
//                    pass_output.setInputType(InputType.TYPE_CLASS_TEXT |
//                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass_output.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setText(decrypted);


                    pass_output.post(new Runnable() {
                        @Override
                        public void run() {
                            pass_output.setText(password.getText());
                        }
                    });
//            pass_output.setText(password.getText());


                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLooked) {
                    pass_output.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isLooked = true;
                }
                else {
                    pass_output.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isLooked = false;
                }
            }
        });

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
