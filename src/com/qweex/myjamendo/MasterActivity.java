package com.qweex.myjamendo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MasterActivity extends Activity
{
    public enum DEVICE_TYPE {NEW_PHONE, OLD_PHONE, TABLET};

    public static DEVICE_TYPE DeviceType;

    Button swipe_button;
    ProgressBar loading;
    ImageView not_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //if tablet
        //   tablet ui
        //if >4
        //   old ui
        //else
        {
            DeviceType = DEVICE_TYPE.NEW_PHONE;
            setContentView(R.layout.master_phone);
        }

        // --------------- Loading ----------------
        loading = (ProgressBar) findViewById(R.id.titlebar_progress_start);
        not_loading = (ImageView) findViewById(R.id.titlebar_progress_stop);
        stopLoading();

        setupTabs("Jamendo", false, false);
    }

    public void setupTabs(String title, boolean showSearch, boolean showTabs)
    {
        // --------------- Show/hide views ----------------
        /* Extras:
            - Title (for titlebar)
            - Search (show/hide
            - tabs (show/hide)
        */


        findViewById(R.id.titlebar_search).setVisibility(showSearch ? View.VISIBLE : View.GONE);
        findViewById(R.id.tabHost).setVisibility(showTabs ? View.VISIBLE : View.GONE);


        // --------------- Titlebar ----------------
        /*DEBUG*/ title = "Derp";
        ((TextView)findViewById(R.id.titlebar_title)).setText(title);


        // --------------- Swipe Buttons ----------------
        swipe_button = (Button) findViewById(R.id.titlebar_button);
        switch(MasterActivity.DeviceType)
        {
            case TABLET:
                //TODO: Should not exist?
            case OLD_PHONE:
                swipe_button.setOnClickListener(pressBack);
            case NEW_PHONE:
            default:
                swipe_button.setOnClickListener(pressSwipe);
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
}
