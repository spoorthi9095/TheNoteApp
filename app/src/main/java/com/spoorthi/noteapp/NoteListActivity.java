package com.spoorthi.noteapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.spoorthi.noteapp.databinding.ActivityNoteListBinding;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class NoteListActivity extends AppCompatActivity {

    private ActivityNoteListBinding binding;
    private Context context;
    private NoteViewModel noteViewModel;

    private static final String TAG = NoteListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        context = NoteListActivity.this;

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        binding.fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddNoteActivity.class);
                startActivity(intent);

            }
        });

        noteViewModel.getNotesMLD().observe(this, new Observer<List<NoteBean>>() {
            @Override
            public void onChanged(List<NoteBean> noteBeans) {
                Log.d(TAG, "onChanged: "+noteBeans);
            }
        });

    }
}
