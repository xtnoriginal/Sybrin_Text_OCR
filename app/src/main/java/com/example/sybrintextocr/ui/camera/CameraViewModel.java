package com.example.sybrintextocr.ui.camera;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.database.PictureDetailsRepository;

import java.util.List;

public class CameraViewModel extends AndroidViewModel {

    private PictureDetailsRepository repository;


    public CameraViewModel(Application application) {
        super(application);
        repository = new PictureDetailsRepository(application);

    }


    public String createFileName(){
        return "extract"+repository.numberOfPicture()+1;
    }


}