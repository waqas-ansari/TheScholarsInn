package com.arktech.waqasansari.thescholarsinn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Linta Ansari on 7/7/2016.
 */
public class GetPicture extends AsyncTask<String, Integer, Bitmap> {
    ImageView imageView;

    public GetPicture(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL imgURL;
        try {
            imgURL = new URL(params[0]);
            return BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
    }
}

