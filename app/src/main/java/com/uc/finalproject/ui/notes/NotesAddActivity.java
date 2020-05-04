package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;
import com.uc.finalproject.model.SimpanNotes;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class NotesAddActivity extends AppCompatActivity {
    EditText judul, isi;
    String TempJudul, TempIsi;
    Toolbar toolbar;
    final ArrayList<SimpanNotes>simpanNotes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_input);
        judul = findViewById(R.id.judul_notes);
        isi = findViewById(R.id.input_field);
        toolbar = findViewById(R.id.toolbar_input_notes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesAddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void inputNotes() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url ="hansrichard2000.c1.biz/studentsassist/notes/notesInput.php";

        RequestParams params = new RequestParams();
        params.put("judul", judul);
        params.put("isi", isi);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("SimpanNotes");
                    for (int i = 0; i<list.length(); i++){
                        JSONObject obj = list.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

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

                break;
        }
        inputNotes();
        Intent intent = new Intent(NotesAddActivity.this, NotesResult.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, msg="New Data Inserted", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
