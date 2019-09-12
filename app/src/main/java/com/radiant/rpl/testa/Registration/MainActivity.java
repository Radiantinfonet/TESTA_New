package com.radiant.rpl.testa.Registration;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
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
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
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
import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;


public class MainActivity extends BaseActivity {


    Paint myRectPaint;

    Spinner yearofbirth, monthofbirth, dateofbirth, education, employer, sector, bankname, state, district, input_jobrole,
            disablity_type, type_of_disablity,
            Employment_status, OtherIdproof,
            input_layout_prefferedlanguage, category;

    EditText input_name, input_last_name, input_mobile_no, input_address1, input_Id_no, input_address2, input_pincode, input_aadhar, input_pancard, input_bank_ac, input_ifsc_code,
            input_bank_username, input_empid, input_loc, Email, alt_no, your_city, other_qualification;


    String gender, eduction1, employer1, sector1, bankname1, state1, district1, encodedphoto, encodedphotoaadhar, jobrole1,
            preflang1, categoryy, disablity_type1,
            type_of_disablity1, Employment_status1, OtherIdproof1, bankiddd, stateiddd, districtiddd,
            employeridname, sectoridd, jobroleeiddd, preflangiddd, cmp_id, language_hi, namefromaadhaar_main, emp_statuss,
            Stateid, Statevalue, bankid, bankvalue, districtid, districtvalue, selectedstatetext, sectorid, sectorvalue,
            employerid, employervalue, jobroleid, jobrolevalue, preflangid, preflangvalue, newString2, spinner_msg_Eduction_other, spinner_msg_any_disablity_yes, spinner_msg_other_id_proof,
            error_msg1, toast_msg2,
            toast_msg3, toast_msg4, toast_msg5, toast_msg6, toast_msg7, toast_msg8,toast_msg9,
            toast_msg10, toast_msg11, toast_msg12, toast_msg13, toast_msg14, toast_msg15,toast_msg16,
            snackbar_msg1,snackbar_msg2,snackbar_msg3,snackbar_msg4;


    CoordinatorLayout parentv;



    SharedPreferences language_prefs;
    String selected_language;


    String[] banks, states, districts, employers, jobrole, sectors, preflangg;
    List<String> bankslist, Statelist, districtlist, sectorlist, employerlist, jobrolelist, preflang;
    HashMap<String, String> bankdetail = new HashMap<>();
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String, String> Statedetail = new HashMap<>();
    HashMap<String, String> districtdetail = new HashMap<>();
    HashMap<String, String> sectordetail = new HashMap<>();
    HashMap<String, String> employdetail = new HashMap<>();
    HashMap<String, String> langdetail = new HashMap<>();

    CheckBox checkBox;

    CircleImageView input_photograph, input_aadharpic;

    Button input_submit, input_photograph1, input_aadharpic1, alreadyregistered;

    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String yearobirth, monthobirth, dateobirth;
    AwesomeValidation awesomeValidation;

    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ArrayAdapter<String> jobroleadapter;
    SparseArray<Face> faces;
    @IdRes
    int resId;

    private static final int ZBAR_CAMERA_PERMISSION = 1;


    TextView course_detail, Registration_Details, personal_Details, candidate_Details, Address_Details, qualification_Details,
            Employer_Details, Employment_status_Details, Identification_Details, Bank_Details, date_of_birth, decl;


    TextInputLayout mobile, first_name, last_name, email_id1, alternet_mobile_no, enter_your_city, Address_line1, Address_line2, pincode, employee_id, store_location,
            AAdhar_no, pancard_no, Bank_Ac_no, name_in_bank, ifsc_code, other_qualification1, input_layout_Id_no;

