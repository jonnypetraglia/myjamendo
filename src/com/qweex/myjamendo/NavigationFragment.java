package com.qweex.myjamendo;

import android.*;
import android.R;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NavigationFragment extends ListFragment
{
    MasterActivity MasterActivityRef;
    View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        MasterActivityRef = (MasterActivity) getActivity();
        contentView = super.onCreateView(inflater, container, savedInstanceState);

/*
        <ListView android:id="@+id/left_drawer"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:choiceMode="singleChoice"
        android:divider="@android:color/transparent"
        android:dividerHeight="0dp"
        android:background="#111"/>
*/

        return contentView;
    }



    private class NavigationEntry
    {
        private final String value;
        private final boolean isTitle;
        NavigationEntry(String value, boolean isTitle)
        {
            this.value = value;
            this.isTitle = isTitle;
        }
    }

    private class NavigationAdapter extends BaseAdapter
    {
        private final Context context;
        private final NavigationEntry[] entries;
        private final int resourceEntry, resourceTitle;

        public NavigationAdapter(Context context,
                                 int resourceEntry,
                                 int resourceTitle,
                                 NavigationEntry[] entries) {
            this.context = context;
            this.resourceEntry = resourceEntry;
            this.resourceTitle = resourceTitle;
            this.entries = entries;
        }

        @Override
        public int getCount() {
            return entries.length;
        }

        @Override
        public NavigationEntry getItem(int i) {
            return entries[i];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if(rowView==null ||
                    (entries[i].isTitle!="title".equals(rowView.findViewById(R.id.text1).getTag())))
            {
                rowView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(entries[i].isTitle ? resourceTitle : resourceEntry, viewGroup, false);
            }


            return rowView;
        }
    }
}
