package com.example.sybrintextocr.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Provides;

@Database(entities = {PictureDetail.class}, version = 2, exportSchema = true,  autoMigrations = {
        @AutoMigration(from = 1, to = 2)
})
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
                                    AppDatabase.class, "app_database")
                                .build();
                    }
            }
        }
        return INSTANCE;
    }


}
