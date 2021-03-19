package com.datwhite.passwordmanagertest.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.datwhite.passwordmanagertest.model.Password;

@Database(entities = {Password.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PasswordDao passwordDao();
}
