package com.qweex.myjamendo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class ListFragment extends android.app.ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        getListView().setDivider(new ColorDrawable(this.getResources().getColor(R.color.icon_color)));
        getListView().setDividerHeight(3);

        //getListView().setBackgroundColor(getResources().getColor(R.color.j_shadow));
    }
}
