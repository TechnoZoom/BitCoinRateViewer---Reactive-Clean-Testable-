package com.kapil.core.livedata_ext;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kapil.core.Constants.DataResourceStatus;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class DataResource<T>  {

    @NonNull
    public final int status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public DataResource(@NonNull int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> DataResource<T> success(@Nullable T data) {
        return new DataResource<>(DataResourceStatus.SUCCESS, data, null);
    }

    public static <T> DataResource<T> error(String msg, @Nullable T data) {
        return new DataResource<>(DataResourceStatus.ERROR, data, msg);
    }

    public static <T> DataResource<T> loading(@Nullable T data) {
        return new DataResource<>(DataResourceStatus.LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataResource<?> resource = (DataResource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    
    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
