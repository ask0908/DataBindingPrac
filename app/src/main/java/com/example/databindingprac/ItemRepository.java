package com.example.databindingprac;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ItemRepository implements Item.OnStatusChangeListener {

    private final ArrayList<Item> items = new ArrayList<>();
    private final MutableLiveData<ArrayList<Item>> mutableLiveData = new MutableLiveData<>();

    public ItemRepository() {
        items.clear();
        items.add(new Item(this, "item 1", "READY", 2));
        items.add(new Item(this, "item 1", "READY", 3));
        items.add(new Item(this, "item 1", "READY", 4));
        items.add(new Item(this, "item 1", "READY", 5));
        items.add(new Item(this, "item 1", "READY", 6));
        items.add(new Item(this, "item 1", "READY", 7));
        items.add(new Item(this, "item 1", "READY", 8));
        items.add(new Item(this, "item 1", "READY", 9));
        notifyItemsChanged();
    }

    @Override
    public void onStatusChanged(Item item, String status) {
        notifyItemsChanged();
    }

    public MutableLiveData<ArrayList<Item>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void loadItems() {
        for (Item item : items)
            item.load();
    }

    public boolean isReadyToStart() {
        for (Item item : items)
            if ("DOING".equals(item.getStatus()))
                return false;
        return true;
    }

    private void notifyItemsChanged() {
        if (Looper.myLooper() == Looper.getMainLooper())
            mutableLiveData.setValue(items);
        else
            mutableLiveData.postValue(items);
    }
}
