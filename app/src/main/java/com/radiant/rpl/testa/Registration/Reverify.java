package com.radiant.rpl.testa.Registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Reverify extends BaseActivity {

    EditText fname_txt,lname_txt,mob_txt,aadharno_txt,bankacc_txt,inputbankusername_txt,ifsccode1,pannumberr;
    String fname,lname,mob,aadharno,bankacc,yearobirth,monthobirth,dateobirthh,gender,bank1,statee,districtt,educationn,pancardd,employerr,sectorr,addline11,addline22,pincode1,nameasinbank1,
    iffccode1,photouri,jobrolee,empidd,locationn,aadharpic,language,category1,Email1;
    String getFname,getLname,getMob,getAadharno,getBankacc;
    ProgressDialog pd;
    Button btn_Register;
    AwesomeValidation awesomeValidation;
    String geturl,gettestingurl;
    String regno;

    TextInputLayout mobile, first_name, last_name, aadhar_no,Pancard ,Name_in_bank,Bank_Ac_no,Ifsc_no;

    SharedPreferences language_prefs;
    String selected_language, toast_msg1,toast_msg2;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        fname_txt=findViewById(R.id.input_fname);
        lname_txt=findViewById(R.id.input_last_lname);
        mob_txt=findViewById(R.id.input_mobile_noo);
        aadharno_txt=findViewById(R.id.input_aadhar_no);
        bankacc_txt=findViewById(R.id.input_bank_acdetails);
        inputbankusername_txt=findViewById(R.id.input_bank_username1);
        ifsccode1=findViewById(R.id.input_ifsc_code1);
        pannumberr=findViewById(R.id.input_pancard);
        geturl= Start_Registration.getURL();
        gettestingurl=Start_Registration.getTestingURL();
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        Intent ii=getIntent();
        fname=ii.getStringExtra("first_namee");
        lname=ii.getStringExtra("last_namee");
        mob=ii.getStringExtra("mobile");
        aadharno=ii.getStringExtra("aadhar");
        bankacc=ii.getStringExtra("bankaccount");
        yearobirth=ii.getStringExtra("doy");
        monthobirth=ii.getStringExtra("dom");
        dateobirthh=ii.getStringExtra("dod");
        gender=ii.getStringExtra("gender");
        bank1=ii.getStringExtra("bank");
        statee=ii.getStringExtra("state");
        districtt=ii.getStringExtra("district");
        educationn=ii.getStringExtra("education");
        pancardd=ii.getStringExtra("pancard");
        employerr=ii.getStringExtra("employer");
        sectorr=ii.getStringExtra("sector");
        addline11=ii.getStringExtra("addline1");
        addline22=ii.getStringExtra("addline2");
        pincode1=ii.getStringExtra("pincode");
        nameasinbank1=ii.getStringExtra("nameasinbank");
        iffccode1=ii.getStringExtra("ifsccode");
        jobrolee=ii.getStringExtra("jobrole");
        empidd=ii.getStringExtra("empid");
        language=ii.getStringExtra("preflang");
        locationn=ii.getStringExtra("location");
        aadharpic=ii.getStringExtra("picaadhar");
        photouri=ii.getStringExtra("pic");
        category1 = ii.getStringExtra("categroy");
        Email1= ii.getStringExtra("Email");




        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }






        btn_Register=findViewById(R.id.btn_Register);
        mobile = findViewById(R.id.input_layout_mobile_no);
        first_name = findViewById(R.id.input_layout_name);
        last_name = findViewById(R.id.input_layout_last_name);
        aadhar_no = findViewById(R.id.input_layout_aadhar);
        Pancard = findViewById(R.id.input_layout_pancard_no);
        Name_in_bank = findViewById(R.id.input_layout_bank_username1);
        Bank_Ac_no = findViewById(R.id.input_layout_bank_ac);
        Ifsc_no = findViewById(R.id.input_layout_ifsc_code1);

        Resources resources = getResources();

        toast_msg1 =  resources.getString(R.string.Aadhar_Already_user_Message);
        toast_msg2 =  resources.getString(R.string.Global_ErrorResponse_Message);




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






        fname_txt.setText(fname);
        lname_txt.setText(lname);
        mob_txt.setText(mob);
        bankacc_txt.setText(bankacc);
        inputbankusername_txt.setText(nameasinbank1);
        ifsccode1.setText(iffccode1);
        if (pancardd!=null){
            pannumberr.setText(pancardd);
        }else {
            pannumberr.setVisibility(View.GONE);
        }

        if (aadharno!=null){
            aadharno_txt.setText(aadharno);
        }else {
            aadharno_txt.setVisibility(View.GONE);
        }


        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFname=fname_txt.getText().toString();
                getLname=lname_txt.getText().toString();
                getMob=mob_txt.getText().toString();
                getAadharno=aadharno_txt.getText().toString();
                getBankacc=bankacc_txt.getText().toString();
                SaveDetail(getFname,getLname,getMob,getAadharno,getBankacc);


            }
        });

        awesomeValidation.addValidation(Reverify.this, R.id.input_fname,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_last_lname,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Reverify.this, R.id.input_mobile_noo,"^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Reverify.this, R.id.input_aadhar_no,"^[0-9]{12}$", R.string.err_msg_foraadhar);
        awesomeValidation.addValidation(Reverify.this, R.id.input_bank_acdetails,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();


        mobile.setHint(resources.getString(R.string.hint_mobile));
        first_name.setHint(resources.getString(R.string.hint_name));
        last_name.setHint(resources.getString(R.string.hint_last_name));
        aadhar_no.setHint(resources.getString(R.string.hint_aadhar));
        Pancard.setHint(resources.getString(R.string.hint_pancard));
        Name_in_bank.setHint(resources.getString(R.string.name_as_in_bank));
        Bank_Ac_no.setHint(resources.getString(R.string.hint_bank_ac));
        Ifsc_no.setHint(resources.getString(R.string.hint_ifsc_code));
        btn_Register.setText(resources.getString(R.string.register));


        toast_msg1 =  resources.getString(R.string.Aadhar_Already_user_Message);
        toast_msg2 =  resources.getString(R.string.Global_ErrorResponse_Message);





    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reverify;
    }


    private void SaveDetail(final String fnamee, final String lnamee, final String mobbb, final String aadhaar, final String bankacccc) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/save_student_data.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    if (status.equals("1")){
                          Intent iii=new Intent(Reverify.this, Registration_Done.class);
                          iii.putExtra("sectorid",sectorr);
                          iii.putExtra("mobileeno",mobbb);
                          startActivity(iii);
                    }
                   else if (status.equals("0")){
                        String msg= jobj.getString("msg");
                        if (msg.equals(getResources().getString(R.string.Asdhar_msg3))){
                            regno=jobj.getString("registered_number");
                            Toast.makeText(getApplicationContext(),toast_msg1+regno,Toast.LENGTH_LONG).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent ii=new Intent(Reverify.this,SignInAct.class);
                                    ii.putExtra("key", regno);
                                    startActivity(ii);
                                    //Do something after 100ms
                                }
                            }, 100);
                        }
                        else {
                            String msg1= jobj.getString("msg");
                            Toast.makeText(getApplicationContext(),msg1,Toast.LENGTH_LONG).show();
                        }
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
                Toast.makeText(getApplicationContext(),toast_msg2, Toast.LENGTH_LONG).show();
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
                map.put("firstname",fnamee);
                map.put("lastname",lnamee);
                map.put("mobile",mobbb);
                map.put("bankac",bankacccc);
                if (monthobirth!=null || dateobirthh!=null){
                map.put("dob",yearobirth+"-"+monthobirth+"-"+dateobirthh);}
                else{
                    map.put("dob",yearobirth);
                }
                //error send year in dob column
                //disablity is not sending
                map.put("gender",gender);
                map.put("qualification",educationn);
                map.put("address1",addline11);
                if (addline22!=null){
                map.put("address2",addline22);}
                map.put("state_id",statee);
               // System.out.print("stateddddd",)
                map.put("district_id",districtt);
                map.put("pincode",pincode1);
                if (aadharno!=null){
                map.put("aadhar",aadharno_txt.getText().toString());}
                map.put("ssc_id",sectorr);
                map.put("company_id",employerr);
                map.put("sector_id","0");
                map.put("bankname",bank1);
                map.put("name_in_bank",nameasinbank1);
                map.put("ifsc",iffccode1);
                map.put("jobrole_id",jobrolee);
                if (empidd!=null){
                map.put("employee_id",empidd);}
                map.put("language",language);
                if (pancardd!=null){
                    map.put("pan",pancardd);
                }
                if (locationn!=null){
                map.put("StoreLocation",locationn);}
                if (category1!=null){
                map.put("category",category1);}

                if (Email1!=null){
                map.put("email",Email1);}


                if (aadharpic!=null){
                map.put("aadhar_image",aadharpic);}
                map.put("student_image",photouri);
                map.put("data_source","Mobile");
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


}
