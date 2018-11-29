package com.rushabhvakharwala.helpmev2.Activities.PostLogin;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.Activities.MainActivity;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.NearbyPlaces;
import com.rushabhvakharwala.helpmev2.R;

public class FeedActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private View parent_view;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initToolbar();
        initNavigationMenu();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Feed");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                Intent intent;
                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();

                switch (item.getItemId()){
                    case R.id.nav_home:
                        return true;

                    case R.id.nearby_places:
                        intent = new Intent(FeedActivity.this, NearbyPlacesActivity.class);
                        startActivity(intent);
                        return true;


                    case R.id.redeem_coupon:
                        intent = new Intent(FeedActivity.this, RedeemCoupon.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_latest:
                        return true;

                    case R.id.nav_setting:
                        Toast.makeText(FeedActivity.this, "Settings Selected",Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.nav_logout:
                        intent = new Intent(FeedActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                //actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();
                return true;
            }
        });
    }
}
