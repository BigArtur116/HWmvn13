package ru.netology.javaqa95.HWmvn13.services;

public class SimpleTask extends Task {
    protected String title;

    public SimpleTask(int id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean matches(String query) {
        if (title.contains(query)) {
            return true;
        }
        return false;
    }
}