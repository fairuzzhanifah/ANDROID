package com.example.eduvent;

public class DataHolder {
    private static final DataHolder instance = new DataHolder();
    private Project project;

    private DataHolder() {}

    public static DataHolder getInstance() {
        return instance;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}

