package com.example.sybrintextocr.ui.display;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sybrintextocr.R;
import com.example.sybrintextocr.database.PictureDetail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<PictureDetail> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageViewRecyclerview);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView(){
            return  imageView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    @Inject
    public RecyclerViewAdapter(@NonNull  List<PictureDetail> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        PictureDetail curr = localDataSet.get(position);
        viewHolder.getTextView().setText(curr.getDetails());
        viewHolder.getImageView().setImageBitmap(Data.image);



        Context context = viewHolder.imageView.getContext();
        // get input stream
        InputStream ims = null;


    /*    if(Data.image != null){
            viewHolder.getImageView().setImageBitmap(Data.image);

        }else {
            try {
                ims = context.getAssets().open("1.png");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                viewHolder.getImageView().setImageDrawable(d);
                ims.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
