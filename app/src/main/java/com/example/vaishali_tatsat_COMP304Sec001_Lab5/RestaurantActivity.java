package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {
    public static final String EXTRA_SELECTED_CUISINE = "com.example.vaishali_tatsat_COMP304Sec001_Lab5.EXTRA_SELECTED_CUISINE";

    String SELECTED_CUISINE;

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private ArrayList<RestaurantCard> restaurantsArrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();
        SELECTED_CUISINE = intent.getStringExtra(EXTRA_SELECTED_CUISINE);

        progressDialog = new ProgressDialog(RestaurantActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);

        InitializeCardView();

    }

    private void InitializeCardView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantsArrayList = new ArrayList<>();


        adapter =  new RestaurantAdapter(this, restaurantsArrayList);
        recyclerView.setAdapter(adapter);
        CreateDataForCards();

        adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RestaurantCard rest) {

                LatLng latLng = rest.getLatLng();
                Intent intent = new Intent(RestaurantActivity.this, MapsActivity.class);
                intent.putExtra(MapsActivity.EXTRA_ADDRESS, rest.getAddress());
                intent.putExtra(MapsActivity.EXTRA_NAME, rest.getName());
                intent.putExtra(MapsActivity.EXTRA_CUISINE, rest.getType());
                intent.putExtra(MapsActivity.EXTRA_LAT, latLng.latitude);
                intent.putExtra(MapsActivity.EXTRA_LNG, latLng.longitude);

               startActivity(intent);
            }
        });
    }

    private void CreateDataForCards() {

        String url  = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" +
                SELECTED_CUISINE + "+Restaurant"+
                "&sensor=true"+
                "&location=43.6,-79.438"+
                "&radius=20"+
                "&key="+ getResources().getString(R.string.google_maps_key);

        new PlaceTask().execute(url);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
    }

    private class PlaceTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) {
           new ParserTask().execute(s);
        }

        private String downloadUrl(String string) throws IOException {
            URL url = new URL(string);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";

            while((line=reader.readLine()) != null){
                builder.append(line);
            }
            String data = builder.toString();
            reader.close();

            return data;

        }

        private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
            @Override
            protected List<HashMap<String, String>> doInBackground(String... strings) {
               JsonParser jsonParser = new JsonParser();
               List<HashMap<String, String>> maplist = null;
                JSONObject object = null;
                try {
                    object = new JSONObject(strings[0]);
                    maplist = jsonParser.parseResult(object);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return  maplist;
            }

            @Override
            protected void onPostExecute(List<HashMap<String, String>> hashMaps) {

                for(int i = 0; i< hashMaps.size(); i++)
                {
                    HashMap<String, String> data = hashMaps.get(i);
                    LatLng latLng = new LatLng(Double.parseDouble(data.get("lat")), Double.parseDouble(data.get("lng")));

                    restaurantsArrayList.add(new RestaurantCard(data.get("name"), SELECTED_CUISINE, data.get("address"), latLng));
                }
                progressDialog.hide();
                adapter.notifyDataSetChanged();
            }
        }
    }
}