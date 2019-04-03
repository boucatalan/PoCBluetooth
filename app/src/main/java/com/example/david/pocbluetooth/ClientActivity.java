package com.example.david.pocbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.util.UUID;

public class ClientActivity extends AppCompatActivity {

    private Button colorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        colorView = findViewById(R.id.btnColor);

        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothServerSocket ServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("Test", UUID.fromString("UUIDTEST"));

            while (true) {
                BluetoothSocket socket = ServerSocket.accept();
                DataInputStream DIS = new DataInputStream(socket.getInputStream());

                switch (DIS.readInt()) {
                    case 1:
                        colorView.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        break;
                    case 2:
                        colorView.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        break;
                    case 3:
                        colorView.setBackgroundColor(getResources().getColor(R.color.colorRed));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
