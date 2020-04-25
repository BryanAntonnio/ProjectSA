package com.uc.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.finalproject.ui.account.AccountFragments;
import com.uc.finalproject.ui.notes.NotesAddActivity;
import com.uc.finalproject.ui.notes.NotesFragments;
import com.uc.finalproject.ui.reminder.ReminderFragments;

public class MainActivity extends AppCompatActivity {
//    FloatingActionButton button_add_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                new NotesFragments()).commit();

//        button_add_notes = findViewById(R.id.button_tambah_notes);
//        button_add_notes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NotesAddActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.notes_page:
                            selectedFragment = new NotesFragments();
                            break;
                        case R.id.reminder_page:
                            selectedFragment = new ReminderFragments();
                            break;
                        case R.id.account_page:
                            selectedFragment = new AccountFragments();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            selectedFragment).commit();

                    return true;
                }
            };
}
