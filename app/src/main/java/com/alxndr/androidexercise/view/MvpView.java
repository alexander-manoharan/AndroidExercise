package com.alxndr.androidexercise.view;

import android.widget.TextView;

import com.alxndr.androidexercise.model.MvpModel;

import java.util.ArrayList;

/**
 * Created by manoharana on 29/01/18.
 */

public interface MvpView {
    void refreshView();
    void updateTitle(String title);
    void setRowItems(ArrayList<MvpModel.RowItem> rowItems);
}
