package com.examantonio.palabrasroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private PalabraViewModel mWordViewModel;
    private PalabraListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new PalabraListAdapter(new PalabraListAdapter.WordDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this).get(PalabraViewModel.class);

        mWordViewModel.getPalabras().observe(this, palabras -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(palabras);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> showAddPalabraDialog());
    }

    private void showAddPalabraDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.activity_nueva_palabra, null);
        EditText editTextPalabra = dialogView.findViewById(R.id.edit_word);

        new AlertDialog.Builder(this)
                .setTitle("Agregar Palabra")
                .setView(dialogView)
                .setPositiveButton("Agregar", (dialogInterface, i) -> {
                    String palabra = editTextPalabra.getText().toString();
                    if (!palabra.trim().isEmpty()) {
                        mWordViewModel.insert(new Palabra(palabra));
                        Toast.makeText(this, "Palabra agregada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Por favor, ingresa una palabra", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.dismiss())
                .create()
                .show();
    }
}