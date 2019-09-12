package com.radiant.rpl.testa.Registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Company_dropdown extends BaseActivity {
    AutoCompleteTextView searchableSpinner;
    String employerid,employervalue,employerrridd;
    HashMap<String,String> employdetail =new HashMap<>();
    List<String> employerlist;
    String[] employers;
    Button btn_select_company;
    String employerrrr,msg,msg1,msg2,msg3,msg4,msg5,msg6,msg7,selected_language;
    SharedPreferences language_prefs;


    TextView t1;
    int count;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        searchableSpinner=findViewById(R.id.searchablespinnerr);
        btn_select_company=findViewById(R.id.btn_select_company);
        t1 = findViewById(R.id.Company_name);

        employers=new String[]{(String) getString(R.string.employerlist)};
        employerlist=new ArrayList<>(Arrays.asList(employers));



        Employerlist();

        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }






        Resources resources = getResources();
        msg = resources.getString(R.string.Employer_Toast_Message_At_Select_Time);
        msg1 = resources.getString(R.string.form_employee_Err);
        msg2 = resources.getString(R.string.Company_Err);
        msg3 = resources.getString(R.string.Choose_Company_Message);
        msg4 = resources.getString(R.string.choose_company_message2);
        msg5 = resources.getString(R.string.Alert);
        msg6 = resources.getString(R.string.Cancel);
        msg7 = resources.getString(R.string.Confirm);




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





        btn_select_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employerrrr=searchableSpinner.getText().toString();

                for (int i =0;i<=employerlist.size()-1;i++){

                    if (employerlist.get(i).equals(employerrrr)){
                        employerrridd = employdetail.get(employerrrr);
                        break;
                    } else {
                        employerrridd ="";
                    }


                }



                if (employerrrr.equals(getResources().getString(R.string.employerlist))){
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }else{
                    String sourceString = msg3+"<b>" + employerrrr + "</b> " + msg4;

                    AlertDialog alertDialog = new AlertDialog.Builder(Company_dropdown.this)
                            .setMessage(Html.fromHtml(sourceString))
                            .setTitle(msg5)
                            .setCancelable(false)
                            .setPositiveButton(msg6, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    employerrridd="";
                                }
                            })
                            .setNegativeButton(msg7,new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (employerrridd==""){
                                                Toast.makeText(Company_dropdown.this,msg2,Toast.LENGTH_LONG).show();
                                            }
                                            else  if (employerrridd.equals("22") || employerrridd.equals("23")){
                                                Intent ii=new Intent(Company_dropdown.this,Ola_uber_registration.class);
                                                ii.putExtra("cmp_id",employerrridd);
                                                ii.putExtra("cmp_name",employerrrr);
                                                startActivity(ii);
                                            }

                                            else if (employerrridd.equals("19")){
                                                Intent iii=new Intent(Company_dropdown.this,Amway_Registration.class);
                                                iii.putExtra("cmp_id",employerrrr);
                                                startActivity(iii);
                                            }
                                            else if (employerrridd.equals("24")){
                                                Intent iii=new Intent(Company_dropdown.this,Upica_Registration.class);
                                                iii.putExtra("cmp_id",employerrrr);
                                                startActivity(iii);
                                            }
                                            else {
                                                Intent iii=new Intent(Company_dropdown.this,MainActivity.class);
                                                iii.putExtra("cmp_id",employerrrr);

                                                if(count==1) {
                                                    iii.putExtra("language_id", "1");
                                                }
                                                if(count==0) {
                                                    iii.putExtra("language_id", "0");
                                                }
                                                startActivity(iii);
                                            }

                                        }
                                    }


                            ).create();

                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_dropdown;
    }

    //Employer_List
    private void Employerlist() {
        show_progressbar();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_employer.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("employer");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            employerid = c.getString("id");
                            employervalue = c.getString("name");
                            employdetail.put(employervalue, employerid);
                            employerlist.add(employervalue);
                        }

                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                Company_dropdown.this, android.R.layout.simple_dropdown_item_1line,
                                employerlist);


                        searchableSpinner.setAdapter(arrayAdapter);
                        searchableSpinner.setThreshold(2);
                        searchableSpinner.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View arg0) {
                                //searchableSpinner.showDropDown();
                            }
                        });



                    }
                    else {
                        Toast.makeText(getApplicationContext(),msg1,Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hide_progressbar();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hide_progressbar();
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

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

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
            count=0;
        }


        if(item.getItemId() == R.id.language_hi)
        {
            Paper.book().write("language","hi");
            updateView((String)Paper.book().read("language"));
            count=1;
        }


        return super.onOptionsItemSelected(item);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();
        t1.setText(resources.getString(R.string.enter_your_company_name));
        btn_select_company.setText(resources.getString(R.string.proceed));
        searchableSpinner.setHint(resources.getString(R.string.company_name));
        msg = resources.getString(R.string.Employer_Toast_Message_At_Select_Time);
        msg1 = resources.getString(R.string.form_employee_Err);
        msg2 = resources.getString(R.string.Company_Err);
        msg3 = resources.getString(R.string.Choose_Company_Message);
        msg4 = resources.getString(R.string.choose_company_message2);
        msg5 = resources.getString(R.string.Alert);
        msg6 = resources.getString(R.string.Cancel);
        msg7 = resources.getString(R.string.Confirm);

        setTitle(resources.getString(R.string.choose_company));

    }




}
