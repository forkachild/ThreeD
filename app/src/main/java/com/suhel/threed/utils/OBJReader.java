package com.suhel.threed.utils;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.SparseArray;

import com.suhel.threed.gfx.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class OBJReader {

    private static SparseArray<Model> models = new SparseArray<>();

    public static Model fromFile(Context context, @RawRes int resource) {
        try {
            Model temp = models.get(resource);

            if (temp == null) {
                temp = _fromFile(context, resource);
                models.put(resource, temp);
            }

            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Model _fromFile(Context context, @RawRes int resource) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(resource)));

        String line;
        int vertexCount = 0, faceCount = 0;
        float[] vertices;
        short[] indices;

        List<String> lines = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("#") || line.isEmpty())
                continue;

            String[] parts = line.split("\\s+");

            switch (parts[0]) {

                case "v":

                    vertexCount++;
                    break;

                case "f":

                    faceCount++;
                    break;

            }
            lines.add(line);
        }

        vertices = new float[vertexCount * 4];      // vec4(x y z w)
        indices = new short[faceCount * 3];

        int v = 0, i = 0;

        for (String s : lines) {

            String[] parts = s.split("\\s+");

            switch (parts[0]) {

                case "v":

                    vertices[v++] = Float.parseFloat(parts[1]);
                    vertices[v++] = Float.parseFloat(parts[2]);
                    vertices[v++] = Float.parseFloat(parts[3]);
                    vertices[v++] = 1.0f;
                    break;

                case "f":

                    for (int j = 1; j < 4; j++) {
                        String[] subParts = parts[j].split("/");
                        indices[i++] = Short.parseShort(subParts[0]);
                    }
                    break;

            }
        }
        return new Model(vertices, indices, 4);
    }

}
