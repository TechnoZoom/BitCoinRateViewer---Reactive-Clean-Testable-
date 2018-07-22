package com.kapil.bitcoinrateviewer.util;

import android.support.test.InstrumentationRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetReaderUtil {

    public static String asset(String assetPath) {
        try {
            InputStream inputStream = InstrumentationRegistry.getTargetContext().getAssets().open("body_files/" + assetPath);
            return inputStreamToString(inputStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static final int BUFFER_SIZE = 4 * 1024;

    public static String inputStreamToString(InputStream inputStream, String charsetName)
            throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(inputStream, charsetName);
        char[] buffer = new char[BUFFER_SIZE];
        int length;
        while ((length = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, length);
        }
        return builder.toString();
    }
}
