package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button AWS_btn;
    public DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView searchFlag;
    TextView toolbartxt;

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

        AWS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                startActivity(intent);
            }
        });
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

}
