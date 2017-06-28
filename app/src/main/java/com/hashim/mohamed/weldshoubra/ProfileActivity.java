package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Pattern;

import static android.R.attr.filter;


public class ProfileActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt, name_txt, email_txt, number_txt;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("Mob WPS");
        name_txt = (TextView) findViewById(R.id.name);
        email_txt = (TextView) findViewById(R.id.email);
        number_txt = (TextView) findViewById(R.id.number);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");
        name_txt.setText(name);
        if (name.equals("Mahmoud Talaat")) {
            email_txt.setText("mahmoodamer5@gmail.com");
            number_txt.setText("+201113268260");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Mohammed Faheem")) {
            email_txt.setText("mohammedfaheem904@gmail.com");
            number_txt.setText("+201014565099");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Ibrahim Reda")) {
            email_txt.setText("ibrahimreda64@gmail.com");
            number_txt.setText("+201015973186");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Mahmoud Alashery")) {
            email_txt.setText("ma89533@gmail.com");
            number_txt.setText("+201098954529");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Youmna Ashraf")) {
            email_txt.setText("yoka.yoyo2015@yahoo.com");
            number_txt.setText("+201111478591");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Mostafa Eltokhey")) {
            email_txt.setText("mostafaeltokhey2017@gmail.com");
            number_txt.setText("+201091137088");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Noor Hesham")) {
            email_txt.setText("noorhesham88@gmail.com");
            number_txt.setText("+201006233362");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
        } else if (name.equals("Mahmoud Elnggar")) {
            email_txt.setText("mahmoudelnggar47@yahoo.com ");
            number_txt.setText("+201154207585");
            Linkify.addLinks(number_txt, Linkify.PHONE_NUMBERS);
            Linkify.addLinks(email_txt, Linkify.EMAIL_ADDRESSES);
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
                Intent logout = new Intent(ProfileActivity.this, MainActivity.class);
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
