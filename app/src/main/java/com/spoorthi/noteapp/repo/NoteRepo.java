package com.spoorthi.noteapp.repo;

import android.app.Application;

import com.spoorthi.noteapp.storage.NoteBean;
import com.spoorthi.noteapp.storage.NoteDB;
import com.spoorthi.noteapp.storage.NoteDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<NoteBean>> allNotes;

    public NoteRepo(Application application){
        NoteDB noteDB = NoteDB.getDatabase(application);
        noteDao = noteDB.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<NoteBean>> getAllNotes() {
        return allNotes;
    }

    public void insert(final NoteBean noteBean) {
        NoteDB.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.addNote(noteBean);
            }
        });
    }

}
