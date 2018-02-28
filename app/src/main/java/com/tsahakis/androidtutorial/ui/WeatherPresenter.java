package com.tsahakis.androidtutorial.ui;


import android.support.annotation.NonNull;

import com.tsahakis.androidtutorial.api.WeatherItem;
import com.tsahakis.androidtutorial.data.WeatherRepository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter implements WeatherContract.Presenter {

    private final WeatherContract.View mView;
    private final WeatherRepository mWeatherRepository;
    private final CompositeDisposable mCompositeDisposable;

    public WeatherPresenter(@NonNull WeatherContract.View view,
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
                        .flatMap(response -> Single.fromCallable(response::getWeatherItem))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<WeatherItem>() {
                            @Override
                            public void onSuccess(WeatherItem weatherItem) {
                                mView.hideErrorContainer();
                                mView.showResponseContainer();
                                mView.displayData(weatherItem);
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
