package com.kapil.bitcoinrateviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private NavigationController navigationController;

    private BitCoinRateViewFragment bitCoinRateViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationController = new NavigationController(R.id.container, getSupportFragmentManager());
        bitCoinRateViewFragment = navigationController.navigateToBitCoinRateFragment();
        setupSubscriptions();
    }

    private void setupSubscriptions() {
        bitCoinRateViewFragment.getErrorPublishSubject()
                .subscribe(errorMessage -> navigationController.navigateToErrorFragment(errorMessage));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
