package com.qweex.myjamendo;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MasterActivity extends Activity
{
    public enum DEVICE_TYPE {NEW_PHONE, OLD_PHONE, TABLET};

    public static DEVICE_TYPE DeviceType;

    ImageButton titlebar_button;
    ProgressBar loading;
    ImageView not_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(false) //isTablet)
        {
            DeviceType = DEVICE_TYPE.TABLET;
            setContentView(R.layout.master_tablet);
        }
        //if >4
        //   old ui
        else
        {
            DeviceType = DEVICE_TYPE.NEW_PHONE;
            setContentView(R.layout.master_phone);
        }

        // --------------- Loading ----------------
        loading = (ProgressBar) findViewById(R.id.titlebar_progress_start);
        not_loading = (ImageView) findViewById(R.id.titlebar_progress_stop);
        stopLoading();


        setupTitlebar("Jamendo", false); //todo
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setupTitlebar(int titleID, boolean showSearch) {
        setupTitlebar(getResources().getString(titleID), showSearch);
    }

    public void setupTitlebar(String title, boolean showSearch)
    {
        // --------------- Show/hide views ----------------
        /* Extras:
            - Title (for titlebar)
            - Search (show/hide
            - tabs (show/hide)
        */

        findViewById(R.id.titlebar_search).setVisibility(showSearch ? View.VISIBLE : View.GONE);


        // --------------- Titlebar ----------------
        if(title==null)
            title="";
        ((TextView)findViewById(R.id.titlebar_title)).setText(title);


        // --------------- Swipe Buttons ----------------
        titlebar_button = (ImageButton) findViewById(R.id.titlebar_button);
        switch(MasterActivity.DeviceType)
        {
            case TABLET:
                //TODO: Should not exist?
            case OLD_PHONE:
                titlebar_button.setOnClickListener(pressBack);
            case NEW_PHONE:
            default:
                titlebar_button.setOnClickListener(pressSwipe);
        }
    }

    //TODO
    /** */
    private View.OnClickListener pressBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    /** For the NEW_PHONE */
    private View.OnClickListener pressSwipe = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DrawerLayout d = (DrawerLayout) findViewById(R.id.drawer_layout);

            if(d.isDrawerOpen(Gravity.LEFT))
                d.closeDrawer(Gravity.LEFT);
            else
                d.openDrawer(Gravity.LEFT);
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

    public static int getThemeColor(Context context, int attr) // attr = R.attr.theme_color
    {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }
}
