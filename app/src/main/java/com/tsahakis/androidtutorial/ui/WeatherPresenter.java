package com.tsahakis.androidtutorial.ui;


import android.support.annotation.NonNull;

import com.tsahakis.androidtutorial.data.WeatherRepository;
import com.tsahakis.androidtutorial.data.WeatherResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View mView;
    private final WeatherRepository mWeatherRepository;
    private final CompositeDisposable mCompositeDisposable;

    WeatherPresenter(@NonNull WeatherContract.View view,
                     @NonNull WeatherRepository weatherRepository,
                     @NonNull CompositeDisposable compositeDisposable) {
        mView = view;
        mWeatherRepository = weatherRepository;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void init() {
        mView.hideResponseContainer();
        mView.hideErrorContainer();
    }

    @Override
    public void onButtonClicked() {
        mCompositeDisposable.add(
                mWeatherRepository.getWeatherItem()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<WeatherResponse>() {
                            @Override
                            public void onSuccess(WeatherResponse weatherResponse) {
                                mView.hideErrorContainer();
                                mView.showResponseContainer();
                                mView.displayData(weatherResponse.getWeatherItem());
                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.showErrorContainer();
                                mView.hideResponseContainer();
                                mView.displayError(e.getMessage());
                            }
                        })
        );
    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}
