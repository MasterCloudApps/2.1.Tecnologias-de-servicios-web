package es.mastercloudapps.security_example_API.model;

public class Book {
    private long id = -1;
    private String title;

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

    @Override
    public String toString() {
        return "Item [id=" + id + ", description=" + title + "]";
    }
}

