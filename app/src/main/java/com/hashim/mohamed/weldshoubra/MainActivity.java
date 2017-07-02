package com.hashim.mohamed.weldshoubra;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AbsRuntimePermission {

    Button AWS_btn;
    public DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView searchFlag;
    TextView toolbartxt;
    private static final int REQUEST_PERMISSION = 10;
    String iconsStoragePath = Environment.getExternalStorageDirectory() + "/MobWPS/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AWS_btn = (Button) findViewById(R.id.AWS_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        mDrawer.addDrawerListener(drawerToggle);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        searchFlag = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("Codes");
        searchFlag.setImageResource(R.drawable.menue);
        searchFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);

            }
        });
        requestAppPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                },
                R.string.msg, REQUEST_PERMISSION);

        AWS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
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

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_second_fragment:
                Intent i = new Intent(this, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.nav_third_fragment:
                Intent logout = new Intent(MainActivity.this, MainActivity.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
                moveTaskToBack(true);
                break;
        }
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
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
