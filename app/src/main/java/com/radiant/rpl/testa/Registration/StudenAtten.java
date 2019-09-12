package com.radiant.rpl.testa.Registration;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.radiant.rpl.testa.ExamSection.ContactUsActivity;
import com.radiant.rpl.testa.ExamSection.Technical_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class StudenAtten extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
   GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{
    ImageView img;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    TextView tv;
    List<Address> addresses;
    Button b1;
    public static final String mypreference = "mypref";
    GoogleApiClient mGoogleApiClient;
    private Location location;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissions = new ArrayList<>();
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    String aaa,batchidd,studentidd;
    String encoded;
    SharedPreferences sharedPreferences,language_prefs;
    TextView nameid,addressid,batchid,student_name,jobrole_id,Address_id;
    private android.app.AlertDialog progressDialog;
    String geturl,gettestingurl;
    LinearLayout linearlayout;
    Button bb;
    String uid, msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,msg8,msg9,msg10,msg11,msg12;
    String stringLatitude,stringLongitude,selected_language;;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_atten);


        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }




        progressDialog = new SpotsDialog(StudenAtten.this, R.style.Custom);
        img = findViewById(R.id.assessorpic);
        tv = findViewById(R.id.locationn);
        b1 = findViewById(R.id.attendencesubmit);
        bb=findViewById(R.id.uploadphoto);
        nameid=findViewById(R.id.nameid);
        addressid=findViewById(R.id.addressid);
        batchid=findViewById(R.id.jobroleid);
        geturl= Start_Registration.getURL();
        linearlayout = findViewById(R.id.linearlayout);
        gettestingurl=Start_Registration.getTestingURL();
        student_name = findViewById(R.id.student_name);
        jobrole_id = findViewById(R.id.jobrole_id);
        Address_id = findViewById(R.id.address_iddd);


        Resources resources = getResources();
        msg = resources.getString(R.string.Camera_Warning);
        msg1 = resources.getString(R.string.Google_Play_Req);
        msg2 = resources.getString(R.string.Location_Req);
        msg3 = resources.getString(R.string.Camera_Denied);
        msg4 = resources.getString(R.string.Internet_connectivity);
        msg5 = resources.getString(R.string.Camera_granted);
        msg6 = resources.getString(R.string.Camera_Message);
        msg7 = resources.getString(R.string.Global_ErrorResponse_Message);
        msg8 = resources.getString(R.string.Attend_ERR_msg);
        msg9 = resources.getString(R.string.Exit_Alert_Message);
        msg10 = resources.getString(R.string.amway_Disablity_yes);
        msg11 = resources.getString(R.string.No);



        //changed
        Paper.init(this);
        String language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        }


        if (  selected_language!=null && selected_language.equals("1")) {

            Paper.book().write("language", "hi");
            updateView((String) Paper.book().read("language"));
            System.out.println("ideide" + language_prefs.getString("hindi", ""));
        }
        else if(selected_language!=null && selected_language.equals("0")) {

            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
            System.out.println("ideide" + language_prefs.getString("english", ""));


        }




        //getLocation();
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Name")) {
            nameid.setText(sharedPreferences.getString("Name", ""));
        }
        if (sharedPreferences.contains("address")){
            addressid.setText(sharedPreferences.getString("address",""));
        }
        if (sharedPreferences.contains("batchid")){
            batchidd=sharedPreferences.getString("batchid","");
        }
        if (sharedPreferences.contains("jobrole")){
            batchid.setText(sharedPreferences.getString("jobrole",""));
        }else{
            batchid.setText("NA");
        }
        if (sharedPreferences.contains("userid")){
            uid=sharedPreferences.getString("userid","");
            studentidd=sharedPreferences.getString("userid","");
        }


        try{
            bb.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
                            cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                            cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }

                    }
                    else {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
                        cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                        cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }

            });}catch (Exception e){
            e.printStackTrace();
        }

        try{
        img.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
                    cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                    cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
                else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
                    cameraIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                    cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }

        });}catch (Exception e){
            e.printStackTrace();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if (encoded!=null) {
                   AssessorAttendance();
               }else {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();

           }
            }
        });
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = permissionsToRequest(permissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        }

        // we build google api client
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (  selected_language!=null && selected_language.equals("1")) {
            getMenuInflater().inflate(R.menu.main1, menu);

        }
        else
            getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_menu:
                Intent i = new Intent(getApplicationContext(), ContactUsActivity.class);
                startActivity(i);
                //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                return true;
            case R.id.search_menu:
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.skillassessment.org/ssc/FAQ.html"));
                startActivity(viewIntent);

                return true;




            case R.id.save_technical:
                Intent j = new Intent(getApplicationContext(), Technical_Activity.class);
                startActivity(j);
                return true;

           }


        if(item.getItemId() == R.id.language_en)
        {
            Paper.book().write("language","en");
            updateView((String)Paper.book().read("language"));
        }


        if(item.getItemId() == R.id.language_hi)
        {
            Paper.book().write("language","hi");
            updateView((String)Paper.book().read("language"));
        }




        // default:
        return super.onOptionsItemSelected(item);







    }


    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }



    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (googleApiClient!=null){
        buildGoogleApiClient();
        }
        if (!checkPlayServices()) {
            Toast.makeText(this,msg1,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        // stop location updates
        if (googleApiClient != null  &&  googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, (com.google.android.gms.location.LocationListener) this);
            googleApiClient.disconnect();
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST);
            } else {
                finish();
            }

            return false;
        }

        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Geocoder geocoder;
        geocoder = new Geocoder(StudenAtten.this);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Permissions ok, we get last location
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location != null) {
            try {
                stringLatitude= Double.toString(location.getLatitude());
                stringLongitude= Double.toString(location.getLongitude());

                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (addresses != null && addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String streett = addresses.get(0).getSubLocality();
                    String country = addresses.get(0).getCountryName();
                    aaa = address + "," + streett + "," + city + "," + country;
                    tv.setText(aaa);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            buildAlertMessageNoGps();
        }
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(50000);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, msg2, Toast.LENGTH_SHORT).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {

        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,msg5, Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this,msg3, Toast.LENGTH_LONG).show();
            }

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                if(data.getExtras()==null || (data.getExtras().get("data")==null ||  !(data.getExtras().get("data") instanceof Bitmap))){
                    //todo - show error
                    Toast.makeText(getApplicationContext(),msg6,Toast.LENGTH_LONG).show();
                    return;
                }
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //img.setImageBitmap(photo);
            int currentBitmapWidth = photo.getWidth();
            int currentBitmapHeight = photo.getHeight();
            int ivWidth = img.getWidth();
            int ivHeight = img.getHeight();
            int newWidth = ivWidth;
            int newHeight = (int) Math.floor((double) currentBitmapHeight *( (double) ivWidth / (double) currentBitmapWidth));
            Bitmap newbitMap = Bitmap.createScaledBitmap(photo, newWidth, newHeight, true);
            img.setImageBitmap(newbitMap);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void buildAlertMessageNoGps() {
         GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .addApi(LocationServices.API).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            startLocationUpdates();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the result
                                // in onActivityResult().
                                status.startResolutionForResult(StudenAtten.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            });
        }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }


    private void saveLog(final String fnamee, final String ip, final String activity, final String lat, final String longi,final String cmpid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/save_logs.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), msg7, Toast.LENGTH_LONG).show();

            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("username",fnamee);
                map.put("ip",ip);
                map.put("company_id",cmpid);
                map.put("activity",activity);
                map.put("lat",lat);
                map.put("longi",longi);

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void AssessorAttendance() {
        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/save_student_attendance.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("ddd"+response);
                    String status= jobj.getString("status");
                    String message= jobj.getString("msg");
                      if (status.equals("1")) {
                          SharedPreferences.Editor editor = sharedPreferences.edit();
                          editor.putString("lat", stringLatitude);
                          editor.putString("long", stringLongitude);
                          editor.commit();
                          saveLog(uid,"","Instructions",stringLatitude,stringLongitude,"");
                          Intent ii = new Intent(StudenAtten.this, Testinstruction.class);
                          startActivity(ii);
                      }else {
                          Toast.makeText(getApplicationContext(),msg8,Toast.LENGTH_LONG).show();
                      }
                    }
                    catch (JSONException e) {
                    e.printStackTrace();
                }


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Snackbar snackbar = Snackbar
                        .make(linearlayout,msg4, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("student_id", studentidd);
                map.put("student_image",encoded);
                map.put("location",tv.getText().toString());
                map.put("attendance","PRESENT");
                map.put("batch_id",batchidd);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage(msg9)
                .setPositiveButton(msg10, new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton(msg11, new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }





    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();
        b1.setText(resources.getString(R.string.proceed));
        bb.setText(resources.getString(R.string.upload_your_photo));
        student_name.setText(resources.getString(R.string.student_name));
        jobrole_id.setText(resources.getString(R.string.jobrole));
        Address_id.setText(resources.getString(R.string.address));
        msg = resources.getString(R.string.Camera_Warning);
        msg1 = resources.getString(R.string.Google_Play_Req);
        msg2 = resources.getString(R.string.Location_Req);
        msg3 = resources.getString(R.string.Camera_Denied);
        msg4 = resources.getString(R.string.Internet_connectivity);
        msg5 = resources.getString(R.string.Camera_granted);
        msg6 = resources.getString(R.string.Camera_Message);
        msg7 = resources.getString(R.string.Global_ErrorResponse_Message);
        msg8 = resources.getString(R.string.Attend_ERR_msg);
        msg9 = resources.getString(R.string.Exit_Alert_Message);
        msg10 = resources.getString(R.string.amway_Disablity_yes);
        msg11 = resources.getString(R.string.No);

        setTitle(resources.getString(R.string.Attendence));





    }










}




