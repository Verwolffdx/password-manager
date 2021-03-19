package com.datwhite.passwordmanagertest.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

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
            @Override
            public void onChanged(List<Password> notes) {
                adapter.setItems(notes);
            }
        });
    }
}