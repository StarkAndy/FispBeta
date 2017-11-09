package com.example.thelost.fisp_beta10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.thelost.fisp_beta10.R;
import com.example.thelost.fisp_beta10.adapter.RoomAdapter;
import com.example.thelost.fisp_beta10.bean.RoomBean;
import com.example.thelost.fisp_beta10.data.FispDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RoomAdapter.OnViewClickListener {


    List<RoomBean> roomLists = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;

    private static final String TAG = "Error";

    private static final int SELECT_PICTURE = 100;

    FispDatabase fispDatabase;

    AppCompatImageView imgView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //initializing recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        //initializing ImageView


        fispDatabase = new FispDatabase(this);

        fispDatabase.open();

        roomLists= getTableData();

        //initializing room adapter
        mRoomAdapter = new RoomAdapter(HomePage.this, roomLists,fispDatabase.getTwoRemainingImage(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //These code will help recycler view to scroll smoothly
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setAdapter(mRoomAdapter);

        mRoomAdapter.notifyDataSetChanged();

    }

    //function get data from the datatabase
    public List<RoomBean> getTableData() {

        try {

            List<RoomBean> roomBeen= fispDatabase.retriveTableRoomData();

            return fispDatabase.retriveTableRoomData();


        }catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }

    }


    @Override
    public void onViewClicked() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sigin_signup) {
            //Intent navigate to CreateUSer Profile
            Intent intent =new Intent(HomePage.this,CreateUserProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
