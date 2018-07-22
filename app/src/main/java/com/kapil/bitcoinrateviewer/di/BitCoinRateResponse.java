package com.kapil.bitcoinrateviewer.di;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@AutoValue
public abstract class BitCoinRateResponse {

    @NonNull
    public @SerializedName("values")
    abstract List<BitCoinRate> bitCoinRateList();

    @NonNull
    public static TypeAdapter<BitCoinRateResponse> typeAdapter(@NonNull final Gson gson) {
        return new AutoValue_BitCoinRateResponse.GsonTypeAdapter(gson);
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_BitCoinRateResponse.Builder();
    }


    @AutoValue.Builder
    public interface Builder {
        Builder bitCoinRateList(List<BitCoinRate> bitCoinRateList);

        BitCoinRateResponse build();
    }
}

