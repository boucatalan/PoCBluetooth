package com.example.david.pocbluetooth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RolActivity extends AppCompatActivity {

    private Button btnClient, btnMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);

        btnClient = findViewById(R.id.btnClient);
        btnMaster = findViewById(R.id.btnMaster);

        btnMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(), MasterActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(), ClientActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
