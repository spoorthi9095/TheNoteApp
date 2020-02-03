package com.spoorthi.noteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteBean {

    @PrimaryKey(autoGenerate = true)
    private int id;
    String title;
    String content;
    long timeCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
