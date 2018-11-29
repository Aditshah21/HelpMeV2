package com.rushabhvakharwala.helpmev2.Activities.PostLogin;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.API.RetrofitClient;
import com.rushabhvakharwala.helpmev2.Activities.MainActivity;
import com.rushabhvakharwala.helpmev2.Adapters.NearbyPlacesAdapter;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location._NearbyPlace;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location._UserLocationAttributes;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.NearbyPlaces;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesRequest;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesResponse;
import com.rushabhvakharwala.helpmev2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyPlacesActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_LOCATION = 1;
    private ActionBar actionBar;
    private Toolbar toolbar;
    RecyclerView recyclerView;
    NearbyPlacesAdapter placesAdapter;
    private String access_token;
    private String email;
    private List<String> items = new ArrayList<>();
    private ArrayAdapter adapter;
    private TextView txt_no_item;

    ArrayList<NearbyPlaces> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);
        initToolbar();
        initNavigationMenu();

        //access_token = getIntent().getExtras().getString("access_token");
//        email = getIntent().getExtras().getString("email");

        placesList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findNearbyPlaces();

        //findViewById(R.id.postbutton).setOnClickListener(this);
        //showCustomDialog();

        //findViewById(R.id.postbutton).setOnClickListener(this);


    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nearby Places");
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


                    case R.id.nearby_places:
                        drawer.closeDrawers();
                        return true;

                    case R.id.redeem_coupon:
                        intent = new Intent(NearbyPlacesActivity.this, RedeemCoupon.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_latest:
                        intent = new Intent(NearbyPlacesActivity.this, FeedActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.store_profile:
                        intent = new Intent(NearbyPlacesActivity.this, StoreProfileActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_setting:
                        Toast.makeText(NearbyPlacesActivity.this, "Settings Selected",Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.nav_logout:
                        intent = new Intent(NearbyPlacesActivity.this, MainActivity.class);
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


    private void findNearbyPlaces() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            return;
        }else {


            Location loc1 = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location loc2 = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location loc3 = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);


            if(loc1 !=null) {
                String latitude = String.valueOf(loc1.getLatitude());
                String longitude = String.valueOf(loc1.getLongitude());

                //Toast.makeText(NearbyPlacesActivity.this, latitude + " " + longitude, Toast.LENGTH_LONG).show();
                LocAndPrint(latitude,longitude);
            }
            if(loc2 !=null) {
                String latitude = String.valueOf(loc2.getLatitude());
                String longitude = String.valueOf(loc2.getLongitude());

                //Toast.makeText(NearbyPlacesActivity.this, latitude + " " + longitude, Toast.LENGTH_LONG).show();
                LocAndPrint(latitude,longitude);
            }
            if(loc3 !=null) {
                String latitude = String.valueOf(loc3.getLatitude());
                String longitude = String.valueOf(loc3.getLongitude());

                //Toast.makeText(NearbyPlacesActivity.this, latitude + " " + longitude, Toast.LENGTH_LONG).show();
                LocAndPrint(latitude,longitude);
            }else {
                Toast.makeText(NearbyPlacesActivity.this, "Unable to locate you", Toast.LENGTH_LONG).show();
            }


        }


    }

    private void LocAndPrint(String latitude,String longitude){

        //Ittsel hashmap
        HashMap<String, HashMap<String, HashMap<String, String>>> hash = new HashMap<>();
        hash.put("nearby_place", new HashMap<String, HashMap<String, String>>() );
        hash.get("nearby_place").put("user_location_attributes", new HashMap<String, String>());
        hash.get("nearby_place").get("user_location_attributes").put("lat",latitude); // add gps location here
        hash.get("nearby_place").get("user_location_attributes").put("lng",longitude); // add gps location here

        Log.d("Location", latitude + " " + longitude );

        Call<HashMap<String, List<HashMap<String, String>>>> call = RetrofitClient
                .getInstance()
                .getGoogleGsonApi()
                .nearby_places(hash);

        final ArrayList<NearbyPlaces> nearbyPlaces = new ArrayList<>();

        call.enqueue(new Callback<HashMap<String, List<HashMap<String, String>>>>() {
            @Override
            public void onResponse(Call<HashMap<String, List<HashMap<String, String>>>> call, Response<HashMap<String, List<HashMap<String, String>>>> response) {
                Log.d("MyApp", String.valueOf(response.body().get("nearby_places").size()));

                for (int i = 0; i < response.body().get("nearby_places").size(); i++){
                    String name = response.body().get("nearby_places").get(i).get("name");
                    String vicinity = response.body().get("nearby_places").get(i).get("vicinity");
                    String icon = response.body().get("nearby_places").get(i).get("icon");
                    String status = response.body().get("nearby_places").get(i).get("status");
                    String rating = response.body().get("nearby_places").get(i).get("rating");
                    String photo = response.body().get("nearby_places").get(i).get("photo");

                    nearbyPlaces.add(new NearbyPlaces(name,vicinity,icon,status,photo,rating));

                }

                placesList = nearbyPlaces;
                placesAdapter = new NearbyPlacesAdapter(NearbyPlacesActivity.this, placesList);
                recyclerView.setAdapter(placesAdapter);

            }

            @Override
            public void onFailure(Call<HashMap<String, List<HashMap<String, String>>>> call, Throwable t) {

            }
        });


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
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {

        showCustomDialog();
    }
}