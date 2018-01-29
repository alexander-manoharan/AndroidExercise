package com.alxndr.androidexercise.presenter;

import android.content.Context;
import android.content.res.Resources;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by manoharana on 29/01/18.
 */

public class MvpPresenter {
    private MvpView mvpView;
    private MvpModel mvpModel;
    private Context context;
    private final static String TAG = "dbgPresenter";

    public MvpPresenter(MvpView mvpView, Context context)    {
        this.mvpView = mvpView;
        this.context = context;
        this.mvpModel = new MvpModel();
    }

    public void start()  {
        // Fetch JSON file using retrofit
        downloadFile(context.getResources().getString(R.string.json_base_url));
    }

    private void downloadFile (String jsonUrl)   {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(jsonUrl)
                .build();
        JSONFileDownloader jsonFileDownloader = retrofit.create(JSONFileDownloader.class);
        Call<ResponseBody> request = jsonFileDownloader.downloadFile();

        // Perform JSON file download asynchronously
        request.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int nBytes;
                byte jsonData[] = new byte[4 * 1024];
                String json;
                StringBuilder stringBuilder = new StringBuilder();

                Log.i(TAG, "Response received.");
                if (response.body() != null)    {

                    try {
                        InputStream inputStream = new BufferedInputStream(response.body().byteStream(), 4 * 1024);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();

                        while ((nBytes = inputStream.read(jsonData)) != -1) {
                            out.write(jsonData, 0, nBytes);
                            stringBuilder.append(out.toString());
                            //stringBuilder.append(jsonData);
                        }
                        json = stringBuilder.toString();
                        Log.i(TAG, "string = " + json);
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

    private void parseJson (String json)
    {
        String title;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            title = jsonObject.getString("title");
            mvpModel.setTitle(title);
            mvpView.updateTitle(title);
        } catch (JSONException exception)   {
            exception.printStackTrace();
        }
    }
}

