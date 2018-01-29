package com.alxndr.androidexercise;

import android.app.Activity;
import android.os.Bundle;

import com.alxndr.androidexercise.presenter.MvpPresenter;
import com.alxndr.androidexercise.view.MvpView;

public class MainActivity extends Activity implements MvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MvpPresenter mvpPresenter = new MvpPresenter(MainActivity.this, this);
        mvpPresenter.start();
    }

    @Override
    public void updateTitle() {

    }

    @Override
    public void refreshView() {

    }
}
