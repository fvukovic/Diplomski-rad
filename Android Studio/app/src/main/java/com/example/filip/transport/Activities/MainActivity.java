package com.example.filip.transport.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filip.transport.Fragments.SviRadniNalozi;
import com.example.filip.transport.Fragments.UploadImage;
import com.example.filip.transport.R;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /*   userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        String ime = userDetails.getString("ime", "nema");
        String prezime = userDetails.getString("prezime", "nema");
        if(ime.equals("nema")) {
            this.finishAffinity();
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
        android.support.v4.app.Fragment fragment = null;
        fragment = new SviRadniNalozi();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_main, fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawer.closeDrawers();
                        if(menuItem.getTitle().equals("Radni nalofzi")){

                        }else if(menuItem.getTitle().equals("Radni nalozi")){
                            SviRadniNalozi nextFrag= new SviRadniNalozi();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_main, nextFrag,"findThisFragment")
                                    .addToBackStack(null)
                                    .commit();

                        }else if(menuItem.getTitle().equals("Upload slike")){
                            UploadImage nextFrag= new UploadImage();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_main, nextFrag,"findThisFragment")
                                    .addToBackStack(null)
                                    .commit();
                        }
                        return true;
                    }
                });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}