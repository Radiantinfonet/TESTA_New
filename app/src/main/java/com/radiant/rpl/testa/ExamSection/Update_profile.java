package com.radiant.rpl.testa.ExamSection;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.radiant.rpl.testa.MainActivity;
import com.radiant.rpl.testa.MyNetwork;
import com.radiant.rpl.testa.NetworkStateReceiver;
import com.radiant.rpl.testa.Registration.BaseActivity;
import com.radiant.rpl.testa.Reverify;
import com.radiant.rpl.testa.Welcome_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class Update_profile extends BaseActivity {

    Spinner category,myspinner,yearofbirth,bankname,state,district,education,employer,sector,disablity_type,type_of_disablity,input_jobrole,
            Employment_status;
    EditText input_name,input_last_name,input_mobile_no,input_aadhar,input_bank_ac,input_ifsc_code,
            input_bank_username,input_pann,other_qualification,input_address,Email_update,input_pincode_update;

    SharedPreferences sharedpreferences;
    String yearobirth,eduction1,bankname1,state1,district1,bankiddd,stateiddd,districtiddd,Stateid,Statevalue,
            bankid,bankvalue,districtid,districtvalue,selectedstatetext,gotbankid,gotstateid,gotdistrictid,employer1,sector1,
            employeridname,sectoridd,jobroleeiddd,jobrole1,sectorid,sectorvalue, employerid,employervalue,jobroleid,jobrolevalue,Employment_status1,
            disablity_type1, type_of_disablity1,gotEmailid,gotpincode,gotjobroleid,gotsectorid,gotcompanyid,gotcompanyid1;

    String emp_statuss;
    ArrayAdapter<String> jobroleadapter;

    String[] banks,states,districts,employers,jobrole;
    List<String> bankslist,Statelist,districtlist,gotbankname,gotstataename,
            gotdistrictname,sectorlist,employerlist,jobrolelist,gotjobrolename,gotcompanyname,gotsectorname;

    HashMap<String, String> bankdetail = new HashMap<>();
    HashMap<String, String> Statedetail = new HashMap<>();
    HashMap<String, String> districtdetail = new HashMap<>();
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String, String> employdetail = new HashMap<>();
    HashMap<String,String> sectordetail=new HashMap<>();





    public static final String mypreference = "mypref";
    Button input_submit;
    CheckBox checkBox;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    AwesomeValidation awesomeValidation;
    String gender,encodedphotoaadhar,categoryy;
    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private android.app.AlertDialog progressDialog;
    String first_name,last_name,gender1,categoryselected,bankaccount,aadharselected,name_in_bank,
            ifsc_selected,mobile_no_selected,User_namee,stateiddd1,districtid1,address1,Category1,year1,
            education2,disablity1,typediablity1,otheredu,pancard1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);





        yearofbirth=findViewById(R.id.input_layout_year_update);
        education=findViewById(R.id.input_layout_Education_update);
        other_qualification = findViewById(R.id.input_Eduction_other_update);
        bankname=findViewById(R.id.input_layout_bankname_update);
        state=findViewById(R.id.input_layout_State_update);
        district=findViewById(R.id.input_layout_District_update);
        input_address = findViewById(R.id.input_address1_update);
        Employment_status= findViewById(R.id.employment_status_update);
        Email_update = findViewById(R.id.input_email_update);
        input_pincode_update = findViewById(R.id.input_pincode_update);
        employer=findViewById(R.id.input_layout_Employer_update);
        sector=findViewById(R.id.input_layout_Sector_update);
        input_jobrole = findViewById(R.id.input_layout_jobrole_update);
        disablity_type = findViewById(R.id.input_layout_Disablity_type_update);
        type_of_disablity = findViewById(R.id.input_layout_type_of_Disablity_update);

        myspinner = findViewById(R.id.input_layout_gender_update);
        category=findViewById(R.id.input_layout_category_update);
        input_submit=findViewById(R.id.btn_signup_update);
        input_name=findViewById(R.id.input_name_update);
        input_last_name=findViewById(R.id.input_last_name_update);
        input_mobile_no=findViewById(R.id.input_mobile_no_update);
        input_aadhar=findViewById(R.id.input_aadhar_update);
        input_bank_ac=findViewById(R.id.input_bank_ac_update);
        input_ifsc_code=findViewById(R.id.input_ifsc_code_update);
        input_bank_username = findViewById(R.id.input_bank_username_update);
        input_pann=findViewById(R.id.input_pan_update);
        progressDialog = new SpotsDialog(Update_profile.this, R.style.Custom);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox_update);

        //Bankdetails();
        //Statedetails();

        Employerlist();
        banks = new String[]{"Select the Bank"};
        states=new String[]{"Select the State"};
        districts=new String[]{"Select the District"};
        employers=new String[]{"Select the Employer"};
        jobrole=new String[]{"Select the Jobrole"};
        String[] sectors=new String[]{"Select the Sector"};



        Statelist = new ArrayList<>(Arrays.asList(states));
        districtlist=new ArrayList<>(Arrays.asList(districts));
        gotdistrictname=new ArrayList<>(Arrays.asList(districts));

        bankslist = new ArrayList<>(Arrays.asList(banks));
        gotbankname=new ArrayList<>(Arrays.asList(banks));
        gotstataename=new ArrayList<>(Arrays.asList(states));
        gotcompanyname = new ArrayList<>(Arrays.asList(employers));
        gotjobrolename = new ArrayList<>(Arrays.asList(jobrole));
        gotsectorname = new ArrayList<>(Arrays.asList(sectors));
        employerlist=new ArrayList<>(Arrays.asList(employers));
        jobrolelist=new ArrayList<>(Arrays.asList(jobrole));
        sectorlist=new ArrayList<>(Arrays.asList(sectors));








        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedpreferences.contains("userid")) {
            User_namee=sharedpreferences.getString("userid", "");
            System.out.println("");
        }

        awesomeValidation.addValidation(Update_profile.this, R.id.input_address1_update,"(.|\\s)*\\S(.|\\s)*", R.string.err_msg_for_address1);

        awesomeValidation.addValidation(Update_profile.this, R.id.input_name_update,"[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_last_name_update,"[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_bank_ac_update,"^[0-9]{6,18}$", R.string.err_msg_for_acno);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_ifsc_code_update,"^[a-zA-Z0-9]{5,14}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_mobile_no_update,"^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Update_profile.this, R.id.input_bank_username_update,"[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);
        //awesomeValidation.addValidation(Update_profile.this, R.id.input_aadhar_update,"^[0-9]{12}$", R.string.err_msg_for_namein_bank);
        mySwipeRefreshLayout=new SwipeRefreshLayout(getApplicationContext());
        input_mobile_no.setEnabled(false);
        input_mobile_no.setClickable(false);






        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (gender.equals("Select Gender")){
                    Toast.makeText(getApplicationContext(),"Gender must be selected",Toast.LENGTH_LONG).show();
                }
                else if (categoryy.equals("Select Category")){
                    Toast.makeText(getApplicationContext(),"category must be selected",Toast.LENGTH_LONG).show();
                }
                if(yearobirth.equals("Year")){
                    Toast.makeText(getApplicationContext(),"Year must be selected",Toast.LENGTH_LONG).show();
                }

                else if (gender.equals("Select Gender")){
                    Toast.makeText(getApplicationContext(),"Gender must be selected",Toast.LENGTH_LONG).show();
                }
                else if (categoryy.equals("Select categroy")){
                    Toast.makeText(getApplicationContext(),"categroy must be selected",Toast.LENGTH_LONG).show();
                }
                else if (state1.equals("Select the State")){
                    Toast.makeText(getApplicationContext(),"State must be selected",Toast.LENGTH_LONG).show();
                }

                else if (district1.equals("Select the District")){
                    Toast.makeText(getApplicationContext(),"District must be selected",Toast.LENGTH_LONG).show();
                }

                else if (eduction1.equals("Select Education")){
                    Toast.makeText(getApplicationContext(),"Education must be selected",Toast.LENGTH_LONG).show();
                }


                else if (bankname1.equals("Select the Bank")){
                    Toast.makeText(getApplicationContext(),"Bank  name must be selected",Toast.LENGTH_LONG).show();

                }

                else if ((eduction1.equals("Other"))&& (other_qualification.getText().toString().matches(""))){
                    Toast.makeText(getApplicationContext(),"Education must be filled",Toast.LENGTH_LONG).show();
                }
                else if (disablity_type1.equals("Any Disability ?")){
                    Toast.makeText(getApplicationContext(),"Disability must be Selected",Toast.LENGTH_LONG).show();
                }

                else if ((disablity_type1.equals("Yes"))&& (type_of_disablity1.equals("Select Type of Disability"))){
                    Toast.makeText(getApplicationContext(),"Disablity type must be selected",Toast.LENGTH_LONG).show();
                }







                else if ((stateiddd1.equals("2") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("3") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("16") && (input_pann.getText().toString().matches("")))  ||
                        (stateiddd1.equals("17") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("18") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("23") && (input_pann.getText().toString().matches("")))||
                        (stateiddd1.equals("19") && (input_pann.getText().toString().matches(""))) ||
                        (stateiddd1.equals("26") && (input_pann.getText().toString().matches("")))||
                        (stateiddd1.equals("10") && (input_pann.getText().toString().matches(""))) )

                {

                    Toast.makeText(Update_profile.this, "PAN Card cannot be empty ", Toast.LENGTH_SHORT).show();
                }

                else if ((stateiddd1.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("5") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("8") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("11") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("13") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("19") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("20") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("22") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("27") && (input_aadhar.getText().toString().matches("")))||
                        (stateiddd1.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("30") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                        (stateiddd1.equals("33") && (input_aadhar.getText().toString().matches("")))  ||
                        (stateiddd1.equals("34") && (input_aadhar.getText().toString().matches(""))))





                {

                    Toast.makeText(Update_profile.this, "PAN Card  be empty  but not aadhar", Toast.LENGTH_SHORT).show();
                }
                else if (awesomeValidation.validate()
                        && !(gender.equals("Select Gender"))
                        && !category.equals("Select Category")
                        && !state1.equals("Select the State")
                        && ! disablity_type1.equals("Any Disability ?")
                        && ! ((disablity_type1.equals("Yes"))&& (type_of_disablity.equals("Select Type of Disability")))
                        && !yearobirth.equals("Year")
                        && !district1.equals("Select the District")
                        && !eduction1.equals("Select Education")
                        && !((eduction1.equals("Other"))&& (other_qualification.getText().toString().matches("")))
                        && checkBox.isChecked())
                {
                    Update_Student_detail();
                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please check the declaration form.Please verify the form again and submit.", Toast.LENGTH_LONG).show();
                }



            }
        });






        //Gender

    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        myspinner.setAdapter(myAdapter);

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {

                        gender = myspinner.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }








        });





        //Choose category
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Category));
        categoryadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(categoryadapt);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                categoryy = category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //year_of_birth

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Year));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearofbirth.setAdapter(myAdapter1);

        yearofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                yearobirth=yearofbirth.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });





        //Bankname

        ArrayAdapter<String> myAdapterBankname = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,bankslist);
        myAdapterBankname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bankname.setAdapter(myAdapterBankname);

        bankname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                bankname1=bankname.getSelectedItem().toString();
                if(position > 0){
                    bankiddd= bankdetail.get(bankname1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Education
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Education));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(myAdapter4);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                eduction1=education.getSelectedItem().toString();

                if (eduction1.equals("Other")){
                    other_qualification.setVisibility(View.VISIBLE);
                }
                else {
                    other_qualification.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //state
        ArrayAdapter<String> myAdapterState = new ArrayAdapter<String>(Update_profile.this,

                android.R.layout.simple_list_item_1,Statelist);
        myAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(myAdapterState);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                state1=state.getSelectedItem().toString();
                selectedstatetext =(String) parent.getItemAtPosition(position);

                if(position > 0){
                    String value= Statedetail.get(selectedstatetext);
                    stateiddd=value;
                    System.out.println("vvv"+value);
                    DistrictDetails(value);
                    district.setVisibility(View.VISIBLE);
                }
                else {
                    district.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,districtlist);
        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        district.setAdapter(myAdapterDistrict);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                try{
                if (district.getSelectedItem().toString()!=null){
                district1=district.getSelectedItem().toString();
                districtiddd=districtdetail.get(district1);
                }}catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });





        //Employer
        ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,employerlist);
        myAdapterEmployer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        employer.setAdapter(myAdapterEmployer);
        employer.setEnabled(false);
        employer.setClickable(false);

        employer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {

                if(position > 0) {
                    employer1 = employer.getSelectedItem().toString();
                    employeridname = employdetail.get(employer1);

                    Sectorlist(employeridname);
                    //languageSelect(employeridname);
                      getJobroleList(employeridname);
                      Log.d("fgdfg",employeridname);
                    sector.setVisibility(View.VISIBLE);
                    //input_layout_prefferedlanguage.setVisibility(View.VISIBLE);
                    input_jobrole.setVisibility(View.VISIBLE);
                   // course_detail.setVisibility(View.VISIBLE);



                }
                else
                {
                    employer1=employer.getSelectedItem().toString();
                    sector.setVisibility(View.GONE);
                    //input_layout_prefferedlanguage.setVisibility(View.GONE);
                    input_jobrole.setVisibility(View.GONE);
                    //course_detail.setVisibility(View.GONE);




                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });




        //disablity_type
        ArrayAdapter<String> myAdapterdisablity_type = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Disablity));
        myAdapterdisablity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        disablity_type.setAdapter(myAdapterdisablity_type);

        disablity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                disablity_type1=disablity_type.getSelectedItem().toString();
                if (disablity_type1.equals("Yes")){
                    type_of_disablity.setVisibility(View.VISIBLE);
                }
                else {
                    type_of_disablity.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //type_of_disablity





        ArrayAdapter<String> myAdapter_type_of_disablity = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type_of_Disablity));
        myAdapter_type_of_disablity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type_of_disablity.setAdapter(myAdapter_type_of_disablity);

        type_of_disablity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                type_of_disablity1=type_of_disablity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });



        //Sector

        ArrayAdapter<String> myAdaptersector = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,sectorlist);
        myAdaptersector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sector.setAdapter(myAdaptersector);
        sector.setEnabled(false);
        sector.setClickable(false);

        sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                sector1=sector.getSelectedItem().toString();
                String selectedsectortext  = (String) parent.getItemAtPosition(position);

                if(position > 0){
                    sectoridd=sectordetail.get(selectedsectortext);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //jobrole

        jobroleadapter= new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,jobrolelist);
        jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        input_jobrole.setEnabled(false);
        input_jobrole.setClickable(false);

        input_jobrole.setAdapter(jobroleadapter);


        input_jobrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // if(position > 0) {
                jobrole1 = input_jobrole.getSelectedItem().toString();
                jobroleeiddd=Jobrolelist.get(jobrole1);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });




        //Employment _status


        ArrayAdapter<String> myAdapterEmployment_status = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Employment_status_string));
                 myAdapterEmployment_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Employment_status.setEnabled(false);
        Employment_status.setClickable(false);

        Employment_status.setAdapter(myAdapterEmployment_status);

        Employment_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                Employment_status1=Employment_status.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_profile;
    }


    @Override
    protected void onStart() {
        super.onStart();
        getStudentProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkStateReceiver= new NetworkStateReceiver(new NetworkStateReceiver.NetworkListener() {
            @Override
            public void onNetworkAvailable() {
                input_submit.setEnabled(true);
            }

            @Override
            public void onNetworkUnavailable() {
                input_submit.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Internet Not available",Toast.LENGTH_LONG).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //Update_profile
    private void getStudentProfile() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/test.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                      System.out.println("sdf"+response);
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("student_detail");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            first_name = c.getString("firstname");
                            last_name = c.getString("lastname");
                            gender1 = c.getString("gender");
                            Category1 = c.getString("category");
                            year1 = c.getString("year_of_birth");
                            education2 = c.getString("qualification");
                            otheredu = c.getString("other_qualification");
                            disablity1 = c.getString("disabilitytype");
                            typediablity1 = c.getString("disability");
                            pancard1 = c.getString("pan");
                            address1 = c.getString("address1");
                            categoryselected = c.getString("category");
                            bankaccount = c.getString("bankac");
                            aadharselected = c.getString("aadhar");
                            name_in_bank = c.getString("NameAsOnBank");
                            ifsc_selected = c.getString("ifsc");
                            mobile_no_selected=c.getString("mobile");
                            gotbankid=c.getString("mstbank");
                            gotbankname.add(gotbankid);
                            stateiddd1=c.getString("state_id");
                            districtid1=c.getString("district_id");
                            gotstateid = c.getString("state_name");
                            gotstataename.add(gotstateid);
                            gotdistrictid = c.getString("district_name");
                            gotdistrictname.add(gotdistrictid);
                            gotjobroleid = c.getString("jobrole_name");
                            gotjobrolename.add(gotjobroleid);
                            gotcompanyid = c.getString("company_name");
                            gotcompanyname.add(gotcompanyid);
                            gotcompanyid1 = c.getString("company_id");
                            gotsectorid = c.getString("sector_name");
                            gotsectorname.add(gotsectorid);

                            gotEmailid = c.getString("email");
                            gotpincode = c.getString("pincode");
                            Bankdetails();
                            Statedetails();
                            DistrictDetails(stateiddd1);
                            Employerlist();
                            Sectorlist(gotcompanyid1);
                            getJobroleList(gotcompanyid1);
                            Log.d("fff",gotcompanyid1);


                            for (int k=0;k<=getResources().getStringArray(R.array.gender).length-1;k++){
                                System.out.println("gender1"+gender1);
                                System.out.println(""+getResources().getStringArray(R.array.gender));
                                if (getResources().getStringArray(R.array.gender)[k].equals(gender1)){
                                    myspinner.setSelection(k);
                                }}



                            for (int k=0;k<=getResources().getStringArray(R.array.Category).length-1;k++){
                                System.out.println("category1"+Category1);
                                System.out.println(""+getResources().getStringArray(R.array.Category));
                                if (getResources().getStringArray(R.array.Category)[k].equals(Category1)){
                                    category.setSelection(k);
                                }}



                            for (int k=0;k<=getResources().getStringArray(R.array.Year).length-1;k++){
                                System.out.println("Year1"+year1);
                                System.out.println(""+getResources().getStringArray(R.array.Year));
                                if (getResources().getStringArray(R.array.Year)[k].equals(year1)){
                                    yearofbirth.setSelection(k);
                                }}




                            for (int k=0;k<=getResources().getStringArray(R.array.Education).length-1;k++){
                                if (getResources().getStringArray(R.array.Education)[k].equals(education2)){
                                    education.setSelection(k);
                                }}



                            for (int k=0;k<=getResources().getStringArray(R.array.Disablity).length-1;k++){
                                System.out.println("Disablity1"+gender1);
                                System.out.println(""+getResources().getStringArray(R.array.Disablity));
                                if (getResources().getStringArray(R.array.Disablity)[k].equals(disablity1)){
                                    disablity_type.setSelection(k);
                                }}


                            for (int k=0;k<=getResources().getStringArray(R.array.type_of_Disablity).length-1;k++){
                                System.out.println("Disablity1"+gender1);
                                System.out.println(""+getResources().getStringArray(R.array.type_of_Disablity));
                                if (getResources().getStringArray(R.array.type_of_Disablity)[k].equals(typediablity1)){
                                    type_of_disablity.setSelection(k);
                                }}

                        }
                        input_name.setText(first_name);
                        input_last_name.setText(last_name);input_ifsc_code.setText(ifsc_selected);
                        input_bank_ac.setText(bankaccount);

                        input_aadhar.setText(aadharselected);
                        input_bank_username.setText(name_in_bank);
                        input_mobile_no.setText(mobile_no_selected);
                        input_address.setText(address1);
                        Email_update.setText(gotEmailid);
                        input_pincode_update.setText(gotpincode);
                        other_qualification.setText(otheredu);
                        input_pann.setText(pancard1);

                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Student Details",Toast.LENGTH_LONG).show();
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
                map.put("user_name",User_namee);
                System.out.println("aaaaaa"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }






    private void Statedetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_state.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("state");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            Stateid = c.getString("id");
                            Statevalue = c.getString("name");
                            Statedetail.put(Statevalue, Stateid);
                            Statelist.add(Statevalue);
                        }



                        for (int i=0;i<=Statelist.size()-1;i++){

                            if (Statelist.get(i).equals(gotstateid ) && !Stateid.equals("0")) {
                                state.setSelection(i);
                            }

                    }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch States",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               /* if (pd.isShowing()) {
                    pd.dismiss();
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to fetch State list", Toast.LENGTH_LONG).show();
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






    //District_List
    private void DistrictDetails(final String districtidd) {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_district.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();
                try {
                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        districtlist.clear();
                        JSONArray jsonArray=jobj.getJSONArray("district");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            districtid = c.getString("id");
                            districtvalue = c.getString("name");
                            districtdetail.put(districtvalue, districtid);
                            districtlist.add(districtvalue);


                        }

                        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(Update_profile.this,
                                android.R.layout.simple_list_item_1,districtlist);
                        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        district.setAdapter(myAdapterDistrict);

                            for (int i = 0; i <= districtlist.size()-1; i++) {
                                System.out.println("districtee" + gotdistrictid);
                                System.out.println("districteeee" + districtlist.get(i));
                                if (districtlist.get(i).equals(gotdistrictid) ) {
                                    district.setSelection(i);
                                }
                            }



                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Districts",Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Failed to fetch Districts", Toast.LENGTH_LONG).show();
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
                map.put("state_id",districtidd);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }



    //API For Bank
    private void Bankdetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_bank.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("bank");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            bankid = c.getString("id");
                            bankvalue = c.getString("name");
                            bankdetail.put(bankvalue, bankid);
                            bankslist.add(bankvalue);

                        }
                        for (int i=0;i<=bankslist.size()-1;i++){
                            System.out.println("dfg"+gotbankid);
                            System.out.println("dfgg"+bankslist.get(i));
                        if (bankslist.get(i).equals(gotbankid)){
                            bankname.setSelection(i);
                        }}
                        // Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Bank Details",Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), "Failed to fetch Bank Details", Toast.LENGTH_LONG).show();
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






    private void Employerlist() {
       /* pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
*/
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


                        for (int i=0;i<=employerlist.size()-1;i++){
                            System.out.println("employere"+employerid );
                            System.out.println("employereeee"+employerlist.get(i));
                            if (employerlist.get(i).equals(gotcompanyid)){
                                employer.setSelection(i);
                            }
                        }




                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Employers",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               /* if (pd.isShowing()) {
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pd.dismiss();
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

    //Sector_list
    private void Sectorlist(final String Sectorvalue) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_sector.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    if (sectorlist.size()>2){
                        sectorlist.clear();
                    }
                    sectorlist.add("Choose the Sector");
                    if (status.equals("1")){
                        JSONArray jsonArray=jobj.getJSONArray("sector");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            sectorid = c.getString("id");
                            sectorvalue = c.getString("name");
                            sectordetail.put(sectorvalue, sectorid);
                            sectorlist.add(sectorvalue);
                            sector.setSelection(sectorlist.size()-1);

                        }


                        for (int i=0;i<=sectorlist.size()-1;i++){
                            System.out.println("sectoree"+sectorid );
                            System.out.println("sectoreee"+sectorlist.get(i));
                            if (sectorlist.get(i).equals(gotsectorid)){
                                sector.setSelection(i);
                            }
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Failed to fetch Sector Details",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Failed to fetch Sectors List", Toast.LENGTH_LONG).show();
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
                map.put("company_id",Sectorvalue);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void getJobroleList(final String sscid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/get_jobrole.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    emp_statuss=jobj.getString("emp_status");

                    ArrayAdapter myAdap = (ArrayAdapter) Employment_status.getAdapter(); //cast to an ArrayAdapter
                    int spinnerPosition = myAdap.getPosition(emp_statuss);

                    //set the default according to value
                    Employment_status.setSelection(spinnerPosition);

                    /*if (jobrolelist.size()<=1){
                        jobrolelist.clear();
                    }*/
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


                        for (int i=0;i<=jobrolelist.size()-1;i++){
                            System.out.println("jobrolee"+gotjobroleid );
                            System.out.println("jobroleee"+jobrolelist.get(i));
                            if (jobrolelist.get(i).equals(gotjobroleid)){
                                input_jobrole.setSelection(i);
                            }
                        }
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












    private void Update_Student_detail() {
        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/update_student_data.php";
        System.out.println("sdff"+serverURL);

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("sdff"+response);
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        Intent ii=new Intent(Update_profile.this, Welcome_page.class);
                        startActivity(ii);
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Failed to Update the profile.Please Try Again.",Toast.LENGTH_LONG).show();
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
                System.out.println("Failed to Update the profile.Please Try Again.");
                Toast.makeText(getApplicationContext(), "Failed to Update the profile.Please Try Again.", Toast.LENGTH_LONG).show();
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
                map.put("user_name",User_namee);
                map.put("firstname", input_name.getText().toString());
                map.put("lastname", input_last_name.getText().toString());
                map.put("gender", gender);
                map.put("category", categoryy);
                map.put("address1", input_address.getText().toString());
                map.put("year_of_birth", yearobirth);
                map.put("district_id", districtiddd);
                map.put("state_id", stateiddd);
                map.put("bankname", bankiddd);
                map.put("qualification", eduction1);
                if (other_qualification.getText().toString()!=null){
                map.put("other_qualification",other_qualification.getText().toString() );}
                map.put("bankac", input_bank_ac.getText().toString());
                if (input_aadhar.getText().toString()!=null){
                    map.put("aadhar", input_aadhar.getText().toString());
                }
                if(!input_pann.getText().toString().equals("")){
                map.put("pan", input_pann.getText().toString());}
                map.put("NameAsOnBank", input_bank_username.getText().toString());
                map.put("ifsc", input_ifsc_code.getText().toString());
                map.put("mobile", input_mobile_no.getText().toString());
                map.put("email", Email_update.getText().toString());
                map.put("pincode",input_pincode_update.getText().toString());
                if (type_of_disablity1!=null){
                map.put("disability",type_of_disablity1);}
                if (disablity_type1!=null){
                map.put("disabilitytype",disablity_type1);}

               System.out.println("url is"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }





}
