package com.datwhite.passwordmanagertest.screens.main;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datwhite.passwordmanagertest.R;
import com.datwhite.passwordmanagertest.model.Password;
import com.datwhite.passwordmanagertest.screens.details.PasswordAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //отрисовка разделителей между элементами
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Привязка viewmodel к view
        //(установка адаптера)
        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        //кнопка в правом нижнем углу
        FloatingActionButton fab = findViewById(R.id.fab);
//        fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordAddActivity.start(MainActivity.this, null);
            }
        });


        //Подкючение Viewmodel
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //"подписка" на livedata
        mainViewModel.getPasswordLiveData().observe(this, new Observer<List<Password>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(List<Password> passwords) {
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        for (Password p : passwords) {
////                    System.out.println("Encrypted pass " + p.getText());
//
//                            try {
//                                String algorithm = "AES";
//                                String input = p.getText();
//                                String inputPassword = App.getGlobalPass();
//                                String salt = "GfH31Z5a";
//                                SecretKey key = getKeyFromPassword(inputPassword, salt);
//                                String decrypted = decrypt(algorithm, input, key);
//                                p.setText(decrypted);
//                            } catch (NoSuchAlgorithmException e) {
//                                e.printStackTrace();
//                            } catch (InvalidKeySpecException e) {
//                                e.printStackTrace();
//                            } catch (NoSuchPaddingException e) {
//                                e.printStackTrace();
//                            } catch (InvalidAlgorithmParameterException e) {
//                                e.printStackTrace();
//                            } catch (InvalidKeyException e) {
//                                e.printStackTrace();
//                            } catch (BadPaddingException e) {
//                                e.printStackTrace();
//                            } catch (IllegalBlockSizeException e) {
//                                e.printStackTrace();
//                            }
//
////                    System.out.println("Decrypted pass " + p.getText());
//                        }
//                    }
//                };

//                Thread thread = new Thread(runnable);
//                thread.start();
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }



                adapter.setItems(passwords);
            }
        });
    }
}