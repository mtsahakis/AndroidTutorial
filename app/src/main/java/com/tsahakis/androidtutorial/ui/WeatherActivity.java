package com.tsahakis.androidtutorial.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tsahakis.androidtutorial.R;
import com.tsahakis.androidtutorial.data.WeatherItem;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showResponseContainer() {

    }

    @Override
    public void hideResponseContainer() {

    }

    @Override
    public void showErrorContainer() {

    }

    @Override
    public void hideErrorContainer() {

    }

    @Override
    public void displayData(WeatherItem weatherItems) {

    }

    @Override
    public void displayError(String error) {

    }
}
