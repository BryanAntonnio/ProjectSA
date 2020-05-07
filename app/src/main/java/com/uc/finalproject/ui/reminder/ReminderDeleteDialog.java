package com.uc.finalproject.ui.reminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReminderDeleteDialog extends AppCompatDialogFragment {
    EditText delete_id;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_delete_reminder, null);
        delete_id = view.findViewById(R.id.id_delete);
        builder.setView(view).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = delete_id.getText().toString().trim();
                getDelete(id);
                Fragment fragment = new ReminderFragments();
                loadFragment(fragment);
            }
        });
        return builder.create();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

    private void getDelete(String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://hansrichard2000.c1.biz/studentsassist/reminder/deleteReminder.php";

        RequestParams params = new RequestParams();
        params.put("id", id);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
//                    String msg = responseObject.getString("message");
//                    showMessage(msg);
                } catch (JSONException e) {
                    Log.d("Error delete reminder", "onSuccess" + e.getMessage() );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("OnFailureReminderDelete", "onFailure" + error.getMessage());
            }
        });
    }
}
