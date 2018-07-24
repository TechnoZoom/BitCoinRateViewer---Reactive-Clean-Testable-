package com.kapil.bitcoinrateviewer;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.mikephil.charting.charts.LineChart;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kapil.bitcoinrateviewer.bitcoinchart.JsonUtils;
import com.kapil.bitcoinrateviewer.presentation.MainActivity;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinPriceTypeFactory;
import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRate;
import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRateMapper;
import bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRateResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class BitCoinChartTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true,
            false);
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8080));
    private static Gson gson;
    @BeforeClass
    public static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(BitCoinPriceTypeFactory.create())
                .create();
    }

    @Test
    public void onInternalServerError_showAppropriateMessage() {
        BitCoinRateStubber.stubBitCoinRateResponseWithError(501);
        reloadActivity();
        onView(allOf(withId(R.id.error_text_view), withText(R.string.something_wrong_at_the_server))).check(matches(isDisplayed()));
    }

    @Test
    public void onBadGatewayError_showAppropriateMessage() {
        BitCoinRateStubber.stubBitCoinRateResponseWithError(502);
        reloadActivity();
        onView(allOf(withId(R.id.error_text_view), withText(R.string.something_wrong_at_the_server))).check(matches(isDisplayed()));
    }

    @Test
    public void onAuthError_showAppropriateMessage() {
        BitCoinRateStubber.stubBitCoinRateResponseWithError(401);
        reloadActivity();
        onView(allOf(withId(R.id.error_text_view), withText(R.string.login_again))).check(matches(isDisplayed()));
    }

    @Test
    public void onNotFoundError_showAppropriateMessage() {
        BitCoinRateStubber.stubBitCoinRateResponseWithError(404);
        reloadActivity();
        onView(allOf(withId(R.id.error_text_view), withText(R.string.no_resource_found))).check(matches(isDisplayed()));
    }

    @Test
    public void onCorrectResponse_checkIfChartXDataValuesAreCorrect() {
        BitCoinRateStubber.stubWeeklyBitCoinResponse();
        reloadActivity();
        LineChart lineChart = mActivityTestRule.getActivity().findViewById(R.id.bit_coin_line_chart);
        BitCoinRateResponse bitCoinRateResponse = gson.fromJson(JsonUtils.getResponseFromJsonFile("bitcoin_rate_response.json"),
                BitCoinRateResponse.class);
        checkXLabeling(bitCoinRateResponse.bitCoinRateList(), lineChart);
    }

    private void checkXLabeling(List<BitCoinRate> bitCoinRateList, LineChart lineChart) {
        BitCoinRateMapper bitCoinRateMapper = new BitCoinRateMapper();
        for (int i = 0; i < bitCoinRateList.size(); i++) {
            try {
                assertEquals(bitCoinRateMapper.apply(bitCoinRateList.get(i)).date(), lineChart.getXAxis().getFormattedLabel(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void onCorrectResponse_checkIfChartYDataValuesAreCorrect() {
        BitCoinRateStubber.stubWeeklyBitCoinResponse();
        reloadActivity();
        LineChart lineChart = mActivityTestRule.getActivity().findViewById(R.id.bit_coin_line_chart);
        bakshi.kapil.com.bitcoinpricetracker.data.BitCoinRateResponse bitCoinRateResponse = gson.fromJson(JsonUtils.getResponseFromJsonFile("bitcoin_rate_response.json"),
                BitCoinRateResponse.class);
        checkYLabeling(bitCoinRateResponse.bitCoinRateList(), lineChart);
    }

    private void checkYLabeling(List<BitCoinRate> bitCoinRateList, LineChart lineChart) {
        BitCoinRateMapper bitCoinRateMapper = new BitCoinRateMapper();
        for (int i = 0; i < bitCoinRateList.size(); i++) {
            try {
                assertEquals(bitCoinRateMapper.apply(bitCoinRateList.get(i)).price(),
                        lineChart.getData().getDataSetByIndex(0).getEntryForIndex(i).getY(), 0.1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void reloadActivity() {
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);
    }

}