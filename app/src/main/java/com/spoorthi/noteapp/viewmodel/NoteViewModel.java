package com.spoorthi.noteapp.viewmodel;

import android.app.Application;

import com.spoorthi.noteapp.storage.NoteBean;
import com.spoorthi.noteapp.repo.NoteRepo;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = NoteViewModel.class.getSimpleName();
    private LiveData<List<NoteBean>> notesMLD;
    private NoteRepo noteRepo;

    public NoteViewModel(Application application){
        super(application);

        noteRepo = new NoteRepo(application);
        notesMLD = noteRepo.getAllNotes();
    }

    public LiveData<List<NoteBean>> getNotesMLD() {
        return notesMLD;
    }
}
