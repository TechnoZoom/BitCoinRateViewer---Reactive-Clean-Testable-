package bakshi.kapil.com.bitcoinpricetracker.data;


import com.kapil.core.Utils.DateUtils;

import javax.inject.Inject;

import io.reactivex.functions.Function;


public class BitCoinRateMapper implements Function<BitCoinRate, BitCoinRateDispFormat> {

    @Inject
    public BitCoinRateMapper() {}

    @Override
    public BitCoinRateDispFormat apply(BitCoinRate bitCoinRate) throws Exception {
        return BitCoinRateDispFormat.builder()
                .date(DateUtils.getStringFormttedDate(bitCoinRate.timePoint()))
                .price((long) bitCoinRate.rateValue())
                .build();
    }

}
