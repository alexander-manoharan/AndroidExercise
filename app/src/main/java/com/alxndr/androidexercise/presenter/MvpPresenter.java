package com.alxndr.androidexercise.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.inputmethodservice.Keyboard;
import android.util.Log;

import com.alxndr.androidexercise.R;
import com.alxndr.androidexercise.model.MvpModel;
import com.alxndr.androidexercise.view.MvpView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.alxndr.androidexercise.utils.JSONFileDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by manoharana on 29/01/18.
 */

/** Core of the application. Presenter class of the application that does all
 *  business processing. This class uses Retrofit to get the data from
 *  network. This class interacts with both Model and View.
 */
public class MvpPresenter {
    private MvpView mvpView;
    private MvpModel mvpModel;
    private Context context;
    private final static String TAG = "dbgPresenter";

    /** Creates the Model instance
     *
     * @param mvpView  Handle the view instance
     * @param context  Handle to the application context
     */
    public MvpPresenter(MvpView mvpView, Context context)    {
        this.mvpView = mvpView;
        this.context = context;
        this.mvpModel = new MvpModel();
    }

    /** Starts the presenter. For now, this file asynchronously starts downloading
     *  the json data from feed.
     */
    public void start()  {
        // Fetch JSON file using retrofit
        downloadFile(context.getResources().getString(R.string.json_base_url));
    }

    /** This function is invoked by the View when user pulls down the screen to
     *  refresh the screen.
     */
    public void onRefresh() {
        // Clear the data in model.
        mvpModel.clearRows();
        // Initiate fresh download of JSON file
        downloadFile(context.getResources().getString(R.string.json_base_url));
    }

    /** Download JSON file from an URL
     *
     * @param jsonUrl Location to the JSON file
     */
    private void downloadFile (String jsonUrl)   {
        if (jsonUrl == null)    {
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(jsonUrl)
                .build();
        JSONFileDownloader jsonFileDownloader = retrofit.create(JSONFileDownloader.class);
        Call<ResponseBody> request = jsonFileDownloader.downloadFile();

        // Perform JSON file download asynchronously
        request.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Good, we have received a success response. Now get to the content.
                int nBytes;
                byte jsonData[] = new byte[4 * 1024];   // Handle bytes in the order of 4KB.
                String json;
                StringBuilder stringBuilder = new StringBuilder();

                Log.i(TAG, "Response received.");
                // If response body has JSON data
                if (response.body() != null)    {

                    try {
                        InputStream inputStream = new BufferedInputStream(response.body().byteStream(), 4 * 1024);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();

                        // Read the data till end
                        while ((nBytes = inputStream.read(jsonData)) != -1) {
                            out.write(jsonData, 0, nBytes);
                            stringBuilder.append(out.toString());
                        }
                        json = stringBuilder.toString();
                        // Now, parse the JSON data
                        parseJson(json);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "Failure error received.");
            }
        });
    }

    /** Parse the JSON data. For now, use JSONObject. Later can use gson
     *
     * @param json JSON data to be parsed
     */
    private void parseJson (String json)
    {
        String title;
        String description;
        String imageRef;
        JSONObject jsonObject;
        JSONArray rows;
        int nRows;

        try {
            jsonObject = new JSONObject(json);
            // Read the title from JSON
            title = jsonObject.getString("title");
            // Update Model and View with the new title
            mvpModel.setTitle(title);
            mvpView.updateTitle(title);

            // Now, read the rows array
            rows = jsonObject.getJSONArray("rows");
            nRows = rows.length();
            // Read each row entry
            for (int counter = 0; counter < nRows; counter++)   {
                jsonObject = rows.getJSONObject(counter);
                title = jsonObject.getString("title");
                description = jsonObject.getString("description");
                imageRef = jsonObject.getString("imageHref");
                Log.i(TAG, "adding row " + title + " " + description);

                // It is not clear how to handle row items that doesn't have any data in a row.
                // Hence, I am not adding any data to the list only if all the data are null.
                if (!title.equals("null") && !description.equals("null") && !imageRef.equals("null")) {
                    // Update Model with row
                    mvpModel.addRow(title, description, imageRef);
                }
            }
            // Update view with the data adapter
            mvpView.setRowItems((ArrayList<MvpModel.RowItem>)mvpModel.getRowItems());
            // Stop swipe refresh
            mvpView.onRefreshSuccess();
        } catch (JSONException exception)   {
            exception.printStackTrace();
        }
    }
}

