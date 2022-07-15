package com.example.sybrintextocr.database;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class PictureDetail {


    public  PictureDetail(@NonNull String details, @NonNull String filename) {
        this.details =details;
        this.filename = filename;
    }

    public String getDetails(){
        return details;
    }

    public int getUid() {
        return uid;
    }

    @PrimaryKey(autoGenerate = true)
    int uid;

    public String getFilename() {
        return filename;
    }

    @ColumnInfo(name = "filename")
    String filename;

    @ColumnInfo(name = "details")
    String details;

    @Ignore
    public Bitmap image ;


}
