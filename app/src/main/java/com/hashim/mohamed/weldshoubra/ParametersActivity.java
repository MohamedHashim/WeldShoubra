package com.hashim.mohamed.weldshoubra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 17/02/17.
 */
public class ParametersActivity extends Activity {

    SearchableSpinner material1_spinner, material2_spinner, position_spinner, joint_type_spinner, thickness_spinner;
    TextView grove;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        material1_spinner = (SearchableSpinner) findViewById(R.id.material1_spinner);
        material2_spinner = (SearchableSpinner) findViewById(R.id.material2_spinner);
        position_spinner = (SearchableSpinner) findViewById(R.id.position_spinner);
        joint_type_spinner = (SearchableSpinner) findViewById(R.id.joint_type_spinner);
        thickness_spinner = (SearchableSpinner) findViewById(R.id.thickness_spinner);
        grove = (TextView) findViewById(R.id.grove);
        next = (Button) findViewById(R.id.next);
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
}
