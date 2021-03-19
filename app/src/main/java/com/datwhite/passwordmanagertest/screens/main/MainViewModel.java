package com.datwhite.passwordmanagertest.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.datwhite.passwordmanagertest.App;
import com.datwhite.passwordmanagertest.model.Password;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Password>> passwordLiveData = App.getInstance().getPasswordDao().getAllLiveData();

    public LiveData<List<Password>> getPasswordLiveData() {
        return passwordLiveData;
    }
}
