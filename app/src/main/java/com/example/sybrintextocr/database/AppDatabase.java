package com.example.sybrintextocr.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PictureDetail.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PictureDetailDAO pictureDetailDAO();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database").addCallback(sRoomDatabaseCallback)
                                .build();
                    }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                PictureDetailDAO dao = INSTANCE.pictureDetailDAO();

                PictureDetail pictureDetail = new PictureDetail("sfafasfsafs");
                dao.insert(pictureDetail);
                PictureDetail pictureDetail2 = new PictureDetail("Here is the code for creating the callback within the WordRoomDatabase class. Because you cannot do Room database operations on the UI thread, onCreate() uses the previously defined databaseWriteExecutor to execute a lambda on a background thread. The lambda deletes the contents of the database, then populates it with the two words \"Hello\" and \"World\". Feel free to add more words!");
                dao.insert(pictureDetail2);
            });
        }
    };

}
