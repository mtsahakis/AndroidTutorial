package com.tsahakis.androidtutorial.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tsahakis.androidtutorial.R;
import com.tsahakis.androidtutorial.app.WeatherApplication;
import com.tsahakis.androidtutorial.api.WeatherItem;
import com.tsahakis.androidtutorial.data.WeatherRepository;
import com.tsahakis.androidtutorial.databinding.ActivityWeatherBinding;

import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    private ActivityWeatherBinding mBinding;

    private WeatherContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        setSupportActionBar(mBinding.toolbar);

        mPresenter = new WeatherPresenter(
                this,
                new WeatherRepository(((WeatherApplication) getApplication()).getWeatherApi()),
                new CompositeDisposable());
        mPresenter.init();

        mBinding.getData.setOnClickListener((v) -> mPresenter.onButtonClicked());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.unsubscribe();
    }

    @Override
    public void showResponseContainer() {
        mBinding.responseContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResponseContainer() {
        mBinding.responseContainer.setVisibility(View.GONE);
    }

    @Override
    public void showErrorContainer() {
        mBinding.errorContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorContainer() {
        mBinding.errorContainer.setVisibility(View.GONE);
    }

    @Override
    public void displayData(WeatherItem weatherItem) {
        mBinding.temperature.setText(String.format(Locale.getDefault(), "%.2f", weatherItem.getTemp()));
        mBinding.humidity.setText(String.format(Locale.getDefault(), "%d", weatherItem.getHumidity()));
        mBinding.pressure.setText(String.format(Locale.getDefault(), "%d", weatherItem.getPressure()));
        mBinding.tempMin.setText(String.format(Locale.getDefault(), "%.2f", weatherItem.getTempMin()));
        mBinding.tempMax.setText(String.format(Locale.getDefault(), "%.2f", weatherItem.getTempMax()));
    }

    @Override
    public void displayError(String error) {
        mBinding.errorText.setText(error);
    }
}
