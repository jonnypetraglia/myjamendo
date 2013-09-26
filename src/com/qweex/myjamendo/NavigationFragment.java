package com.qweex.myjamendo;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        getListView().setBackgroundColor(getResources().getColor(R.color.j_shadow)); //TODO: Themify
        getListView().setOnItemClickListener(selectNav);
        createAdapter();
    }


    ListView.OnItemClickListener selectNav = new ListView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            FragmentTransaction transaction = getFragmentManager().beginTransaction();



            String selection = ((TextView)view).getText().toString();

            if(getResources().getString(R.string.radios).equals(selection))
            {
                //TODO: Fetch Radios
                MainFragment mf = new MainFragment();
                transaction.replace(R.id.frag_main, mf);
                MasterActivityRef.setupTabs(R.string.radios, false, false);
            }
            if(getResources().getString(R.string.featured).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.search).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.settings).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.about).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.search).equals(selection))
            {
                //TODO
            }


            if(getResources().getString(R.string.my_profile).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.playlists).equals(selection))
            {
                //TODO
            }
            if(getResources().getString(R.string.favorites).equals(selection))
            {
                //TODO
            }
            Log.wtf("Derp",((TextView)view).getText().toString() + "!!!");


            transaction.addToBackStack(null);
            transaction.commit();

            DrawerLayout d = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
            d.closeDrawer(Gravity.LEFT);
        }
    };

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
