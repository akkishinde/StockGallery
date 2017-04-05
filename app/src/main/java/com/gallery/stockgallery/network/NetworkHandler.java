package com.gallery.stockgallery.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkHandler {
    public static final String GET = "GET";
    public static final String MALFORMED_URL_EXCEPTION = "MalformedURLException: ";
    public static final String PROTOCOL_EXCEPTION = "ProtocolException: ";
    public static final String IO_EXCEPTION = "IOException: ";
    public static final String EXCEPTION = "Exception: ";
    private static final String TAG = NetworkHandler.class.getSimpleName();

    public NetworkHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);
            InputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(bufferedInputStream);
        } catch (MalformedURLException exception) {
            Log.e(TAG, MALFORMED_URL_EXCEPTION + exception.getMessage());
        } catch (ProtocolException exception) {
            Log.e(TAG, PROTOCOL_EXCEPTION + exception.getMessage());
        } catch (IOException exception) {
            Log.e(TAG, IO_EXCEPTION + exception.getMessage());
        } catch (Exception exception) {
            Log.e(TAG, EXCEPTION + exception.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
