package com.alxndr.androidexercise;

import android.content.Context;
import android.provider.ContactsContract;
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
import com.squareup.picasso.Picasso;

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
        ViewHolder viewHolder;
        Log.i("ArrayAdapter", "getView position " + position);
        MvpModel.RowItem rowItem = getItem(position);
        if (convertView == null)    {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_row_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.i("ArrayAdapter", "title " + rowItem.getTitle());
        viewHolder.title.setText(rowItem.getTitle());
        viewHolder.description.setText(rowItem.getDescription());

        Picasso.with(getContext()).setLoggingEnabled(true);
        Picasso.with(getContext()).load(rowItem.getImageRef()).into(viewHolder.imageView);
        return convertView;
    }
}
