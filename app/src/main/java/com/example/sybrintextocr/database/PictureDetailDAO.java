package com.example.sybrintextocr.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PictureDetailDAO {

        // allowing the insert of the same word multiple times by passing a
        // conflict resolution strategy
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(PictureDetail pictureDetail);

        @Insert
        void insertAll(PictureDetail ...pictureDetails);

        @Delete
        void delete(PictureDetail pictureDetail);

        @Query("SELECT * FROM PictureDetail")
        LiveData<List<PictureDetail>> getAll();

        @Query("SELECT * FROM PictureDetail")
        List<PictureDetail> getAllNonLive();


        @Query("SELECT COUNT(*) FROM PictureDetail")
        int size();

}
