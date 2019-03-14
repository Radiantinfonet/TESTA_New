package com.radiant.rpl.testa;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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
import com.radiant.rpl.testa.ExamSection.Update_profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Welcome_page extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        progressDialog = new SpotsDialog(Welcome_page.this, R.style.Custom);
        blink();
        session = new SessionManager();
        textView2=findViewById(R.id.textView2);
        ttv=findViewById(R.id.textView);
        logoutt=findViewById(R.id.logouttext);
        alertmessage=findViewById(R.id.textView3);
        geturl= Start_Registration.getURL();
        gettestingurl=Start_Registration.getTestingURL();
        enableButton = (SwipeButton) findViewById(R.id.swipe_btn);
        enableButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
            }
        });
        enableButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                Intent ii=new Intent(Welcome_page.this, StudenAtten.class);
                startActivity(ii);

            }
        });

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("Name")) {
            textView2.setText(sharedpreferences.getString("Name", ""));
        }

        if (sharedpreferences.contains("userid")) {
            userid=sharedpreferences.getString("userid", "");
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


    @Override
    protected void onStart() {
        super.onStart();
        Check_Profille();
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
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/check_student_profile.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("ddd"+response);
                    String status= jobj.getString("status");
                    String message= jobj.getString("msg");
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
}



