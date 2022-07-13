package com.example.sybrintextocr.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class PictureDetail {


    public  PictureDetail(@NonNull String details) {
        this.details =details;
    }

    public String getDetails(){
        return details;
    }

    @PrimaryKey(autoGenerate = true)
    int uid;

    @ColumnInfo(name = "details")
    String details;


}
