package com.uc.finalproject.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.finalproject.R;
import com.uc.finalproject.adapter.ReminderAdapter;
import com.uc.finalproject.model.SimpanReminder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ReminderFragments extends Fragment {
    TextView lbl_nodata_3, lbl_nodata_4, lbl_nodata_5;
    Button delete, update;
    private ProgressBar progressBar;
    RecyclerView mRecycleView;
    final ArrayList<SimpanReminder>listReminder = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lbl_nodata_3 = view.findViewById(R.id.lbl_nodata_3);
        lbl_nodata_4 = view.findViewById(R.id.lbl_nodata_4);
        lbl_nodata_5 = view.findViewById(R.id.lbl_nodata_5);
        mRecycleView = view.findViewById(R.id.recycler_reminder);
        progressBar = view.findViewById(R.id.progressBar);
        delete = view.findViewById(R.id.deleteButton);
        update = view.findViewById(R.id.updateButton);
        showLoading(true);
        getReminder();
        FloatingActionButton button_add_reminder = view.findViewById(R.id.button_tambah_reminder);
        button_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDelete();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate();
            }
        });
    }

    private void openDialogDelete() {
        ReminderDeleteDialog reminderDeleteDialog = new ReminderDeleteDialog();
        reminderDeleteDialog.show(getActivity().getSupportFragmentManager(), "deleteReminder");
    }

    private void openDialogUpdate() {
        ReminderUpdateDialog reminderUpdateDialog = new ReminderUpdateDialog();
        reminderUpdateDialog.show(getActivity().getSupportFragmentManager(), "updateReminder");
    }


    private void getReminder() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://hansrichard2000.c1.biz/studentsassist/reminder/listReminder.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("SimpanReminder");
                    for (int i = 0; i<list.length(); i++){
                        JSONObject obj = list.getJSONObject(i);
                        SimpanReminder s = new SimpanReminder(obj.getString("id"), obj.getString("judulReminder"));
                        listReminder.add(s);
                        if (listReminder.isEmpty()){
                            lbl_nodata_3.setVisibility(View.VISIBLE);
                            lbl_nodata_4.setVisibility(View.VISIBLE);
                            lbl_nodata_5.setVisibility(View.VISIBLE);
                        }
                        else {
                            lbl_nodata_3.setVisibility(View.INVISIBLE);
                            lbl_nodata_4.setVisibility(View.INVISIBLE);
                            lbl_nodata_5.setVisibility(View.INVISIBLE);
                            showReminder(listReminder);
                        }
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

    private void openDialog() {
        ReminderAddActivity reminderAddActivity = new ReminderAddActivity();
        reminderAddActivity.show(getActivity().getSupportFragmentManager(), "input reminder");
    }

    public void showReminder (ArrayList<SimpanReminder>listReminder){
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ReminderAdapter reminderAdapter = new ReminderAdapter(getContext());
        reminderAdapter.setListReminder(listReminder);
        showLoading(false);
        mRecycleView.setAdapter(reminderAdapter);
    }

    private void showLoading (Boolean state ){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
