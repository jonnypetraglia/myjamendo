package com.qweex.myjamendo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class JListView extends ListView implements AdapterView.OnItemClickListener
{
    String listType;

    public JListView(Context context) {
        super(context);
    }

    public JListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //TODO
    }

    public void setType(String l)
    {
        this.listType = l;
    }
}
