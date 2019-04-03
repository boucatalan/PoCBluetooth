package com.example.david.pocbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class MasterActivity extends AppCompatActivity {

    private final int request_code_for_enabling_bt = 1;
    private Button btnRed, btnGreen, btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BluetoothSender( 1).run();
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BluetoothSender( 2).run();
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BluetoothSender( 3).run();
            }
        });
    }

    public class BluetoothSender extends Thread {
        private int requestCode;

        public BluetoothSender(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void run() {
            try {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, request_code_for_enabling_bt);
                }

                Set<BluetoothDevice> DispositiusConectats = bluetoothAdapter.getBondedDevices();

                for(BluetoothDevice Dispositiu : DispositiusConectats) {
                    BluetoothSocket socket = Dispositiu.createRfcommSocketToServiceRecord(UUID.fromString("UUIDTEST"));

                    DataOutputStream DIS = new DataOutputStream(socket.getOutputStream());
                    DIS.writeInt(requestCode);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
