package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.finalproject.R;

public class NotesMainActivity extends AppCompatActivity {
    FloatingActionButton button_add_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes);
        button_add_notes = findViewById(R.id.button_tambah_notes);
        button_add_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesMainActivity.this, NotesAddActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
