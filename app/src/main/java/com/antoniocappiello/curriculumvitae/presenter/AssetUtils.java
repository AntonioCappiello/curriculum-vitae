package com.antoniocappiello.curriculumvitae.presenter;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {

    public static String readAsset(Context context, String fileName) throws IOException {

        InputStream is = context.getAssets().open(fileName);

        int size = is != null ? is.available() : 0;
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");

    }
}
