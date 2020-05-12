package pt.ua.nextweather.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;
import pt.ua.nextweather.network.WeatherTypesResultsObserver;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentForecast#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentForecast extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;


    public static View mView ;
    public  static IpmaWeatherClient client = new IpmaWeatherClient();
    public static  HashMap<String, City> cities = new HashMap<>();
    public static  HashMap<Integer, WeatherType> weatherDescriptions;
    public static ArrayList<Weather> WeathersList = new ArrayList<>();

    public FragmentForecast() {
        // Required empty public constructor
    }



    public static void callWeatherForecastAndShowFragment(View v, String cityname){
        mView = v;
        callWeatherForecastForACityStep1(cityname);
        //in end of step 3 show new fragment
    }

    public static FragmentForecast newInstance() {
        FragmentForecast fragment = new FragmentForecast();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);



        recyclerView = rootView.findViewById(R.id.recycler_forecast);


        mAdapter = new WeatherAdapter(inflater.getContext(), WeathersList);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_forecast, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public static void callWeatherForecastForACityStep1(String city) {

        //feedback.append("\nGetting forecast for: " + city); feedback.append("\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
                Log.d("FRAGFORECAST","Failed to get weather conditions!");
            }
        });

    }

    public  static void  callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                    Log.d("FRAGFORECAST", "unknown city: " + city);
                }

            }

            @Override
            public void onFailure(Throwable cause) {
                Log.d("MAIN", "Failed to get cities list!");
            }
        });
    }

    public static void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                WeathersList = new ArrayList<Weather>(forecast);

                FragmentForecast forecastFrag = FragmentForecast.newInstance();
                AppCompatActivity activity = (AppCompatActivity) mView.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCities, forecastFrag).addToBackStack(null).commit();
            }

            @Override
            public void onFailure(Throwable cause) {
                Log.d("FRAGFORECAST", "Failed to get forecast for 5 days");
            }
        });
    }
}
