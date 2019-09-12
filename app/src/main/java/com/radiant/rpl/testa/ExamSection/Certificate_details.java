package com.radiant.rpl.testa.ExamSection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.Common.CommonUtils;
import com.radiant.rpl.testa.Initials.MyNetwork;
import com.radiant.rpl.testa.Registration.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Certificate_details extends AppCompatActivity {
TextView tv;
    private String msg;
    private String pathh;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    private String userid;
    ClickableSpan clickableSpan;
    private android.app.AlertDialog pd;
    String urlEncoded;
    URL sourceUrl;
    Button downloadbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_details);
        tv=findViewById(R.id.tvv1);
        downloadbutton=findViewById(R.id.download_button);
        pd = new SpotsDialog(Certificate_details.this, R.style.Custom);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("userid")) {
            userid=sharedpreferences.getString("userid", "");
        }

        download();


        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathh));
                startActivity(browserIntent);
            }
        });
        //Set drawable programmatically
       /* tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (tv.getRight() - tv.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Toast.makeText(getApplicationContext(),"drawabvle right clicked",Toast.LENGTH_LONG).show();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathh));
                        startActivity(browserIntent);
                        return true;
                    }
                }
                return false;
            }
        });*/

        clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pathh));
                startActivity(browserIntent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
            }
        };


    }

    private void download() {

        pd.show();
        String serverURL = CommonUtils.url+"get_certificate.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status = jobj.getString("status");


                    if (status.equals("1")) {
                        msg = jobj.getString("msg");
                        pathh = jobj.getString("certificate_path");

                        pathh = pathh.replaceAll(" ", "%20");
                        try {
                            sourceUrl = new URL(pathh);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }


                        urlEncoded=encode(pathh);
                        System.out.println("encoded url is"+pathh);


                        if (msg.equals("Has certificate")){
                            SpannableString string = new SpannableString("Your Certificate is ready.Download it now.");
                            string.setSpan(clickableSpan, 26, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            //tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_download, 0);

                            tv.setText(R.string.crt_msg);
                            downloadbutton.setVisibility(View.VISIBLE);
                            tv.setMovementMethod(LinkMovementMethod.getInstance());
                        }
                        else if(msg.equals("No certificate")){
                            tv.setText(R.string.crt_upload_soon);
                        }


                        //Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    } else if (status.equals("0")){
                        tv.setText("No record found");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pd.isShowing()) {
                    pd.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Encountered an Error.Have patience. We will be back soon.", Toast.LENGTH_LONG).show();
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
                map.put("user_name",userid);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    public static String encode (String s){
        try {
            s= URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("exception caught");
            e.printStackTrace();
        }
        return s;
    }











}
