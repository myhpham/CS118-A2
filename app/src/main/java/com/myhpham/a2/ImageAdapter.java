package com.myhpham.a2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<Image> {
    private static final String TAG = "ImageAdapter";

    MyDB db;
    private Context iContext;
    int iResource;

    public ImageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Image> objects) {
        super(context, resource, objects);
        iContext = context;
        iResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(iContext);
        convertView = inflater.inflate(iResource, parent, false);

        TextView id_textView = convertView.findViewById(R.id.imgID_textView);
        TextView title_textView = convertView.findViewById(R.id.img_title_textview);
        ImageView img_view = convertView.findViewById(R.id.img_view);
        ImageButton deleteicon = convertView.findViewById(R.id.deleteButton);

        String imgid = getItem(position).getId();
        String imgname = getItem(position).getName();
        String imgurl = getItem(position).getUrl();

        DownloadImage di = new DownloadImage(img_view);
        di.execute(imgurl);

        Bitmap imgbitmap = getItem(position).getBitmap();

        id_textView.setText(imgid);
        title_textView.setText(imgname);
        img_view.setImageBitmap(imgbitmap);

        deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new MyDB(iContext);
                db.deleteImage(getItem(position).getId());
                Image im = getItem(position);
                remove(im);
            }
        });
        return convertView;
    }
}