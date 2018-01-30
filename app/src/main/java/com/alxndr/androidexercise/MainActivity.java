package com.alxndr.androidexercise;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.alxndr.androidexercise.model.MvpModel;
import com.alxndr.androidexercise.presenter.MvpPresenter;
import com.alxndr.androidexercise.view.MvpView;

import java.util.ArrayList;

public class MainActivity extends Activity implements MvpView {

    private ArrayList<MvpModel.RowItem> rowItems;
    private MvpPresenter mvpPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvpPresenter = new MvpPresenter(MainActivity.this, this);
        mvpPresenter.start();

        listView = (ListView) findViewById(R.id.row_item_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshView();
            }
        });
    }

    @Override
    public void updateTitle(String title) {
        if (title != null) {
            getActionBar().setTitle(title);
        }
    }

    @Override
    public void refreshView() {
        mvpPresenter.onRefresh();
    }

    @Override
    public void onRefreshSuccess() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setRowItems(ArrayList<MvpModel.RowItem> rowItems) {
        if (rowItems != null) {
            this.rowItems = rowItems;
            ListView listView = (ListView) findViewById(R.id.row_item_list);
            ListArrayAdapter adapter = new ListArrayAdapter(this, rowItems);
            listView.setAdapter(adapter);
        }
    }
}
