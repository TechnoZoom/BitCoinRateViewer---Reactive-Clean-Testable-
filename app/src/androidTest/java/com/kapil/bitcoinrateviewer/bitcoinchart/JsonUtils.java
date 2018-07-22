package com.kapil.bitcoinrateviewer.bitcoinchart;

import android.support.test.InstrumentationRegistry;

import java.io.IOException;
import java.io.InputStream;

import static com.kapil.bitcoinrateviewer.util.AssetReaderUtil.inputStreamToString;

/**
 * Created by kapilbakshi on 22/07/18.
 */

public class JsonUtils {

    public static String getResponseFromJsonFile(String path) {
        StringBuilder buf = new StringBuilder();
        try {
            InputStream inputStream = InstrumentationRegistry.getTargetContext().getAssets().open("body_files/" + path);
            String jsonString = inputStreamToString(inputStream, "UTF-8");
            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
