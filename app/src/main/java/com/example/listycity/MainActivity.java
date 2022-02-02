package com.example.listycity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    ListView cityList;
    ArrayAdapter<City> cityAdapter2;
    ArrayList<City> cityDataList;

    String stringToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Vancouver","Toronto", "Hamilton", "Denver", "Los Angeles"};
        String[] provinces = {"AB", "BC", "ON", "ON", "CO", "CA"};

        cityDataList = new ArrayList<City>();

        for (int i=0; i<cities.length;i++) {
            cityDataList.add((new City(cities[i], provinces[i])));
        }

        cityAdapter2 = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter2);

        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener((v) -> {
            new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                view.setSelected(true);

                int index = i;
                //Toast.makeText(MainActivity.this,"Selected item: "+ dataList.get(index), Toast.LENGTH_LONG).show();
                City fetchedCity = cityAdapter2.getItem(i);

                Log.d("MainActivity", fetchedCity.getCityName());

                AddCityFragment addEditCityFragment = new AddCityFragment();

                AddCityFragment bundledCityFragment = addEditCityFragment.newInstance(fetchedCity.getCityName(), fetchedCity.getProvinceName(), i);

                bundledCityFragment.show(getSupportFragmentManager(), "Edit_city");
                //new AddCityFragment(fetchedCity.getCityName(), fetchedCity.getProvinceName()).show();

            }
        });
    }

    @Override
    public void onOkPressed(City newCity, int indexToEdit) {

        if (indexToEdit == -1) {
            //Add a new city baby
            cityAdapter2.add(newCity);
        }

        else {
            //edit a city

            Log.d("MainActivity", "need to edit a city now with index "+ indexToEdit);

            City cityToChange = cityAdapter2.getItem(indexToEdit);
            cityToChange.setCity(newCity.getCityName());
            cityToChange.setProvince(newCity.getProvinceName());
            cityAdapter2.notifyDataSetChanged();


        }
    }

}