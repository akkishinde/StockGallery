package com.gallery.stockgallery.imagePreview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gallery.stockgallery.R;
import com.gallery.stockgallery.databinding.ActivityImagePreviewBinding;
import com.gallery.stockgallery.imageUtil.ImageLoader;

import static com.gallery.stockgallery.imagePreview.ImagePreviewActivity.IntentKey.IMAGE_URL;

public class ImagePreviewActivity extends AppCompatActivity {

    ActivityImagePreviewBinding dataBinding;
    ImageLoader imageLoader;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_preview);
        imageLoader = new ImageLoader(this);
        Bundle extras = getIntent().getExtras();
        url = extras.getString(IMAGE_URL);
        int loader = R.mipmap.ic_launcher;
        imageLoader.DisplayImage(url, loader, dataBinding.fullscreenImage);
    }

    public interface IntentKey {
        String IMAGE_URL = "com.gallery.stockgallery.IMAGE_URL";
    }
}
