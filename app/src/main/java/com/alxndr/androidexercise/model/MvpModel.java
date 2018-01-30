package com.alxndr.androidexercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoharana on 29/01/18.
 */

/** Main Model class that stores the parsed JSON data from the feed.
 *  This class provides interfaces to operate on the data.
 */
public class MvpModel {
    private String title;

    public class RowItem {
        private String title;
        private String description;
        private String imageRef;

        /** Constructor to initialize data for a row */
        public RowItem(String title, String description, String imageRef)   {
            this.title = title;
            this.description = description;
            this.imageRef = imageRef;
        }

        /** Get title for a row */
        public String getTitle()   {
            return title;
        }

        /** Get description for a row */
        public String getDescription()  {
            return description;
        }

        /** Get image location to downlaod later */
        public String getImageRef() {
            return imageRef;
        }
    }
    private List<RowItem> rowItems;

    public MvpModel()   {
        rowItems = new ArrayList<RowItem>();
    }

    /** Sets the title for the whole data array. Received from JSON */
    public void setTitle(String title)  {
        if (title != null) {
            this.title = title;
        }
    }

    /** Add a row item with proper data */
    public void addRow (String title, String description, String imageRef) {
        RowItem rowItem = new RowItem(title, description, imageRef);
        if (rowItem != null) {
            rowItems.add(rowItem);
        }
    }

    public RowItem getRowItem (int position)    {
        RowItem rowItem = null;
        try {
            rowItem = rowItems.get(position);
        } catch (ArrayIndexOutOfBoundsException exception)  {
            exception.printStackTrace();
        }
        return rowItem;
    }

    public List<RowItem> getRowItems() {
        return rowItems;
    }

    public void clearRows ()    {
        rowItems.clear();
    }
}
