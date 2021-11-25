package com.example.databindingprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.databindingprac.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 * MVVM의 View에 속하는 구현. MainViewModel만 참조하게 모듈화함
 * LiveData로 제공되는 아이템들의 데이터 변화를 감지해 그에 따른 동작 수행
 * https://curiousengineer.tistory.com/15
 *
 */
public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private ItemAdapter itemAdapter;
    public ObservableBoolean isReadyToStart = new ObservableBoolean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        RecyclerView recyclerView = binding.viewItems;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getMutableLiveDataItemsList().observe(this, new Observer<ArrayList<Item>>() {
            @Override
            public void onChanged(ArrayList<Item> items) {
                itemAdapter.setItemsList((ArrayList<Item>) items);
                isReadyToStart.set(mainViewModel.isReadyToStart());
            }
        });
    }

    public void onStartButtonClicked(View view) {
        mainViewModel.loadItems();
    }

}