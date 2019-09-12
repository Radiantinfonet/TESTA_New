package com.radiant.rpl.testa.Initials;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.radiant.rpl.testa.Common.CommonUtils;
import com.radiant.rpl.testa.ExamSection.Certificate_details;
import com.radiant.rpl.testa.ExamSection.ContactUsActivity;
import com.radiant.rpl.testa.ExamSection.Technical_Activity;
import com.radiant.rpl.testa.ExamSection.Update_profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Welcome_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SessionManager session;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    TextView textView2,logoutt,alertmessage;
    TextView ttv;
    boolean active;
    String geturl,gettestingurl;
    private android.app.AlertDialog progressDialog;
    String userid;
    SwipeButton enableButton;
    String img;
    ByteArrayOutputStream baos;
    byte[] imageBytes;
    CircleImageView imgview;
    private String student_type;
    String exam_statuss;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private TextView headertext;
    int itemid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        progressDialog = new SpotsDialog(Welcome_page.this, R.style.Custom);
        //blink();
        session = new SessionManager();
        textView2=findViewById(R.id.textView2);
        ttv=findViewById(R.id.textView);
        logoutt=findViewById(R.id.logouttext);
        alertmessage=findViewById(R.id.textView3);
        imgview=findViewById(R.id.set_photo);
        geturl= Start_Registration.getURL();
        gettestingurl=Start_Registration.getTestingURL();
        enableButton = (SwipeButton) findViewById(R.id.swipe_btn);



        mDrawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Home");

        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        headertext = header.findViewById(R.id.textview2);





        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("exam_status")) {
            exam_statuss=sharedpreferences.getString("exam_status", "");
        }

        baos = new ByteArrayOutputStream();
        enableButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
            }
        });
        enableButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                if (exam_statuss.equals("Attempted")){
                    if (userid.equals("aman")) {
                        Intent in = new Intent(Welcome_page.this, StudenAtten.class);
                        startActivity(in);
                    }
                    else{
                    AlertDialog alertDialog = new AlertDialog.Builder(Welcome_page.this)
                            .setMessage("You have already attempted the exam.")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).show();
                }
                }
                else {

                    Intent ii = new Intent(Welcome_page.this, StudenAtten.class);
                    startActivity(ii);
                }
            }
        });

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("Name")) {
            textView2.setText(sharedpreferences.getString("Name", ""));
        }

        if (sharedpreferences.contains("userid")) {
            userid=sharedpreferences.getString("userid", "");
        }
        if (sharedpreferences.contains("student_type")){
            student_type=sharedpreferences.getString("student_type","");
        }


        //logout Code
        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Welcome_page.this, Update_profile.class);
                startActivity(in);
            }
        });
    }


    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToogle.onOptionsItemSelected(item)) {
            return true;
        }
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

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("rerr"+student_type);
        if (student_type.equals("Regular")){
            alertmessage.setText("Mark Your Attendance on the next page and kindly follow the instructions to start the Assessment.");
            enableButton.setVisibility(View.VISIBLE);
            logoutt.setVisibility(View.GONE);
        }
        else {
            Check_Profille();
        }
    }



    private void blink() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = (TextView) findViewById(R.id.textView2);
                        if (txt.getVisibility() == View.VISIBLE) {
                            txt.setVisibility(View.INVISIBLE);
                        } else {
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }


    private void Check_Profille() {
        progressDialog.show();
        String serverURL = CommonUtils.url+"check_student_profile.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("ddd"+response);
                    String status= jobj.getString("status");
                    String message= jobj.getString("msg");
                    img=jobj.getString("student_img");
                    imageBytes = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    imgview.setImageBitmap(decodedImage);

                    if (status.equals("1")) {

                        alertmessage.setText("Mark Your Attendance on the next page and kindly follow the instructions to start the Assessment.");
                        enableButton.setVisibility(View.VISIBLE);
                        logoutt.setVisibility(View.GONE);
                    }else if (status.equals("0")){
                        alertmessage.setText("Your Profile is incomplete. Please update the profile to take Assessment");
                        logoutt.setVisibility(View.VISIBLE);
                        enableButton.setVisibility(View.GONE);
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
                Toast.makeText(getApplicationContext(), "Error: Please try again Later", Toast.LENGTH_LONG).show();
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
                map.put("user_name", userid);
                map.put("student_type",student_type);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        itemid = item.getItemId();
        switch (itemid) {


                case R.id.logout:
                    //prefs.removeAll();
                    Intent j = new Intent(Welcome_page.this, Certificate_details.class);
                    startActivity(j);
                    finish();
                    break;




            default:
                break;

        }



        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}



