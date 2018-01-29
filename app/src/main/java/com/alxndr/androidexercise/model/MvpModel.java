package com.alxndr.androidexercise.model;

import java.util.List;

/**
 * Created by manoharana on 29/01/18.
 */

public class MvpModel {
    private String title;
    private List<RowItem> rowItems;

    public void setTitle(String title)  {
        this.title = title;
    }

    private class RowItem {
        private String title;
        private String description;
        private String imageRef;

        public void addRow (String title, String description, String imageRef) {
            this.title = title;
            this.description = description;
            this.imageRef = imageRef;
        }
    }

}
