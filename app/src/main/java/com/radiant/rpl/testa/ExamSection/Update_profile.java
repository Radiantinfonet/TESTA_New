package com.radiant.rpl.testa.ExamSection;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.radiant.rpl.testa.LocaleHelper;
import com.radiant.rpl.testa.MyNetwork;
import com.radiant.rpl.testa.NetworkStateReceiver;
import com.radiant.rpl.testa.Registration.BaseActivity;
import com.radiant.rpl.testa.Welcome_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Update_profile extends BaseActivity {

    Spinner category,myspinner,yearofbirth,bankname,state,district,education,employer,sector,disablity_type,type_of_disablity,input_jobrole,
            Employment_status;

    EditText input_name,input_last_name,input_mobile_no,input_aadhar,input_bank_ac,input_ifsc_code,
            input_bank_username,input_pann,other_qualification,input_address,Email_update,input_pincode_update;


    SharedPreferences sharedpreferences,language_prefs;

    String yearobirth,eduction1,bankname1,state1,district1,bankiddd,stateiddd,districtiddd,Stateid,Statevalue,
            bankid,bankvalue,districtid,districtvalue,selectedstatetext,gotbankid,gotstateid,gotdistrictid,employer1,sector1,
            employeridname,sectoridd,jobroleeiddd,jobrole1,sectorid,sectorvalue, employerid,employervalue,jobroleid,jobrolevalue,Employment_status1,
            disablity_type1, type_of_disablity1,gotEmailid,gotpincode,gotjobroleid,gotsectorid,gotcompanyid,gotcompanyid1,selected_language;




    TextInputLayout mobile, first_name_input, last_name_input, email_id1, Address_line1,pincode,
            AAdhar_no, pancard_no, Bank_Ac_no, name_in_bank_input, ifsc_code,other_qualification1;

    TextView update_profile_textview,yob,decl2;


    String[] spinner_msg_Eduction, spinner_msg_category, spinner_msg_any_disablity, spinner_msg_any_disablity_type,
            spinner_msg_gender, other_id_proof,spinner_msg_Year,banks,states,districts,employers,jobrole,sectors;




    String  toast_msg2,
            toast_msg3, toast_msg4, toast_msg5, toast_msg6, toast_msg7, toast_msg8,toast_msg9,
            toast_msg10, toast_msg11, toast_msg12, toast_msg13, toast_msg14, toast_msg15,toast_msg16,
            toast_msg17, toast_msg18, toast_msg19, toast_msg20, toast_msg21, toast_msg22,toast_msg23,toast_msg24,toast_msg25,
            spinner_msg_Eduction_other, spinner_msg_any_disablity_yes,emp_statuss;





    ArrayAdapter<String> jobroleadapter;


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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
        banks = new String[]{getString(R.string.Select_Bank)};










        Resources res = getResources();

        sectors=res.getStringArray(R.array.Amway_Select_the_Sector);
       // preflangg=res.getStringArray(R.array.Amway_Select_the_PrefferedLanguage);
        states=res.getStringArray(R.array.Amway_Select_the_State);
        districts=res.getStringArray(R.array.Amway_Select_the_District);
        employers=res.getStringArray(R.array.Amway_Select_the_Employer);
        jobrole=res.getStringArray(R.array.Amway_Select_the_Jobrole);




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



        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }







        //TextInputLayout

        mobile = findViewById(R.id.input_layout_mobile_no_update);
        first_name_input = findViewById(R.id.input_layout_name_update);
        last_name_input = findViewById(R.id.input_layout_last_name_update);
        email_id1 = findViewById(R.id.input_layout_email_update);
        Address_line1 = findViewById(R.id.input_layout_address1_update);
        pincode = findViewById(R.id.input_layout_pincode_update);
        AAdhar_no = findViewById(R.id.input_layout_aadhar_update);
        pancard_no = findViewById(R.id.input_layout_pan_update);
        Bank_Ac_no = findViewById(R.id.input_layout_bank_ac_update);
        name_in_bank_input = findViewById(R.id.input_layout_bank_username_update);
        ifsc_code = findViewById(R.id.input_layout_ifsc_code_update);
        other_qualification1 = findViewById(R.id.input_layout_Eduction_other_update);


        update_profile_textview = findViewById(R.id.update_profile_textview);
        yob  = findViewById(R.id.yob);
        decl2 =   findViewById(R.id.decl2);




        //spinner msg


        spinner_msg_Eduction = res.getStringArray(R.array.Education);
        spinner_msg_category = res.getStringArray(R.array.Category);
        spinner_msg_any_disablity = res.getStringArray(R.array.Disablity);
        spinner_msg_any_disablity_type = res.getStringArray(R.array.type_of_Disablity);
        spinner_msg_gender = res.getStringArray(R.array.gender);
        other_id_proof = res.getStringArray(R.array.other_id);
        spinner_msg_Year = res.getStringArray(R.array.Year);
        spinner_msg_any_disablity_yes = res.getString(R.string.amway_Disablity_yes);
        spinner_msg_Eduction_other = res.getString(R.string.amway_other);




        toast_msg2 =  res.getString(R.string.amway_Gender_Toast);
        toast_msg3 =  res.getString(R.string.amway_categroy_Toast);
        toast_msg4 =  res.getString(R.string.amway_Year_Toast);
        toast_msg5 =  res.getString(R.string.amway_Gender_Toast);
        toast_msg6 =  res.getString(R.string.amway_State_Toast);
        toast_msg7 =  res.getString(R.string.amway_District_Toast);
        toast_msg8 =  res.getString(R.string.amway_Eduction_Toast);
        toast_msg9 =  res.getString(R.string.Select_Bank_Toast);
        toast_msg10 =  res.getString(R.string.amway_other_Employ_Toast);
        toast_msg11 =  res.getString(R.string.amway_Disablity_Toast);
        toast_msg12 =  res.getString(R.string.amway_Disablit_type_Toast);
        toast_msg13 =  res.getString(R.string.amway_pancard_Toast);
        toast_msg14 =  res.getString(R.string.amway_Aadhar_Toast);
        toast_msg15 =  res.getString(R.string.Decleration_Message);
        toast_msg16 =  res.getString(R.string.amway_Internet_Toast);


        toast_msg17 =  res.getString(R.string.Student_Err);
        toast_msg18 =  res.getString(R.string.form_job_role_ERR);
        toast_msg19 =  res.getString(R.string.amway_States_Err__Toast);
        toast_msg20 =  res.getString(R.string.amway_District_Err);
        toast_msg21 =  res.getString(R.string.Bank_Err);
        toast_msg22 =  res.getString(R.string.form_employee_Err);
        toast_msg23 =  res.getString(R.string.form_Sector_Err);
        toast_msg24 =  res.getString(R.string.form_job_role_ERR);
        toast_msg25 =  res.getString(R.string.Form_failed);


















        //changed
        Paper.init(this);
        String language = Paper.book().read("language");
        if (language == null) {
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










        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (gender.equals((getResources().getString(R.string.amway_Gender)))){

                    Toast.makeText(getApplicationContext(), toast_msg2,Toast.LENGTH_LONG).show();
                }
                else if (categoryy.equals(getResources().getString(R.string.amway_categroy))){
                    Toast.makeText(getApplicationContext(),toast_msg3,Toast.LENGTH_LONG).show();
                }
                if(yearobirth.equals(getResources().getString(R.string.amway_year))){
                    Toast.makeText(getApplicationContext(),toast_msg4,Toast.LENGTH_LONG).show();
                }

                else if (gender.equals(getResources().getString(R.string.amway_Gender))){
                    Toast.makeText(getApplicationContext(),toast_msg5,Toast.LENGTH_LONG).show();
                }

                else if (state1.equals(getResources().getString(R.string.amway_state))){
                    Toast.makeText(getApplicationContext(),toast_msg6,Toast.LENGTH_LONG).show();
                }

                else if (district1.equals(getResources().getString(R.string.amway_District))){
                    Toast.makeText(getApplicationContext(),toast_msg7 ,Toast.LENGTH_LONG).show();
                }

                else if (eduction1.equals(getResources().getString(R.string.amway_Eduction))){
                    Toast.makeText(getApplicationContext(), toast_msg8,Toast.LENGTH_LONG).show();
                }


                else if (bankname1.equals(getResources().getString(R.string.Select_Bank))){
                    Toast.makeText(getApplicationContext(),toast_msg9,Toast.LENGTH_LONG).show();

                }

                else if ((eduction1.equals(getResources().getString(R.string.amway_other)))&& (other_qualification.getText().toString().matches(""))){
                    Toast.makeText(getApplicationContext(),toast_msg10,Toast.LENGTH_LONG).show();
                }
                else if (disablity_type1.equals(getResources().getString(R.string.amway_Disablity))){
                    Toast.makeText(getApplicationContext(),toast_msg11,Toast.LENGTH_LONG).show();
                }

                else if ((disablity_type1.equals(getResources().getString(R.string.amway_Disablity_yes))&& (type_of_disablity1.equals(getResources().getString(R.string.amway_type_of_disablity1)))))
                {
                    Toast.makeText(getApplicationContext(),toast_msg12,Toast.LENGTH_LONG).show();
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

                    Toast.makeText(Update_profile.this, toast_msg13, Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(Update_profile.this,toast_msg14, Toast.LENGTH_SHORT).show();
                }

                else if (awesomeValidation.validate()
                        && !(gender.equals((getResources().getString(R.string.amway_Gender))))
                        && !category.equals((getResources().getString(R.string.amway_Category)))
                        && !state1.equals((getResources().getString(R.string.Select_state)))
                        && ! disablity_type1.equals(getResources().getString(R.string.amway_Disablity))
                        && ! ((disablity_type1.equals(getResources().getString(R.string.amway_Disablity_yes))&& (type_of_disablity.equals(getResources().getString(R.string.amway_type_of_disablity1)))))
                        && !yearobirth.equals(getResources().getString(R.string.amway_year))
                        && !district1.equals(getResources().getString(R.string.amway_District))
                        && !eduction1.equals(getResources().getString(R.string.amway_Eduction))
                        && !((eduction1.equals(getResources().getString(R.string.amway_other)))&& (other_qualification.getText().toString().matches("")))
                        && checkBox.isChecked())
                {
                    Update_Student_detail();
                }
                else
                {

                    Toast.makeText(getApplicationContext(), toast_msg15, Toast.LENGTH_LONG).show();
                }



            }
        });



        //Gender

    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Update_profile.this,
                android.R.layout.simple_list_item_1,spinner_msg_gender);
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
                android.R.layout.simple_list_item_1,spinner_msg_category);
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
                android.R.layout.simple_list_item_1,spinner_msg_Year);
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
                android.R.layout.simple_list_item_1,spinner_msg_Eduction);
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(myAdapter4);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                eduction1=education.getSelectedItem().toString();

                if (eduction1.equals(getResources().getString(R.string.amway_other))){
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
                android.R.layout.simple_list_item_1,spinner_msg_any_disablity);
        myAdapterdisablity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        disablity_type.setAdapter(myAdapterdisablity_type);

        disablity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                disablity_type1=disablity_type.getSelectedItem().toString();
                if (disablity_type1.equals(spinner_msg_any_disablity_yes)){
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
                android.R.layout.simple_list_item_1,spinner_msg_any_disablity_type);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this, lang);
        final Resources resources = context.getResources();

        //set Text for Textview heading


        //set hint text for Edittext in form



        mobile.setHint(resources.getString(R.string.hint_mobile));
        first_name_input.setHint(resources.getString(R.string.hint_name));
        last_name_input.setHint(resources.getString(R.string.hint_last_name));
        email_id1.setHint(resources.getString(R.string.hint_email));
        Address_line1.setHint(resources.getString(R.string.hint_address_line1));
        pincode.setHint(resources.getString(R.string.hint_pincode));
        other_qualification1.setHint(resources.getString(R.string.hint_other));
        AAdhar_no.setHint(resources.getString(R.string.hint_aadhar));
        pancard_no.setHint(resources.getString(R.string.hint_pancard));
        Bank_Ac_no.setHint(resources.getString(R.string.hint_bank_ac));
        name_in_bank_input.setHint(resources.getString(R.string.name_as_in_bank));
        ifsc_code.setHint(resources.getString(R.string.hint_ifsc_code));


        update_profile_textview.setText(resources.getString(R.string.update_profile_for_assessment));
        yob.setText(resources.getString(R.string.year_of_birth));
        decl2.setText(resources.getString(R.string.i_am_willing_to_declare_my_aadhar_details_for_pmkvy_rpl4_project));



        input_submit.setText(resources.getString(R.string.update));





        spinner_msg_Eduction = resources.getStringArray(R.array.Education);
        spinner_msg_category = resources.getStringArray(R.array.Category);
        spinner_msg_any_disablity = resources.getStringArray(R.array.Disablity);
        spinner_msg_any_disablity_type = resources.getStringArray(R.array.type_of_Disablity);
        spinner_msg_gender = resources.getStringArray(R.array.gender);
        other_id_proof = resources.getStringArray(R.array.other_id);
        spinner_msg_Year = resources.getStringArray(R.array.Year);
        spinner_msg_any_disablity_yes = resources.getString(R.string.amway_Disablity_yes);
        spinner_msg_Eduction_other = resources.getString(R.string.amway_other);




        toast_msg2 =  resources.getString(R.string.amway_Gender_Toast);
        toast_msg3 =  resources.getString(R.string.amway_categroy_Toast);
        toast_msg4 =  resources.getString(R.string.amway_Year_Toast);
        toast_msg5 =  resources.getString(R.string.amway_Gender_Toast);
        toast_msg6 =  resources.getString(R.string.amway_State_Toast);
        toast_msg7 =  resources.getString(R.string.amway_District_Toast);
        toast_msg8 =  resources.getString(R.string.amway_Eduction_Toast);
        toast_msg9 =  resources.getString(R.string.Select_Bank_Toast);
        toast_msg10 =  resources.getString(R.string.amway_other_Employ_Toast);
        toast_msg11 =  resources.getString(R.string.amway_Disablity_Toast);
        toast_msg12 =  resources.getString(R.string.amway_Disablit_type_Toast);
        toast_msg13 =  resources.getString(R.string.amway_pancard_Toast);
        toast_msg14 =  resources.getString(R.string.amway_Aadhar_Toast);
        toast_msg15 =  resources.getString(R.string.Decleration_Message);
        toast_msg16 =  resources.getString(R.string.amway_Internet_Toast);
        toast_msg17 =  resources.getString(R.string.Student_Err);
        toast_msg18 =  resources.getString(R.string.form_job_role_ERR);
        toast_msg19 =  resources.getString(R.string.amway_States_Err__Toast);
        toast_msg20 =  resources.getString(R.string.amway_District_Err);
        toast_msg21 =  resources.getString(R.string.Bank_Err);
        toast_msg22 =  resources.getString(R.string.form_employee_Err);
        toast_msg23 =  resources.getString(R.string.form_Sector_Err);
        toast_msg24 =  resources.getString(R.string.form_job_role_ERR);
        toast_msg25 =  resources.getString(R.string.Form_failed);







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
                Toast.makeText(getApplicationContext(),toast_msg16,Toast.LENGTH_LONG).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //Update_profile
    private void getStudentProfile() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/test.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
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


                            for (int k = 0; k<=getResources().getStringArray(R.array.gender).length-1; k++){
                                if (getResources().getStringArray(R.array.gender)[k].equals(gender1)){
                                    myspinner.setSelection(k);
                                }}



                            for (int k = 0; k<=getResources().getStringArray(R.array.Category).length-1; k++){
                                if (getResources().getStringArray(R.array.Category)[k].equals(Category1)){
                                    category.setSelection(k);
                                }}



                            for (int k = 0; k<=getResources().getStringArray(R.array.Year).length-1; k++){
                                if (getResources().getStringArray(R.array.Year)[k].equals(year1)){
                                    yearofbirth.setSelection(k);
                                }}




                            for (int k = 0; k<=getResources().getStringArray(R.array.Education).length-1; k++){
                                if (getResources().getStringArray(R.array.Education)[k].equals(education2)){
                                    education.setSelection(k);
                                }}



                            for (int k = 0; k<=getResources().getStringArray(R.array.Disablity).length-1; k++){
                                if (getResources().getStringArray(R.array.Disablity)[k].equals(disablity1)){
                                    disablity_type.setSelection(k);
                                }}


                            for (int k = 0; k<=getResources().getStringArray(R.array.type_of_Disablity).length-1; k++){
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
                        Toast.makeText(getApplicationContext(),toast_msg17,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),toast_msg18, Toast.LENGTH_LONG).show();
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
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void Statedetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_state.php";


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
                        Toast.makeText(getApplicationContext(),toast_msg19,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), toast_msg19, Toast.LENGTH_LONG).show();
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


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_district.php";
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
                                if (districtlist.get(i).equals(gotdistrictid) ) {
                                    district.setSelection(i);
                                }
                            }



                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(), toast_msg20,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),toast_msg20, Toast.LENGTH_LONG).show();
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


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_bank.php";
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
                        if (bankslist.get(i).equals(gotbankid)){
                            bankname.setSelection(i);
                        }}
                        // Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),toast_msg21,Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), toast_msg21 , Toast.LENGTH_LONG).show();
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


                        for (int i=0;i<=employerlist.size()-1;i++){

                            if (employerlist.get(i).equals(gotcompanyid)){
                                employer.setSelection(i);
                            }
                        }




                    }
                    else {
                        Toast.makeText(getApplicationContext(),toast_msg22,Toast.LENGTH_LONG).show();
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
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_sector.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");
                    if (sectorlist.size()>2){
                        sectorlist.clear();
                    }
                    sectorlist.add(getResources().getString(R.string.Choose_Sector));
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
                            if (sectorlist.get(i).equals(gotsectorid)){
                                sector.setSelection(i);
                            }
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), toast_msg23,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),toast_msg23, Toast.LENGTH_LONG).show();
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
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_jobrole.php";


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
                            if (jobrolelist.get(i).equals(gotjobroleid)){
                                input_jobrole.setSelection(i);
                            }
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),toast_msg24,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),toast_msg24 , Toast.LENGTH_LONG).show();
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
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void Update_Student_detail() {
        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/update_student_data.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status= jobj.getString("status");

                    if (status.equals("1")){
                        Intent ii=new Intent(Update_profile.this, Welcome_page.class);
                        startActivity(ii);
                    }

                    else {
                        Toast.makeText(getApplicationContext(),toast_msg25,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),toast_msg25, Toast.LENGTH_LONG).show();
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
