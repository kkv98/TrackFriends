package com.kkv.friendtracker.trackfriends;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button b1,b2;
Intent i;
private int a;
    public static final int RequestPermissionCode = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.currentloc);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.friendsloc);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.currentloc:
                a=R.id.currentloc;
                if(CheckingPermissionIsEnabledOrNot())
                {
                    i=new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(i);
                }
                else {
                    RequestMultiplePermission();

                }

                break;
            case R.id.friendsloc:
                a=R.id.friendsloc;
                if(CheckingPermissionIsEnabledOrNot())
                {
                i=new Intent(MainActivity.this,Friendsignin.class);
                startActivity(i);
                }
                else {
                    RequestMultiplePermission();

                }

                break;
        }
    }
    private void RequestMultiplePermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE, INTERNET, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION
                }, RequestPermissionCode);
    }
    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_WIFI_STATE);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ForthPermissionResult == PackageManager.PERMISSION_GRANTED  &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean WifiStatePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean NetworkStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean InternetPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean AccessCoarseLocPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean AccessFineLocPermission = grantResults[4] == PackageManager.PERMISSION_GRANTED;


                    if (WifiStatePermission && NetworkStatePermission && InternetPermission && AccessCoarseLocPermission && AccessFineLocPermission) {
                        switch (a) {
                            case R.id.currentloc:
                                i = new Intent(MainActivity.this, MapsActivity.class);
                                startActivity(i);
                                break;
                            case R.id.friendsloc:
                                i = new Intent(MainActivity.this, Friendsignin.class);
                                startActivity(i);
                                break;
                        }
                    } else {
                        AlertDialog.Builder a=new AlertDialog.Builder(this);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        RequestMultiplePermission();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        Toast.makeText(MainActivity.this,"Sorry!",Toast.LENGTH_SHORT).show();
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                        break;
                                }
                            }
                        };
                        a.setTitle("All Permission Needed");
                        a.setMessage("Please Grant All Permission Press Yes to continue");
                        a.setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();


                    }
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
