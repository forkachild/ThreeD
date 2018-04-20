package com.suhel.threed.utils;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ResourcesHelper {

    public static String readFile(Context context, @RawRes int resId) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(resId))
        );
        String line;
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return builder.toString();
    }

}
