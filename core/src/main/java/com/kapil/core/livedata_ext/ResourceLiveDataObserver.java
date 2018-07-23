package com.kapil.core.livedata_ext;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.kapil.core.Constants.ResourceStatus;


public abstract class ResourceLiveDataObserver<T> implements Observer {

    @Override
    public void onChanged(@Nullable Object o) {

        DataResource resource = (DataResource) o;

        switch (resource.status) {

            case ResourceStatus.SUCCESS:
                onSuccessfulFetch(resource);
                break;

            case ResourceStatus.ERROR:
                onErrorFetching(resource);
                break;

            case ResourceStatus.LOADING:
                onResourceLoading(resource);
                break;
        }
    }

    public abstract void onSuccessfulFetch(DataResource<T> resource);
    public abstract void onResourceLoading(DataResource<T> resource);
    public abstract void onErrorFetching(DataResource<T> resource);
}
