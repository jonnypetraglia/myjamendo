package com.qweex.myjamendo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//FUTURE: change from ArrayList to Cursor w/ Strings for columns

public class MainListviewAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<HashMap<String, String>> data;  //1. img   2. title   3. subtitle
    private final int resource;
    private final String[] keys;

    public MainListviewAdapter(Context context,
                               int resource,
                               String[] keys,
                               ArrayList<HashMap<String,String>> data) {
        this.context = context;
        this.resource = resource;
        this.keys = keys;
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }

    public HashMap<String,String> getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if(rowView == null)
            rowView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(this.resource, parent, false);

        TextView title = (TextView)rowView.findViewById(R.id.title),
                 subtitle = (TextView)rowView.findViewById(R.id.subtitle);
        ImageView img = (ImageView)rowView.findViewById(R.id.img);

        HashMap<String,String> values = data.get(position);
        System.out.println("# items: " + data.size() + " " + position);
        //img.setImageDrawable( values.get(keys[0]) );  //TODO: image
        title.setText( values.get(keys[1]) );
        subtitle.setText( values.get(keys[2]) );

        return rowView;
    }
}