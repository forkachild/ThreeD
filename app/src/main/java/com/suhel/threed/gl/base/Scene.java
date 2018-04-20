package com.suhel.threed.gl.base;

import java.util.HashMap;
import java.util.Map;

public abstract class Scene implements Prepareable {

    private Map<String, Program> programs = new HashMap<>();
    private Map<String, Model> models = new HashMap<>();

    @Override
    public void prepare() {
        for (int i = 0; i < getProgramCount(); i++) {
            Program program = getProgram(i);
            program.prepare();
            programs.put(getProgramName(i), program);
        }
        for (int i = 0; i < getModelCount(); i++) {
            Model model = getModel(i);
            model.prepare();
            models.put(getModelName(i), model);
        }
    }

    public abstract void changeDimensions(int width, int height);

    protected abstract int getProgramCount();

    protected abstract Program getProgram(int position);

    protected abstract String getProgramName(int position);

    protected final Program getProgram(String name) {
        return programs.get(name);
    }

    protected abstract int getModelCount();

    protected abstract Model getModel(int position);

    protected abstract String getModelName(int position);

    protected final Model getModel(String name) {
        return models.get(name);
    }

    public abstract void render();

}
