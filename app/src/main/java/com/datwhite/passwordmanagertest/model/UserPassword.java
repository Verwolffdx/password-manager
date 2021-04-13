package com.datwhite.passwordmanagertest.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserPassword {
    @PrimaryKey()
    @NonNull
    private String userpassword;

    public UserPassword() {
    }

    public UserPassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
