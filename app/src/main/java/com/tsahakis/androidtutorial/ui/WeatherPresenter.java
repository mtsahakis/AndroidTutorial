package com.tsahakis.androidtutorial.ui;


public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View mView;

    WeatherPresenter(WeatherContract.View view) {
        mView = view;
    }

    @Override
    public void init() {
        mView.hideResponseContainer();
        mView.hideErrorContainer();
    }

    @Override
    public void onButtonClicked() {

    }
}
