package com.spoorthi.noteapp.storage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notebean ORDER BY id DESC")
    LiveData<List<NoteBean>> getAllNotes();

    @Insert
    void addNote(NoteBean noteBean);

}
