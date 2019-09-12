package com.radiant.rpl.testa.Initials;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.Common.CommonUtils;
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
import radiant.rpl.radiantrpl.R;

public class Testinstruction extends AppCompatActivity {
    Button testinstructproceed;
    AlertDialog alertDialog;
    String language,languageid;
    HashMap<String,String> hm=new HashMap<>();
    CharSequence[] values;
    List<String> listItems = new ArrayList<String>();
    SharedPreferences ssp,sspp;
         String batchidddd;
    Bundle b;
    SharedPreferences.Editor editor;
    private android.app.AlertDialog progressDialog;
    String geturl,gettestingurl;
    LinearLayout pl;
    String stringlatitude3,stringlongitude3,stuidd;
    String exam_status;
    long theory_time;
    long practical_time;
    DbAutoSave dbAutoSave;
    private String student_type;


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
        System.out.println("lat and long are"+stringlatitude3+" "+stringlongitude3);
        stuidd=sspp.getString("userid","");

        if (sspp.contains("student_type")){
            student_type=sspp.getString("student_type","");
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
        builder.setTitle("Choose Your Language");
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                switch (i) {
                    case 0:
                        String aa = String.valueOf(values[i]);
                        String bb = hm.get(aa);
                        SharedPreferences.Editor editor = ssp.edit();
                        editor.putString("languagev", bb);
                        System.out.println("language of value 1 is"+bb);
                        editor.putString("examstatus",exam_status);
                        editor.putLong("theorytime",theory_time);
                        editor.putLong("practicaltime",practical_time);
                        editor.apply();
                        editor.commit();
                        //saveLog(stuidd,"","Start Exam",stringlatitude3,stringlongitude3,"");
                            Intent ii = new Intent(Testinstruction.this, TestQuestion.class);
                            startActivity(ii);

                        break;
                    case 1:
                        String cc= String.valueOf(values[i]);
                        String dd=hm.get(cc);
                        SharedPreferences.Editor editor1 = ssp.edit();
                        editor1.putString("languagev", dd);
                        System.out.println("language of value 2 is"+cc);
                        editor1.putString("examstatus",exam_status);
                        editor1.putLong("theorytime",theory_time);
                        editor1.putLong("practicaltime",practical_time);
                        editor1.apply();
                        editor1.commit();
                        //saveLog(stuidd,"","Start Exam",stringlatitude3,stringlongitude3,"");
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


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

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    //Exam Languages
    private void getExamLanguage(final String Sectorvalue) {
        progressDialog.show();
        String serverURL = CommonUtils.url+"get_batch_language.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    exam_status=jobj.getString("exam_status");
                    theory_time=jobj.getLong("theory_time");
                    practical_time=jobj.getLong("practical_time");
                    System.out.println("theoruy"+theory_time+"  "+"practical time"+practical_time);
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
                        Toast.makeText(getApplicationContext(),"Failed to fetch Language Details",Toast.LENGTH_LONG).show();
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
                        .make(pl,"Internet not available,Cross check your internet connectivity.",Snackbar.LENGTH_LONG);
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
                map.put("student_type",student_type);
                map.put("username",stuidd);
                map.put("mobile_imei","");
                map.put("ip","");
                map.put("company_id","");
                map.put("activity","Start Exam");
                map.put("lat",stringlatitude3);
                map.put("longi",stringlongitude3);

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void saveLog(final String fnamee, final String ip, final String activity, final String lat, final String longi,final String cmpid) {
        String serverURL = CommonUtils.url+"save_logs.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Saving the details", Toast.LENGTH_LONG).show();
                System.out.println("aa"+error);
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
                map.put("student_type",student_type);
                System.out.println("test start data"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }






}
