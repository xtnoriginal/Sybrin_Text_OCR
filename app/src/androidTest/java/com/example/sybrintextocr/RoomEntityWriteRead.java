package com.example.sybrintextocr;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sybrintextocr.database.AppDatabase;
import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.database.PictureDetailDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class RoomEntityWriteRead {

    @Inject
    public PictureDetailDAO pictureDetailDAO;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        pictureDetailDAO = db.pictureDetailDAO();

    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        PictureDetail pictureDetails []  = new PictureDetail[10];
        for (int i = 0; i < 10; i++) {
            pictureDetails[i] = new PictureDetail("Hello"+1,"pic.jpg");
        }

       pictureDetailDAO.insertAll(pictureDetails);
        assertEquals(pictureDetails.length,pictureDetailDAO.size());


        pictureDetailDAO.delete(pictureDetails[0]);
        assertEquals(pictureDetails.length,pictureDetailDAO.size());


        pictureDetailDAO.insert(pictureDetails[0]);
        assertEquals(pictureDetails.length+1,pictureDetailDAO.size());


        List<PictureDetail> pictureDetailList = pictureDetailDAO.getAllNonLive();
        LiveData<List<PictureDetail>> pictureDetailListLive = pictureDetailDAO.getAll();


        //assertArrayEquals(pictureDetailList.toArray().,pictureDetailListLive.getValue().toArray());



    }
}
