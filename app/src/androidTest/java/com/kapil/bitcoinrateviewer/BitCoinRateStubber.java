package com.kapil.bitcoinrateviewer;

import com.kapil.bitcoinrateviewer.constants.ServerConfig;
import com.kapil.bitcoinrateviewer.util.AssetReaderUtil;
import com.kapil.core.commons.BaseUrlChangingInterceptor;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

/**
 * Created by kapilbakshi on 06/06/18.
 */

public class BitCoinRateStubber {

    public static void stubBitCoinRateResponseWithError(int errorCode) {
        String url = "/charts/market-price";
        BaseUrlChangingInterceptor.get().setInterceptor(ServerConfig.BLOCKCHAIN_BASE_URL + url);
        stubFor(get(urlPathMatching(url))
                .willReturn(aResponse()
                        .withStatus(errorCode)
                ));
    }

    public static void stubWeeklyBitCoinResponse() {
        String url = "/charts/market-price";
        BaseUrlChangingInterceptor.get().setInterceptor(ServerConfig.BLOCKCHAIN_BASE_URL + url);
        String jsonBody = AssetReaderUtil.asset("bitcoin_rate_response.json");
        stubFor(get(urlPathMatching(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(jsonBody)));
    }
}
