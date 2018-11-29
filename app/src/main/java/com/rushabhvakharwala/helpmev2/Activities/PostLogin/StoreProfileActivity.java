package com.rushabhvakharwala.helpmev2.Activities.PostLogin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.Activities.MainActivity;
import com.rushabhvakharwala.helpmev2.R;
import com.rushabhvakharwala.helpmev2.Utils.Tools;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class StoreProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int IMAGE_PICKER = 0;
    private List<String> items = new ArrayList<>();
    private ArrayAdapter adapter;
    private TextView txt_no_item;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);


        findViewById(R.id.postbutton).setOnClickListener(this);
//        findViewById(R.id.image_upload).setOnClickListener(this);
        initToolbar();
        initComponent();
        initNavigationMenu();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_5);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_6);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_7);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_8);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_10);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_12);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
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


                    case R.id.nearby_places:
                        intent = new Intent(StoreProfileActivity.this, NearbyPlacesActivity.class);
                        startActivity(intent);

                        return true;

                    case R.id.redeem_coupon:
                        intent = new Intent(StoreProfileActivity.this, RedeemCoupon.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_latest:
                        intent = new Intent(StoreProfileActivity.this, FeedActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.store_profile:
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_setting:
                        Toast.makeText(StoreProfileActivity.this, "Settings Selected",Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.nav_logout:
                        intent = new Intent(StoreProfileActivity.this, MainActivity.class);
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

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.postbutton:
                showCustomDialog();

        }
    }





    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_add_review);
        dialog.setCancelable(true);



        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final TextView tw = findViewById(R.id.postemail);
        //tw.setText((String)email);
        final EditText et_post = (EditText) dialog.findViewById(R.id.et_post);
        final AppCompatRatingBar rating_bar = (AppCompatRatingBar) dialog.findViewById(R.id.rating_bar);
        ((AppCompatButton) dialog.findViewById(R.id.bt_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.bt_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String review = et_post.getText().toString().trim();
                    if (review.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill review text", Toast.LENGTH_SHORT).show();
                    } else {
                        items.add("(" + rating_bar.getRating() + ") " + review);
                        adapter.notifyDataSetChanged();
                    }
                    if (!adapter.isEmpty()) {
                        txt_no_item.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    Log.d("Exception",e.toString());
                }
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();

            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
