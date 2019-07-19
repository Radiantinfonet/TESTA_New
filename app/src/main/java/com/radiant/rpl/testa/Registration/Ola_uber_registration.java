package com.radiant.rpl.testa.Registration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;
import com.radiant.rpl.testa.Barcode_d.SimpleScannerActivity;
import com.radiant.rpl.testa.MainActivity;
import com.radiant.rpl.testa.MyNetwork;
import com.radiant.rpl.testa.Registration_Done;
import com.radiant.rpl.testa.Reverify;
import com.radiant.rpl.testa.SignInAct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import radiant.rpl.radiantrpl.R;

public class Ola_uber_registration extends BaseActivity {
EditText mob_olauber,name_olauber,aadhar_olauber,lastname_olauber;
Spinner gender_olauber,jobrole_olauber,language_olauber;
String genderr,emp_statuss,jobroleid,jobrolevalue,preflangid,preflangvalue,getjobrole,gejobrolevalue,get_language,getlanguage_id;
    List<String> jobrolelist,preflang;
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String,String> langdetail =new HashMap<>();
    String[] jobrole, preflangg;
    ArrayAdapter<String> jobroleadapter;
  String cmp_id,cmp_name;
    AwesomeValidation awesomeValidation;
    CoordinatorLayout parentvv;
    Button register_olauber;
    private static final int ZBAR_CAMERA_PERMISSION = 1;
String namefromaadhaar_ola;
    ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mob_olauber=findViewById(R.id.input_mobile_no_olauber);
        name_olauber=findViewById(R.id.input_name_olauber);
        aadhar_olauber=findViewById(R.id.input_aadhaar_no_olauber);
        lastname_olauber=findViewById(R.id.input_last_name_olauber);
        gender_olauber=findViewById(R.id.input_layout_gender_olauber);
        jobrole_olauber=findViewById(R.id.input_layout_jobrole_olauber);
        language_olauber=findViewById(R.id.input_layout_language_olauber);
        register_olauber=findViewById(R.id.btn_signup_olauber);
        parentvv=findViewById(R.id.register_yourself_olauber);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(Ola_uber_registration.this, R.id.input_name_olauber,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Ola_uber_registration.this, R.id.input_mobile_no_olauber,"^[0-9]{10}$", R.string.err_msg_formobile);
        jobrole=new String[]{"Select the Jobrole"};
        preflangg =new String[]{"Select the Preffered Language"};
        jobrolelist=new ArrayList<>(Arrays.asList(jobrole));
        preflang=new ArrayList<>(Arrays.asList(preflangg));

