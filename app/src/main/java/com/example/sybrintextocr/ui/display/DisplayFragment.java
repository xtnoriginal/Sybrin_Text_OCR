package com.example.sybrintextocr.ui.display;

import android.os.Bundle;
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
import com.example.sybrintextocr.database.PictureDetail;
import com.example.sybrintextocr.databinding.FragmentDisplayBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DisplayFragment extends Fragment {

    private FragmentDisplayBinding binding;

    private RecyclerViewAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DisplayViewModel displayViewModel =
                new ViewModelProvider(this).get(DisplayViewModel.class);

        binding = FragmentDisplayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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



         List<PictureDetail> pics = new ArrayList<>();
         pics.add(new PictureDetail("hjgjjhjh"));
        pics.add(new PictureDetail("l,gfjydtydtu"));

        //Set up recyclerview
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(pics);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}