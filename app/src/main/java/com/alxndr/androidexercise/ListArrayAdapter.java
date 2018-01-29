package com.alxndr.androidexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alxndr.androidexercise.model.MvpModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by manoharana on 29/01/18.
 */

public class ListArrayAdapter extends ArrayAdapter<MvpModel.RowItem> {
    private ArrayList<MvpModel.RowItem> rowItems;

    private class ViewHolder    {
        TextView title;
        TextView description;
        ImageView imageView;
    }
    private ViewHolder viewHolder[];

    public ListArrayAdapter(Context context, ArrayList<MvpModel.RowItem> rowItems)  {
        super(context, 0, rowItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i("ArrayAdapter", "getView position " + position);
        MvpModel.RowItem rowItem = getItem(position);
        if (convertView == null)    {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_row_item, parent, false);
        }

        // ViewHolder pattern to cache resource view look up. Do it first time only.
        if (viewHolder == null) {
            viewHolder = new ViewHolder[getCount()];
            Log.i("ArrayAdapter", "count = " + getCount());
        }
        if (viewHolder[position] == null)   {
            viewHolder[position] = new ViewHolder();
            viewHolder[position].title = (TextView) convertView.findViewById(R.id.title);
            viewHolder[position].description = (TextView) convertView.findViewById(R.id.description);
            viewHolder[position].imageView = (ImageView) convertView.findViewById(R.id.image);
        }

        Log.i("ArrayAdapter", "title " + rowItem.getTitle());
        viewHolder[position].title.setText(rowItem.getTitle());
        viewHolder[position].description.setText(rowItem.getDescription());

        return convertView;
    }
}
