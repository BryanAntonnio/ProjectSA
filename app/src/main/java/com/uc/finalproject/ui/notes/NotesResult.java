package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;

public class NotesResult extends AppCompatActivity {
    Toolbar toolbar;
    EditText judul, isi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_result);
        toolbar = findViewById(R.id.toolbar_notes_result);
        judul = findViewById(R.id.judul_result);
        isi = findViewById(R.id.result_isi);
        getNotes();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesResult.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getNotes() {
    }
}
