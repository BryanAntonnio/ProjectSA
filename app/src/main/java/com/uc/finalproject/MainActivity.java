package com.uc.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.finalproject.ui.account.AccountFragments;
import com.uc.finalproject.ui.notes.NotesAddActivity;
import com.uc.finalproject.ui.notes.NotesFragments;
import com.uc.finalproject.ui.reminder.ReminderFragments;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.notes);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,new NotesFragments()).commit();



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()){
                        case R.id.notes_page:
                            toolbar.setTitle(R.string.notes);
                            setSupportActionBar(toolbar);
                            fragment = new NotesFragments();
                            loadFragment(fragment);
                            return true;
                        case R.id.reminder_page:
                            toolbar.setTitle(R.string.reminder);
                            setSupportActionBar(toolbar);
                            fragment = new ReminderFragments();
                            loadFragment(fragment);
                            return true;
                        case R.id.account_page:
                            toolbar.setTitle(R.string.account);
                            setSupportActionBar(toolbar);
                            fragment = new AccountFragments();
                            loadFragment(fragment);
                            return true;
                    }return false;

//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                            selectedFragment).commit();
//
//                    return true;
                }
            };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}
