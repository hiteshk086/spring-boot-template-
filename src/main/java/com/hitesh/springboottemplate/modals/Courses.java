package com.hitesh.springboottemplate.modals;

public class Courses {
    private long id;
    private String title;
    private String descriptio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptio() {
        return descriptio;
    }

    public void setDescriptio(String descriptio) {
        this.descriptio = descriptio;
    }

    public Courses() {
        super();
    }

    public Courses(long id, String title, String descriptio) {
        this.id = id;
        this.title = title;
        this.descriptio = descriptio;
    }
}
