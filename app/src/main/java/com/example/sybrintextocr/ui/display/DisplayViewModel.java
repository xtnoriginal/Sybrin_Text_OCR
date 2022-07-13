package com.example.sybrintextocr.ui.display;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.database.PictureDetailsRepository;

import java.util.List;

public class DisplayViewModel extends AndroidViewModel {

    private LiveData<List<PictureDetail>> mPictureDetails;
    private PictureDetailsRepository repository;

    public DisplayViewModel(Application application) {
        super(application);
       repository = new PictureDetailsRepository(application);
       mPictureDetails = repository.getAll();
    }

    public LiveData<List<PictureDetail>> getPhotoDetail() {
        return mPictureDetails;
    }
}