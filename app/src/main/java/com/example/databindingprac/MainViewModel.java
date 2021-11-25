package com.example.databindingprac;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    private final ItemRepository itemRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository();
    }

    public LiveData<ArrayList<Item>> getMutableLiveDataItemsList() {
        return itemRepository.getMutableLiveData();
    }

    public void loadItems() {
        itemRepository.loadItems();
    }

    public boolean isReadyToStart() {
        return itemRepository.isReadyToStart();
    }

}
