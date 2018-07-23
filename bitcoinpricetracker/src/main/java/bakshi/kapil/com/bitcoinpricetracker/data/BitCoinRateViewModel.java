package bakshi.kapil.com.bitcoinpricetracker.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.kapil.core.errorhandling.ErrorUtils;
import com.kapil.core.livedata_ext.DataResource;

import java.util.List;

import javax.inject.Inject;


public class BitCoinRateViewModel extends ViewModel {

    @NonNull
    private MutableLiveData<DataResource<List<BitCoinRateDispFormat>>> bitCoinLiveData = new MutableLiveData<>();

    @NonNull
    private BitCoinRateRepository bitCoinRateRepository;

    @Inject
    BitCoinRateViewModel(@NonNull BitCoinRateRepository bitCoinRateRepository) {
        this.bitCoinRateRepository = bitCoinRateRepository;
    }


    public void getBitCoinRates() {
        bitCoinLiveData.setValue(DataResource.loading(null));
        bitCoinRateRepository.getBitCoinRates()
                .subscribe(bitCoinRateDispFormatList -> bitCoinLiveData.setValue(DataResource.success(bitCoinRateDispFormatList)),
                        e -> bitCoinLiveData.setValue(DataResource.<List<BitCoinRateDispFormat>>error(
                                ErrorUtils.getFormattedErrorMessage(e), null)));
    }

    @NonNull
    public MutableLiveData<DataResource<List<BitCoinRateDispFormat>>> getBitCoinLiveData() {
        return bitCoinLiveData;
    }


}
