package com.hashim.mohamed.weldshoubra;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mohamed on 17/02/17.
 */
public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    String iconsStoragePath = Environment.getExternalStorageDirectory() + "/MobWPS/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
        Bitmap square_joint = BitmapFactory.decodeResource(getResources(), R.drawable.square_joint);
        Bitmap single_bevel = BitmapFactory.decodeResource(getResources(), R.drawable.single_bevel);
        Bitmap double_bevel = BitmapFactory.decodeResource(getResources(), R.drawable.double_bevel);
        Bitmap single_v = BitmapFactory.decodeResource(getResources(), R.drawable.single_v);
        Bitmap double_v = BitmapFactory.decodeResource(getResources(), R.drawable.double_v);
        storeImage(square_joint, "square_joint.jpg");
        storeImage(single_bevel, "single_bevel.jpg");
        storeImage(double_bevel, "double_bevel.jpg");
        storeImage(single_v, "single_v.jpg");
        storeImage(double_v, "double_v.jpg");
        final SharedPreferences sharedPref = getSharedPreferences("parameters", getApplicationContext().MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("photo_path", iconsStoragePath);
        editor.apply();
        File folder = new File(Environment.getExternalStorageDirectory() + "/MobWPS/WPS/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private void storeImage(Bitmap imageData, String filename) {
        //get path to external storage (SD card)
        File sdIconStorageDir = new File(iconsStoragePath);

        //create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();

        try {
            String filePath = iconsStoragePath + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

            //choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.PNG, 100, bos);

            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file" + e.getMessage());
            Toast.makeText(getApplicationContext(), "Error saving image file:", Toast.LENGTH_LONG).show();

        }
    }

}