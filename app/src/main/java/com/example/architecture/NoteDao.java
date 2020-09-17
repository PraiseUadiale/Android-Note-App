package com.example.architecture;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    // These would be the database operations we want to make on the note entity such. think of CRUD

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete From note_table")
    void deleteAll();// Delete notes

    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority desc")
    LiveData<List<Note>> getAllNotes();// Turns notes into a list object in real time

}
