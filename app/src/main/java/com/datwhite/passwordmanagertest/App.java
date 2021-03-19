package com.datwhite.passwordmanagertest;

import android.app.Application;

import androidx.room.Room;

import com.datwhite.passwordmanagertest.data.AppDatabase;
import com.datwhite.passwordmanagertest.data.PasswordDao;


public class App extends Application {

    private AppDatabase database;
    private PasswordDao passwordDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "passw-db-test")
                .allowMainThreadQueries()
                .build();

        passwordDao = database.passwordDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public PasswordDao getPasswordDao() {
        return passwordDao;
    }

    public void setPasswordDao(PasswordDao passwordDao) {
        this.passwordDao = passwordDao;
    }
}
