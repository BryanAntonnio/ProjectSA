package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    EditText id, judul, isi;
    String id_notes;
    Button detail;
    RecyclerView recyclerView;
    final ArrayList<SimpanNotes> simpanNotes = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_result);
        toolbar = findViewById(R.id.toolbar_notes_result);
        id = findViewById(R.id.id_notes_result);
        judul = findViewById(R.id.judul_result);
        isi = findViewById(R.id.result_isi);
        detail = findViewById(R.id.button_result);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesResult.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_notes = id.getText().toString().trim();
                getNotes(id_notes);
                Toast.makeText(NotesResult.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNotes(String id_notes) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://hansrichard2000.c1.biz/studentsassist/notes/notesResult.php";

        RequestParams params = new RequestParams();
        params.put("id", id_notes);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("SimpanNotes");
                    JSONObject obj = list.getJSONObject(0);
                    judul.setText(obj.getString("judul"));
                    isi.setText(obj.getString("isi"));

//                    showNotes(simpanNotes);
                } catch (JSONException e) {
                    Log.d("Error checking notes", "onSuccess" + e.getMessage() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("OnFailureCheckingNotes", "onFailure" + error.getMessage());
            }
        });
    }

//    private void showNotes(ArrayList<SimpanNotes> simpanNotes) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(NotesResult.this));
//        NotesResultAdapter notesResultAdapter = new NotesResultAdapter(NotesResult.this);
//        notesResultAdapter.setListNotes(simpanNotes);
//        recyclerView.setAdapter(notesResultAdapter);
//
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
                id_notes = id.getText().toString().trim();
                String judulText = judul.getText().toString().trim();
                String isiText = isi.getText().toString().trim();
                getUpdate(id_notes, judulText, isiText);
                Intent intent = new Intent(NotesResult.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUpdate(String id_notes, String judulText, String isiText) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://hansrichard2000.c1.biz/studentsassist/notes/updateNotes.php";

        RequestParams params = new RequestParams();
        params.put("judul", judulText);
        params.put("isi", isiText);
        params.put("id", id_notes);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String msg = responseObject.getString("message");
                    showMessage(msg);
                } catch (JSONException e) {
                    Log.d("Error update notes", "onSuccess" + e.getMessage() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("OnFailureUpdateNotes", "onFailure" + error.getMessage());
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(NotesResult.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NotesResult.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
