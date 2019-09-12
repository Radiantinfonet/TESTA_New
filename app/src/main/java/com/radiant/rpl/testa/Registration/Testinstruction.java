package com.radiant.rpl.testa.Registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.ExamSection.ContactUsActivity;
import com.radiant.rpl.testa.ExamSection.Technical_Activity;
import com.radiant.rpl.testa.ExamSection.TestQuestion;
import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Testinstruction extends AppCompatActivity {
    Button testinstructproceed;
    AlertDialog alertDialog;
    String language,languageid;
    HashMap<String,String> hm=new HashMap<>();
    CharSequence[] values;
    List<String> listItems = new ArrayList<String>();
    SharedPreferences ssp,sspp,language_prefs;
         String batchidddd;
    Bundle b;
    SharedPreferences.Editor editor;
    private android.app.AlertDialog progressDialog;
    String geturl,gettestingurl;
    LinearLayout pl;
    String stringlatitude3,stringlongitude3,stuidd,msg,msg1,msg2,msg3,selected_language,msg4;
    String exam_status;
    long theory_time;
    long practical_time;
    DbAutoSave dbAutoSave;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t22,t13;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testinstruction);
        testinstructproceed=findViewById(R.id.testinstructproceed);
        b=new Bundle();
        ssp=getSharedPreferences("mypreff",MODE_PRIVATE);
        sspp=getSharedPreferences("mypref",MODE_PRIVATE);
        geturl= Start_Registration.getURL();
        pl = findViewById(R.id.pl);
        dbAutoSave = new DbAutoSave(getApplicationContext());
        gettestingurl=Start_Registration.getTestingURL();
        progressDialog = new SpotsDialog(Testinstruction.this, R.style.Custom);
        batchidddd=sspp.getString("batchid","");
        stringlatitude3=sspp.getString("lat","");
        stringlongitude3=sspp.getString("long","");
        stuidd=sspp.getString("userid","");

        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }




        t1 = findViewById(R.id.tv1);
        t2 = findViewById(R.id.tv2);
        t22 = findViewById(R.id.tv22);
        t3 = findViewById(R.id.tv3);
        t4 = findViewById(R.id.tv4);
        t5 = findViewById(R.id.tv5);
        t6 = findViewById(R.id.tv6);
        t7 = findViewById(R.id.tv7);
        t8 = findViewById(R.id.tv8);
        t9 = findViewById(R.id.tv9);
        t10 = findViewById(R.id.tv10);
        t11 = findViewById(R.id.tv11);
        t12 = findViewById(R.id.tv12);
        t13  = findViewById(R.id.header);

        Resources resources = getResources();
        msg1  = resources.getString(R.string.Internet_connectivity);
        msg2 = resources.getString(R.string.Global_ErrorResponse_Message);
        msg3  = resources.getString(R.string.Internet_connectivity);
        msg = resources.getString(R.string.amway_Language_Err_Toast);
        msg4 = resources.getString(R.string.Choose_Language);







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

        testinstructproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExamLanguage(batchidddd);


            }
        });

        if (dbAutoSave.getstudentid("1") != null){
            dbAutoSave.updateStudentid(stuidd,"1");
        }else {
        dbAutoSave.insertStudentid(stuidd,"1"); }
    }

    public void showDialog(){
        values = listItems.toArray(new CharSequence[listItems.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(Testinstruction.this);
        builder.setTitle(msg4);
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                switch (i) {
                    case 0:
                        String aa = String.valueOf(values[i]);
                        String bb = hm.get(aa);
                        SharedPreferences.Editor editor = ssp.edit();
                        editor.putString("languagev", bb);
                        editor.putString("examstatus",exam_status);
                        editor.putLong("theorytime",theory_time);
                        editor.putLong("practicaltime",practical_time);
                        editor.apply();
                        editor.commit();
                        saveLog(stuidd,"","Start Exam",stringlatitude3,stringlongitude3,"");
                            Intent ii = new Intent(Testinstruction.this, TestQuestion.class);
                            startActivity(ii);

                        break;
                    case 1:
                        String cc= String.valueOf(values[i]);
                        String dd=hm.get(cc);
                        SharedPreferences.Editor editor1 = ssp.edit();
                        editor1.putString("languagev", dd);
                        editor1.putString("examstatus",exam_status);
                        editor1.putLong("theorytime",theory_time);
                        editor1.putLong("practicaltime",practical_time);
                        editor1.apply();
                        editor1.commit();
                        saveLog(stuidd,"",getResources().getString(R.string.Start_Exam1),stringlatitude3,stringlongitude3,"");
                        Intent iii = new Intent(Testinstruction.this, TestQuestion.class);
                        startActivity(iii);

                        break;
                }

                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();





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







                return super.onOptionsItemSelected(item);


    }


    //Exam Languages
    private void getExamLanguage(final String Sectorvalue) {
        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_batch_language.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    exam_status=jobj.getString("exam_status");
                    theory_time=jobj.getLong("theory_time");
                    practical_time=jobj.getLong("practical_time");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("language");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            languageid = c.getString("language_code");
                            language = c.getString("name");
                            hm.put(language, languageid);
                            if (listItems.size()<=1){
                            listItems.add(language);
                            }

                        }
                        showDialog();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
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
                        .make(pl,msg1, Snackbar.LENGTH_LONG);
                snackbar.show();
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
                map.put("batch_id",Sectorvalue);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
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
                Toast.makeText(getApplicationContext(), msg2, Toast.LENGTH_LONG).show();
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






    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();


        t1.setText(resources.getString(R.string.line1));
        t22.setText(resources.getString(R.string.line22));
        t2.setText(resources.getString(R.string.line2));
        t3.setText(resources.getString(R.string.line3));
        t4.setText(resources.getString(R.string.line4));
        t5.setText(resources.getString(R.string.line5));
        t6.setText(resources.getString(R.string.line6));
        t7.setText(resources.getString(R.string.line7));
        t8.setText(resources.getString(R.string.line8));
        t9.setText(resources.getString(R.string.line9));
        t10.setText(resources.getString(R.string.line10));
        t11.setText(resources.getString(R.string.line11));
        t12.setText(resources.getString(R.string.line13));
        t13.setText(resources.getString(R.string.GeneralInstruction));
        testinstructproceed.setText((resources.getString(R.string.start)));
        msg = resources.getString(R.string.amway_Language_Err_Toast);
        msg1  = resources.getString(R.string.Internet_connectivity);
        msg2 = resources.getString(R.string.Global_ErrorResponse_Message);
        msg3  = resources.getString(R.string.Internet_connectivity);

        msg4 = resources.getString(R.string.Choose_Language);
        setTitle(resources.getString(R.string.Instruction));




    }

}
