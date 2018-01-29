package com.alxndr.androidexercise.view;

import android.widget.TextView;

/**
 * Created by manoharana on 29/01/18.
 */

public interface MvpView {
    void refreshView();
    void updateTitle(String title);
}
