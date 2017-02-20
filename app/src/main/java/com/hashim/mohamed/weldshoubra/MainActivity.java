package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button API_btn, ASME_btn, AWS_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        API_btn = (Button) findViewById(R.id.API_button);
        ASME_btn = (Button) findViewById(R.id.ASME_button);
        AWS_btn = (Button) findViewById(R.id.AWS_button);

        API_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                startActivity(intent);

            }
        });
        ASME_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                startActivity(intent);

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
}
