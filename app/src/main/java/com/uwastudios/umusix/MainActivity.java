package com.uwastudios.umusix;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.uwastudios.umusix.Tab.MyAdapter;
import com.uwastudios.umusix.Tab.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("HOME");
        setSupportActionBar(toolbar);

        mViewPager=(ViewPager)findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);

        //Header
        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.bg)
             //   .addProfiles(
               //         new ProfileDrawerItem().withName("Ujang Wahyu").withEmail("ujang_wahyu@outlook.com").withIcon(getResources().getDrawable(R.drawable.circle_mask))
               // )
                .build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItem(0)
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Cari").withIcon(getResources().getDrawable(R.drawable.ic_search_white_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.drawable.ic_home_white_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("List Lagu").withIcon(getResources().getDrawable(R.drawable.ic_view_list_white_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Buat Daftar Putar").withIcon(getResources().getDrawable(R.drawable.ic_library_add_white_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Pengaturan").withIcon(getResources().getDrawable(R.drawable.ic_settings_white_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Keluar").withIcon(getResources().getDrawable(R.drawable.ic_exit_to_app_white_48dp)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
