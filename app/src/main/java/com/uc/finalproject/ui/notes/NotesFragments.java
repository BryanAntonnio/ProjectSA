package com.uc.finalproject.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.finalproject.R;
import com.uc.finalproject.adapter.NotesAdapter;
import com.uc.finalproject.model.SimpanNotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NotesFragments extends Fragment {
    public NotesFragments(){

    }

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_notes);
        getNotes();
        FloatingActionButton button_add_notes = view.findViewById(R.id.button_tambah_notes);
        button_add_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotesAddActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void getNotes(){
        final ArrayList<SimpanNotes>simpanNotes = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://hansrichard2000.c1.biz/studentsassist/notes/listNotes.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("SimpanNotes");
                    for (int i = 0; i<list.length(); i++){
                        JSONObject obj = list.getJSONObject(i);
                        SimpanNotes s = new SimpanNotes(obj.getString("id"), obj.getString("judul"), obj.getString("isi"));
                        simpanNotes.add(s);
                    }
                    showNotes(simpanNotes);
                }catch (Exception e){
                    Log.d("Exception", "onSuccess" + e.getMessage() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("OnFailureStudent", "onFailure" + error.getMessage());
            }
        });
    }

    private void showNotes(ArrayList<SimpanNotes> simpanNotes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NotesAdapter notesAdapter = new NotesAdapter(getContext());
        notesAdapter.setListNotes(simpanNotes);
        recyclerView.setAdapter(notesAdapter);

    }

}
