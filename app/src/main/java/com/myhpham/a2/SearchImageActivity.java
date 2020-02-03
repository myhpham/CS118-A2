package com.myhpham.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchImageActivity extends AppCompatActivity {

    ArrayList<Image> imageList;
    Image i;
    MyDB db;
    ArrayAdapter adapter;
    ListView searchListView;
    EditText searchImgEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_image);

        searchListView = findViewById(R.id.searchListView);
        searchImgEditText = findViewById(R.id.searchImg_editText);
        Button searchImgButton = findViewById(R.id.searchImg_button);

        db = new MyDB(this);
        //final String searchString = searchImgEditText.getText().toString();

        searchImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageList = new ArrayList<>();
                view();
            }
        });
    }

    public void view(){
        Cursor c = db.searchImage(searchImgEditText.getText().toString());
        while(c.moveToNext()){
            String imgid = c.getString(0);
            String imgname = c.getString(1);
            String imgurl = c.getString(2);

            i = new Image(imgid, imgname, imgurl);
            imageList.add(i);
        }

        adapter = new ImageAdapter(SearchImageActivity.this, R.layout.list_item, imageList);
        searchListView.setAdapter(adapter);
    }
}
