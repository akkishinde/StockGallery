package com.gallery.stockgallery.imageList;

public class MainPresenter {
    private MainView mainView;

    MainPresenter(final MainView mainView) {
        this.mainView = mainView;
    }

    public void onClickImage(String url){
        mainView.viewImage(url);
    }
}
