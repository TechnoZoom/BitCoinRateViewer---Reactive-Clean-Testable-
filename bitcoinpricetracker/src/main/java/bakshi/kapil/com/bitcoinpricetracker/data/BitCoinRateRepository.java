package bakshi.kapil.com.bitcoinpricetracker.data;

import android.support.annotation.NonNull;

import com.kapil.core.Constants.ReactiveConstants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;


public class BitCoinRateRepository {

    private final Scheduler androidScheduler;
    private final Scheduler processingScheduler;
    private BitCoinRateMapper bitCoinRateMapper;
    private BitCoinService bitCoinService;

    @Inject
    BitCoinRateRepository(@NonNull final BitCoinService bitCoinService,
                          @Named(ReactiveConstants.ANDROID_SCHEDULER) Scheduler androidScheduler,
                          @Named(ReactiveConstants.PROCESSING_SCHEDULER) Scheduler processingScheduler,
                          @NonNull BitCoinRateMapper bitCoinRateMapper) {
        this.bitCoinService = bitCoinService;
        this.androidScheduler = androidScheduler;
        this.processingScheduler = processingScheduler;
        this.bitCoinRateMapper = bitCoinRateMapper;
    }

    public Single<List<BitCoinRateDispFormat>> getBitCoinRates()  {
        return bitCoinService.getBitCoinRates("1weeks")
                .toObservable()
                .subscribeOn(processingScheduler)
                .observeOn(androidScheduler)
                .map(BitCoinRateResponse::bitCoinRateList)
                .concatMapIterable(bitCoinRateList -> bitCoinRateList)
                .map(bitCoinRateMapper)
                .toList();
    }

}
