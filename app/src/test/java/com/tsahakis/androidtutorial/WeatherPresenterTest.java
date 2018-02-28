package com.tsahakis.androidtutorial;

import com.tsahakis.androidtutorial.common.TrampolineSchedulerRule;
import com.tsahakis.androidtutorial.data.WeatherRepository;
import com.tsahakis.androidtutorial.ui.WeatherContract;
import com.tsahakis.androidtutorial.ui.WeatherPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.disposables.CompositeDisposable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Mock
    private WeatherContract.View mView;

    @Mock
    private WeatherRepository mWeatherRepository;

    @Mock
    private CompositeDisposable mCompositeDisposable;

    private WeatherPresenter mPresenter;

    @Rule
    public TrampolineSchedulerRule mTrampolineSchedulerRule = new TrampolineSchedulerRule();

    @Before
    public void setup() {
        mPresenter = new WeatherPresenter(
                mView,
                mWeatherRepository,
                mCompositeDisposable
        );
    }

    @Test
    public void init() {
        // when
        mPresenter.init();

        // then
        verify(mView, times(1)).hideResponseContainer();
        verify(mView, times(1)).hideErrorContainer();
    }

    @Test
    public void unsubscribe_composite_not_disposed() {
        // given
        when(mCompositeDisposable.isDisposed()).thenReturn(false);

        // when
        mPresenter.unsubscribe();

        // then
        verify(mCompositeDisposable, times(1)).dispose();
    }
}