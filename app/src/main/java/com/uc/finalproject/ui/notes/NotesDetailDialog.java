package com.uc.finalproject.ui.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.R;
import com.uc.finalproject.model.SimpanNotes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NotesDetailDialog extends AppCompatDialogFragment {
    EditText id;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.notes_update_dialog, null);
        builder.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idText = id.getText().toString().trim();
                getDetail(idText);
                Intent intent = new Intent(getActivity(), NotesResult.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return builder.create();
    }

    private void getDetail(String idText) {
        final ArrayList<SimpanNotes> simpanNotes = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url ="http://hansrichard2000.c1.biz/studentsassist/notes/notesResult.php";

        RequestParams params = new RequestParams();
        params.put("id", idText);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String judulText = responseObject.getString("judul");
                    String isiText = responseObject.getString("isi");
//                    judul.setText(judulText);
//                    isi.setText(isiText);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
