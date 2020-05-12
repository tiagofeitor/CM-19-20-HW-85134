package pt.ua.nextweather.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private static final String TAG = "WeatherAdapter";

    private ArrayList<Weather> mWeatherList;

    private Context mContext;

    public WeatherAdapter( Context mContext , ArrayList<Weather> mWeatherList) {
        this.mWeatherList = mWeatherList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView temperature;


        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temperature = itemView.findViewById(R.id.temperature);
            parentLayout = itemView.findViewById(R.id.weather_layout);


        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_layout, parent, false);
        WeatherAdapter.ViewHolder holder = new WeatherAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "on>BindViewHolder: called.");
        if(holder.temperature == null){
            Log.d(TAG, "on>BindViewHolder: TEMEPRATURE TEXT VIEW A NULL.");

        }

        else if(mWeatherList!=null)
            holder.temperature.setText("Dia: " +  mWeatherList.get(position).getForecastDate() + "\n"
                    + "Min temp: " +  mWeatherList.get(position).getTMin() + "ºC    Max Temp: " + mWeatherList.get(position).getTMax()+"ºC\n"+
                    "Direção vento: "+mWeatherList.get(position).getPredWindDir() + "     Classe vel. vento: " +  mWeatherList.get(position).getClassWindSpeed()
                    + "\nProb Precipitação: " +  mWeatherList.get(position).getPrecipitaProb()        +"%");



    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }






}
