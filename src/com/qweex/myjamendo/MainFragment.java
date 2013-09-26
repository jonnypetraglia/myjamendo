package com.qweex.myjamendo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;


public class MainFragment extends Fragment
{
    JListView listview;
    TabHost tabs;
    View contentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceStatee)
    {
        contentView = inflater.inflate(R.layout.main, container, false);


        // --------------- Set up tabs ----------------
        tabs = (TabHost) contentView.findViewById(R.id.tabHost);
        tabs.setup();
        String[] tabNames = {"Track", "Album", "Artists", "Playlists", "Users"};
        for(String t : tabNames)
        {
            TabHost.TabSpec spec1 = tabs.newTabSpec(t);
            spec1.setIndicator(t);
            spec1.setContent(android.R.id.tabcontent);
            tabs.addTab(spec1);
        }
        tabs.setOnTabChangedListener(changeTab);


        // --------------- Listview ----------------
        listview = (JListView) contentView.findViewById(R.id.listview);
        tabs.setCurrentTab(0);
        changeTab.onTabChanged(tabs.getCurrentTabTag());


        return contentView;
        /*
        if (savedInstanceState == null) {
            tb = new TitleBar();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, tb).commit();
        }
        //*/
    }

    /** DEBUG */
    private HashMap<String,String> createHash(String[] keys, String a, String b, String c)
    {
        HashMap<String,String> h = new HashMap<String, String>();
        h.put(keys[0], a);
        h.put(keys[1], b);
        h.put(keys[2], c);
        return h;
    }

    /** Change tabs, so change fragments **/
    TabHost.OnTabChangeListener changeTab = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String s) {
            Log.d("Derp MainActivity", "Tab: " + s);

            String[] keys = {"album_art", "track", "album"};
            ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();

            data.add(createHash(keys, "derp", s, "Herpaderp"));
            data.add(createHash(keys, "derp", "Mirrors", "All:my:faults"));
            data.add(createHash(keys, "derp", "The Final Rewind", "Tryad"));
            data.add(createHash(keys, "derp", "Silver", "The Gray Havens"));

            MainListviewAdapter adapter = new MainListviewAdapter(MainFragment.this.getActivity(),
                                                                  R.layout.listview_row, keys, data);


            listview.setType(s);
            listview.setAdapter(adapter);
        }
    };
}
