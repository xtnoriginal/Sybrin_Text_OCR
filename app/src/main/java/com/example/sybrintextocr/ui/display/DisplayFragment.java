package com.example.sybrintextocr.ui.display;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sybrintextocr.R;
import com.example.sybrintextocr.databinding.FragmentDisplayBinding;

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




        //Set up recyclerview
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(displayViewModel.getPhotoDetail().getValue());
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