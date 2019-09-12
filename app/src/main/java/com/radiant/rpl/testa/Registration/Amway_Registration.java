package com.radiant.rpl.testa.Registration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
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
import com.radiant.rpl.testa.Barcode_d.SimpleScannerActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Amway_Registration extends BaseActivity {


    Spinner yearofbirth, monthofbirth, dateofbirth, education, employment, employer, sector, state, district, input_jobrole,
            disablity_type, type_of_disablity,
            Employment_status, OtherIdproof,
            input_layout_prefferedlanguage, category, guardiantype, title;
    EditText input_name, input_last_name, input_mobile_no, input_address1, input_Id_no, input_address2, input_pincode, input_aadhar, input_pancard, input_ifsc_code,
            input_empid, input_loc, Email, alt_no, your_city, other_qualification,
            input_fatherfirst_name, input_fatherlast_name, input_guardianfirst_name, input_guardianlast_name, input_Birthplace;
    //ProgressDialog pd;
    String emp_statuss;
    String[] states, districts, employers, jobrole, sectors, preflangg;
    List<String> Statelist, districtlist, sectorlist, employerlist, jobrolelist, preflang;
    HashMap<String, String> bankdetail = new HashMap<>();
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String, String> Statedetail = new HashMap<>();
    HashMap<String, String> districtdetail = new HashMap<>();
    HashMap<String, String> sectordetail = new HashMap<>();
    HashMap<String, String> employerdetail = new HashMap<>();
    HashMap<String, String> employdetail = new HashMap<>();
    HashMap<String, String> langdetail = new HashMap<>();

    CheckBox checkBox;
    // Resources res = getResources();
    //  String[] planets = res.getStringArray(R.array.planets_array);
    //  String[] sectors=getResources().getStringArray(R.array.Amway_Select_the_Sector);
    //new String[]{"Select the Sector"};


    CircleImageView input_photograph, input_aadharpic;
    Button input_submit, input_photograph1, input_aadharpic1,alreadyregistered;
    String Stateid, Statevalue, districtid, districtvalue, selectedstatetext, sectorid, sectorvalue,
            employerid, employervalue, jobroleid, jobrolevalue, preflangid, preflangvalue;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String yearobirth, monthobirth, dateobirth;
    AwesomeValidation awesomeValidation;
    String title1, guardian, gender, eduction1, employment1, employer1, sector1, state1, district1, encodedphoto, encodedphotoaadhar, jobrole1,
            preflang1, categoryy, disablity_type1,
            type_of_disablity1, Employment_status1, OtherIdproof1, newString1;
    String stateiddd, districtiddd, employeridd, employeridname, sectoridd, jobroleeiddd, preflangiddd;
    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ArrayAdapter<String> jobroleadapter;
    private android.app.AlertDialog progressDialog;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String defaultCameraPackage;
    PackageManager packageManager;
    CoordinatorLayout parentv11;
    private static final int ZBAR_CAMERA_PERMISSION = 1;
    String namefromaadhaar_amway;
    Spinner myspinner;


    TextView course_detail, Registration_Details, personal_Details, candidate_Details, Address_Details, qualification_Details,
            Employer_Details, Employment_status_Details, Identification_Details, date_of_birth, decl;


    TextInputLayout mobile, first_name, last_name, email_id1, alternet_mobile_no, enter_your_city, Address_line1, Address_line2, pincode, employee_id, store_location,
            AAdhar_no, pancard_no, other_qualification1, input_layout_Id_no, input_layout_fatherfirst_name_amway, input_layout_fatherlast_name_amway,
            input_layout_guardianfirst_name_amway, input_layout_guardianlast_name_amway, input_layout_Birthplace_amway;


    String[] spinner_msg_Eduction, spinner_msg_category, spinner_msg_any_disablity, spinner_msg_any_disablity_type, spinner_msg_gender, other_id_proof,
            spinner_msg_title,spinner_guradian;


    SharedPreferences language_prefs;
    String selected_language, toast_msg2,
            toast_msg3, toast_msg4, toast_msg5, toast_msg6, toast_msg7, toast_msg8,toast_msg9,
            toast_msg10, toast_msg11, toast_msg12, toast_msg13, toast_msg14, toast_msg15,toast_msg16,
            snackbar_msg1, spinner_msg_Eduction_other, spinner_msg_any_disablity_yes, spinner_msg_other_id_proof;



    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        myspinner = findViewById(R.id.input_layout_gender_amway);
        yearofbirth = findViewById(R.id.input_layout_year_amway);
        category = findViewById(R.id.input_layout_category_amway);
        monthofbirth = findViewById(R.id.input_layout_month_amway);
        dateofbirth = findViewById(R.id.input_layout_date_amway);
        education = findViewById(R.id.input_layout_Education_amway);
        disablity_type = findViewById(R.id.input_layout_Disablity_type_amway);
        type_of_disablity = findViewById(R.id.input_layout_type_of_Disablity_amway);
        Employment_status = findViewById(R.id.employment_status_amway);
        OtherIdproof = findViewById(R.id.otherIdproof_amway);
        input_Id_no = findViewById(R.id.input_Id_no_amway);
        course_detail = findViewById(R.id.course_detail_amway);
        alt_no = findViewById(R.id.input_alt_mobile_no_amway);
        your_city = findViewById(R.id.input_city_amway);
        parentv11 = findViewById(R.id.amwayreg);
        other_qualification = findViewById(R.id.input_Eduction_other_amway);

        input_fatherfirst_name = findViewById(R.id.input_fatherfirst_name_amway);
        input_fatherlast_name = findViewById(R.id.input_fatherlast_name_amway);
        input_guardianfirst_name = findViewById(R.id.input_guardianfirst_name_amway);
        input_guardianlast_name = findViewById(R.id.input_guardianlast_name_amway);
        input_Birthplace = findViewById(R.id.input_Birthplace_amway);
        guardiantype = findViewById(R.id.input_layout_Guardiantype_amway);
        title = findViewById(R.id.input_layout_title_amway);


        //employment=findViewById(R.id.input_layout_Employment);
        employer = findViewById(R.id.input_layout_Employer_amway);
        sector = findViewById(R.id.input_layout_Sector_amway);

        state = findViewById(R.id.input_layout_State_amway);
        district = findViewById(R.id.input_layout_District_amway);
        input_photograph1 = findViewById(R.id.input_photograph1_amway);
        input_aadharpic1 = findViewById(R.id.input_photograph_aadhar1_amway);
        input_photograph = findViewById(R.id.input_photograph_amway);
        input_aadharpic = findViewById(R.id.input_photograph_aadhar_amway);
        input_submit = findViewById(R.id.btn_signup_amway);
        alreadyregistered = findViewById(R.id.btn_already_register);
        input_name = findViewById(R.id.input_name_amway);
        input_last_name = findViewById(R.id.input_last_name_amway);
        input_mobile_no = findViewById(R.id.input_mobile_no_amway);
        input_address1 = findViewById(R.id.input_address1_amway);
        input_address2 = findViewById(R.id.input_address2_amway);
        input_pincode = findViewById(R.id.input_pincode_amway);
        input_jobrole = findViewById(R.id.input_layout_jobrole_amway);
        input_empid = findViewById(R.id.input_empid_amway);
        input_loc = findViewById(R.id.input_loc_amway);
        input_aadhar = findViewById(R.id.input_aadhar_amway);
        input_pancard = findViewById(R.id.input_pancard_amway);
        input_layout_prefferedlanguage = findViewById(R.id.input_layout_prefferedlanguage_amway);
        progressDialog = new SpotsDialog(Amway_Registration.this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox_amway);
        Email = findViewById(R.id.input_email_amway);




        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }





        //textview heading


        Registration_Details = findViewById(R.id.Registration_heading);
        personal_Details = findViewById(R.id.personal_heading);
        candidate_Details = findViewById(R.id.candidates_heading);
        Address_Details = findViewById(R.id.address_heading);
        qualification_Details = findViewById(R.id.qualification_heading);
        Employer_Details = findViewById(R.id.Employer_heading);
        course_detail = findViewById(R.id.course_detail_amway);
        Employment_status_Details = findViewById(R.id.employment_status_heading);
        Identification_Details = findViewById(R.id.identifications_heading);
        date_of_birth = findViewById(R.id.dob);
        decl = findViewById(R.id.dec2);


        //TextInputLayout

        mobile = findViewById(R.id.input_layout_mobile_no);
        first_name = findViewById(R.id.input_layout_name_amway);
        last_name = findViewById(R.id.input_layout_last_name_amway);
        input_layout_fatherfirst_name_amway = findViewById(R.id.input_layout_fatherfirst_name_amway);
        input_layout_fatherlast_name_amway = findViewById(R.id.input_layout_fatherlast_name_amway);
        input_layout_guardianfirst_name_amway = findViewById(R.id.input_layout_guardianfirst_name_amway);
        input_layout_guardianlast_name_amway = findViewById(R.id.input_layout_guardianlast_name_amway);
        email_id1 = findViewById(R.id.input_layout_email_amway);
        alternet_mobile_no = findViewById(R.id.input_layout_alt_mobile_no_amway);
        input_layout_Birthplace_amway = findViewById(R.id.input_layout_Birthplace_amway);
        enter_your_city = findViewById(R.id.input_layout_city);
        Address_line1 = findViewById(R.id.input_layout_address1_amway);
        Address_line2 = findViewById(R.id.input_layout_address2_amway);
        pincode = findViewById(R.id.input_layout_pincode_amway);
        employee_id = findViewById(R.id.input_layout_empid_amway);
        store_location = findViewById(R.id.input_layout_storeloc_amway);
        AAdhar_no = findViewById(R.id.input_layout_aadhar_amway);
        pancard_no = findViewById(R.id.input_layout_pancard_amway);
        other_qualification1 = findViewById(R.id.input_layout_Eduction_other_amway);

        input_layout_Id_no = findViewById(R.id.input_layout_Id_no_amway);









        //spinner


        Resources resources = getResources();


        spinner_msg_Eduction = resources.getStringArray(R.array.Education);
        spinner_msg_category = resources.getStringArray(R.array.Category);
        spinner_msg_any_disablity = resources.getStringArray(R.array.Disablity);
        spinner_msg_any_disablity_type = resources.getStringArray(R.array.type_of_Disablity);
        spinner_msg_gender = resources.getStringArray(R.array.gender);

        spinner_msg_title = resources.getStringArray(R.array.Title);
        spinner_guradian = resources.getStringArray(R.array.guardiantype);


        other_id_proof = resources.getStringArray(R.array.other_id);
        spinner_msg_Eduction_other = resources.getString(R.string.amway_other);
        spinner_msg_any_disablity_yes = resources.getString(R.string.amway_Disablity_yes);
        spinner_msg_other_id_proof = resources.getString(R.string.amway_otheridproof);










        toast_msg2 =  resources.getString(R.string.amway_Year_Toast);
        toast_msg3 =  resources.getString(R.string.amway_Gender_Toast);
        toast_msg4 =  resources.getString(R.string.amway_Title_Toast);
        toast_msg5 =  resources.getString(R.string.amway_Guardian_Toast);
        toast_msg6 =  resources.getString(R.string.amway_categroy_Toast);
        toast_msg7 =  resources.getString(R.string.amway_State_Toast);
        toast_msg8 =  resources.getString(R.string.amway_District_Toast);
        toast_msg9 =  resources.getString(R.string.amway_Eduction_Toast);
        toast_msg10 =  resources.getString(R.string.amway_Employer_Toast);
        toast_msg11 =  resources.getString(R.string.amway_Employer_4_Toast);
        toast_msg12 =  resources.getString(R.string.amway_other_Employ_Toast);
        toast_msg13 =  resources.getString(R.string.amway_Disablity_Toast);
        toast_msg14 =  resources.getString(R.string.amway_Disablit_type_Toast);
        toast_msg15 =  resources.getString(R.string.amway_Disablit_type_Toast);
        toast_msg16 =  resources.getString(R.string.amway_otheridproof_Toast);

        snackbar_msg1 =  resources.getString(R.string.amway_aadhar_snack);








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





        ImageView iv = findViewById(R.id.actionQrCode);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcScanQRCode();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString1 = null;
            } else {
                newString1 = extras.getString("cmp_id");
            }
        } else {
            newString1 = (String) savedInstanceState.getSerializable("cmp_id");
        }


        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_name_amway, "[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_last_name_amway, "[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_address1_amway, "(.|\\s)*\\S(.|\\s)*", R.string.err_msg_for_address1);
        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_pincode_amway, "^[0-9]{6}$", R.string.err_msg_pincode);
        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_mobile_no_amway, "^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Amway_Registration.this, R.id.input_email_amway, Patterns.EMAIL_ADDRESS, R.string.err_msg_email);
        sector.setEnabled(false);
        employer.setEnabled(false);
       /*
        employer.setEnabled(false);
        input_jobrole.setEnabled(false);
        input_empid.setEnabled(false);
        input_loc.setEnabled(false);
        input_layout_prefferedlanguage.setEnabled(false);*/

        Statedetails();
        Employerlist();

        //new code

        Resources res = getResources();

        sectors = res.getStringArray(R.array.Amway_Select_the_Sector);
        preflangg = res.getStringArray(R.array.Amway_Select_the_PrefferedLanguage);
        states = res.getStringArray(R.array.Amway_Select_the_State);
        districts = res.getStringArray(R.array.Amway_Select_the_District);
        employers = res.getStringArray(R.array.Amway_Select_the_Employer);
        jobrole = res.getStringArray(R.array.Amway_Select_the_Jobrole);

        Statelist = new ArrayList<>(Arrays.asList(states));
        districtlist = new ArrayList<>(Arrays.asList(districts));
        sectorlist = new ArrayList<>(Arrays.asList(sectors));
        employerlist = new ArrayList<>(Arrays.asList(employers));
        jobrolelist = new ArrayList<>(Arrays.asList(jobrole));
        preflang = new ArrayList<>(Arrays.asList(preflangg));
        mySwipeRefreshLayout = new SwipeRefreshLayout(getApplicationContext());





        alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiregistered = new Intent(Amway_Registration.this, SignInAct.class);
                startActivity(iiregistered);
            }
        });


        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    if (yearobirth.equals(getResources().getString(R.string.amway_year))) {

                        Toast.makeText(getApplicationContext(),toast_msg2, Toast.LENGTH_LONG).show();

                    } else if (!new VerhoeffAlgorithm().validateVerhoeff(input_aadhar.getText().toString())) {

                        Snackbar.make(parentv11, snackbar_msg1, Snackbar.LENGTH_SHORT).show();

                    } else if (gender.equals(getResources().getString(R.string.amway_Gender))) {

                        Toast.makeText(getApplicationContext(), toast_msg3, Toast.LENGTH_LONG).show();

                    } else if (title1.equals(getResources().getString(R.string.amway_Title))) {

                        Toast.makeText(getApplicationContext(), toast_msg4, Toast.LENGTH_LONG).show();

                    } else if (guardian.equals(getResources().getString(R.string.amway_Guardian))) {

                        Toast.makeText(getApplicationContext(), toast_msg5, Toast.LENGTH_LONG).show();

                    } else if (categoryy.equals(getResources().getString(R.string.amway_categroy))) {

                        Toast.makeText(getApplicationContext(), toast_msg6, Toast.LENGTH_LONG).show();

                    } else if (state1.equals(getResources().getString(R.string.amway_state))) {

                        Toast.makeText(getApplicationContext(), toast_msg7, Toast.LENGTH_LONG).show();

                    } else if (district1.equals(getResources().getString(R.string.amway_District))) {

                        Toast.makeText(getApplicationContext(), toast_msg8, Toast.LENGTH_LONG).show();

                    } else if (eduction1.equals(getResources().getString(R.string.amway_Eduction))) {

                        Toast.makeText(getApplicationContext(),toast_msg9, Toast.LENGTH_LONG).show();

                    } else if (employer1.equals(getResources().getString(R.string.amway_Employer))) {

                        Toast.makeText(getApplicationContext(), toast_msg10, Toast.LENGTH_LONG).show();

                    } else if (employeridname.equals("4") && (input_empid.getText().toString().matches(""))) {

                        Toast.makeText(Amway_Registration.this, toast_msg11, Toast.LENGTH_SHORT).show();

                    } else if ((eduction1.equals(getResources().getString(R.string.amway_other))) && (other_qualification.getText().toString().matches(""))) {

                        Toast.makeText(getApplicationContext(),toast_msg12, Toast.LENGTH_LONG).show();

                    } else if (disablity_type1.equals(getResources().getString(R.string.amway_Disablity))) {
                        Toast.makeText(getApplicationContext(), toast_msg13, Toast.LENGTH_LONG).show();

                    } else if ((disablity_type1.equals(getResources().getString(R.string.amway_Disablity_yes))) && (type_of_disablity1.equals(getResources().getString(R.string.amway_type_of_disablity1)))) {

                        Toast.makeText(getApplicationContext(), toast_msg14, Toast.LENGTH_LONG).show();

                    } else if ((disablity_type1.equals(getResources().getString(R.string.amway_Disablity_yes))) && (type_of_disablity.equals(getResources().getString(R.string.amway_type_of_disablity1)))) {

                        Toast.makeText(getApplicationContext(), toast_msg15, Toast.LENGTH_LONG).show();

                    } else if (!(OtherIdproof1.equals(getResources().getString(R.string.amway_otheridproof))) && (input_Id_no.getText().toString().matches(""))) {

                        Toast.makeText(getApplicationContext(),toast_msg16, Toast.LENGTH_LONG).show();




                    } else if ((stateiddd.equals("2") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("3") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("16") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("17") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("18") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("23") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("19") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("26") && (input_pancard.getText().toString().matches(""))) ||
                            (stateiddd.equals("10") && (input_pancard.getText().toString().matches("")))) {

                        Toast.makeText(Amway_Registration.this, R.string.amway_pancard_Toast, Toast.LENGTH_SHORT).show();


                    } else if ((stateiddd.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("5") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("8") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("11") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("13") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("19") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("20") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("22") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("27") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("30") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("33") && (input_aadhar.getText().toString().matches(""))) ||
                            (stateiddd.equals("34") && (input_aadhar.getText().toString().matches("")))) {

                        Toast.makeText(Amway_Registration.this, R.string.amway_Aadhar_Toast, Toast.LENGTH_SHORT).show();


                    } else if (awesomeValidation.validate()
                            && !(gender.equals(getResources().getString(R.string.amway_Gender)))
                            && !(title1.equals(getResources().getString(R.string.amway_Title)))
                            && !(guardian.equals((getResources().getString(R.string.amway_Guardian))))
                            && !state1.equals((getResources().getString(R.string.amway_state)))
                            && !disablity_type1.equals((getResources().getString(R.string.amway_Disablity)))
                            && !((disablity_type1.equals(R.string.amway_Disablity_yes)) && (type_of_disablity.equals(R.string.amway_type_of_disablity1))
                            && !yearobirth.equals((getResources().getString(R.string.amway_Year)))
                            && !district1.equals((getResources().getString(R.string.amway_District)))
                            && !eduction1.equals((getResources().getString(R.string.amway_Eduction)))
                            && !employer1.equals((getResources().getString(R.string.employerlist)))

                            && !(employeridname.equals("4") && (input_empid.getText().toString().matches("")))
                            && !((eduction1.equals(R.string.amway_other)) && (other_qualification.getText().toString().matches("")))
                            && !(!(OtherIdproof1.equals(R.string.amway_otheridproof)) && (input_Id_no.getText().toString().matches("")))


                            && !(stateiddd.equals("2") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("3") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("16") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("17") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("18") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("23") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("19") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("26") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("10") && (input_pancard.getText().toString().matches("")))
                            && !(stateiddd.equals("4") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("5") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("6") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("7") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("8") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("9") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("11") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("12") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("13") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("14") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("15") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("19") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("20") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("21") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("22") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("24") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("25") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("27") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("28") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("29") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("30") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("31") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("32") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("33") && (input_aadhar.getText().toString().matches("")))
                            && !(stateiddd.equals("34") && (input_aadhar.getText().toString().matches("")))

                            && checkBox.isChecked() && encodedphoto != null)) {


                        SaveDetail();


                    } else {

                        Toast.makeText(getApplicationContext(), R.string.amway_formErr_Toast, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        try {


            input_photograph.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {


                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {


            input_photograph1.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {


                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        input_aadharpic1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                    }
                }

            }
        });


        input_aadharpic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                    }
                }

            }
        });


        //Gender
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_gender);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        myspinner.setAdapter(myAdapter);

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gender = myspinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //title
        ArrayAdapter<String> Nametitle = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_title);
        Nametitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        title.setAdapter(Nametitle);

        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                title1 = title.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //guardiantype
        ArrayAdapter<String> myguardiantype = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1,spinner_guradian );
        myguardiantype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        guardiantype.setAdapter(myguardiantype);

        guardiantype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                guardian = guardiantype.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //disablity_type
        ArrayAdapter<String> myAdapterdisablity_type = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_any_disablity);
        myAdapterdisablity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        disablity_type.setAdapter(myAdapterdisablity_type);

        disablity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                disablity_type1 = disablity_type.getSelectedItem().toString();
                if (disablity_type1.equals(spinner_msg_any_disablity_yes)) {
                    type_of_disablity.setVisibility(View.VISIBLE);
                } else {
                    type_of_disablity.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //type_of_disablity


        ArrayAdapter<String> myAdapter_type_of_disablity = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_any_disablity_type);
        myAdapter_type_of_disablity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type_of_disablity.setAdapter(myAdapter_type_of_disablity);

        type_of_disablity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                type_of_disablity1 = type_of_disablity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //Employment _status


        ArrayAdapter<String> myAdapterEmployment_status = new ArrayAdapter<String>(Amway_Registration.this,

                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Employment_status_string));
        myAdapterEmployment_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Employment_status.setEnabled(false);
        Employment_status.setClickable(false);


        Employment_status.setAdapter(myAdapterEmployment_status);

        Employment_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Employment_status1 = Employment_status.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        // Other_id_Details


        ArrayAdapter<String> myAdapterOtherIdproof = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1,other_id_proof);
        myAdapterOtherIdproof.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        OtherIdproof.setAdapter(myAdapterOtherIdproof);

        OtherIdproof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                OtherIdproof1 = OtherIdproof.getSelectedItem().toString();

                if (position == 0) {
                    input_Id_no.setVisibility(View.GONE);
                } else {
                    input_Id_no.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Year of birth

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Year));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearofbirth.setAdapter(myAdapter1);

        yearofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                yearobirth = yearofbirth.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Month of birth

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Month));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthofbirth.setAdapter(myAdapter2);

        monthofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                monthobirth = monthofbirth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Date of birth

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Date));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dateofbirth.setAdapter(myAdapter3);

        dateofbirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dateobirth = dateofbirth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }


        });

        //Education
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_Eduction);
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(myAdapter4);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                eduction1 = education.getSelectedItem().toString();

                if (eduction1.equals(spinner_msg_Eduction_other)) {
                    other_qualification.setVisibility(View.VISIBLE);
                } else {
                    other_qualification.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //Employer
        ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, employerlist);
        myAdapterEmployer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employer.setAdapter(myAdapterEmployer);

        employer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if (position > 0) {
                    employer1 = employer.getSelectedItem().toString();
                    employeridname = employdetail.get(employer1);

                    Sectorlist(employeridname);
                    languageSelect(employeridname);
                    getJobroleList(employeridname);

                    sector.setVisibility(View.VISIBLE);
                    input_layout_prefferedlanguage.setVisibility(View.VISIBLE);
                    input_jobrole.setVisibility(View.VISIBLE);
                    course_detail.setVisibility(View.VISIBLE);


                } else {
                    employer1 = employer.getSelectedItem().toString();
                    sector.setVisibility(View.GONE);
                    input_layout_prefferedlanguage.setVisibility(View.GONE);
                    input_jobrole.setVisibility(View.GONE);
                    course_detail.setVisibility(View.GONE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Sector

        ArrayAdapter<String> myAdaptersector = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, sectorlist);
        myAdaptersector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sector.setAdapter(myAdaptersector);

        sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                sector1 = sector.getSelectedItem().toString();
                String selectedsectortext = (String) parent.getItemAtPosition(position);

                if (position > 0) {
                    sectoridd = sectordetail.get(selectedsectortext);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //jobrole


        input_jobrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // if(position > 0) {
                jobrole1 = input_jobrole.getSelectedItem().toString();
                jobroleeiddd = Jobrolelist.get(jobrole1);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //Choose category
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, spinner_msg_category);
        categoryadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(categoryadapt);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // if(position > 0) {
                categoryy = category.getSelectedItem().toString();
                //jobroleeiddd=Jobrolelist.get(jobrole1);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //Preffered Exam Language


        input_layout_prefferedlanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                preflang1 = input_layout_prefferedlanguage.getSelectedItem().toString();
                preflangiddd = langdetail.get(preflang1);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        //state

        ArrayAdapter<String> myAdapterState = new ArrayAdapter<String>(Amway_Registration.this,
                android.R.layout.simple_list_item_1, Statelist);
        myAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(myAdapterState);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                state1 = state.getSelectedItem().toString();
                selectedstatetext = (String) parent.getItemAtPosition(position);

                if (position > 0) {
                    String value = Statedetail.get(selectedstatetext);
                    stateiddd = value;
                    DistrictDetails(value);
                    district.setVisibility(View.VISIBLE);


                } else {
                    district.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });


        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                district1 = district.getSelectedItem().toString();
                districtiddd = districtdetail.get(district1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this, lang);
        final Resources resources = context.getResources();

        //set Text for Textview heading
        Registration_Details.setText(resources.getString(R.string.registration_details));
        personal_Details.setText(resources.getString(R.string.personal_details));
        candidate_Details.setText(resources.getString(R.string.candidate_details));
        Address_Details.setText(resources.getString(R.string.address_details));
        qualification_Details.setText(resources.getString(R.string.qualification));
        Employer_Details.setText(resources.getString(R.string.employer_details));
        Employment_status_Details.setText(resources.getString(R.string.employment_status));
        Identification_Details.setText(resources.getString(R.string.identification_details));
        course_detail.setText(resources.getString(R.string.course_details));
        date_of_birth.setText(resources.getString(R.string.date_of_birth));
        decl.setText(resources.getString(R.string.i_am_willing_to_declare_my_aadhar_details_for_pmkvy_rpl4_project));


        //textview text in form
        input_photograph1.setText(resources.getString(R.string.upload_your_photo));
        input_aadharpic1.setText(resources.getString(R.string.upload_your_aadhar));
        input_submit.setText(resources.getString(R.string.register));
        alreadyregistered.setText(resources.getString(R.string.already_registered));


        //set hint text for Edittext in form

        mobile.setHint(resources.getString(R.string.hint_mobile));
        first_name.setHint(resources.getString(R.string.hint_name));
        last_name.setHint(resources.getString(R.string.hint_last_name));
        email_id1.setHint(resources.getString(R.string.hint_email));
        alternet_mobile_no.setHint(resources.getString(R.string.alt_hint_mobile));
        enter_your_city.setHint(resources.getString(R.string.hint_city));
        Address_line1.setHint(resources.getString(R.string.hint_address_line1));
        Address_line2.setHint(resources.getString(R.string.hint_address_line2));
        pincode.setHint(resources.getString(R.string.hint_pincode));
        other_qualification1.setHint(resources.getString(R.string.hint_other));
        employee_id.setHint(resources.getString(R.string.hint_empid));
        store_location.setHint(resources.getString(R.string.hint_location));
        AAdhar_no.setHint(resources.getString(R.string.hint_aadhar));
        pancard_no.setHint(resources.getString(R.string.hint_pancard));

        input_layout_Id_no.setHint(resources.getString(R.string.hint_other_id));


        input_layout_fatherfirst_name_amway.setHint(resources.getString(R.string.father_first_name));
        input_layout_fatherlast_name_amway.setHint(resources.getString(R.string.father_last_name));
        input_layout_guardianfirst_name_amway.setHint(resources.getString(R.string.guardian_first_name));
        input_layout_guardianlast_name_amway.setHint(resources.getString(R.string.guardian_last_name));
        input_layout_Birthplace_amway.setHint(resources.getString(R.string.birth_place));





        // static spinner
        spinner_msg_Eduction = resources.getStringArray(R.array.Education);
        spinner_msg_category = resources.getStringArray(R.array.Category);
        spinner_msg_any_disablity = resources.getStringArray(R.array.Disablity);
        spinner_msg_any_disablity_type = resources.getStringArray(R.array.type_of_Disablity);
        spinner_msg_gender = resources.getStringArray(R.array.gender);
        other_id_proof = resources.getStringArray(R.array.other_id);
        spinner_msg_Eduction_other = resources.getString(R.string.amway_other);
        spinner_msg_any_disablity_yes = resources.getString(R.string.amway_Disablity_yes);
        spinner_msg_other_id_proof = resources.getString(R.string.amway_otheridproof);

        spinner_msg_title = resources.getStringArray(R.array.Title);
        spinner_guradian = resources.getStringArray(R.array.guardiantype);



        //error msg

        //   error_msg1 = resources.getString(R.string.err_msg_for_first_name);






        /*21/8/2019 */


        toast_msg2 =  resources.getString(R.string.amway_Year_Toast);
        toast_msg3 =  resources.getString(R.string.amway_Gender_Toast);
        toast_msg4 =  resources.getString(R.string.amway_Title_Toast);
        toast_msg5 =  resources.getString(R.string.amway_Guardian_Toast);
        toast_msg6 =  resources.getString(R.string.amway_categroy_Toast);
        toast_msg7 =  resources.getString(R.string.amway_State_Toast);
        toast_msg8 =  resources.getString(R.string.amway_District_Toast);
        toast_msg9 =  resources.getString(R.string.amway_Eduction_Toast);
        toast_msg10 =  resources.getString(R.string.amway_Employer_Toast);
        toast_msg11 =  resources.getString(R.string.amway_Employer_4_Toast);
        toast_msg12 =  resources.getString(R.string.amway_other_Employ_Toast);
        toast_msg13 =  resources.getString(R.string.amway_Disablity_Toast);
        toast_msg14 =  resources.getString(R.string.amway_Disablit_type_Toast);
        toast_msg15 =  resources.getString(R.string.amway_Disablit_type_Toast);
        toast_msg16 =  resources.getString(R.string.amway_otheridproof_Toast);

        snackbar_msg1 =  resources.getString(R.string.amway_aadhar_snack);





    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amway__registration;
    }


    @Override
    protected void onResume() {
        super.onResume();
        networkStateReceiver = new NetworkStateReceiver(new NetworkStateReceiver.NetworkListener() {
            @Override
            public void onNetworkAvailable() {
                input_submit.setEnabled(true);
            }

            @Override
            public void onNetworkUnavailable() {
                input_submit.setEnabled(false);
                Toast.makeText(getApplicationContext(), R.string.amway_Internet_Toast, Toast.LENGTH_LONG).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //call qr code
    private void funcScanQRCode() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent ii = new Intent(Amway_Registration.this, SimpleScannerActivity.class);
            startActivityForResult(ii, 1);
        }
    }

    //Language Api Call
    private void languageSelect(final String cmp_id) {

        progressDialog.show();
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_language.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jobj = new JSONObject(response);

                    String status = jobj.getString("status");

                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("language");
                        preflang.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            preflangid = c.getString("language_code");
                            preflangvalue = c.getString("name");
                            langdetail.put(preflangvalue, preflangid);
                            preflang.add(preflangvalue);
                        }
                        ArrayAdapter<String> preflanguage = new ArrayAdapter<String>(Amway_Registration.this,
                                android.R.layout.simple_list_item_1, preflang);
                        preflanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_layout_prefferedlanguage.setAdapter(preflanguage);

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.amway_Language_Err_Toast, Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), R.string.amway_Language_Err_Toast, Toast.LENGTH_LONG).show();
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
                map.put("company_id", cmp_id);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    //State List
    private void Statedetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_state.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status = jobj.getString("status");
                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("state");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            Stateid = c.getString("id");
                            Statevalue = c.getString("name");
                            Statedetail.put(Statevalue, Stateid);
                            Statelist.add(Statevalue);
                        }
                        //Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.amway_States_Err__Toast, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), R.string.amway_States_Err__Toast, Toast.LENGTH_LONG).show();
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
                    String status = jobj.getString("status");

                    if (status.equals("1")) {
                        districtlist.clear();
                        JSONArray jsonArray = jobj.getJSONArray("district");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            districtid = c.getString("id");
                            districtvalue = c.getString("name");
                            districtdetail.put(districtvalue, districtid);
                            districtlist.add(districtvalue);


                        }
                        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(Amway_Registration.this,
                                android.R.layout.simple_list_item_1, districtlist);
                        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        district.setAdapter(myAdapterDistrict);
                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), (R.string.amway_District_Err), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), (R.string.amway_District_Err), Toast.LENGTH_LONG).show();
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
                map.put("state_id", districtidd);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Employer_List
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

                    String status = jobj.getString("status");
                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("employer");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            employerid = c.getString("id");
                            employervalue = c.getString("name");
                            employdetail.put(employervalue, employerid);
                            employerlist.add(employervalue);
                        }


                        for (int i = 0; i <= employerlist.size() - 1; i++) {


                            if (employerlist.get(i).equals(newString1)) {
                                employer.setSelection(i);
                            }
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), (R.string.form_employee_Err), Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
                    String status = jobj.getString("status");
                    if (sectorlist.size() > 2) {
                        sectorlist.clear();
                    }
                    sectorlist.add("Choose the Sector");
                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("sector");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            sectorid = c.getString("id");
                            sectorvalue = c.getString("name");
                            sectordetail.put(sectorvalue, sectorid);
                            sectorlist.add(sectorvalue);
                            sector.setSelection(sectorlist.size() - 1);

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), (R.string.form_Sector_Err), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), (R.string.form_Sector_Err), Toast.LENGTH_LONG).show();
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
                map.put("company_id", Sectorvalue);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    //Jobrole Api Call
    private void getJobroleList(final String sscid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_jobrole.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jobj = new JSONObject(response);
                    String status = jobj.getString("status");
                    emp_statuss = jobj.getString("emp_status");

                    ArrayAdapter myAdap = (ArrayAdapter) Employment_status.getAdapter(); //cast to an ArrayAdapter
                    int spinnerPosition = myAdap.getPosition(emp_statuss);

//set the default according to value
                    Employment_status.setSelection(spinnerPosition);

                    /*if (jobrolelist.size()<=1){
                        jobrolelist.clear();
                    }*/
                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("jobrole");
                        jobrolelist.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            jobroleid = c.getString("jobrole_id");
                            jobrolevalue = c.getString("name");
                            Jobrolelist.put(jobrolevalue, jobroleid);
                            jobrolelist.add(jobrolevalue);
                        }

                        jobroleadapter = new ArrayAdapter<String>(Amway_Registration.this,
                                android.R.layout.simple_list_item_1, jobrolelist);
                        jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_jobrole.setAdapter(jobroleadapter);


                    } else {
                        Toast.makeText(getApplicationContext(), (R.string.form_job_role_ERR), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), (R.string.form_job_role_ERR), Toast.LENGTH_LONG).show();
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
                map.put("company_id", sscid);

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                // cameraIntent.setPackage(defaultCameraPackage);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else {
                    Toast.makeText(getApplicationContext(), (R.string.Photo_Message), Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    private void SaveDetail() {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect/save_student_data.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    String status = jobj.getString("status");
                    if (status.equals("1")) {
                        Intent iii = new Intent(Amway_Registration.this, Registration_Done.class);
                        //  iii.putExtra("sectorid",sectorr);
                        // iii.putExtra("mobileeno",mobbb);
                        startActivity(iii);


                        //Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), (R.string.Saving_Err), Toast.LENGTH_LONG).show();
                        Log.d("Response", response);
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
                Toast.makeText(getApplicationContext(), (R.string.Saving_Err), Toast.LENGTH_LONG).show();


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


                map.put("mobile", input_mobile_no.getText().toString());
                map.put("aadhar", input_aadhar.getText().toString());
                map.put("salutation", title1);
                map.put("firstname", input_name.getText().toString());
                map.put("lastname", input_last_name.getText().toString());
                map.put("fathername", input_fatherfirst_name.getText().toString());
                map.put("father_last_name", input_fatherlast_name.getText().toString());
                map.put("guardian_type", guardian);
                map.put("guardian_first_name", input_guardianfirst_name.getText().toString());
                map.put("guardian_last_name", input_guardianlast_name.getText().toString());
                map.put("gender", gender);

                if (categoryy != null) {
                    map.put("category", categoryy);
                }


                if (Email.getText().toString() != null) {
                    map.put("email", Email.getText().toString());
                }


                if (alt_no.getText().toString() != null) {
                    map.put("landline", alt_no.getText().toString());
                }


                if (disablity_type1 != null) {
                    map.put("disabilitytype", disablity_type1);
                }


                if (type_of_disablity1 != null) {
                    map.put("disability", type_of_disablity1);
                }


                if (monthobirth != null || dateobirth != null) {
                    map.put("dob", yearobirth + "-" + monthobirth + "-" + dateobirth);
                } else {
                    map.put("year_of_birth", yearobirth);
                }

                map.put("birth_place", input_Birthplace.getText().toString());
                map.put("state_id", stateiddd);
                if (districtiddd != null) {
                    map.put("district_id", districtiddd);
                }


                if (your_city.getText().toString() != null) {
                    map.put("City", your_city.getText().toString());
                }


                map.put("address1", input_address1.getText().toString());


                if (input_address2.getText().toString() != null) {
                    map.put("address2", input_address2.getText().toString());
                }

                map.put("pincode", input_pincode.getText().toString());

                map.put("qualification", eduction1);


                if (other_qualification.getText().toString() != null) {
                    map.put("other_qualification", other_qualification.getText().toString());
                }


                map.put("company_id", employeridname);

                map.put("ssc_id", sectoridd);

                if (input_empid.getText().toString() != null) {
                    map.put("employee_id", input_empid.getText().toString());
                }


                if (input_loc.getText().toString() != null) {
                    map.put("StoreLocation", input_loc.getText().toString());
                }


                map.put("jobrole_id", jobroleeiddd);
                map.put("language", preflangiddd);

                if (Employment_status1 != null) {
                    map.put("employment", Employment_status1);
                }


                map.put("pan", input_pancard.getText().toString());

                if (OtherIdproof1 != null) {
                    map.put("other_id_type", OtherIdproof1);
                }
                if (input_Id_no.getText().toString() != null) {
                    map.put("other_id_number", input_Id_no.getText().toString());
                }


                if (encodedphotoaadhar != null) {
                    map.put("aadhar_image", encodedphotoaadhar);
                }

                if (encodedphoto != null) {
                    map.put("student_image", encodedphoto);
                }
                Log.v("PARAMS", map.toString());


                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            if (resultCode == 2 && requestCode == 1) {
                //do something
                HashMap<String, String> map = new HashMap<>();
                Bundle extras = data.getExtras();
                String ss[] = extras.getStringArray("ss");
                String uid_data = ss[1].replace("encoding=\"UTF-8\"?>\n<PrintLetterBarcodeData ", "");
                ss[1] = uid_data;
                for (String s : ss) {
                    String[] sd = s.split("=");
                    Log.d("dataaa", sd[0]);
                    Log.d("dataaa", sd[1]);
                    map.put(sd[0], sd[1]);
                    //map.put("value", sd[1]);
                    //Toast.makeText(this,"result"+ss[0]+" "+ss[1],Toast.LENGTH_LONG).show();
                }

                if (map.get("name") != null) {
                    namefromaadhaar_amway = map.get("name").replace("\"", "");
                    String namee[] = namefromaadhaar_amway.split(" ");
                    input_name.setEnabled(false);
                    input_last_name.setEnabled(false);
                    input_name.setText(namee[0]);
                    input_last_name.setText(namee[1]);
                }
                if (map.get("uid") != null) {
                    input_aadhar.setEnabled(false);
                    input_aadhar.setText(map.get("uid").replace("\"", ""));

                }

                if (map.get("gender") != null) {
                    String genderr = map.get("gender").replace("\"", "");
                    if (genderr == "M") {
                        myspinner.setSelection(2);
                    } else if (genderr == "F") {
                        myspinner.setSelection(3);

                    }
                }
                if (map.get("pc") != null) {
                    input_pincode.setText(map.get("pc").replace("/>", "").replace("\"", ""));
                    input_pincode.setEnabled(false);
                }
                if (map.get("house") != null) {
                    input_address1.setText(map.get("house").replace("\"", ""));
                    input_address1.setEnabled(false);
                }
                if (map.get("co") != null) {
                    String guardiannamefromaadhaar_amway = map.get("co").replace("\"", "");
                    //String guardiannamefromaadhaar_amwayreplace=guardiannamefromaadhaar_amway.replace("S/O","");
                    String namee[] = guardiannamefromaadhaar_amway.split(" ");
                    input_guardianfirst_name.setEnabled(false);
                    input_guardianlast_name.setEnabled(false);
                    input_guardianfirst_name.setText(namee[1]);
                    input_guardianlast_name.setText(namee[2]);
                }

                if (map.get("lm") != null) {
                    input_address2.setText(map.get("lm").replace("\"", ""));
                    input_address2.setEnabled(false);
                }


                if (map.get("subdist") != null) {
                    your_city.setText(map.get("subdist").replace("\"", ""));
                    your_city.setEnabled(false);
                }
            } else {
                // Toast.makeText(this,"aaaaa",Toast.LENGTH_LONG).show();
                //do something else
            }
        } catch (Exception e) {

        }


        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                if (data.getExtras() == null || (data.getExtras().get("data") == null || !(data.getExtras().get("data") instanceof Bitmap))) {
                    //todo - show error
                    Toast.makeText(getApplicationContext(), R.string.Photo_Message, Toast.LENGTH_LONG).show();
                    return;
                }
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                int currentBitmapWidth = photo.getWidth();
                int currentBitmapHeight = photo.getHeight();
                input_photograph.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedphoto = Base64.encodeToString(byteArray, Base64.DEFAULT);

            }

            if (requestCode == CAMERA_AADHAR_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                int currentBitmapWidth = photo.getWidth();
                int currentBitmapHeight = photo.getHeight();
                mySwipeRefreshLayout.setRefreshing(false);
                input_aadharpic.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedphotoaadhar = Base64.encodeToString(byteArray, Base64.DEFAULT);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Employerlist();
    }


}
