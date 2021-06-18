package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.*;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class Notes extends AppCompatActivity {
    @Nullable
    @BindView(R.id.Notes)
    RecyclerView Notes;
    Button Back;

    RecyclerView.Adapter adapter;
    List<Note> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);


        Back = findViewById(R.id.backButton);



        initViews();

        NotesDatabase db = new NotesDatabase(this);
        notesList.addAll(db.getAllNotes());
        adapter = new NotesAdapter(this,notesList);
        if(notesList.size() != 0){

            Notes.setAdapter(adapter);
        }
        loadNotes();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Notes.this, LoginScreen.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {

        Notes.setLayoutManager(new LinearLayoutManager(this));
    }



    private void loadNotes(){

        adapter.notifyDataSetChanged();

    }


    @Optional
    @OnClick(R.id.fabAddNote)
    public void addNote(){
        Intent i = new Intent(Notes.this, AddNote.class);
        startActivity(i);
    }





}