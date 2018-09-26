package com.wordpress.piedcipher.samachar.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.wordpress.piedcipher.samachar.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private final static String TAG = Utils.class.getName();
    public final static String API_BASE_URL = "https://content.guardianapis.com/search?api-key=f826acc7-efd2-4e78-8bca-37ae59f6edfd&show-fields=headline,thumbnail&show-tags=contributor";

    /**
     * @return parsed JSON from String JSON
     */
    public static List<News> fetchNewsFromApi(String url) {
        String jsonResponseString = null;
        Log.d(TAG, buildURL(API_BASE_URL).toString());

        try {
            jsonResponseString = makeHttpRequest(buildURL(url));
        } catch (IOException e) {
            Log.e(TAG, "IOException occurred while making Http Request.");
        }

        return jsonParser(jsonResponseString);
    }

    /**
     * @param context is NewsActivity
     * @return boolean value that indicated a device is connected to a network or not
     */
    public static boolean checkNetworkStatus(Context context) {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = networkInfo != null && networkInfo.isConnected();
        }

        return isConnected;
    }

    /**
     * @param stringURL URL of API
     * @return URL by parsing String URL
     */
    private static URL buildURL(String stringURL) {
        URL url = null;

        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL is invalid or malformed.");
        }

        return url;
    }

    /**
     * @param url is URL of API
     * @return JSON String formed by reading from InputStream
     * @throws IOException while closing httpURLConnection
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponseString = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        if (url == null) {
            return null;
        }

        try {
            Log.e(TAG, url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponseString = readFromInputStream(inputStream);
            } else {
                Log.e(TAG, "Invalid Response Code received." + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(TAG, "IOException occurred while making Http Request.");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponseString;
    }

    /**
     * @param inputStream input-stream of the API
     * @return json in String format
     * @throws IOException while reading line using BufferedReader
     */
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder jsonResponseStringBuilder = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                jsonResponseStringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }

        return jsonResponseStringBuilder.toString();
    }

    /**
     * @param jsonResponseString is JSON String formed by reading from InputStream
     * @return list of news
     */
    private static List<News> jsonParser(String jsonResponseString) {
        List<News> newsList = new ArrayList<>();
        Log.e(TAG, jsonResponseString + "");

        if (TextUtils.isEmpty(jsonResponseString)) {
            return null;
        }

        try {
            JSONObject jsonObjectBase = new JSONObject(jsonResponseString);
            JSONObject jsonObjectResponse = jsonObjectBase.optJSONObject("response");
            JSONArray jsonArrayResults = jsonObjectResponse.optJSONArray("results");

            for (int i = 0; i < jsonArrayResults.length(); i++) {
                JSONObject jsonObjectCurrent = jsonArrayResults.optJSONObject(i);
                JSONObject jsonObjectCurrentFields = jsonObjectCurrent.optJSONObject("fields");
                JSONArray jsonArrayTags = jsonObjectCurrent.optJSONArray("tags");

                String headline = jsonObjectCurrentFields.optString("headline");
                String thumbnail = jsonObjectCurrentFields.optString("thumbnail");
                String webUrl = jsonObjectCurrent.optString("webUrl");
                String timestamp = jsonObjectCurrent.optString("webPublicationDate");
                String section = jsonObjectCurrent.optString("sectionName");
                String author = "";

                if (jsonArrayTags.length() > 0) {
                    JSONObject jsonObjectTags = jsonArrayTags.optJSONObject(0);
                    author = jsonObjectTags.optString("webTitle");
                }

                newsList.add(new News(headline, thumbnail, webUrl, timestamp.substring(0, 10), section, author));
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error occurred while parsing JSON.");
        }

        return newsList;
    }
}