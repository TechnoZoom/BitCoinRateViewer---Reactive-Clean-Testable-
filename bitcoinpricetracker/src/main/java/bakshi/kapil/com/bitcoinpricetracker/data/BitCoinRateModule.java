package bakshi.kapil.com.bitcoinpricetracker.data;

import android.support.annotation.NonNull;

import com.google.gson.TypeAdapterFactory;
import com.kapil.core.Constants.ReactiveConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;


@Module
public class BitCoinRateModule {

    @Provides
    public BitCoinService provideBitCoinService(Retrofit retrofit) {
        return retrofit.create(BitCoinService.class);
    }

    @Provides
    public BitCoinRateRepository provideBitCoinRepository(BitCoinService bitCoinService,
                                                          @Named(ReactiveConstants.ANDROID_SCHEDULER) Scheduler androidScheduler,
                                                          @Named(ReactiveConstants.PROCESSING_SCHEDULER) Scheduler processingScheduler,
                                                          @NonNull BitCoinRateMapper bitCoinRateMapper) {
        return new BitCoinRateRepository(bitCoinService, androidScheduler,processingScheduler, bitCoinRateMapper) ;
    }

    @Provides
    @IntoSet
    TypeAdapterFactory provideTypeAdapterFactory()
    {
        return BitCoinPriceTypeFactory.create();
    }

}
