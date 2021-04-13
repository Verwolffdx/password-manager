package com.datwhite.passwordmanagertest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.datwhite.passwordmanagertest.model.UserPassword;

import java.util.List;

@Dao
public interface UserPasswordDao {

    @Query("SELECT userpassword FROM UserPassword")
    UserPassword getUserPassword();

    @Query("SELECT * FROM UserPassword")
    List<UserPassword> getAll();

    @Query("SELECT * FROM UserPassword")
    LiveData<List<UserPassword>> getAllLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserPassword userPassword);

    @Update
    void update(UserPassword userPassword);

    @Delete
    void delete(UserPassword userPassword);
}
