package com.kapil.bitcoinrateviewer.presentation;

import android.support.v4.app.FragmentManager;

import com.kapil.core.errorhandling.ErrorFragment;

import javax.inject.Inject;

import bakshi.kapil.com.bitcoinpricetracker.presentation.BitCoinRateViewFragment;

/**
 * Created by kapilbakshi on 21/07/18.
 */

public class NavigationController {

    private int containerId;
    private FragmentManager fragmentManager;

    @Inject
    public NavigationController(int containerId, FragmentManager fragmentManager) {
        this.containerId =containerId;
        this.fragmentManager = fragmentManager;
    }

    public BitCoinRateViewFragment navigateToBitCoinRateFragment() {
        BitCoinRateViewFragment bitCoinRateViewFragment = new BitCoinRateViewFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, bitCoinRateViewFragment)
                .commitAllowingStateLoss();
        return bitCoinRateViewFragment;
    }

    public ErrorFragment navigateToErrorFragment(String errorMessage) {
        ErrorFragment errorFragment = ErrorFragment.newInstance(errorMessage);
        fragmentManager.beginTransaction()
                .replace(containerId, errorFragment)
                .commitAllowingStateLoss();
        return errorFragment;
    }
}
