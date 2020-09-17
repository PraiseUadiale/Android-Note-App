package com.example.architecture;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")// Defines name of table which is overriding default
public class Note {
    @PrimaryKey(autoGenerate = true)// automatically generates the primary key
    private int id;

    private String title;// theses are columns that this table would contain. This would be created automatically

    private String description;

    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    //Annotations allow us to write less code
}
