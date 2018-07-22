package com.kapil.bitcoinrateviewer;

import com.kapil.bitcoinrateviewer.Utils.DateUtils;
import com.kapil.bitcoinrateviewer.di.BitCoinRate;

import javax.inject.Inject;

import io.reactivex.functions.Function;


public class BitCoinRateMapper implements Function<BitCoinRate, BitCoinRateDispFormat> {

    @Inject
    BitCoinRateMapper() {}

    @Override
    public BitCoinRateDispFormat apply(BitCoinRate bitCoinRate) throws Exception {
        return BitCoinRateDispFormat.builder()
                .date(DateUtils.getStringFormttedDate(bitCoinRate.timePoint()))
                .price((long) bitCoinRate.rateValue())
                .build();
    }

}
