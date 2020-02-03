package com.myhpham.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DownloadImageActivity extends AppCompatActivity {

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    EditText imgUrlEditText, imgTitleEditText;
    Button dlImgButton;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        //db = new MyDB(DownloadImageActivity.this, "IMG_DB", null, 1);

        //set up edittext and button
        imgUrlEditText = findViewById(R.id.imgURLEditText);
        imgTitleEditText = findViewById(R.id.imgTitleEditText);
        dlImgButton = findViewById(R.id.downloadButton);

        db = new MyDB(DownloadImageActivity.this);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        dlImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
    }

    public void checkConnection(){
        if(!(networkInfo != null && networkInfo.isConnected())){
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
        else {
            if(URLUtil.isValidUrl(imgUrlEditText.getText().toString())){
                db.insertImage(imgTitleEditText.getText().toString(), imgUrlEditText.getText().toString());
                finish();
            }
        }
    }
}
