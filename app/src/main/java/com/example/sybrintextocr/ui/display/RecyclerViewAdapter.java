package com.example.sybrintextocr.ui.display;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sybrintextocr.R;
import com.example.sybrintextocr.database.PictureDetail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends ListAdapter<PictureDetail,RecyclerViewAdapter.ViewHolder> {

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



    public RecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<PictureDetail> diffCallback) {
        super(diffCallback);

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
        PictureDetail curr = getItem(position);
        viewHolder.getTextView().setText(curr.getDetails());
        viewHolder.getImageView().setImageBitmap(Data.image);



        Context context = viewHolder.imageView.getContext();

        // Open a specific media item using InputStream.
        ContentResolver resolver = context.getApplicationContext()
                .getContentResolver();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            try {
                Bitmap thumbnail =
                        context.getContentResolver().loadThumbnail(
                               Uri.parse(curr.getFilename()), new Size(640, 480), null);
                viewHolder.getImageView().setImageBitmap(thumbnail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {


            InputStream ims;
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
        }

    }



    static class PictureDetailsDiff extends DiffUtil.ItemCallback<PictureDetail> {

        @Override
        public boolean areItemsTheSame(@NonNull PictureDetail oldItem, @NonNull PictureDetail newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PictureDetail oldItem, @NonNull PictureDetail newItem) {
            return oldItem.getUid()== newItem.getUid() || oldItem.getFilename().equals(newItem.getFilename());
        }
    }
}
