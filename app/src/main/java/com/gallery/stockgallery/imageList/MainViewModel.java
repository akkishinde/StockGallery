package com.gallery.stockgallery.imageList;

import android.databinding.BaseObservable;

public class MainViewModel extends BaseObservable {

    private ImageDataModel imageDataModel;

    public MainViewModel(ImageDataModel imageDataModel) {
        this.imageDataModel = imageDataModel;
    }

    public String getUserName() {
        return imageDataModel.userName;
    }

    public String getUrl() {
        return imageDataModel.url;
    }
}
