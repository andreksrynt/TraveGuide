package skeletonteam.traveguide;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    // GPSTracker class
    GPSTracker gps;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    double latitude = -0.233333;
    double longitude = 130.51666699999998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton backButton = (ImageButton) findViewById(R.id.arrowback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tour.class);
                startActivity(intent);
            }
        });
        Button gmap = (Button) findViewById(R.id.gmap);
        gmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = "I'm Here!";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
        Button fab = (Button)findViewById(R.id.gps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create class object
                gps = new GPSTracker(MapsActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    LatLng my = new LatLng(latitude, longitude);
                    mGoogleMap.addMarker(new MarkerOptions().position(my)
                            .title("Current Location")
                            .snippet("this is yours")
                    );
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(my));
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
            }
        });
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final Button normal = (Button) findViewById(R.id.normal);
        final Button satellite = (Button) findViewById(R.id.satellite);
        final Button hybrid = (Button) findViewById(R.id.hybrid);
        final Button terrain = (Button) findViewById(R.id.terrain);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.normal:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        normal.setTextColor(Color.parseColor("#000000"));
                        satellite.setTextColor(Color.parseColor("#000000"));
                        hybrid.setTextColor(Color.parseColor("#000000"));
                        terrain.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.satellite:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        normal.setTextColor(Color.parseColor("#FFFFFF"));
                        satellite.setTextColor(Color.parseColor("#FFFFFF"));
                        hybrid.setTextColor(Color.parseColor("#FFFFFF"));
                        terrain.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case R.id.terrain:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        normal.setTextColor(Color.parseColor("#000000"));
                        satellite.setTextColor(Color.parseColor("#000000"));
                        hybrid.setTextColor(Color.parseColor("#000000"));
                        terrain.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.hybrid:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        normal.setTextColor(Color.parseColor("#FFFFFF"));
                        satellite.setTextColor(Color.parseColor("#FFFFFF"));
                        hybrid.setTextColor(Color.parseColor("#FFFFFF"));
                        terrain.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    default:
                        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(true);
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(true);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng rajae = new LatLng(latitude, longitude);
        mGoogleMap.addMarker(new MarkerOptions().position(rajae)
                .title("Raja Empat")
                .snippet("The most beauty place in Papua.")
        );
        mGoogleMap.getUiSettings().setMapToolbarEnabled(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(rajae));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }
}
