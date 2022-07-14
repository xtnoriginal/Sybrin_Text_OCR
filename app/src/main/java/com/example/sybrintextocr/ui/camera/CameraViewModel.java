package com.example.sybrintextocr.ui.camera;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sybrintextocr.database.PictureDetailsRepository;

public class CameraViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private PictureDetailsRepository repository;

    public CameraViewModel(Application application) {
        super(application);
        repository = new PictureDetailsRepository(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}