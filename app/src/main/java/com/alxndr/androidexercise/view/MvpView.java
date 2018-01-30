package com.alxndr.androidexercise.view;

import android.widget.TextView;

import com.alxndr.androidexercise.model.MvpModel;

import java.util.ArrayList;

/**
 * Created by manoharana on 29/01/18.
 */

/** Main View to interact with UI elements in Android
 *  There is no logic handled with the data.
 */
public interface MvpView {
    /** Refresh the view. Invoked by Presenter when the user swipes the screen down */
    void refreshView();
    /** Stop swipe refresh, when the JSON file has been downloaded successfully */
    void onRefreshSuccess();

    /** Update title in the view **
     *
     * @param title Title received from JSON data
     */
    void updateTitle(String title);

    /** Sets up the array adapter for the view
     *
     * @param rowItems data model received from presenter
     */
    void setRowItems(ArrayList<MvpModel.RowItem> rowItems);
}
