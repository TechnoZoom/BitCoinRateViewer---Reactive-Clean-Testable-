package bakshi.kapil.com.bitcoinpricetracker.data;

import android.support.annotation.NonNull;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by kapilbakshi on 23/07/18.
 */
@GsonTypeAdapterFactory
public abstract class BitCoinPriceTypeFactory implements TypeAdapterFactory {

    @NonNull
    public static TypeAdapterFactory create() {
        return new AutoValueGson_BitCoinPriceTypeFactory();
    }
}