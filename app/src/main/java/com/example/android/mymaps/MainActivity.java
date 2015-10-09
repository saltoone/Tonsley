package com.example.android.mymaps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity
    implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    int i=1;
    String posttomain="";
    String post2="";
    String post1="";
    String post3="";
    String StudentFan="";
    String StudentFan1="";
    TextView tex1,tex2,tex3;
    Bundle savedInstanceStat;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    GoogleMap mMap;
    private static final double
            FlinUni_LAT = -35.0198966,
            FlinUni_LNG =138.5741253;


    private GoogleApiClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOK()) {
            setContentView(R.layout.activity_map);

            if (initMap()) {
                gotoLocation(FlinUni_LAT, FlinUni_LNG, 15);

                mLocationClient = new GoogleApiClient.Builder(this)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .build();

                mLocationClient.connect();

            } else {
                Toast.makeText(this, "Map not connected!", Toast.LENGTH_SHORT).show();
            }

        } else {
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public boolean servicesOK() {

        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog =
                    GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to mapping service", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }
        return (mMap != null);
    }

    private void gotoLocation(double lat, double lng, float zoom) {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.moveCamera(update);
    }

	private void hideSoftKeyboard(View v) {
		InputMethodManager imm =
                (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

    public void sending(MenuItem item){

        Intent shareintent = new Intent(Intent.ACTION_SEND);
        shareintent.setType("text/plain");
        String sendTo = "Co-HAB Tonsley, Level 1, Mitsubishi Administration Building, Clovelly Park 1284 South Road, Adelaide SA 5042";
        shareintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "The Location Of Tonsely");
        shareintent.putExtra(android.content.Intent.EXTRA_TEXT, sendTo);
        // startActivity(Intent.createChooser(shareintent, "Share via"));
        startActivity(Intent.createChooser(shareintent, "Share via"));

    }
    public void gotomapfromm(MenuItem item) {
        setContentView(R.layout.activity_main22);

    }
    public void puttxt (MenuItem item) {
        setContentView(R.layout.sendmass);
    }
    public void gotomap(View view) {

        if (servicesOK()) {
            setContentView(R.layout.activity_map);
        }else
            setContentView(R.layout.activity_main);
    }
    public void login(View view) {
        setContentView(R.layout.activity_main2);

    }
    public void puttext(View v) {


        EditText tex = (EditText)findViewById(R.id.editText3);
        //EditText tex11 = (EditText)findViewById(R.id.editText2);
        posttomain =tex.getText().toString();
        //StudentFan =tex11.getText().toString();
        Toast.makeText(this, "Posted ..", Toast.LENGTH_SHORT).show();
        //texpost1.setText(posttomain);
        // Intent i1 = new Intent(getBaseContext(), MainActivity.class);
        setContentView(R.layout.activity_main22);
         tex1 = (TextView) findViewById(R.id.textView1);
         tex2 = (TextView) findViewById(R.id.textView2);
         tex3 = (TextView) findViewById(R.id.textView3);


        if (i == 1) {

            tex1.setText("Message "+i+":\n\n"+posttomain);
            post1="Message 1:\n\n"+posttomain;
            // tex2.setText(post2);
            tex2.setText(post2);
            tex3.setText(post3);
            //tex3.setText(post3);
            i++;
        } else if (i == 2) {
            tex1.setText(post1);
            tex2.setText("Message "+i+":\n\n" + posttomain);
            post2="Message 2:\n\n"+posttomain;
            tex3.setText("Message "+i+":\n\n"+post3);
            i++;
        } else if (i == 3) {
            tex1.setText(post1);
            tex2.setText(post2);
            tex3.setText("Message "+i+":\n\n"+ posttomain);
            post3="Message 3:\n\n"+posttomain;

            i = 1;
        }


    }
    public void gotoAnnon(View view) {
        setContentView(R.layout.sendmass);

    }
    public void Post (View view) {
        setContentView(R.layout.sendmass);

    }

    public void loginfun(MenuItem item) {
        setContentView(R.layout.activity_main2);

    }
    public void gotomain(MenuItem item) {
        setContentView(R.layout.activity_main);

    }
    public void gotomain1(View v) {

        EditText fan = (EditText) findViewById(R.id.editText2);
        StudentFan = fan.getText().toString();
        Toast.makeText(this, "Welcome .. " + StudentFan + "", Toast.LENGTH_SHORT).show();
        StudentFan1 = "Hi .. "+StudentFan;

        //newFan(StudentFan);
        setContentView(R.layout.activity_main);


    }
    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
