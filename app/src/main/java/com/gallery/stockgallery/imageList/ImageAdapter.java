package com.gallery.stockgallery.imageList;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gallery.stockgallery.R;
import com.gallery.stockgallery.databinding.ViewImagesBinding;
import com.gallery.stockgallery.imageUtil.ImageLoader;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter {
    private final ArrayList<ImageDataModel> imageDataModels;
    private final MainPresenter mainPresenter;
    private ImageLoader imageLoader;

    public ImageAdapter(final ArrayList<ImageDataModel> imageDataModels,
            final MainPresenter mainPresenter) {

        this.imageDataModels = imageDataModels;
        this.mainPresenter = mainPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        imageLoader = new ImageLoader(parent.getContext());
        ViewImagesBinding viewImagesBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.view_images, parent, false);
        return new ImageAdapter.ImageViewHolder(viewImagesBinding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int loader = R.mipmap.ic_launcher;
        ImageDataModel imageDataModel = imageDataModels.get(position);
        ImageAdapter.ImageViewHolder imageViewHolder = (ImageAdapter.ImageViewHolder) holder;
        ViewImagesBinding viewImagesBinding = imageViewHolder.viewImagesBinding;
        viewImagesBinding.setViewModel(new MainViewModel(imageDataModel));
        viewImagesBinding.setPresenter(mainPresenter);
        imageLoader.DisplayImage(imageDataModel.url, loader, viewImagesBinding.imageView);
    }

    @Override
    public int getItemCount() {
        return imageDataModels.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ViewImagesBinding viewImagesBinding;

        public ImageViewHolder(ViewImagesBinding viewImagesBinding) {
            super(viewImagesBinding.getRoot());
            this.viewImagesBinding = viewImagesBinding;
        }
    }
}