        cmp_id = getIntent().getStringExtra("cmp_id");
        cmp_name=getIntent().getStringExtra("cmp_name");
        System.out.println("the id of company is"+cmp_id+"  "+cmp_name);
        //gender
       myAdapter = new ArrayAdapter<String>(Ola_uber_registration.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender_olauber.setAdapter(myAdapter);

        ImageView iv=findViewById(R.id.actionQrCode);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcScanQRCode();
            }
        });

        awesomeValidation.addValidation(Ola_uber_registration.this, R.id.input_layout_gender_olauber, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select Gender")) {
                    return false;
                } else {
                    return true;
                }
            }
        }, new CustomValidationCallback() {
            @Override
            public void execute(ValidationHolder validationHolder) {
                TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
                textViewError.setError(validationHolder.getErrMsg());
                textViewError.setTextColor(Color.RED);
            }
        }, new CustomErrorReset() {
            @Override
            public void reset(ValidationHolder validationHolder) {
                TextView textViewError = (TextView) ((Spinner) validationHolder.getView()).getSelectedView();
                textViewError.setError(null);
                textViewError.setTextColor(Color.BLACK);
            }
        }, R.string.err_tech_stacks);




        gender_olauber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                genderr=gender_olauber.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //jobrole
        jobrole_olauber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // if(position > 0) {
                getjobrole = jobrole_olauber.getSelectedItem().toString();
                gejobrolevalue=Jobrolelist.get(getjobrole);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Preffered Exam Language


        language_olauber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                get_language = language_olauber.getSelectedItem().toString();
                getlanguage_id=langdetail.get(get_language);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        register_olauber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!new VerhoeffAlgorithm().validateVerhoeff(aadhar_olauber.getText().toString())){
                    Snackbar.make(parentvv,"This Aadhaar number is invalid.Please input correct aadhaar number.",Snackbar.LENGTH_SHORT).show();
                } else if (aadhar_olauber.getText().toString().equals("")){
                    Snackbar.make(parentvv,"Aadhaar number can't be left empty.",Snackbar.LENGTH_SHORT).show();

                }
                else if( awesomeValidation.validate() && !(genderr.equals("Select Gender"))&& !getjobrole.equals("Select the Jobrole")
                        && ! get_language.equals("Select the Preffered Language")){
                    SaveDetail();

                    }
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        getJobroleList(cmp_id);
        languageSelect(cmp_id);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            if (resultCode == 2 && requestCode == 1){
                //do something
                HashMap<String, String> map = new HashMap<>();
                Bundle extras = data.getExtras();
                String ss[]=extras.getStringArray("ss");
                String uid_data=ss[1].replace("encoding=\"UTF-8\"?>\n<PrintLetterBarcodeData ","");
                ss[1] = uid_data;
                for (String s: ss) {
                    String[] sd=s.split("=");
                    Log.d("dataaa",sd[0]);
                    Log.d("dataaa",sd[1]);
                    map.put(sd[0], sd[1]);
                    //map.put("value", sd[1]);
                    //Toast.makeText(this,"result"+ss[0]+" "+ss[1],Toast.LENGTH_LONG).show();
                }
                Log.d("daaaa",ss[1]);
                Log.d("daaaa",ss[0]);

                if (map.get("uid")!=null){
                    aadhar_olauber.setEnabled(false);
                    aadhar_olauber.setText(map.get("uid").replace("\"",""));

                }

                if (map.get("name")!=null){
                    namefromaadhaar_ola=map.get("name").replace("\"","");
                    String namee[]=namefromaadhaar_ola.split(" ");
                    name_olauber.setEnabled(false);
                    lastname_olauber.setEnabled(false);
                    name_olauber.setText(namee[0]);
                    lastname_olauber.setText(namee[1]);
                }
               /* if (map.get("gender")!=null){
                    String genderr=map.get("gender").replace("\"","");
                    if (genderr=="M"){
                        gender_olauber.setSelection(myAdapter.getPosition("Male"));
                    }
                    else if (genderr=="F"){
                        gender_olauber.setSelection(myAdapter.getPosition("Female"));

                    }
                }*/
            }else{
            }}catch (Exception e){
            System.out.println("fffff"+e);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ola_uber_registration;
    }


    //Jobrole Api Call
    private void getJobroleList(final String sscid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_jobrole.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    emp_statuss=jobj.getString("emp_status");

                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("jobrole");
                        jobrolelist.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            jobroleid = c.getString("jobrole_id");
                            jobrolevalue = c.getString("name");
                            Jobrolelist.put(jobrolevalue, jobroleid);
                            jobrolelist.add(jobrolevalue);
                        }
                        System.out.println("jborole list"+jobrolelist);

                        jobroleadapter= new ArrayAdapter<String>(Ola_uber_registration.this,
                                android.R.layout.simple_list_item_1,jobrolelist);
                        jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        jobrole_olauber.setAdapter(jobroleadapter);



                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Job Roles",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Failed to fetch Job Roles", Toast.LENGTH_LONG).show();
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
                map.put("company_id",sscid);
                System.out.println("aaaaaa"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void funcScanQRCode() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent ii=new Intent(Ola_uber_registration.this, SimpleScannerActivity.class);
            startActivityForResult(ii, 1);
        }
    }

    //Language Api Call
    private void languageSelect(final String cmp_id) {

        show_progressbar();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_language.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    System.out.println("language is"+response);
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("language");
                        preflang.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            preflangid = c.getString("language_code");
                            preflangvalue = c.getString("name");
                            langdetail.put(preflangvalue,preflangid );
                            preflang.add(preflangvalue);
                        }
                        ArrayAdapter<String> preflanguage = new ArrayAdapter<String>(Ola_uber_registration.this,
                                android.R.layout.simple_list_item_1,preflang);
                        preflanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        language_olauber.setAdapter(preflanguage);

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Language Details",Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), "Failed to fetch Language Details", Toast.LENGTH_LONG).show();
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
                map.put("company_id",cmp_id);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }



    //save_data

    private void SaveDetail() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/save_student_data.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        Intent iii=new Intent(Ola_uber_registration.this, Registration_Done.class);

                        startActivity(iii);
                    }
                    else if (status.equals("0")){
                        String msg= jobj.getString("msg");
                        if (msg.equals("Given Aadhar number is already registered! Try using another Aadhar.")){
                            Toast.makeText(getApplicationContext(),"You have already registered using this aadhar number with ",Toast.LENGTH_LONG).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent ii=new Intent(Ola_uber_registration.this, SignInAct.class);
                                    //ii.putExtra("key", regno);
                                    startActivity(ii);
                                    //Do something after 100ms
                                }
                            }, 100);
                        }
                    }
                    else {
                        String msg1= jobj.getString("msg");
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
                map.put("firstname",name_olauber.getText().toString());
                if (lastname_olauber.getText().toString()!=null){
                    map.put("lastname",lastname_olauber.getText().toString());}
                map.put("mobile",mob_olauber.getText().toString());
                map.put("gender",genderr);
                if (aadhar_olauber.getText().toString()!=null){
                    map.put("aadhar",aadhar_olauber.getText().toString());}
                    if (jobrolevalue!=null){
                map.put("jobrole_id",jobrolevalue);}
                map.put("company_id",cmp_id);
                if (getlanguage_id!=null){
                map.put("language",getlanguage_id);}
                System.out.print("ggggggg"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }



}
