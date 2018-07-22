package com.kapil.bitcoinrateviewer;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.kapil.bitcoinrateviewer.di.Injectable;
import com.kapil.bitcoinrateviewer.livedata_ext.DataResource;
import com.kapil.bitcoinrateviewer.livedata_ext.ResourceLiveDataObserver;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subjects.PublishSubject;


public class BitCoinRateViewFragment extends BaseFragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private PublishSubject<String> errorPublishSubject = PublishSubject.create();

 /*   @Inject
    BaseUrlChangingInterceptor baseUrlChangingInterceptor;*/

    private BitCoinRateViewModel bitCoinRateViewModel;

    private LineChart bitCoinRateLineChart;

    public BitCoinRateViewFragment() {
    }

    public static BitCoinRateViewFragment newInstance() {
        BitCoinRateViewFragment fragment = new BitCoinRateViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //baseUrlChangingInterceptor.setInterceptor("http://127.0.0.1:8080/");
        //bitCoinService.getBitCoinRates("1weeks");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bit_coin_rate_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bitCoinRateLineChart = view.findViewById(R.id.bit_coin_line_chart);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bitCoinRateViewModel = ViewModelProviders.of(this, viewModelFactory).get(BitCoinRateViewModel.class);
        observeLivaData();
        bitCoinRateViewModel.getBitCoinRates();
    }

    private void observeLivaData() {

        bitCoinRateViewModel.getBitCoinLiveData().observe(this, new ResourceLiveDataObserver<List<BitCoinRateDispFormat>>() {
            @Override
            public void onChanged(@Nullable Object o) {
                super.onChanged(o);
            }

            @Override
            public void onSuccessfulFetch(DataResource<List<BitCoinRateDispFormat>> resource) {
                dismissProgressDialog();
                if (!resource.data.isEmpty()) {
                    populateBitCoinChart(resource.data);
                }
            }

            @Override
            public void onResourceLoading(DataResource<List<BitCoinRateDispFormat>> resource) {
                showProgressDialog(getString(R.string.loading));
            }

            @Override
            public void onErrorFetching(DataResource<List<BitCoinRateDispFormat>> resource) {
                errorPublishSubject.onNext(resource.message);
                //Toast.makeText(getActivity().getApplicationContext(), resource.message, Toast.LENGTH_LONG).show();
                dismissProgressDialog();
            }

        });

    }

    private void populateBitCoinChart(List<BitCoinRateDispFormat> bitCoinRateList) {

        bitCoinRateLineChart.getXAxis().setDrawGridLines(false);
        bitCoinRateLineChart.getAxisRight().setDrawGridLines(false);
        bitCoinRateLineChart.getAxisRight().setEnabled(false);
        bitCoinRateLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        bitCoinRateLineChart.setData(getPriceLineDataAlongYAxis(bitCoinRateList));
        bitCoinRateLineChart.invalidate();
        IndexAxisValueFormatter indexAxisValueFormatter = new IndexAxisValueFormatter();
        indexAxisValueFormatter.setValues(setTimeXAxisValues(bitCoinRateList));
        bitCoinRateLineChart.getXAxis().setValueFormatter(indexAxisValueFormatter);
        bitCoinRateLineChart.getXAxis().setLabelRotationAngle(45f);

        /*
        bitCoinRateLineChart.setVisibleXRangeMaximum(12);
*/
        bitCoinRateLineChart.setAutoScaleMinMaxEnabled(true);

    }

    private LineData getPriceLineDataAlongYAxis(List<BitCoinRateDispFormat> bitCoinRateList) {
        ArrayList<Entry> yVals = setPriceYAxisValues(bitCoinRateList);
        LineDataSet set1;
        set1 = new LineDataSet(yVals, "");
        set1.setColor(Color.BLACK);
        set1.setDrawCircles(false);
        return new LineData(set1);

    }

    private String[] setTimeXAxisValues(List<BitCoinRateDispFormat> bitCoinRateDispFormatList) {
        String[] xTimeVals = new String[bitCoinRateDispFormatList.size()];
        for (int i = 0; i < bitCoinRateDispFormatList.size(); i++) {
            xTimeVals[i] = (bitCoinRateDispFormatList.get(i).date());
        }
        return xTimeVals;

    }

    private ArrayList<Entry> setPriceYAxisValues(List<BitCoinRateDispFormat> bitCoinRateDispFormatList) {
        ArrayList<Entry> yEntryVals = new ArrayList<Entry>();
        for (int i = 0; i < bitCoinRateDispFormatList.size(); i++) {
            yEntryVals.add(new Entry(i, bitCoinRateDispFormatList.get(i).price()));
        }
        return yEntryVals;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public PublishSubject<String> getErrorPublishSubject() {
        return errorPublishSubject;
    }

}
