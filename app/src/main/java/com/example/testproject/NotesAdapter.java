package com.example.testproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CaseMap;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    Context context;
    List<Note> noteList;
    NotesDatabase myDB;
    SQLiteDatabase database;

    private int selectedPos = RecyclerView.NO_POSITION;

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
        myDB = new NotesDatabase(context);
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_note,parent,false);
        NotesViewHolder nvh = new NotesViewHolder(v);
        return nvh;
    }



    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        holder.tvTitle.setText(noteList.get(position).getTitle());
        holder.tvNote.setText(noteList.get(position).getNote());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("TITLE", noteList.get(position).getTitle());
                intent.putExtra("DETAILS", noteList.get(position).getNote());

                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = myDB.getWritableDatabase();
                myDB.deleteNote(String.valueOf(noteList.get(position).getId()));
                noteList.remove(position);
                notifyDataSetChanged();
                notifyItemRemoved(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }



    public class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvNote;
        Button Delete;
        public NotesViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvNote = itemView.findViewById(R.id.tvNoteText);
            Delete = itemView.findViewById(R.id.buttonDelete);

        }
    }
}
