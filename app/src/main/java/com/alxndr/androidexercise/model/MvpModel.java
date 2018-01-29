package com.alxndr.androidexercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoharana on 29/01/18.
 */

public class MvpModel {
    private String title;

    public class RowItem {
        private String title;
        private String description;
        private String imageRef;

        public RowItem(String title, String description, String imageRef)   {
            this.title = title;
            this.description = description;
            this.imageRef = imageRef;
        }

        public String getTitle()   {
            return title;
        }

        public String getDescription()  {
            return description;
        }

        public String getImageRef() {
            return imageRef;
        }
    }
    private List<RowItem> rowItems;

    public MvpModel()   {
        rowItems = new ArrayList<RowItem>();
    }

    public void setTitle(String title)  {
        this.title = title;
    }

    public void addRow (String title, String description, String imageRef) {
        RowItem rowItem = new RowItem(title, description, imageRef);
        rowItems.add(rowItem);
    }

    public RowItem getRowItem (int position)    {
        return rowItems.get(position);
    }

    public List<RowItem> getRowItems() {
        return rowItems;
    }
}
