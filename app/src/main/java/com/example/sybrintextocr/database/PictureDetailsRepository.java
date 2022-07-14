package com.example.sybrintextocr.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PictureDetailsRepository {
    private PictureDetailDAO pictureDetailDAO;
    private LiveData<List<PictureDetail>> mPictureDetails;


    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public PictureDetailsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        pictureDetailDAO = db.pictureDetailDAO();
        mPictureDetails = pictureDetailDAO.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<PictureDetail>> getAll() {
        return mPictureDetails;
    }

    public List<PictureDetail> getAllNonLive() {
        return pictureDetailDAO.getAllNonLive();
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(PictureDetail pictureDetail) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            pictureDetailDAO.insert(pictureDetail);
        });
    }


}
