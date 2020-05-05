package com.uc.finalproject.ui.reminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;
import com.uc.finalproject.model.ArrayReminder;
import com.uc.finalproject.model.SimpanNotes;
import com.uc.finalproject.model.SimpanReminder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ReminderAddActivity extends AppCompatDialogFragment {
    private EditText reminder_input;
    String judulReminder;
    Fragment fragment;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_reminder_input,null);
        reminder_input = view.findViewById(R.id.reminder_field);
        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                judulReminder = reminder_input.getText().toString().trim();
                inputReminder(judulReminder);
                fragment = new ReminderFragments();
                loadFragment(fragment);
            }
        });


        return builder.create();
    }

    private void inputReminder(String judulReminder) {
        final ArrayList<SimpanNotes> simpanNotes = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url ="http://hansrichard2000.c1.biz/studentsassist/reminder/reminderInput.php";

        RequestParams params = new RequestParams();
        params.put("judulReminder", judulReminder);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                } catch (JSONException e) {
                    Log.d("Error reminder", "onSuccess" + e.getMessage() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }
}
