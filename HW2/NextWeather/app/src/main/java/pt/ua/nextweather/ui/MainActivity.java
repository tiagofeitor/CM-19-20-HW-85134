package pt.ua.nextweather.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;
import pt.ua.nextweather.network.WeatherTypesResultsObserver;


public class MainActivity extends AppCompatActivity {

    private TextView feedback;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public static IpmaWeatherClient client = new IpmaWeatherClient();
    public static HashMap<String, City> cities = new HashMap<>();
    public static HashMap<Integer, WeatherType> weatherDescriptions;

    FragmentManager fragmentManager = getSupportFragmentManager();

    public static ArrayList<Weather> WeathersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callWeatherForecastForACityStep1("Aveiro");



        FragmentCities simpleFragment = FragmentCities.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment , simpleFragment);
        fragmentTransaction.commit();




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //feedback = findViewById(R.id.tvFeedback);



    }


    public void initCitiesRecycler(){
        //RECYCLER VIEW FOR CITIES LIST

        recyclerView = findViewById(R.id.recycler_cities);

        java.util.ArrayList<String> cityNames = new ArrayList<String>();
        Set<String> names = cities.keySet();
        cityNames.addAll(names);

        mAdapter = new CityAdapter(this, cityNames);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public ArrayList<Weather> getForecast(String cityName){
            callWeatherForecastForACityStep1(cityName);
            Log.d("MAIN", "WEATHERS LIST SIZE : " + WeathersList.size());
            return WeathersList;

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


    public void callWeatherForecastForACityStep1(String city) {

        //feedback.append("\nGetting forecast for: " + city); feedback.append("\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                MainActivity.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
               Log.d("MAIN","Failed to get weather conditions!");
            }
        });

    }

    public  void  callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                   Log.d("MAIN", "unknown city: " + city);
                }
                initCitiesRecycler();

            }

            @Override
            public void onFailure(Throwable cause) {
                Log.d("MAIN", "Failed to get cities list!");
            }
        });
    }

    public void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                WeathersList =  new ArrayList<Weather>(forecast);
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.d("MAIN", "Failed to get forecast for 5 days");
            }
        });

    }

}
