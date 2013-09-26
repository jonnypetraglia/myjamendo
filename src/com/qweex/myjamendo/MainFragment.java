package com.qweex.myjamendo;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

// Cache:  http://developer.android.com/training/displaying-bitmaps/cache-bitmap.html

public class MainFragment extends Fragment {


    ListFragment list_frag;
    Button swipe_button;
    ProgressBar loading;
    ImageView not_loading;
    TabHost tabs;

    View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceStatee)
    {
        contentView = inflater.inflate(R.layout.main, container, false);

        // --------------- Show/hide views ----------------
        /* Extras:
            - Title (for titlebar)
            - Search (show/hide
            - tabs (show/hide)
        */
        String title = getArguments().getString("title");
        boolean showSearch = getArguments().getBoolean("search");
        boolean showTabs = getArguments().getBoolean("tabs");

        if(!showSearch)
            contentView.findViewById(R.id.titlebar_search).setVisibility(View.GONE);
        if(!showTabs)
            contentView.findViewById(R.id.tabHost).setVisibility(View.GONE);


        // --------------- Titlebar ----------------
        /*DEBUG*/ title = "Derp";
        ((TextView)contentView.findViewById(R.id.titlebar_title)).setText(title);


        // --------------- Swipe Buttons ----------------
        swipe_button = (Button) contentView.findViewById(R.id.titlebar_swipe);
        //TODO: set swipe gesture/onclick


        // --------------- Loading ----------------
        loading = (ProgressBar) contentView.findViewById(R.id.titlebar_progress_start);
        not_loading = (ImageView) contentView.findViewById(R.id.titlebar_progress_stop);
        stopLoading();


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
        list_frag = (ListFragment) MainActivity.this.getFragmentManager().findFragmentById(R.id.frag_listview);
        tabs.setCurrentTab(0);
        changeTab.onTabChanged(tabs.getCurrentTabTag());
        list_frag.getListView().setOnItemClickListener(clickLVItem);


        return contentView;
        /*
        if (savedInstanceState == null) {
            tb = new TitleBar();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, tb).commit();
        }
        //*/
    }

    //DEBUG
    private HashMap<String,String> createHash(String[] keys, String a, String b, String c)
    {
        HashMap<String,String> h = new HashMap<String, String>();
        h.put(keys[0], a);
        h.put(keys[1], b);
        h.put(keys[2], c);
        return h;
    }

    /** Click item; maybe move this to a separate class **/
    AdapterView.OnItemClickListener clickLVItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //TODO
        }
    };

    /** Change tabs, so change fragments **/
    TabHost.OnTabChangeListener changeTab = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String s) {
            Log.d("MainActivity", s);

            String[] keys = {"album_art", "track", "album"};
            ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();

            data.add(createHash(keys, "derp", s, "Herpaderp"));
            data.add(createHash(keys, "derp", "Mirrors", "All:my:faults"));
            data.add(createHash(keys, "derp", "The Final Rewind", "Tryad"));
            data.add(createHash(keys, "derp", "Silver", "The Gray Havens"));

            MainListviewAdapter adapter = new MainListviewAdapter(MainActivity.this.getActivity(),
                                                                  R.layout.main_row, keys, data);

            list_frag.getArguments().putString("type", s);
            list_frag.setListAdapter(adapter);

        }
    };

    public void startLoading()
    {
        loading.setVisibility(View.VISIBLE);
        not_loading.setVisibility(View.GONE);
    }

    public void stopLoading()
    {
        loading.setVisibility(View.GONE);
        not_loading.setVisibility(View.VISIBLE);
    }
}
