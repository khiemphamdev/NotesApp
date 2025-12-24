package clc65.PhamGiaKhiem.notes_app;

public class NoteModel {

    String title,description;

    int id;
    long createdTime;


    public NoteModel(String title, String description, int id, long createdTime) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.createdTime = createdTime;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
