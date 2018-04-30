package com.suhel.threed.utils;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.SparseArray;

import com.suhel.threed.gfx.objects.geometry.ExtendedGeometry;
import com.suhel.threed.gfx.objects.geometry.Geometry;
import com.suhel.threed.gfx.objects.geometry.SimpleGeometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ModelReader {

    private static final SparseArray<Geometry> resMap = new SparseArray<>();

    public static Geometry fromFile(Context context, @RawRes int resource, Type type) {
        Geometry temp = null;
        try {
            if (resMap.get(resource) != null)
                return resMap.get(resource);

            switch (type) {

                case DAT:

                    temp = _fromDATFile(context, resource);
                    break;

                case OBJ:

                    temp = _fromOBJFile(context, resource);
                    break;
            }

            if (temp != null)
                resMap.put(resource, temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    private static Geometry _fromDATFile(Context context, @RawRes int resource) throws IOException {
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

        return new SimpleGeometry(vertices, vertexStride, indices);
    }

    private static Geometry _fromOBJFile(Context context, @RawRes int resource) throws IOException {
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

        vertices = new float[vertexCount * 3];      // vec4(x y z w)
        indices = new short[faceCount * 3];

        int v = 0, i = 0;

        for (String s : lines) {

            String[] parts = s.split("\\s+");

            switch (parts[0]) {

                case "v":

                    vertices[v++] = Float.parseFloat(parts[1]);
                    vertices[v++] = Float.parseFloat(parts[2]);
                    vertices[v++] = Float.parseFloat(parts[3]);
                    break;

                case "f":

                    for (int j = 1; j < 4; j++) {
                        String[] subParts = parts[j].split("/");
                        indices[i++] = (short) (Short.parseShort(subParts[0]) - 1);
                    }
                    break;

            }
        }
        return new SimpleGeometry(vertices, 3, indices);
    }

    private static Geometry _fromOBJFileAlt(Context context, @RawRes int resource) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(resource)));

        String line, parts[], subParts[];
        List<Float> vertices = new ArrayList<>();
        List<Short> indices = new ArrayList<>();
        List<Float> normalsRef = new ArrayList<>();
        List<Float> normals = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.isEmpty() || line.startsWith("#"))
                continue;

            parts = line.split("\\s+");

            switch (parts[0]) {

                case "v":

                    vertices.add(Float.parseFloat(parts[1]));
                    vertices.add(Float.parseFloat(parts[2]));
                    vertices.add(Float.parseFloat(parts[3]));
                    break;

                case "vn":

                    normalsRef.add(Float.parseFloat(parts[1]));
                    normalsRef.add(Float.parseFloat(parts[2]));
                    normalsRef.add(Float.parseFloat(parts[3]));
                    break;

                case "f":

                    // Only triangulated faces allowed
                    for (int i = 0; i < 3; i++) {
                        subParts = parts[i + 1].split("/");

                        short vertexIndex = Short.parseShort(subParts[0]);
                        int normalIndex = Short.parseShort(subParts[2]) - 1;

                        indices.add(--vertexIndex);
                        normals.add(normalsRef.get(3 * normalIndex));
                        normals.add(normalsRef.get(3 * normalIndex + 1));
                        normals.add(normalsRef.get(3 * normalIndex + 1));
                    }
                    break;

            }

        }

        br.close();

        float[] arrVertices = new float[vertices.size()];
        float[] arrNormals = new float[normals.size()];
        short[] arrIndices = new short[indices.size()];

        for (int i = 0; i < vertices.size(); i++)
            arrVertices[i] = vertices.get(i);

        for (int i = 0; i < normals.size(); i++)
            arrNormals[i] = normals.get(i);

        for (int i = 0; i < indices.size(); i++)
            arrIndices[i] = indices.get(i);

        return new ExtendedGeometry(arrVertices, 3, arrNormals, 3, arrIndices);
    }

    public enum Type {

        DAT,
        OBJ

    }

}
