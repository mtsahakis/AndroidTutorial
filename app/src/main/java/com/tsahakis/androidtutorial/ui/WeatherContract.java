package com.tsahakis.androidtutorial.ui;


import com.tsahakis.androidtutorial.api.WeatherItem;

public interface WeatherContract {

    interface View {

        void showResponseContainer();

        void hideResponseContainer();

        void showErrorContainer();

        void hideErrorContainer();

        void displayData(WeatherItem weatherItem);

        void displayError(String error);

    }

    interface Presenter {

        void init();

        void onButtonClicked();

        void unsubscribe();

    }

}
