package com.spoorthi.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.spoorthi.noteapp.databinding.ActivityAddNoteBinding;

import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);

        context = AddNoteActivity.this;

        binding.btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private boolean validateFields() {

        if(TextUtils.isEmpty(binding.tvTitle.getText().toString())) {
            showErrorMsg(binding.tlTitle,"Title is required");
            return false;
        }
        else if(TextUtils.isEmpty(binding.tvContent.getText().toString())) {
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
}
