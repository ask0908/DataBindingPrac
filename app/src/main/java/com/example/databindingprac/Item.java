package com.example.databindingprac;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Item {

    private String title;
    private String status;
    private final OnStatusChangeListener listener;
    private final int fakeLoadingTimeSeconds;

    public interface OnStatusChangeListener {
        void onStatusChanged(Item item, String status);
    }

    public Item(OnStatusChangeListener listener, String title, String status, int fakeLoadingTimeSeconds) {
        this.listener = listener;
        this.title = title;
        this.status = status;
        this.fakeLoadingTimeSeconds = fakeLoadingTimeSeconds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        listener.onStatusChanged(this, status);
    }

    @BindingAdapter({"statusIcon"})
    public static void loadStatusIcon(ImageView imageView, String status) {
        if ("DONE".equals(status)) {
            imageView.setImageResource(R.drawable.ic_baseline_done_24);
        } else if ("DOING".equals(status)) {
            imageView.setImageResource(R.drawable.ic_baseline_loop_24);
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_block_24);
        }
    }

    void load() {
        setStatus("DOING");
        new Thread(() -> {
            try {
                Thread.sleep(fakeLoadingTimeSeconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setStatus("DONE");
        }).start();
    }

}
