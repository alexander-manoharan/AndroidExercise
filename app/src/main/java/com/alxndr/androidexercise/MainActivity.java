package com.alxndr.androidexercise;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.alxndr.androidexercise.model.MvpModel;
import com.alxndr.androidexercise.presenter.MvpPresenter;
import com.alxndr.androidexercise.view.MvpView;

import java.util.ArrayList;

public class MainActivity extends Activity implements MvpView {

    private ArrayList<MvpModel.RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MvpPresenter mvpPresenter = new MvpPresenter(MainActivity.this, this);
        mvpPresenter.start();

    }

    @Override
    public void updateTitle(String title) {
        if (title != null) {
            getActionBar().setTitle(title);
        }
    }

    @Override
    public void refreshView() {

    }

    @Override
    public void setRowItems(ArrayList<MvpModel.RowItem> rowItems) {
        this.rowItems = rowItems;
        ListView listView = (ListView) findViewById(R.id.row_item_list);
        ListArrayAdapter adapter = new ListArrayAdapter(this, rowItems);
        listView.setAdapter(adapter);
    }
}
