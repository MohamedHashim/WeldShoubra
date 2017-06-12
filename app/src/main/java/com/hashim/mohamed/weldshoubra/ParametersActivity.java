package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class ParametersActivity extends AppCompatActivity {

    SearchableSpinner material1_spinner, material2_spinner, position_spinner,area_condition_spinner, welding_method_spinner;
    Button next;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt, groove,groove_txt;
    private EditText thickness;
    private String groove_types[] = {"Square joint", "Single-bevel joint", "Double-bevel joint", "Single-V joint", "Double-V joint"};
    String thickness_txt;
    int position_index, area_condition_index, welding_method_index;
    Double thick_num;

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
        welding_method_spinner = (SearchableSpinner) findViewById(R.id.welding_method);
        area_condition_spinner = (SearchableSpinner) findViewById(R.id.area_condition);


        groove = (TextView) findViewById(R.id.groove);
        groove_txt= (TextView) findViewById(R.id.groove_txt);
        next = (Button) findViewById(R.id.next);
        thickness = (EditText) findViewById(R.id.thickness);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("Welding Parameters");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        thickness.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (thickness.getText().toString().isEmpty())
                        thickness.setError("this field is empty");
                    else {
                        groove_txt.setVisibility(View.VISIBLE);
                        thick_num = Double.parseDouble(thickness.getText().toString());
                        if (thick_num >= 3 && thick_num <= 6.25) {
                            groove.setText(groove_types[0]);
                        } else if (thick_num >= 4.7 && thick_num <= 12) {
                            groove.setText(groove_types[1]);
                        } else if (thick_num > 12&&thick_num<50) {
                            groove.setText(groove_types[2]);
                        } else if (thick_num >= 6.25 && thick_num <= 19) {
                            groove.setText(groove_types[3]);
                        } else if (thick_num > 19&&thick_num<50) {
                            groove.setText(groove_types[4]);
                        }
                        else{
                            thickness.setError("Thickness interval from 3 to 50 mm");

                        }
                    }
                }
                return false;
            }
        });
        List<String> material1;
        material1 = new ArrayList<String>();
        material1.add("ST37");
        material1.add("ST44");
        material1.add("ST52");
        ArrayAdapter<String> material1_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, material1);
        material1_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material1_spinner.setAdapter(material1_Adapter);

        List<String> material2;
        material2 = new ArrayList<String>();
        material2.add("ST37");
        material2.add("ST44");
        material2.add("ST52");
        ArrayAdapter<String> material2_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, material2);
        material2_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material2_spinner.setAdapter(material2_Adapter);

        List<String> position;
        position = new ArrayList<String>();
        position.add("1G - Flat Groove");
        position.add("2G - Horizontal Groove");
        position.add("3G - Vertical Uphill");
        position.add("3G - vertical Downhill");
        position.add("4G - Overhead Groove");
        ArrayAdapter<String> position_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, position);
        position_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position_spinner.setAdapter(position_Adapter);

        position_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position_index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                position_index = -1;
            }
        });

        List<String> area_condition;
        area_condition = new ArrayList<String>();
        area_condition.add("Open air");
        area_condition.add("Closed air");
        ArrayAdapter<String> area_condition_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, area_condition);
        area_condition_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_condition_spinner.setAdapter(area_condition_Adapter);

        area_condition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area_condition_index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                area_condition_index = -1;

            }
        });

        List<String> welding_method;
        welding_method = new ArrayList<String>();
        welding_method.add("Manual");
        welding_method.add("Semi-automatic");
        welding_method.add("Automatic");
        ArrayAdapter<String> welding_method_Adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, welding_method);
        welding_method_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        welding_method_spinner.setAdapter(welding_method_Adapter);

        welding_method_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                welding_method_index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                welding_method_index = -1;
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            boolean smaw, gmaw, gtaw, fcaw, saw;

            @Override
            public void onClick(View view) {
                Log.d("items", position_index + "\t" + area_condition_index + "\t" + welding_method_index);
//                View selectedView = position_spinner.getSelectedView();
//                if (selectedView != null && selectedView instanceof TextView) {
//                    TextView selectedTextView = (TextView) selectedView;
//                    if (position_index ==-1) {
//                        selectedTextView.setError("Select item");
//                    }
//                    else {
//                        selectedTextView.setError(null);
//                    }
//                }


                if (thickness.getText().toString().isEmpty())
                    thickness.setError("this field is empty");
                else {


                    if (welding_method_index == -1 && area_condition_index == -1) {
                        smaw = true;
                        gmaw = true;
                        gtaw = true;
                        fcaw = true;
                        if (thick_num >= 10 && position_index == 0)
                            saw = true;
                    }
                    if (thick_num >= 10 && position_index == 0 && welding_method_index == 2)
                        saw = true;
                    else
                        saw=false;
                    if (welding_method_index == 1 && area_condition_index == 1)
                        gmaw = true;
                    else
                        gmaw=false;
                    if (welding_method_index == 0 && area_condition_index == 1)
                        gtaw = true;
                    else
                        gtaw=false;
                    if (welding_method_index == 1)
                        fcaw = true;
                    else
                        fcaw=false;
                    if (welding_method_index == 0)
                        smaw = true;
                    else
                        smaw=false;



                    Log.d("bool data", thick_num + "\t" + position_index + "\t" + welding_method_index + "\t" + area_condition_index);
                    Log.d("bool", saw + "\t" + gmaw + "\t" + gtaw + "\t" + fcaw + "\t" + smaw);

                    Intent intent = new Intent(ParametersActivity.this, ProcessActivity.class);
                    intent.putExtra("saw", saw);
                    intent.putExtra("gmaw", gmaw);
                    intent.putExtra("gtaw", gtaw);
                    intent.putExtra("fcaw", fcaw);
                    intent.putExtra("smaw", smaw);
                    startActivity(intent);
                }
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

