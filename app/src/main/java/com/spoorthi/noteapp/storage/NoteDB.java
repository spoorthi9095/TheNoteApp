package com.spoorthi.noteapp.storage;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteBean.class},version = 1,exportSchema = false)
public abstract class NoteDB extends RoomDatabase {

    public abstract NoteDao noteDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile NoteDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static NoteDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDB.class, "note_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
