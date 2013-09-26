package com.qweex.myjamendo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NowPlayingFragment extends Fragment
{
    MasterActivity MasterActivityRef;
    View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        MasterActivityRef = (MasterActivity) getActivity();
        contentView = inflater.inflate(R.layout.now_playing, container, false);


        return contentView;
    }


    /**
     *  Update Info (Track, Album, Artist, and Full Time)
     *  and Album Art
     *  Loop to update current time (slider and text)
     *
     *  prev onclicklistener
     *  next onclicklistener
     *  play onclicklistener
     *  pause onclicklistener
     */
}
