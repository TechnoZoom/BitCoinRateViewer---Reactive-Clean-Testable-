package com.kapil.bitcoinrateviewer.di;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


@AutoValue
public abstract class BitCoinRate {

    @NonNull
    public @SerializedName("x")
    abstract long timePoint();

    @NonNull
    public @SerializedName("y")
    abstract float rateValue();


    @NonNull
    public static TypeAdapter<BitCoinRate> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_BitCoinRate.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_BitCoinRate.Builder();
    }


    @AutoValue.Builder
    public interface Builder {

        Builder timePoint(final long timePoint);

        Builder rateValue(final float rateValue);

        BitCoinRate build();
    }
}
