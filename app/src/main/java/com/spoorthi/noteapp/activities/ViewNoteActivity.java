package com.spoorthi.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.spoorthi.noteapp.R;
import com.spoorthi.noteapp.databinding.ActivityViewNoteBinding;
import com.spoorthi.noteapp.storage.NoteBean;
import com.spoorthi.noteapp.utils.AppConstants;
import com.spoorthi.noteapp.utils.NoteAppUtils;

import java.util.Calendar;

public class ViewNoteActivity extends AppCompatActivity {

    ActivityViewNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_note);

        NoteBean noteBean = (NoteBean) getIntent().getSerializableExtra(AppConstants.NOTE_KEY);

        binding.header.tvHeader.setText("NoteApp");
        binding.header.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*Populating the note fields*/
        if(noteBean!=null){
            binding.tvNoteTitle.setText(noteBean.getTitle());
            binding.tvNoteContent.setText(noteBean.getContent());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(noteBean.getTimeCreated());
            String time = NoteAppUtils.convertDateToString(calendar,"dd MMMM yyyy, hh:mm a");
            binding.tvTimeCreated.setText(time);
        }

    }
}
