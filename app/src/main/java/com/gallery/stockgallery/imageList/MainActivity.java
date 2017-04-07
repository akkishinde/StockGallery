package com.gallery.stockgallery.imageList;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gallery.stockgallery.R;
import com.gallery.stockgallery.databinding.ActivityMainBinding;
import com.gallery.stockgallery.imagePreview.ImagePreviewActivity;
import com.gallery.stockgallery.network.NetworkHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.gallery.stockgallery.imagePreview.ImagePreviewActivity.IntentKey.IMAGE_URL;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String WEB_FORMAT_URL = "webformatURL";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String HITS = "hits";
    private static final String USER = "user";
    private String url = "https://pixabay.com/api/?key=4909307-\n" + "\n" + "b42f8c86ca7153ec47ea96145&q=yellow+flowers&image_type=photo&per_page=20&page=1";
    private ActivityMainBinding dataBinding;
    private ImageAdapter imageAdapter;
    private MainPresenter mainPresenter;
    private RecyclerView recyclerView;
    private ArrayList<ImageDataModel> imageDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        dataBinding.setPresenter(mainPresenter);
        recyclerView = dataBinding.recyclerView;
        new GetImages().execute();
    }

    private void populate() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        imageAdapter = new ImageAdapter(imageDataModels, mainPresenter);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void viewImage(final String url) {
        Intent imagePreviewIntent = new Intent(this, ImagePreviewActivity.class);
        imagePreviewIntent.putExtra(IMAGE_URL, url);
        startActivity(imagePreviewIntent);
    }

    private class GetImages extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            NetworkHandler networkHandler = new NetworkHandler();
            String jsonString = networkHandler.makeServiceCall(url);
            if (jsonString != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonString);
                    JSONArray hitsArray = jsonObj.getJSONArray(HITS);
                    for (int i = 0; i < hitsArray.length(); i++) {
                        JSONObject hitsJsonObject = hitsArray.getJSONObject(i);
                        ImageDataModel imageDataModel = new ImageDataModel();
                        imageDataModel.userName = hitsJsonObject.getString(USER);
                        imageDataModel.url = hitsJsonObject.getString(WEB_FORMAT_URL);
                        imageDataModels.add(imageDataModel);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                Log.e(TAG, "Something Went Wrong");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            populate();
        }
    }
}
