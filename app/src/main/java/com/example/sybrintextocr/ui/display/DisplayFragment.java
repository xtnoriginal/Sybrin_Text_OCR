package com.example.sybrintextocr.ui.display;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sybrintextocr.R;
import com.example.sybrintextocr.TextExtractor.MLKIT.TextProcessor;
import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.databinding.FragmentDisplayBinding;
import com.google.mlkit.vision.common.InputImage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class DisplayFragment extends Fragment {

    private FragmentDisplayBinding binding;
    TextProcessor textProcessor;
    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DisplayViewModel displayViewModel =
                new ViewModelProvider(this).get(DisplayViewModel.class);

        binding = FragmentDisplayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        textProcessor = new TextProcessor();







        List<PictureDetail> data = displayViewModel.getPictureDetailList();
        Log.i("DisplayFragment", data.size()+" ");


        //Set up recyclerview
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new RecyclerViewAdapter.PictureDetailsDiff());
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
       displayViewModel.getPhotoDetail().observe(this.getActivity(), words -> {
            // Update the cached copy of the words in the adapter.
            recyclerViewAdapter.submitList(words);
        });





        return root;
    }


    public String readImage(){
        /*Bitmap bitmap = null;
        try (AssetManager assetManager = getContext().getAssets()) {
            InputStream inputStream = assetManager.open("1.png");
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }


        if(bitmap == null){
            Log.i("DisplayFragment", "invalid bitmap");
            return null;}*/

        AssetManager assetManager = getContext().getAssets();
        Bitmap bitmap = null;
        try {

            InputStream inputStream = assetManager.open("1.png");
            bitmap = BitmapFactory.decodeStream(inputStream);

            return TestMLKIT(InputImage.fromBitmap(bitmap,0));



        } catch (IOException e) {
            e.printStackTrace();
        }



        return "";


    }


    public  String TestMLKIT(InputImage image){
        String[] ans = new String[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                //textProcessor.getText(image);
            }
        }).start();

        return ans[0];
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}