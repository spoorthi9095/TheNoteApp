package com.spoorthi.noteapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notebean")
    LiveData<List<NoteBean>> getAllNotes();

    @Insert
    void addNote(NoteBean noteBean);

}
