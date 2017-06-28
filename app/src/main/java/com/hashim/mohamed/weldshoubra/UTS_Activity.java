package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Math.pow;

public class UTS_Activity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt, title_txt, uts_result;
    String process;
    Double f, s, v, t, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uts);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("UTS");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        title_txt = (TextView) findViewById(R.id.title_txt);
        uts_result = (TextView) findViewById(R.id.uts_result);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
            process = bundle.getString("process");

        if (process.equals("saw")) {
            title_txt.setText(title_txt.getText() + "SAW process");
            f = 6.0;
            s = 5.0;
            v = 34.0;
            t = 200.0;
            result = uts_result(f, s, v, t);
        } else if (process.equals("fcaw")) {//TODO data is wrong
            title_txt.setText(title_txt.getText() + "FCAW process");
            f = 6.0;
            s = 5.0;
            v = 34.0;
            t = 200.0;
            result = uts_result(f, s, v, t);
        } else if (process.equals("gtaw")) {
            title_txt.setText(title_txt.getText() + "GTAW process");
            f = 3.0;
            s = 2.0;
            v = 21.0;
            t = 200.0;
            result = uts_result(f, s, v, t);
        } else if (process.equals("gmaw")) {
            title_txt.setText(title_txt.getText() + "GMAW process");
            f = 5.2;
            s = 4.0;
            v = 21.0;
            t = 200.0;
        } else if (process.equals("smaw")) {
            title_txt.setText(title_txt.getText() + "SMAW process");
            f = 6.0;
            s = 5.0;
            v = 26.0;
            t = 200.0;
            result = uts_result(f, s, v, t);
        }
        uts_result.setText(uts_result.getText() + result.toString());
    }

    Double uts_result(double f, double s, double v, double t) {
        return 29.48 * pow(f, -0.045) * pow(v, 0.212) * pow(s, -0.0158) * pow(t, -0.013);
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
                Intent logout = new Intent(UTS_Activity.this, MainActivity.class);
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