    String[] spinner_msg_Eduction, spinner_msg_category, spinner_msg_any_disablity, spinner_msg_any_disablity_type, spinner_msg_gender, other_id_proof;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        final Spinner myspinner = findViewById(R.id.input_layout_gender);
        parentv = findViewById(R.id.register_yourself);
        yearofbirth = findViewById(R.id.input_layout_year);
        category = findViewById(R.id.input_layout_category);
        monthofbirth = findViewById(R.id.input_layout_month);
        dateofbirth = findViewById(R.id.input_layout_date);
        education = findViewById(R.id.input_layout_Education);
        disablity_type = findViewById(R.id.input_layout_Disablity_type);
        type_of_disablity = findViewById(R.id.input_layout_type_of_Disablity);
        Employment_status = findViewById(R.id.employment_status);
        OtherIdproof = findViewById(R.id.otherIdproof);
        alreadyregistered = findViewById(R.id.btn_already_register);
        input_Id_no = findViewById(R.id.input_Id_no);
        alt_no = findViewById(R.id.input_alt_mobile_no);
        your_city = findViewById(R.id.input_city);
        other_qualification = findViewById(R.id.input_Eduction_other);
        employer = findViewById(R.id.input_layout_Employer);
        sector = findViewById(R.id.input_layout_Sector);
        bankname = findViewById(R.id.input_layout_bankname);
        state = findViewById(R.id.input_layout_State);
        district = findViewById(R.id.input_layout_District);
        input_photograph1 = findViewById(R.id.input_photograph1);
        input_aadharpic1 = findViewById(R.id.input_photograph_aadhar1);
        input_photograph = findViewById(R.id.input_photograph);
        input_aadharpic = findViewById(R.id.input_photograph_aadhar);
        input_submit = findViewById(R.id.btn_signup);
        input_name = findViewById(R.id.input_name);
        input_last_name = findViewById(R.id.input_last_name);
        input_mobile_no = findViewById(R.id.input_mobile_no);
        input_address1 = findViewById(R.id.input_address1);
        input_address2 = findViewById(R.id.input_address2);
        input_pincode = findViewById(R.id.input_pincode);
        input_jobrole = findViewById(R.id.input_layout_jobrole);
        input_empid = findViewById(R.id.input_empid);
        input_loc = findViewById(R.id.input_loc);
        input_aadhar = findViewById(R.id.input_aadhar);
        input_pancard = findViewById(R.id.input_pancard);
        input_bank_ac = findViewById(R.id.input_bank_ac);
        input_ifsc_code = findViewById(R.id.input_ifsc_code);
        input_bank_username = findViewById(R.id.input_bank_username);
        input_layout_prefferedlanguage = findViewById(R.id.input_layout_prefferedlanguage);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox);
        Email = findViewById(R.id.input_email);






        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }






        /*date:-16/8/2019
         * changes in textview
         * */

        Registration_Details = findViewById(R.id.Registration_detail);
        personal_Details = findViewById(R.id.personal_details);
        candidate_Details = findViewById(R.id.candidate_details);
        Address_Details = findViewById(R.id.address_details);
        qualification_Details = findViewById(R.id.qualification);
        Employer_Details = findViewById(R.id.Employer_details);
        Bank_Details = findViewById(R.id.bank_details);
        course_detail = findViewById(R.id.course_detail);
        Employment_status_Details = findViewById(R.id.employment_status_detail);
        Identification_Details = findViewById(R.id.identification_details);
        date_of_birth = findViewById(R.id.date_of_birth);
        decl = findViewById(R.id.decl);


        /*date:-19/8/2019
         * changes in method
         * */


        //TextInputLayout

        mobile = findViewById(R.id.input_layout_mobile_no);
        first_name = findViewById(R.id.input_layout_name);
        last_name = findViewById(R.id.input_layout_last_name);
        email_id1 = findViewById(R.id.input_layout_email);
        alternet_mobile_no = findViewById(R.id.input_layout_alt_mobile_no);
        enter_your_city = findViewById(R.id.input_layout_city);
        Address_line1 = findViewById(R.id.input_layout_address1);
        Address_line2 = findViewById(R.id.input_layout_address2);
        pincode = findViewById(R.id.input_layout_pincode);
        employee_id = findViewById(R.id.input_layout_empid);
        store_location = findViewById(R.id.input_layout_storeloc);
        AAdhar_no = findViewById(R.id.input_layout_aadhar);
        pancard_no = findViewById(R.id.input_layout_pancard);
        Bank_Ac_no = findViewById(R.id.input_layout_bank_ac);
        name_in_bank = findViewById(R.id.input_layout_bank_username);
        ifsc_code = findViewById(R.id.input_layout_ifsc_code);
        other_qualification1 = findViewById(R.id.input_layout_Eduction_other);
        input_layout_Id_no = findViewById(R.id.input_layout_Id_no);


        //spinner msg

        Resources resources = getResources();
        spinner_msg_Eduction = resources.getStringArray(R.array.Education);
        spinner_msg_category = resources.getStringArray(R.array.Category);
        spinner_msg_any_disablity = resources.getStringArray(R.array.Disablity);
        spinner_msg_any_disablity_type = resources.getStringArray(R.array.type_of_Disablity);
        spinner_msg_gender = resources.getStringArray(R.array.gender);
        other_id_proof = resources.getStringArray(R.array.other_id);
        spinner_msg_Eduction_other = resources.getString(R.string.amway_other);
        spinner_msg_any_disablity_yes = resources.getString(R.string.amway_Disablity_yes);
        spinner_msg_other_id_proof = resources.getString(R.string.amway_otheridproof);

        resId = getResources().getIdentifier("err_msg_for_first_name", "string", getPackageName());
        System.out.println("ideededed" + resId);
        @SuppressLint("ResourceType") String s = resources.getString(resId);
        System.out.println(s + "ideededed");


        /*date:-20/8/2019
         * changes in error msg
         * */


        error_msg1 = getString(R.string.err_msg_for_first_name);
        getResources().getString(R.string.Photo_Err_Message);




        /*date:-21/8/2019
         * changes in error msg
         * */


        toast_msg2 =  resources.getString(R.string.amway_Employer_4_Toast);
        toast_msg3 =  resources.getString(R.string.amway_other_Employ_Toast);
        toast_msg4 =  resources.getString(R.string.amway_Disablity_Toast);
        toast_msg5 =  resources.getString(R.string.amway_pancard_Toast);
        toast_msg6 =  resources.getString(R.string.amway_Aadhar_Toast);
        toast_msg7 =  resources.getString(R.string.Bank_Err);
        toast_msg8 =  resources.getString(R.string.amway_Language_Err_Toast);
        toast_msg9 =  resources.getString(R.string.amway_States_Err__Toast);
        toast_msg10 =  resources.getString(R.string.amway_District_Err);
        toast_msg11 =  resources.getString(R.string.form_employee_Err);
        toast_msg12 =  resources.getString(R.string.form_Sector_Err);
        toast_msg13 =  resources.getString(R.string.form_job_role_ERR);
        toast_msg14 =  resources.getString(R.string.Photo_Message);
        toast_msg15 =  resources.getString(R.string.Camera_Message);
        toast_msg16 =  resources.getString(R.string.Face_Message);

        snackbar_msg1 =  resources.getString(R.string.Photo_Err_Message);
        snackbar_msg2 =  resources.getString(R.string.amway_aadhar_snack);
        snackbar_msg3 =  resources.getString(R.string.amway_formErr_Toast);
        snackbar_msg4 =  resources.getString(R.string.amway_Internet_Toast);










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
                newString2 = null;
            } else {
                newString2 = extras.getString("cmp_id");

            }
        } else {
            newString2 = (String) savedInstanceState.getSerializable("cmp_id");
        }


        awesomeValidation.addValidation(MainActivity.this, R.id.input_name, "[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_last_name, "[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_address1, "(.|\\s)*\\S(.|\\s)*", R.string.err_msg_for_address1);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_pincode, "^[0-9]{6}$", R.string.err_msg_pincode);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_bank_ac, "^[0-9]{6,18}$", R.string.err_msg_for_acno);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_ifsc_code, "^[a-zA-Z0-9]{6,20}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_mobile_no, "^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(MainActivity.this, R.id.input_bank_username, "[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);

        // awesomeValidation.addValidation(MainActivity.this, R.id.input_email, Patterns.EMAIL_ADDRESS, R.string.err_msg_email);

        employer.setEnabled(false);
        cmp_id = getIntent().getStringExtra("cmp_id");


//        language_hi = getIntent().getStringExtra("language_id");
//        System.out.println(cmp_id + "idddd" + language_hi);
//
//        if (language_hi != null && language_hi.equals("1")) {
//            Paper.book().write("language", "hi");
//            updateView((String) Paper.book().read("language"));
//        } else if (language_hi != null && language_hi.equals("0")) {
//            Paper.book().write("language", "en");
//            updateView((String) Paper.book().read("language"));
//        }



        sector.setVisibility(View.VISIBLE);
        input_layout_prefferedlanguage.setVisibility(View.VISIBLE);
        input_jobrole.setVisibility(View.VISIBLE);
        course_detail.setVisibility(View.VISIBLE);


        alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiregistered = new Intent(MainActivity.this, SignInAct.class);
                startActivity(iiregistered);
            }
        });

        //awesome validation for year
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_year, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_year))) {
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

        //awesome validation for gender
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_gender, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_Gender))) {
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


        //awesome validation for category
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_category, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_categroy))) {
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

        //awesome validation for state
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_State, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_state))) {
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


        //awesome validation for disability
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_Disablity_type, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_Disablity))) {
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

        //awesome validation for district
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_District, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_District))) {
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


        //awesome validation for education
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_Education, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_Eduction))) {
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

        //awesome validation for employer
        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_Employer, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.amway_Employer))) {
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

        //awesome validation for bank

        awesomeValidation.addValidation(MainActivity.this, R.id.input_layout_bankname, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals(getResources().getString(R.string.Select_Bank))) {
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


        sector.setEnabled(false);
        myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(1);
        myRectPaint.setColor(Color.WHITE);
        myRectPaint.setStyle(Paint.Style.STROKE);

        Bankdetails();
        Statedetails();
        Employerlist();

        Resources res = getResources();
        sectors = res.getStringArray(R.array.Amway_Select_the_Sector);
        banks = new String[]{getString(R.string.Select_Bank)};
        preflangg = res.getStringArray(R.array.Amway_Select_the_PrefferedLanguage);
        states = res.getStringArray(R.array.Amway_Select_the_State);
        districts = res.getStringArray(R.array.Amway_Select_the_District);
        employers = res.getStringArray(R.array.Amway_Select_the_Employer);
        jobrole = res.getStringArray(R.array.Amway_Select_the_Jobrole);

        Statelist = new ArrayList<>(Arrays.asList(states));
        bankslist = new ArrayList<>(Arrays.asList(banks));
        districtlist = new ArrayList<>(Arrays.asList(districts));
        sectorlist = new ArrayList<>(Arrays.asList(sectors));
        employerlist = new ArrayList<>(Arrays.asList(employers));
        jobrolelist = new ArrayList<>(Arrays.asList(jobrole));
        preflang = new ArrayList<>(Arrays.asList(preflangg));
        mySwipeRefreshLayout = new SwipeRefreshLayout(getApplicationContext());


        input_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (faces != null && faces.size() == 0) {
                    Snackbar.make(parentv, snackbar_msg1, Snackbar.LENGTH_SHORT).show();
                } else if (!new VerhoeffAlgorithm().validateVerhoeff(input_aadhar.getText().toString())) {
                    Snackbar.make(parentv, snackbar_msg2, Snackbar.LENGTH_SHORT).show();
                } else if (!employer1.equals(getResources().getString(R.string.amway_Employer)) && employeridname.equals("4") && (input_empid.getText().toString().matches(""))) {

                    Toast.makeText(MainActivity.this, toast_msg2, Toast.LENGTH_SHORT).show();

                } else if ((!eduction1.equals(getResources().getString(R.string.amway_Eduction)) && eduction1.equals(getResources().getString(R.string.amway_other))) && (other_qualification.getText().toString().matches(""))) {
                    Toast.makeText(getApplicationContext(), toast_msg3, Toast.LENGTH_LONG).show();
                }
               /* else if (disablity_type1.equals("Any Disability ?")){
                    Toast.makeText(getApplicationContext(),"Disability must be Selected",Toast.LENGTH_LONG).show();
                }*/

                else if ((!disablity_type1.equals(getResources().getString(R.string.amway_Disablity)) && disablity_type1.equals(getResources().getString(R.string.amway_Disablity_yes))) && (type_of_disablity1.equals(getResources().getString(R.string.amway_type_of_disablity1)))) {
                    Toast.makeText(getApplicationContext(), toast_msg4, Toast.LENGTH_LONG).show();
                }


//
//                else if (!(OtherIdproof1.equals(getResources().getString(R.string.amway_otheridproof)))&& (input_Id_no.getText().toString().matches(""))){
//                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.amway_otheridproof_Toast),Toast.LENGTH_LONG).show();
//                }


                else if (!state1.equals(getResources().getString(R.string.amway_state)) && (stateiddd.equals("2") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("3") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("16") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("17") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("18") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("23") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("19") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("26") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("10") && (input_pancard.getText().toString().matches("")))) {

                    Toast.makeText(MainActivity.this, toast_msg5, Toast.LENGTH_SHORT).show();

                } else if ((!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("5") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("8") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("11") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("13") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("19") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("20") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("22") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("27") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("30") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("33") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals(getResources().getString(R.string.amway_state)) && stateiddd.equals("34") && (input_aadhar.getText().toString().matches("")))) {

                    Toast.makeText(MainActivity.this, toast_msg6, Toast.LENGTH_SHORT).show();

                } else if (awesomeValidation.validate()
                        && !(gender.equals((getResources().getString(R.string.amway_Gender))))
                        && !state1.equals((getResources().getString(R.string.Select_state)))
                        && !disablity_type1.equals((getResources().getString(R.string.amway_Disablity)))
                        && !((disablity_type1.equals((getResources().getString(R.string.amway_Disablity_yes)))) && (type_of_disablity.equals((getResources().getString(R.string.amway_type_of_disablity1)))))
                        && !yearobirth.equals((getResources().getString(R.string.amway_year)))
                        && !district1.equals((getResources().getString(R.string.amway_District)))
                        && !eduction1.equals((getResources().getString(R.string.amway_Eduction)))
                        && !employer1.equals((getResources().getString(R.string.amway_Employer)))
                        && !(bankname1.equals((getResources().getString(R.string.Select_Bank))))
                        && !(employeridname.equals("4") && (input_empid.getText().toString().matches("")))
                        && !((eduction1.equals((getResources().getString(R.string.amway_other)))) && (other_qualification.getText().toString().matches("")))

//                            && !(!(OtherIdproof1.equals((getResources().getString(R.string.amway_otheridproof))))&&
//                            (input_Id_no.getText().toString().matches("")))


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

                        && checkBox.isChecked() && encodedphoto != null) {


                    Intent ii = new Intent(MainActivity.this, Reverify.class);
                    ii.putExtra("first_namee", input_name.getText().toString());
                    ii.putExtra("last_namee", input_last_name.getText().toString());
                    ii.putExtra("mobile", input_mobile_no.getText().toString());
                    ii.putExtra("aadhar", input_aadhar.getText().toString());
                    ii.putExtra("pancard", input_pancard.getText().toString());
                    ii.putExtra("bankaccount", input_bank_ac.getText().toString());
                    ii.putExtra("doy", yearobirth);
                    ii.putExtra("dom", monthobirth);
                    ii.putExtra("dod", dateobirth);
                    ii.putExtra("gender", gender);
                    ii.putExtra("bank", bankiddd);
                    ii.putExtra("state", stateiddd);
                    ii.putExtra("district", districtiddd);
                    ii.putExtra("education", eduction1);
                    //ii.putExtra("employed", employment1);
                    ii.putExtra("employer", employeridname);
                    ii.putExtra("sector", sectoridd);
                    ii.putExtra("addline1", input_address1.getText().toString());
                    ii.putExtra("addline2", input_address2.getText().toString());
                    ii.putExtra("pincode", input_pincode.getText().toString());
                    ii.putExtra("nameasinbank", input_bank_username.getText().toString());
                    ii.putExtra("ifsccode", input_ifsc_code.getText().toString());
                    ii.putExtra("jobrole", jobroleeiddd);
                    ii.putExtra("empid", input_empid.getText().toString());
                    ii.putExtra("location", input_loc.getText().toString());
                    ii.putExtra("preflang", preflangiddd);
                    ii.putExtra("pic", encodedphoto);
                    ii.putExtra("picaadhar", encodedphotoaadhar);
                    ii.putExtra("Email", Email.getText().toString());
                    ii.putExtra("categroy", categoryy);
                    ii.putExtra("alt_no", alt_no.getText().toString());
                    ii.putExtra("your_city", your_city.getText().toString());
                    ii.putExtra("other_qualification", other_qualification.getText().toString());
                    ii.putExtra("input_id_no", input_Id_no.getText().toString());
                    //  ii.putExtra("Any_disability",disablity_type1);
                    ii.putExtra("type_of_disblity", type_of_disablity1);
                    ii.putExtra("Any_disability", disablity_type1);
                    ii.putExtra("other_Id_proof_type", OtherIdproof1);
                    ii.putExtra("Employment_status", Employment_status1);


                    startActivity(ii);

                } else {

                    //Toast.makeText(getApplicationContext(), "The form is not filled correctly.Please verify it and submit.", Toast.LENGTH_LONG).show();

                    Snackbar.make(parentv, snackbar_msg3, Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        try {


            input_photograph.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                        }
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
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {

                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                        }
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
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                    }
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
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
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                    }
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_AADHAR_REQUEST);
                }

            }
        });


        //Gender
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
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


        //disablity_type
        ArrayAdapter<String> myAdapterdisablity_type = new ArrayAdapter<String>(MainActivity.this,
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


        ArrayAdapter<String> myAdapter_type_of_disablity = new ArrayAdapter<String>(MainActivity.this,
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


        ArrayAdapter<String> myAdapterEmployment_status = new ArrayAdapter<String>(MainActivity.this,

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


        ArrayAdapter<String> myAdapterOtherIdproof = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, other_id_proof);
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

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(MainActivity.this,
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

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(MainActivity.this,
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

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(MainActivity.this,
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
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(MainActivity.this,
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
        ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(MainActivity.this,
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

        ArrayAdapter<String> myAdaptersector = new ArrayAdapter<String>(MainActivity.this,
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
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(MainActivity.this,
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


        //Bankname

        ArrayAdapter<String> myAdapterBankname = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, bankslist);
        myAdapterBankname.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bankname.setAdapter(myAdapterBankname);

        bankname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bankname1 = bankname.getSelectedItem().toString();
                if (position > 0) {
                    bankiddd = bankdetail.get(bankname1);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

        //state

        ArrayAdapter<String> myAdapterState = new ArrayAdapter<String>(MainActivity.this,
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


        //District

      /*  if (districtlist.size()>1){
            districtlist.clear();
        }
        districtlist.add("Select the District");*/
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
        Bank_Details.setText(resources.getString(R.string.bank_details));
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
        Bank_Ac_no.setHint(resources.getString(R.string.hint_bank_ac));
        name_in_bank.setHint(resources.getString(R.string.name_as_in_bank));
        ifsc_code.setHint(resources.getString(R.string.hint_ifsc_code));


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


        //error msg

        //   error_msg1 = resources.getString(R.string.err_msg_for_first_name);
        resId = resources.getIdentifier("err_msg_for_first_name", "string", getPackageName());

        System.out.println("ideedededupdate" + resId);
        @SuppressLint("ResourceType") String k = resources.getString(resId);
        System.out.println(k + "ideedededupdate");


   /*21/8/2019 */


        toast_msg2 =  resources.getString(R.string.amway_Employer_4_Toast);
        toast_msg3 =  resources.getString(R.string.amway_other_Employ_Toast);
        toast_msg4 =  resources.getString(R.string.amway_Disablity_Toast);
        toast_msg5 =  resources.getString(R.string.amway_pancard_Toast);
        toast_msg6 =  resources.getString(R.string.amway_Aadhar_Toast);
        toast_msg7 =  resources.getString(R.string.Bank_Err);
        toast_msg8 =  resources.getString(R.string.amway_Language_Err_Toast);
        toast_msg9 =  resources.getString(R.string.amway_States_Err__Toast);
        toast_msg10 =  resources.getString(R.string.amway_District_Err);
        toast_msg11 =  resources.getString(R.string.form_employee_Err);
        toast_msg12 =  resources.getString(R.string.form_Sector_Err);
        toast_msg13 =  resources.getString(R.string.form_job_role_ERR);
        toast_msg14 =  resources.getString(R.string.Photo_Message);
        toast_msg15 =  resources.getString(R.string.Camera_Message);
        toast_msg16 =  resources.getString(R.string.Face_Message);


        snackbar_msg1 =  resources.getString(R.string.Photo_Err_Message);
        snackbar_msg2 =  resources.getString(R.string.amway_aadhar_snack);
        snackbar_msg3 =  resources.getString(R.string.amway_formErr_Toast);
        snackbar_msg4 =  resources.getString(R.string.amway_Internet_Toast);






    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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
                Snackbar.make(parentv, snackbar_msg4, Snackbar.LENGTH_SHORT).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //API For Bank
    private void Bankdetails() {


        String serverURL = "https://www.skillassessment.org/sdms/android_connect/get_bank.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jobj = new JSONObject(response);

                    String status = jobj.getString("status");
                    if (status.equals("1")) {
                        JSONArray jsonArray = jobj.getJSONArray("bank");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            bankid = c.getString("id");
                            bankvalue = c.getString("name");
                            bankdetail.put(bankvalue, bankid);
                            bankslist.add(bankvalue);

                        }
                        // Toast.makeText(getApplicationContext(),"Success"+bankslist,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),toast_msg7, Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), toast_msg7, Toast.LENGTH_LONG).show();
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

    //Language Api Call
    private void languageSelect(final String cmp_id) {

        show_progressbar();
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
                        ArrayAdapter<String> preflanguage = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, preflang);
                        preflanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_layout_prefferedlanguage.setAdapter(preflanguage);

                    } else {
                        Toast.makeText(getApplicationContext(), toast_msg8, Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), toast_msg8, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), toast_msg9, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),toast_msg9 , Toast.LENGTH_LONG).show();
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
                        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, districtlist);
                        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        district.setAdapter(myAdapterDistrict);
                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),toast_msg10, Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), toast_msg10, Toast.LENGTH_LONG).show();
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

                            if (employerlist.get(i).equals(newString2)) {
                                employer.setSelection(i);
                            }
                        }


                    } else {
                        Toast.makeText(getApplicationContext(),toast_msg11, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(),toast_msg12, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), toast_msg12, Toast.LENGTH_LONG).show();
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

                        jobroleadapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, jobrolelist);
                        jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_jobrole.setAdapter(jobroleadapter);


                    } else {
                        Toast.makeText(getApplicationContext(), toast_msg13, Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), toast_msg13, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(),toast_msg14, Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private void funcScanQRCode() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent ii = new Intent(MainActivity.this, SimpleScannerActivity.class);
            startActivityForResult(ii, 1);
        }
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
                    namefromaadhaar_main = map.get("name").replace("\"", "");
                    String namee[] = namefromaadhaar_main.split(" ");
                    input_name.setEnabled(false);
                    input_last_name.setEnabled(false);
                    input_name.setText(namee[0]);
                    input_last_name.setText(namee[1]);
                }

                if (map.get("uid") != null) {
                    input_aadhar.setEnabled(false);
                    input_aadhar.setText(map.get("uid").replace("\"", ""));

                }

                if (map.get("pc") != null) {
                    input_pincode.setText(map.get("pc").replace("/>", "").replace("\"", ""));
                    input_pincode.setEnabled(false);
                }
                if (map.get("house") != null) {
                    input_address1.setText(map.get("house").replace("\"", ""));
                    input_address1.setEnabled(false);
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
                    Toast.makeText(getApplicationContext(), toast_msg15, Toast.LENGTH_LONG).show();
                    return;
                }
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                int currentBitmapWidth = photo.getWidth();
                int currentBitmapHeight = photo.getHeight();
                //input_photograph.setImageBitmap(photo);
                int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                Bitmap newbitMap = Bitmap.createScaledBitmap(photo, currentBitmapWidth, newHeight, true);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                newbitMap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encodedphoto = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Bitmap tempBitmap = Bitmap.createBitmap(photo.getWidth(), photo.getHeight(), Bitmap.Config.RGB_565);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(photo, 0, 0, null);

                FaceDetector faceDetector = new
                        FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(true)
                        .build();
                if (!faceDetector.isOperational()) {
                    new AlertDialog.Builder(getApplicationContext()).setMessage(toast_msg16).show();
                    return;
                }

                Frame frame = new Frame.Builder().setBitmap(photo).build();
                faces = faceDetector.detect(frame);


                for (int i = 0; i < faces.size(); i++) {
                    Face thisFace = faces.valueAt(i);
                    Float x1 = new Float(0);
                    x1 = thisFace.getPosition().x;
                    float y1 = thisFace.getPosition().y;
                    float x2 = x1 + thisFace.getWidth();
                    float y2 = y1 + thisFace.getHeight();

                    //tempCanvas.drawCircle(x1,y1,1,myRectPaint);
                    tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 1, 1, myRectPaint);
                }


                input_photograph.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }


}
