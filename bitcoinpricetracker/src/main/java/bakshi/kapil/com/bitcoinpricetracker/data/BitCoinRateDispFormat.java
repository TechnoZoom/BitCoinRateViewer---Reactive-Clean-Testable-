package bakshi.kapil.com.bitcoinpricetracker.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class BitCoinRateDispFormat {

    @NonNull
    public abstract String date();
    @NonNull
    public abstract long price();

    public static Builder builder() {
        return new AutoValue_BitCoinRateDispFormat.Builder();
    }

    @AutoValue.Builder
    public interface Builder {

        Builder date(@NonNull String date);

        Builder price(@NonNull long price);

        BitCoinRateDispFormat build();
    }

}