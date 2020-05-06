package com.uc.finalproject.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReminderDeleteActivity extends AppCompatActivity {
    Toolbar toolbar2;
    EditText delete_id;
    Button delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delete_reminder);
        delete_id = findViewById(R.id.id_delete);
        delete = findViewById(R.id.deleteButton2);

//        toolbar2.findViewById(R.id.toolbar_delete);
//        setSupportActionBar(toolbar2);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ReminderDeleteActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = delete_id.getText().toString().trim();
                getDelete(id);
                Intent intent = new Intent(ReminderDeleteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                    String msg = responseObject.getString("message");
                    showMessage(msg);
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

    private void showMessage(String msg) {
        Toast.makeText(ReminderDeleteActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReminderDeleteActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
