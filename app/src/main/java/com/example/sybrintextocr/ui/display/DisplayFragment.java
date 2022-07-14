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

        // Create the observer which updates the UI.
        final Observer<List<PictureDetail>> observer = new Observer<List<PictureDetail>>() {
            @Override
            public void onChanged(@Nullable final List<PictureDetail> pictureDetail) {
                // Update the UI, in this case, a TextView.
                recyclerViewAdapter.notifyDataSetChanged();
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        displayViewModel.getPhotoDetail().observe( this.getViewLifecycleOwner(),observer);

        readImage();

         List<PictureDetail> pics = new ArrayList<>();
        pics.add(new PictureDetail(Data.data));
         pics.add(new PictureDetail("hjgjjhjh"));
        pics.add(new PictureDetail("l,gfjydtydtu"));
        pics.add(new PictureDetail("helloworld"));


        //Set up recyclerview
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(pics);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);




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
                ans[0] = textProcessor.getText(image);
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