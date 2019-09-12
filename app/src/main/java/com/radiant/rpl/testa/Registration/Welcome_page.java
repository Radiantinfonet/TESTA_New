package com.radiant.rpl.testa.Registration;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Welcome_page extends AppCompatActivity {
    SessionManager session;
    SharedPreferences sharedpreferences,language_prefs;
    public static final String mypreference = "mypref";
    TextView textView2,logoutt,alertmessage;
    TextView ttv;
    boolean active;
    String geturl,gettestingurl;
    private android.app.AlertDialog progressDialog;
    String userid,selected_language;;
    SwipeButton enableButton;
    String img,msg,msg1,msg2,msg3;
    ByteArrayOutputStream baos;
    byte[] imageBytes;
    CircleImageView imgview;



      //changed


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }






    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
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
        baos = new ByteArrayOutputStream();




        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }

        Resources resources = getResources();

        msg = resources.getString(R.string.Global_ErrorResponse_Message);
        msg1 = resources.getString(R.string.Mark_Your_Atten_Welcome);
        msg2 = resources.getString(R.string.profile_incomp);

      //changed
        Paper.init(this);
        String language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        }


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





        if (  selected_language!=null && selected_language.equals("1")) {

            Paper.book().write("language", "hi");
            updateView((String) Paper.book().read("language"));
        }
        else if(selected_language!=null && selected_language.equals("0")) {

            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

            return super.onOptionsItemSelected(item);

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
                    img=jobj.getString("student_img");
                    imageBytes = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    imgview.setImageBitmap(decodedImage);

                    if (status.equals("1")) {
                        alertmessage.setText(msg1);
                        enableButton.setVisibility(View.VISIBLE);
                        logoutt.setVisibility(View.GONE);
                    }else if (status.equals("0")){
                        alertmessage.setText(msg2);
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
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();
        alertmessage.setText(resources.getString(R.string.Mark_Your_Atten_Welcome));
        ttv.setText(resources.getString(R.string.welcome_back));
        enableButton.setText(resources.getString(R.string.Swipetobegin));
        msg = resources.getString(R.string.Global_ErrorResponse_Message);
        msg1 = resources.getString(R.string.Mark_Your_Atten_Welcome);
        msg2 = resources.getString(R.string.profile_incomp);

        setTitle(resources.getString(R.string.home));



    }

}



