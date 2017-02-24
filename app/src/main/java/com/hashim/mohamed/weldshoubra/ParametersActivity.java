package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class ParametersActivity extends AppCompatActivity {

    SearchableSpinner material1_spinner, material2_spinner, position_spinner, joint_type_spinner, thickness_spinner;
    TextView grove;
    Button next;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);

        material1_spinner = (SearchableSpinner) findViewById(R.id.material1_spinner);
        material2_spinner = (SearchableSpinner) findViewById(R.id.material2_spinner);
        position_spinner = (SearchableSpinner) findViewById(R.id.position_spinner);
        joint_type_spinner = (SearchableSpinner) findViewById(R.id.joint_type_spinner);
        thickness_spinner = (SearchableSpinner) findViewById(R.id.thickness_spinner);
        grove = (TextView) findViewById(R.id.grove);
        next = (Button) findViewById(R.id.next);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("Welding Parameters");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<String> material1;
        material1 = new ArrayList<String>();
        material1.add("Steel");
        material1.add("Steel");
        material1.add("Steel");
        material1.add("Steel");
        ArrayAdapter<String> material1_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, material1);
        material1_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material1_spinner.setAdapter(material1_Adapter);

        List<String> material2;
        material2 = new ArrayList<String>();
        material2.add("Steel");
        material2.add("Steel");
        material2.add("Steel");
        material2.add("Steel");
        ArrayAdapter<String> material2_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, material2);
        material2_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material2_spinner.setAdapter(material2_Adapter);

        List<String> position;
        position = new ArrayList<String>();
        position.add("Steel");
        position.add("Steel");
        position.add("Steel");
        position.add("Steel");
        ArrayAdapter<String> position_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, position);
        position_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position_spinner.setAdapter(position_Adapter);

        List<String> joint_type;
        joint_type = new ArrayList<String>();
        joint_type.add("Steel");
        joint_type.add("Steel");
        joint_type.add("Steel");
        joint_type.add("Steel");
        ArrayAdapter<String> joint_type_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, joint_type);
        joint_type_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        joint_type_spinner.setAdapter(joint_type_Adapter);

        List<String> thickness;
        thickness = new ArrayList<String>();
        thickness.add("5 - 10");
        thickness.add("10 - 15");
        thickness.add("15 - 20");
        thickness.add("20 - 25");
        ArrayAdapter<String> thickness_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, thickness);
        thickness_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thickness_spinner.setAdapter(thickness_Adapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParametersActivity.this, ProcessActivity.class);
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
                Intent logout = new Intent(ParametersActivity.this, MainActivity.class);
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

