package com.example.sybrintextocr.TextExtractor.MLKIT;

import android.graphics.Point;
import android.graphics.Rect;
import android.renderscript.ScriptGroup;

import androidx.annotation.NonNull;

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


    public  TextProcessor(){
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    }

    public String getText(InputImage image){

        String[] resultString = new String[1];

        Task<Text> result =
                textRecognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                // Task completed successfully
                                // [START_EXCLUDE]
                                // [START get_text]
                               // for (Text.TextBlock block : visionText.getTextBlocks()) {
                              /*      Rect boundingBox = block.getBoundingBox();
                                    Point[] cornerPoints = block.getCornerPoints();
                                    String text = block.getText();

                                    for (Text.Line line: block.getLines()) {
                                        // ...
                                        for (Text.Element element: line.getElements()) {
                                            // ...
                                        }
                                    }
                                }*/
                                // [END get_text]
                                // [END_EXCLUDE]


                                resultString[0] = visionText.getText();





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

        return  resultString[0];

    }



}
