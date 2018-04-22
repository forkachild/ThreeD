package com.suhel.threed.utils;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.SparseArray;

import com.suhel.threed.gfx.BetterModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ModelHelper {

    private static final SparseArray<BetterModel> resMap = new SparseArray<>();

    public static BetterModel fromFile(Context context, @RawRes int resource) {
        try {
            if (resMap.get(resource) != null)
                return resMap.get(resource);

            BetterModel temp = _fromFile(context, resource);
            if (temp != null)
                resMap.put(resource, temp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BetterModel _fromFile(Context context, @RawRes int resource) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(resource)));

        int vertexCount, normalCount, indexCount,
                vertexStride, normalStride, indexStride;
        float[] vertices, normals;
        short[] indices;
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("//"))
                continue;
            lines.add(line);
        }

        if (lines.size() == 0)
            return null;

        String[] values = lines.get(0).split(",\\s+");

        vertexCount = Integer.parseInt(values[0]);
        normalCount = Integer.parseInt(values[1]);
        indexCount = Integer.parseInt(values[2]);
        vertexStride = Integer.parseInt(values[3]);
        normalStride = Integer.parseInt(values[4]);
        indexStride = Integer.parseInt(values[5]);

        vertices = new float[vertexCount * vertexStride];
        normals = new float[normalCount * normalStride];
        indices = new short[indexCount * indexStride];

        int k = 1;

        for (int i = 0; i < vertexCount; i++, k++) {
            values = lines.get(k).split(",\\s+");
            for (int j = 0; j < vertexStride; j++)
                vertices[(i * vertexStride) + j] = Float.parseFloat(values[j]);
        }

        for (int i = 0; i < normalCount; i++, k++) {
            values = lines.get(k).split(",\\s+");
            for (int j = 0; j < normalStride; j++)
                normals[(i * normalStride) + j] = Float.parseFloat(values[j]);
        }

        for (int i = 0; i < indexCount; i++, k++) {
            values = lines.get(k).split(",\\s+");
            for (int j = 0; j < indexStride; j++)
                indices[(i * indexStride) + j] = (short) (Short.parseShort(values[j]) - 1);
        }

        return new BetterModel(vertices, indices, vertexStride);
    }

}
