package com.radiant.rpl.testa.Registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.ExamSection.ContactUsActivity;
import com.radiant.rpl.testa.ExamSection.Technical_Activity;
import com.radiant.rpl.testa.MainActivity;
import com.radiant.rpl.testa.MyNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import radiant.rpl.radiantrpl.R;

public class Company_dropdown extends BaseActivity {
AutoCompleteTextView searchableSpinner;
String employerid,employervalue,employerrridd;
    HashMap<String,String> employdetail =new HashMap<>();
    List<String> employerlist;
    String[] employers;
    Button btn_select_company;
    String employerrrr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        searchableSpinner=findViewById(R.id.searchablespinnerr);
        btn_select_company=findViewById(R.id.btn_select_company);
       /* searchableSpinner.setTitle("Companies List");
        searchableSpinner.setPositiveButton("Close");*/
        employers=new String[]{"Select the Employer"};
        employerlist=new ArrayList<>(Arrays.asList(employers));



        Employerlist();



        btn_select_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employerrrr=searchableSpinner.getText().toString();
                System.out.println("employer is"+employerrrr);
                System.out.println("employer id is"+employdetail.get(employerrrr));
                for (int i =0;i<=employerlist.size()-1;i++){
                    System.out.println("employer name in loop is"+employerlist.get(i));
                    if (employerlist.get(i).equals(employerrrr)){
                        employerrridd = employdetail.get(employerrrr);
                        break;
                    } else {
                        employerrridd ="";
                    }


                }


                System.out.println("company is"+searchableSpinner.getText().toString());
                if (employerrrr.equals("Select the Employer")){
                    Toast.makeText(getApplicationContext(),"Please select your employer to proceed",Toast.LENGTH_LONG).show();
                }else{
                    String sourceString = "You have selected "+"<b>" + employerrrr + "</b> " + " as your employer.Please Confirm to continue else cancel to change employer.";

                    AlertDialog alertDialog = new AlertDialog.Builder(Company_dropdown.this)
                            .setMessage(Html.fromHtml(sourceString))
                            .setTitle("Alert!")
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                      employerrridd="";
                                }
                            })
                            .setNegativeButton("Confirm",new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (employerrridd==""){
                                                Toast.makeText(Company_dropdown.this,"This is not a valid company Name",Toast.LENGTH_LONG).show();
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
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_employer.php";

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

                        //Employer
                      /*  ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(Company_dropdown.this,
                                android.R.layout.simple_list_item_1,employerlist);
                        myAdapterEmployer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        searchableSpinner.setAdapter(myAdapterEmployer);

                        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int position, long id)
                            {

                                //if(position > 0) {
                                    employerrrr = searchableSpinner.getSelectedItem().toString();
                                    employerrridd = employdetail.get(employerrrr);
                                    System.out.println("id is"+employerrridd);

                                //}

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {


                            }


                        });
*/
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Employers",Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

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
}
