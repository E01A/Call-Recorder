package com.sam.callrecorder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> permissions;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    int accessStorage = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE );
                    int accessContact = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
                    int accessCall = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE );
                    int accessAudio = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO );

                    permissions = new ArrayList();

                    if (accessStorage == PackageManager.PERMISSION_DENIED) {
                        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                    if (accessContact == PackageManager.PERMISSION_DENIED) {
                        permissions.add(Manifest.permission.READ_CONTACTS);
                    }
                    if (accessCall == PackageManager.PERMISSION_DENIED) {
                        permissions.add(Manifest.permission.CALL_PHONE);
                    }
                    if (accessAudio == PackageManager.PERMISSION_DENIED) {
                        permissions.add(Manifest.permission.RECORD_AUDIO);
                    }

                    if(permissions.size() > 0) {
                        ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(new String[permissions.size()]), 1);
                    }else{

                        Toast.makeText(MainActivity.this,"Call Recorder Started",Toast.LENGTH_LONG).show();
                    }
                }

                Intent serviceIntent = new Intent(MainActivity.this, CallRecorder.class);
                startService(serviceIntent);


            }

         });
    }
}
