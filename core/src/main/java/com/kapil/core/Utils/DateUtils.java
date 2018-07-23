package com.kapil.core.Utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static String getStringFormttedDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(TimeUnit.SECONDS.toMillis(timeStamp)));
    }
}
