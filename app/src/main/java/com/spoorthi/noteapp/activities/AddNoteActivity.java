package com.spoorthi.noteapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.spoorthi.noteapp.storage.NoteBean;
import com.spoorthi.noteapp.repo.NoteRepo;
import com.spoorthi.noteapp.R;
import com.spoorthi.noteapp.databinding.ActivityAddNoteBinding;
import com.spoorthi.noteapp.utils.AppConstants;

import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        context = AddNoteActivity.this;

        binding.header.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.header.tvHeader.setText("Add Note");

        binding.btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.btnSaveNote.setClickable(false);

                if(validateFields()){
                    final NoteBean noteBean = new NoteBean();
                    noteBean.setTitle(binding.tvTitle.getText().toString());
                    noteBean.setContent(binding.tvContent.getText().toString());
                    noteBean.setTimeCreated(System.currentTimeMillis());

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            NoteRepo noteRepo = new NoteRepo(getApplication());
                            noteRepo.insert(noteBean);
                            Intent intent = new Intent(context,ViewNoteActivity.class);
                            intent.putExtra(AppConstants.NOTE_KEY,noteBean);
                            startActivity(intent);
                            finish();
                        }
                    });

                }else {
                    binding.btnSaveNote.setClickable(true);
                }

            }
        });

        binding.tvTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!view.hasFocus()) {
                    validateOnFocus(binding.tlTitle, binding.tvTitle.getText().toString(), "Title is required");
                }
            }
        });

        binding.tvContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!view.hasFocus()) {
                    validateOnFocus(binding.tlContent, binding.tvContent.getText().toString(), "Content is required");
                }
            }
        });

        binding.tvTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                validateOnFocus(binding.tlTitle, charSequence.toString(), "Title is required");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.tvContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                validateOnFocus(binding.tlContent, charSequence.toString(), "Content is required");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean validateFields() {

        if(TextUtils.isEmpty(binding.tvTitle.getText().toString().trim())) {
            setErrorFalse(binding.tlTitle);
            showErrorMsg(binding.tlTitle,"Title is required");
            return false;
        }
        else if(TextUtils.isEmpty(binding.tvContent.getText().toString().trim())) {
            setErrorFalse(binding.tlContent);
            showErrorMsg(binding.tlContent,"Content is required");
            return false;
        }
        else {
            setErrorFalse(binding.tlTitle);
            setErrorFalse(binding.tlContent);
            return true;
        }
    }

    private void showErrorMsg(TextInputLayout textInputLayout, String errorMsg) {
        textInputLayout.setError(null);
        textInputLayout.setError(errorMsg);
        textInputLayout.setErrorEnabled(true);
    }

    private void validateOnFocus(TextInputLayout textInputLayout,String txt,String errorTxt){
        if(TextUtils.isEmpty(txt.trim())){
            textInputLayout.setError(null);
            textInputLayout.setError(errorTxt);
            textInputLayout.setErrorEnabled(true);
        }else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        }
    }

    private void setErrorFalse(TextInputLayout textInputLayout){

        /*
         * maintain order
         *   ->clear focus first
         *   ->disable error later
         * */

        textInputLayout.clearFocus();
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}
