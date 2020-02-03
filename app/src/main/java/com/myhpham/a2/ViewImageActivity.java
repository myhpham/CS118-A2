package com.myhpham.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    ArrayList<Image> imageList = new ArrayList<>();
    Image i;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        //set up list view
        ListView viewImgListView = findViewById(R.id.viewImgListView);

        db = new MyDB(this);
        Cursor c = db.viewAllImages();
        while(c.moveToNext()){
            String imgid = c.getString(0);
            String imgname = c.getString(1);
            String imgurl = c.getString(2);

            i = new Image(imgid, imgname, imgurl);
            imageList.add(i);
        }

        ArrayAdapter adapter = new ImageAdapter(this, R.layout.list_item, imageList);
        viewImgListView.setAdapter(adapter);
    }
}
