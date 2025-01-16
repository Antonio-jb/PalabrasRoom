package com.examantonio.palabrasroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PalabraViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private PalabraViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static PalabraViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PalabraViewHolder(view);
    }
}
