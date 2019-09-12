package com.radiant.rpl.testa.Registration;


import android.Manifest;
import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SignInAct extends AppCompatActivity {
    private ImageView bookIconImageView;
    private TextView bookITextView,skiptextview,welcome;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    Button loginsubmit;
    EditText username,passowrd;
    String uname,pass,name,rascibatchid,mobile,exam_status,address,
            userid,batchid,jobrole,status,selected_language,value,stringLatitude,stringLongitude
            ,geturl,gettestingurl,toast_msg1,toast_msg2,toast_msg3,snack_msg1,snack_msg2,toast_msg4;
    private Location location;
    SessionManager sessionManager;
    SharedPreferences sharedpreferences,language_prefs;
    final String mypreference = "mypref";

    private android.app.AlertDialog progressDialog;


    GPSTracker gps;
    double lati,longi;

    DbAutoSave dbAutoSave;
    private boolean TimerRunning;
    TextInputLayout username1, password1;




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        geturl = Start_Registration.getURL();
        gettestingurl = Start_Registration.getTestingURL();
        progressDialog = new SpotsDialog(SignInAct.this, R.style.Custom);
        sessionManager = new SessionManager();
        dbAutoSave = new DbAutoSave(getApplicationContext());
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        initViews();

        Bundle extras = getIntent().getExtras();


        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }



        //changed
        Paper.init(this);
        String language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        }

        Resources resources = getResources();
        toast_msg1 =  resources.getString(R.string.Wrong_credentials);
        toast_msg2 =  resources.getString(R.string.Unable_login);
        toast_msg3 =  resources.getString(R.string.Saving_Err);
        snack_msg1 =  resources.getString(R.string.Wrong_login);
        snack_msg2 =  resources.getString(R.string.Check_Internrt);

        toast_msg4 =  resources.getString(R.string.SignIn_Message);







        if (extras != null) {
            value = extras.getString("key");
            username.setText(value);
            passowrd.setText(value);
        }


        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                startAnimation();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        loginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().equals("") | passowrd.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),toast_msg4, Toast.LENGTH_LONG).show();
                } else {
                    sendDataServer();
                }
            }
        });
        skiptextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(SignInAct.this, Start_Registration.class);
                startActivity(ii);
            }
        });











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






    }

    public void getlocationdata(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignInAct.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            //  Toast.makeText(mContext,"You need have granted permission",Toast.LENGTH_SHORT).show();

            gps = new GPSTracker(getApplicationContext(), SignInAct.this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                lati = gps.getLatitude();
                longi = gps.getLongitude();

            }
            else {

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getlocationdata();            }
        }, 20000);

    }

    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);



        username1 =findViewById(R.id.emailEditText1);
        password1 =findViewById(R.id.passwordEditText1);
        welcome = findViewById(R.id.WelcomeTextView);
        username=findViewById(R.id.emailEditText);
        passowrd=findViewById(R.id.passwordEditText);
        loginsubmit=findViewById(R.id.loginButton);
        skiptextview=findViewById(R.id.skipTextView);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }



    private void sendDataServer() {

        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/login.php";
        uname=username.getText().toString();
        pass= passowrd.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    status= jobj.getString("status");
                    exam_status=jobj.getString("exam_status");
                    if (status.equals("1")) {
                        if (exam_status.equals("Not Attempted")) {
                            JSONObject jsonObject = jobj.getJSONObject("student_details");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                name = jsonObject.getString("name");
                                batchid = jsonObject.getString("batch_id");
                                mobile = jsonObject.getString("mobile");
                                address = jsonObject.getString("address");
                                userid = jsonObject.getString("user_name");
                                rascibatchid = jsonObject.getString("batchid");
                                jobrole=jsonObject.getString("jobrole_name");
                                sessionManager.setPreferences(getApplicationContext(), "status", "1");
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("Name", name);
                                editor.putString("address", address);
                                editor.putString("batchid", batchid);
                                editor.putString("assessorid", mobile);
                                editor.putString("userid", userid);
                                editor.putString("jobrole",jobrole);
                                editor.apply();

                                if(userid.equals("aman")){
                                        Intent in=new Intent(SignInAct.this,StudenAtten.class);
                                    startActivity(in);
                                }
                            }

                            if (dbAutoSave.getstudentid("1") != null) {
                                String vv1 = dbAutoSave.getstudentid("1");
                                if (dbAutoSave.getstudentid("1").equals(userid)) {
                                    //do nothing
                                    SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.clear().commit();
                                    SharedPreferences prefss = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = prefss.edit();
                                    editor1.clear().commit();
                                    dbAutoSave.onDelete();
                                } else {
                                        SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.clear().commit();
                                    SharedPreferences prefss = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = prefss.edit();
                                    editor1.clear().commit();
                                        dbAutoSave.onDelete();
                                }
                            }
                            stringLatitude= Double.toString(lati);
                            stringLongitude= Double.toString(longi);
                            saveLog(userid,"","User Login",stringLatitude,stringLongitude,"");
                            Intent ii = new Intent(SignInAct.this, Welcome_page.class);
                            startActivity(ii);
                        }
                        else if (exam_status.equals("Attempted")) {
                            JSONObject jsonObject = jobj.getJSONObject("student_details");
                            for (int i = 0; i < jsonObject.length(); i++) {
                                name = jsonObject.getString("name");
                                batchid = jsonObject.getString("batch_id");
                                mobile = jsonObject.getString("mobile");
                                address = jsonObject.getString("address");
                                userid = jsonObject.getString("user_name");
                                jobrole=jsonObject.getString("jobrole_name");
                                rascibatchid = jsonObject.getString("batchid");
                                sessionManager.setPreferences(getApplicationContext(), "status", "1");
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("Name", name);
                                editor.putString("address", address);
                                editor.putString("batchid", batchid);
                                editor.putString("assessorid", mobile);
                                editor.putString("userid", userid);
                                editor.putString("jobrole",jobrole);
                                editor.apply();

                                stringLatitude= Double.toString(lati);
                                stringLongitude= Double.toString(longi);
                                /*Intent ii = new Intent(SignInAct.this, Welcome_page.class);
                                startActivity(ii);*/
                                if (userid.equals("aman")) {
                                    Intent in = new Intent(SignInAct.this, Welcome_page.class);
                                    startActivity(in);
                                }
                                else{
                                    AlertDialog alertDialog = new AlertDialog.Builder(SignInAct.this)
                                            .setMessage(getResources().getString(R.string.AlredyAttempt_Exam))
                                            .setCancelable(false)
                                            .setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            }).show();
                                    //alertDialog.show();
                                }
                            }
                            if (dbAutoSave.getstudentid("1") != null) {
                                String vv1 = dbAutoSave.getstudentid("1");
                                if (dbAutoSave.getstudentid("1").equals(userid)) {
                                    //do nothing
                                    SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.clear().commit();
                                    SharedPreferences prefss = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = prefss.edit();
                                    editor1.clear().commit();
                                    dbAutoSave.onDelete();
                                } else {
                                    SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.clear().commit();
                                    SharedPreferences prefss = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = prefss.edit();
                                    editor1.clear().commit();
                                    dbAutoSave.onDelete();
                                }
                            }

                        }



                    }

                    else if (status.equals("0")){
                        Toast.makeText(getApplicationContext(),toast_msg1,Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),toast_msg2,Toast.LENGTH_LONG).show();
                    }
                }

                catch (JSONException e) {
                    e.printStackTrace();

                    Snackbar snackbar = Snackbar
                            .make(rootView, snack_msg1, Snackbar.LENGTH_LONG);
                    snackbar.show();                }

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
                        .make(rootView, snack_msg2, Snackbar.LENGTH_LONG);
                snackbar.show();            }
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
                map.put("user_name", uname);
                map.put("password", pass);
                map.put("key_salt", "UmFkaWFudEluZm9uZXRTYWx0S2V5");
                map.put("app_version", "1.0");
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void saveLog(final String fnamee, final String ip, final String activity, final String   lat, final String longi,final String cmpid) {
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
                Toast.makeText(getApplicationContext(),toast_msg3, Toast.LENGTH_LONG).show();
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();

        username1.setHint(resources.getString(R.string.email));
        password1.setHint(resources.getString(R.string.password));
        welcome.setText(resources.getString(R.string.welcome_back));
        loginsubmit.setText(resources.getString(R.string.login));
        skiptextview.setText(resources.getString(R.string.sign_up1));
        toast_msg1 =  resources.getString(R.string.Wrong_credentials);
        toast_msg2 =  resources.getString(R.string.Unable_login);
        toast_msg3 =  resources.getString(R.string.Saving_Err);

        snack_msg1 =  resources.getString(R.string.Wrong_login);
        snack_msg2 =  resources.getString(R.string.Check_Internrt);
        toast_msg4 =  resources.getString(R.string.SignIn_Message);


    }



}
