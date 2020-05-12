package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Set;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private static final String TAG = "CityAdapter";
    private ArrayList<String> mCityNames = new ArrayList<String>();
    private Context mContext;


    public CityAdapter(Context mContext, ArrayList<String> mCityNames) {
        this.mCityNames = mCityNames;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cityName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.city_name);
            parentLayout = itemView.findViewById(R.id.city_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "on>BindViewHolder: called.");
        holder.cityName.setText(mCityNames.get(position));

        holder.cityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick : clicked on: " + mCityNames.get(position));
               // Toast.makeText(mContext, mCityNames.get(position), Toast.LENGTH_SHORT).show();



               FragmentForecast.callWeatherForecastAndShowFragment( v, mCityNames.get(position) );




            }
        });
    }

    @Override
    public int getItemCount() {
        return mCityNames.size();
    }
}
