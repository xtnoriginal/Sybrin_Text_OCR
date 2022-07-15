package com.example.sybrintextocr.TextExtractor.MLKIT;

import android.graphics.Point;
import android.graphics.Rect;
import android.renderscript.ScriptGroup;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.database.PictureDetailsRepository;
import com.example.sybrintextocr.ui.camera.CameraFragment;
import com.example.sybrintextocr.ui.display.Data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class TextProcessor {

    private TextRecognizer textRecognizer;
    private PictureDetailsRepository pictureDetailsRepository;


    public  TextProcessor(){
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    }

    public void getText(String filename,InputImage image){

        String[] resultString = new String[1];

        Task<Text> result =
                textRecognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {

                                PictureDetail pictureDetail =new PictureDetail(visionText.getText(),filename);
                                pictureDetail.image = image.getBitmapInternal();
                                Data.pictureDetailList.add(pictureDetail);

                                Data.data = visionText.getText();


                                Data.pictureDetailsRepository.insert(pictureDetail);
                                Log.i("TextProcessor",pictureDetail.getUid()+"");







                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });




    }



}
