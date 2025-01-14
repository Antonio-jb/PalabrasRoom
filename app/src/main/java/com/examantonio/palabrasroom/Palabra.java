package com.examantonio.palabrasroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tabla_palabras")
public class Palabra {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "palabra")
    private String mPalabra;
    public Palabra (@NonNull String palabra) {
        this.mPalabra = palabra;
    }
    public String getPalabra() {
        return this.mPalabra;
    }
}
