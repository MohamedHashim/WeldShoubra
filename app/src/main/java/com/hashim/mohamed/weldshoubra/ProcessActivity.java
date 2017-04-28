package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt;
    LinearLayout smaw, gmaw, gtaw, fcaw, saw;
    Boolean smaw_bool,gmaw_bool,gtaw_bool,fcaw_bool,saw_bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("Processes");
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        smaw = (LinearLayout) findViewById(R.id.smaw);
        gmaw = (LinearLayout) findViewById(R.id.gmaw);
        gtaw = (LinearLayout) findViewById(R.id.gtaw);
        fcaw = (LinearLayout) findViewById(R.id.fcaw);
        saw = (LinearLayout) findViewById(R.id.saw);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        smaw.setVisibility(View.GONE);
        gmaw.setVisibility(View.GONE);
        gtaw.setVisibility(View.GONE);
        fcaw.setVisibility(View.GONE);
        saw.setVisibility(View.GONE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        smaw_bool =bundle.getBoolean("smaw");
        gmaw_bool =bundle.getBoolean("gmaw");
        gtaw_bool =bundle.getBoolean("gtaw");
        fcaw_bool =bundle.getBoolean("fcaw");
        saw_bool =bundle.getBoolean("saw");

        Log.d("bools",saw_bool+"\t"+gmaw_bool+"\t"+gtaw_bool+"\t"+fcaw_bool+"\t"+smaw_bool);

        if(smaw_bool)
            smaw.setVisibility(View.VISIBLE);
        if(gmaw_bool)
            gmaw.setVisibility(View.VISIBLE);
        if(gtaw_bool)
            gtaw.setVisibility(View.VISIBLE);
        if(fcaw_bool)
            fcaw.setVisibility(View.VISIBLE);
        if(saw_bool)
            saw.setVisibility(View.VISIBLE);

        if(!smaw_bool&&!gmaw_bool&&!gtaw_bool&&!fcaw_bool&&!saw_bool)
            Toast.makeText(getApplicationContext(),"There is no process to select",Toast.LENGTH_LONG).show();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessActivity.this, ResultActivity.class);
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
                Intent logout = new Intent(ProcessActivity.this, MainActivity.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
                moveTaskToBack(true);
                break;
        }
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
