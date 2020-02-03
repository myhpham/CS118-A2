package com.myhpham.a2;

import android.graphics.Bitmap;

public class Image{
    private String id;
    private String name;
    private String url;
    private Bitmap bitmap;

    public Image(String id, String name, String url) {
        Bitmap b = getBitmap();

        this.id = id;
        this.name = name;
        this.url = url;
        this.bitmap = getBitmap();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
