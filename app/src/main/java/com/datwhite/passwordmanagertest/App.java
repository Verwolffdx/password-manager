package com.datwhite.passwordmanagertest;

import android.app.Application;

import androidx.room.Room;

import com.datwhite.passwordmanagertest.data.AppDatabase;
import com.datwhite.passwordmanagertest.data.PasswordDao;
import com.datwhite.passwordmanagertest.data.UserPasswordDao;


public class App extends Application {

    private AppDatabase database;
    private PasswordDao passwordDao;
    private UserPasswordDao userPasswordDao;

    private static String globalPass;

    public static String getGlobalPass() {
        return globalPass;
    }

    public static void setGlobalPass(String text) {
        globalPass = text;
    }

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "passw-db-test-3")
                .allowMainThreadQueries()
                .build();

        passwordDao = database.passwordDao();
        userPasswordDao = database.userPasswordDao();
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

    public UserPasswordDao getUserPasswordDao() {
        return userPasswordDao;
    }

    public void setUserPasswordDao(UserPasswordDao userPasswordDao) {
        this.userPasswordDao = userPasswordDao;
    }
}
