package com.rushabhvakharwala.helpmev2.Activities.PostLogin;

import android.Manifest;
import android.content.Context;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rushabhvakharwala.helpmev2.API.RetrofitClient;
import com.rushabhvakharwala.helpmev2.Adapters.NearbyPlacesAdapter;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location.CurrentLocation;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location.Place;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.Location.UserLocation;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.NearbyPlaces;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesRequest;
import com.rushabhvakharwala.helpmev2.Models.PostLoginModels.PlacesResponse;
import com.rushabhvakharwala.helpmev2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearbyPlacesActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    RecyclerView recyclerView;
    NearbyPlacesAdapter placesAdapter;
    private String access_token;

    List<NearbyPlaces> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);
        initToolbar();
        initNavigationMenu();

        access_token = getIntent().getExtras().getString("access_token");

        placesList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findNearbyPlaces();

        placesAdapter = new NearbyPlacesAdapter(this, placesList);
        recyclerView.setAdapter(placesAdapter);


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
                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();
                return true;
            }
        });
    }


    private void findNearbyPlaces() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        PlacesRequest placesRequest = new PlacesRequest(new CurrentLocation(new Place(new UserLocation(loc.getLatitude(),loc.getLongitude()))));

        // code sample - place it where you like
        HashMap<String, HashMap<String, HashMap<String, String>>> hash = new HashMap<String, HashMap<String, HashMap<String, String>>>();
        hash.put("nearby_place", new HashMap<String, HashMap<String, String>>() );
        hash.get("nearby_place").put("user_location_attributes", new HashMap<String, String>());
        hash.get("nearby_place").get("user_location_attributes").put("lat","42.3072567"); // add gps location here
        hash.get("nearby_place").get("user_location_attributes").put("lng","-83.0560483"); // add gps location here

        Call<HashMap<String, ArrayList<HashMap<String, String>>>> call2 =  RetrofitClient
                .getInstance()
                .getApi()
                .nearby_places(hash);



        call2.enqueue(new Callback<HashMap<String, ArrayList<HashMap<String, String>>>>() {
            @Override
            public void onResponse(Call<HashMap<String, ArrayList<HashMap<String, String>>>> call2, Response<HashMap<String, ArrayList<HashMap<String, String>>>> response) {
                Log.d("MyApp",response.body().get("nearby_places").get(0).get("name"));
                Log.d("MyApp",response.body().get("nearby_places").get(0).get("vicinity"));
                Log.d("MyApp",response.body().get("nearby_places").get(0).get("icon"));
                Log.d("MyApp",response.body().get("nearby_places").get(0).get("status"));
                Log.d("MyApp",response.body().get("nearby_places").get(0).get("rating"));
            }



            @Override
            public void onFailure(Call<HashMap<String, ArrayList<HashMap<String, String>>>> call2, Throwable t) {

                Toast.makeText(NearbyPlacesActivity.this, t.getMessage() , Toast.LENGTH_LONG).show();

            }
        });

        // end of code

//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"nearby_place\":" +
//                "{\"user_location_attributes\":" +
//                    "{\"lat\":\""+loc.getLatitude()+"\"," +
//                    "\"lng\":\""+loc.getLongitude()+"\"" +
//                "}" +
//                "}" +
//                "}");
//        Request request = new Request.Builder()
//                .url("https://helpme-dev.herokuapp.com/places/nearby_places/fetch")
//                .post(body)
//                .addHeader("content-type", "application/json")
//                .addHeader("authorization", "Bearer "+access_token)
//                .addHeader("cache-control", "no-cache")
//
//                .build();
//
//        try {
//            okhttp3.Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Call<PlacesResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .nearbyPlacesList(placesRequest);

        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {

                PlacesResponse pr = response.body();
                placesList = pr.getNearby_places();
                if(response.code()==200)
                    Toast.makeText(NearbyPlacesActivity.this, "Browse the heck away", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NearbyPlacesActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });

    }

}