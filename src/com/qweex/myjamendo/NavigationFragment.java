package com.qweex.myjamendo;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationFragment extends ListFragment
{
    MasterActivity MasterActivityRef;
    View contentView;

    ArrayList<NavigationEntry> entries;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        this.getListView().setBackgroundColor(getResources().getColor(R.color.j_shadow));
        createAdapter();
    }

    private void createAdapter()
    {
        entries = new ArrayList<NavigationEntry>();

        /*
        if(isLoggedIn)
        {
            entries.add(new NavigationEntry(myUsername, true));
            entries.add(new NavigationEntry(R.string.my_profile, false));
            entries.add(new NavigationEntry(R.string.playlists, false));
            entries.add(new NavigationEntry(R.string.favorites, false));
        }//*/
        entries.add(new NavigationEntry(R.string.jamendo, true));
        entries.add(new NavigationEntry(R.string.featured, false));
        entries.add(new NavigationEntry(R.string.radios, false));
        entries.add(new NavigationEntry(R.string.search, false));
        //if(!isLoggedIn)
            entries.add(new NavigationEntry(R.string.log_in, false));
        entries.add(new NavigationEntry(R.string.settings, false));
        entries.add(new NavigationEntry(R.string.about, false));

        NavigationAdapter na = new NavigationAdapter(this.getActivity(),
                R.layout.nav_entry, R.layout.nav_title, entries);

        setListAdapter(na);
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
        NavigationEntry(int valueID, boolean isTitle)
        {
            this.value = getResources().getString(valueID);
            this.isTitle = isTitle;
        }
    }

    private class NavigationAdapter extends BaseAdapter
    {
        private final Context context;
        private final ArrayList<NavigationEntry> entries;
        private final int resourceEntry, resourceTitle;

        public NavigationAdapter(Context context,
                                 int resourceEntry,
                                 int resourceTitle,
                                 ArrayList<NavigationEntry> entries) {
            this.context = context;
            this.resourceEntry = resourceEntry;
            this.resourceTitle = resourceTitle;
            this.entries = entries;
        }

        @Override
        public int getCount() {
            return entries.size();
        }

        @Override
        public NavigationEntry getItem(int i) {
            return entries.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if(rowView==null ||
                    (entries.get(i).isTitle!="title".equals(rowView.findViewById(android.R.id.text1).getTag())))
            {
                rowView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                        .inflate(entries.get(i).isTitle ? resourceTitle : resourceEntry, viewGroup, false);
            }

            TextView tv = (TextView) rowView.findViewById(android.R.id.text1);
            tv.setText(entries.get(i).value);

            return rowView;
        }
    }
}
