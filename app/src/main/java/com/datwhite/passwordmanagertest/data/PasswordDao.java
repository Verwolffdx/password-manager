package com.datwhite.passwordmanagertest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.datwhite.passwordmanagertest.model.Password;

import java.util.List;

@Dao
public interface PasswordDao {

    @Query("SELECT * FROM Password")
    List<Password> getAll();

    @Query("SELECT * FROM Password")
    LiveData<List<Password>> getAllLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Password password);

    @Update
    void update(Password password);

    @Delete
    void delete(Password password);
}
