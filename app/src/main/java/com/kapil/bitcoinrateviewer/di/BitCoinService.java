package com.kapil.bitcoinrateviewer.di;

import com.kapil.bitcoinrateviewer.Constants.ServerEndPoints;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kapilbakshi on 21/07/18.
 */

public interface BitCoinService {

    @GET(ServerEndPoints.BIT_COIN_RATE_BY_TIME)
    Single<BitCoinRateResponse> getBitCoinRates(@Query("timespan")String timeSpan);
}
