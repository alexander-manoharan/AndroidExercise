package com.alxndr.androidexercise.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by manoharana on 29/01/18.
 */

public interface JSONFileDownloader {
    @GET("facts.json")
    Call<ResponseBody> downloadFile();
}
