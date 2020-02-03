package com.spoorthi.noteapp;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<NoteBean>> allNotes;

    NoteRepo(Application application){
        NoteDB noteDB = NoteDB.getDatabase(application);
        noteDao = noteDB.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<NoteBean>> getAllNotes() {
        return allNotes;
    }

    void insert(final NoteBean noteBean) {
        NoteDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.addNote(noteBean);
            }
        });
    }

}
