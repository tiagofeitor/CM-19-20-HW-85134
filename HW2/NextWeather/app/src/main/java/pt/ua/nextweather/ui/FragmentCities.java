package pt.ua.nextweather.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Set;

import pt.ua.nextweather.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCities#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCities extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public FragmentCities() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FragmentCities.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCities newInstance() {
        FragmentCities fragment = new FragmentCities();
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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cities, container, false);


    }







}
