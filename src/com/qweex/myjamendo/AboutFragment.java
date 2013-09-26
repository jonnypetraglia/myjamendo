package com.qweex.myjamendo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {
    View contentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatee)
    {
        contentView = inflater.inflate(R.layout.about, container, false);
        return contentView;
    }
}
