package com.spoorthi.noteapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spoorthi.noteapp.adapter.NoteAdapter;
import com.spoorthi.noteapp.storage.NoteBean;
import com.spoorthi.noteapp.utils.AppConstants;
import com.spoorthi.noteapp.viewmodel.NoteViewModel;
import com.spoorthi.noteapp.R;
import com.spoorthi.noteapp.databinding.ActivityNoteListBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListActivity extends AppCompatActivity implements NoteAdapter.NoteClick {

    private ActivityNoteListBinding binding;
    private Context context;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    List<NoteBean> noteBeanList = new ArrayList<>();

    private static final String TAG = NoteListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        context = NoteListActivity.this;

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
        noteAdapter = new NoteAdapter(context,noteBeanList,this);
        binding.rvNotes.setLayoutManager(layoutManager);
        binding.rvNotes.setAdapter(noteAdapter);

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
                noteBeanList = noteBeans;
                if(noteBeans!=null && noteBeans.size()>0) {
                    binding.tvEmpty.setVisibility(View.GONE);
                    noteAdapter.setNotes(noteBeans);
                }else {
                    binding.tvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void OnNoteClick(int position) {
        Intent intent = new Intent(context,ViewNoteActivity.class);
        intent.putExtra(AppConstants.NOTE_KEY,noteBeanList.get(position));
        startActivity(intent);
    }
}
