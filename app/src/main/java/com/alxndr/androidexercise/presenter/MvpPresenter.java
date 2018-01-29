package com.alxndr.androidexercise.presenter;

import com.alxndr.androidexercise.view.MvpView;

/**
 * Created by manoharana on 29/01/18.
 */

public class MvpPresenter {
    private MvpView mvpView;

    public MvpPresenter(MvpView mvpView)    {
        this.mvpView = mvpView;
    }

    public void start()  {
        // TODO: Start presenter logic
    }

    public void downloadFile (String jsonUrl)   {
        // TODO: Download JSON File
    }
}

