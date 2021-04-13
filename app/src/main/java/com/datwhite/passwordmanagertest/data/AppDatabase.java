package com.datwhite.passwordmanagertest.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.datwhite.passwordmanagertest.model.Password;
import com.datwhite.passwordmanagertest.model.UserPassword;

@Database(entities = {Password.class, UserPassword.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PasswordDao passwordDao();
    public abstract UserPasswordDao userPasswordDao();

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `UserPassword` (`userpassword` VARCHAR(255), PRIMARY KEY(`userpassword`))");
//        }
//    };

}
