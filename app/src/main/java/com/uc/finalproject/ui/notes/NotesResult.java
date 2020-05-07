package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;
import com.uc.finalproject.adapter.NotesAdapter;
import com.uc.finalproject.adapter.NotesResultAdapter;
import com.uc.finalproject.model.SimpanNotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NotesResult extends AppCompatActivity {
    Toolbar toolbar;
    EditText judul, isi;
    final ArrayList<SimpanNotes> simpanNotes = new ArrayList<>();
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
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://hansrichard2000.c1.biz/studentsassist/notes/notesResult.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("SimpanNotes");
                    for (int i = 0; i<list.length(); i++){
                        JSONObject obj = list.getJSONObject(i);
                        SimpanNotes s = new SimpanNotes(obj.getString("id"), obj.getString("judul"), obj.getString("isi"));
                        simpanNotes.add(s);
                    }
//                    showNotes(simpanNotes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

//    private void showNotes(ArrayList<SimpanNotes> simpanNotes) {
//        judul.setText;
//        NotesResultAdapter notesResultAdapter = new NotesResultAdapter(getCont)
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String msg;
        switch (item.getItemId()){
            case R.id.undo:
                msg = "Undo";
                break;
            case R.id.redo:
                msg = "Redo";
                break;
            case R.id.done:
                Intent intent = new Intent(NotesResult.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
