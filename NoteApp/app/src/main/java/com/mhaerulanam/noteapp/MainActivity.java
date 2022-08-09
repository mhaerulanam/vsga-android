package com.mhaerulanam.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TODO APP");

        Button btnTodo = findViewById(R.id.todo);
        Button btnAbout = findViewById(R.id.about);

        btnTodo.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListActivity.class)));

        btnAbout.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AboutActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            tampilkanDialogKonfirmasiLogout();
        }
        return super.onOptionsItemSelected(item);
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                    hapusFile();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

}