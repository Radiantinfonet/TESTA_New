package com.radiant.rpl.testa.Registration;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
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
import com.radiant.rpl.testa.Common.CommonUtils;
import com.radiant.rpl.testa.Initials.MyNetwork;
import com.radiant.rpl.testa.Initials.NetworkStateReceiver;
import com.radiant.rpl.testa.Initials.Registration_Done;
import com.radiant.rpl.testa.Initials.SignInAct;

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
import radiant.rpl.radiantrpl.R;

public class Upica_Registration extends BaseActivity {



    TextView course_detail;
    Paint myRectPaint;
    Spinner yearofbirth, monthofbirth, dateofbirth, education, employment, employer, empidcumsellerid,sector, bankname, state, district, input_jobrole,
            disablity_type, type_of_disablity,
            Employment_status, OtherIdproof,
            input_layout_prefferedlanguage, category;
    EditText input_name, input_last_name, input_mobile_no, input_address1, input_Id_no, input_address2, input_pincode, input_aadhar, input_pancard, input_bank_ac, input_ifsc_code,
            input_bank_username, input_empid, input_loc, Email, alt_no, your_city, other_qualification;
    String emp_statuss;
    private static final int ZBAR_CAMERA_PERMISSION = 1;
    CoordinatorLayout parentv;

    String[] banks, states, districts, employers, jobrole;
    List<String> bankslist, Statelist, districtlist, sectorlist, employerlist, jobrolelist, preflang;
    HashMap<String, String> bankdetail = new HashMap<>();
    HashMap<String, String> Jobrolelist = new HashMap<>();
    HashMap<String, String> Statedetail = new HashMap<>();
    HashMap<String, String> districtdetail = new HashMap<>();
    HashMap<String, String> sectordetail = new HashMap<>();
    HashMap<String, String> employerdetail = new HashMap<>();
    HashMap<String, String> employdetail = new HashMap<>();
    HashMap<String, String> langdetail = new HashMap<>();

    CheckBox checkBox;
    String[] sectors = new String[]{"Select the Sector"};
    String[] preflangg = new String[]{"Select the Preffered Language"};
    CircleImageView input_photograph, input_aadharpic;
    Button input_submit, input_photograph1, input_aadharpic1, alreadyregistered;
    String Stateid, Statevalue, bankid, bankvalue, districtid, districtvalue, selectedstatetext, sectorid, sectorvalue,
            employerid, employervalue, jobroleid, jobrolevalue, preflangid, preflangvalue;
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_AADHAR_REQUEST = 1889;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String yearobirth, monthobirth, dateobirth;
    AwesomeValidation awesomeValidation;
    String gender, eduction1, employer1, sector1,empidcumsellerid1, bankname1, state1, district1, encodedphoto, encodedphotoaadhar, jobrole1,
            preflang1, categoryy, disablity_type1,
            type_of_disablity1, Employment_status1, OtherIdproof1,newString1;
    String bankiddd, stateiddd, districtiddd, employeridname, sectoridd, jobroleeiddd, preflangiddd;
    NetworkStateReceiver networkStateReceiver;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ArrayAdapter<String> jobroleadapter;
    SparseArray<Face> faces;
     String regno1;
   String namefromaadhaar_upica;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upica__registration);

        final Spinner myspinner = findViewById(R.id.input_layout_gender_Upica);
        parentv = findViewById(R.id.register_yourself_Upica);
        yearofbirth = findViewById(R.id.input_layout_year_Upica);
        category = findViewById(R.id.input_layout_category_Upica);
        monthofbirth = findViewById(R.id.input_layout_month_Upica);
        dateofbirth = findViewById(R.id.input_layout_date_Upica);
        education = findViewById(R.id.input_layout_Education_Upica);
        disablity_type = findViewById(R.id.input_layout_Disablity_type_Upica);
        type_of_disablity = findViewById(R.id.input_layout_type_of_Disablity_Upica);
        Employment_status = findViewById(R.id.employment_status_Upica);
        OtherIdproof = findViewById(R.id.otherIdproof_Upica);
        alreadyregistered = findViewById(R.id.btn_already_register_Upica);
        input_Id_no = findViewById(R.id.input_Id_no_Upica);
        course_detail = findViewById(R.id.course_detail_Upica);
        alt_no = findViewById(R.id.input_alt_mobile_no_Upica);
        your_city = findViewById(R.id.input_city_Upica);
        other_qualification = findViewById(R.id.input_Eduction_other_Upica);
        employer = findViewById(R.id.input_layout_Employer_Upica);
        sector = findViewById(R.id.input_layout_Sector_Upica);
        empidcumsellerid = findViewById(R.id.input_layout_Sector_Upica_emp_id);
        bankname = findViewById(R.id.input_layout_bankname_Upica);
        state = findViewById(R.id.input_layout_State_Upica);
        district = findViewById(R.id.input_layout_District_Upica);
        input_photograph1 = findViewById(R.id.input_photograph1_Upica);
        input_aadharpic1 = findViewById(R.id.input_photograph_aadhar1_Upica);
        input_photograph = findViewById(R.id.input_photograph_Upica);
        input_aadharpic = findViewById(R.id.input_photograph_aadhar_Upica);
        input_submit = findViewById(R.id.btn_signup_Upica);
        input_name = findViewById(R.id.input_name_Upica);
        input_last_name = findViewById(R.id.input_last_name_Upica);
        input_mobile_no = findViewById(R.id.input_mobile_no_Upica);
        input_address1 = findViewById(R.id.input_address1_Upica);
        input_address2 = findViewById(R.id.input_address2_Upica);
        input_pincode = findViewById(R.id.input_pincode_Upica);
        input_jobrole = findViewById(R.id.input_layout_jobrole_Upica);
        // input_empid = findViewById(R.id.input_empid_Upica);
        input_loc = findViewById(R.id.input_loc_Upica);
        input_aadhar = findViewById(R.id.input_aadhar_Upica);
        input_pancard = findViewById(R.id.input_pancard_Upica);
        input_bank_ac = findViewById(R.id.input_bank_ac_Upica);
        input_ifsc_code = findViewById(R.id.input_ifsc_code_Upica);
        input_bank_username = findViewById(R.id.input_bank_username_Upica);
        input_layout_prefferedlanguage = findViewById(R.id.input_layout_prefferedlanguage_Upica);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        checkBox = findViewById(R.id.checkBox_Upica);
        Email = findViewById(R.id.input_email_Upica);
        ImageView iv=findViewById(R.id.actionQrCode);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcScanQRCode();
            }
        });


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString1= null;
            } else {
                newString1= extras.getString("cmp_id");


                System.out.println("newwww" +newString1);




            }
        } else {
            newString1= (String) savedInstanceState.getSerializable("cmp_id");
        }



        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_name_Upica, "[a-zA-Z\\s]+", R.string.err_msg_for_first_name);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_last_name_Upica, "[a-zA-Z\\s]+", R.string.err_msg_for_last_name);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_address1_Upica, "(.|\\s)*\\S(.|\\s)*", R.string.err_msg_for_address1);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_pincode_Upica, "^[0-9]{6}$", R.string.err_msg_pincode);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_bank_ac_Upica, "^[0-9]{6,18}$", R.string.err_msg_for_acno);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_ifsc_code_Upica, "^[a-zA-Z0-9]{5,14}$", R.string.err_msg_for_ifsc);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_mobile_no_Upica, "^[0-9]{10}$", R.string.err_msg_formobile);
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_bank_username_Upica, "[a-zA-Z\\s]+", R.string.err_msg_for_namein_bank);
        //awesomeValidation.addValidation(Upica_Registration.this, R.id.input_email, Patterns.EMAIL_ADDRESS, R.string.err_msg_email);


        alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiregistered = new Intent(Upica_Registration.this, SignInAct.class);
                startActivity(iiregistered);
            }
        });
        //awesome validation for year
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_year_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Year")) {
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
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_gender_Upica, new CustomValidation() {
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


        //awesome validation for category
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_category_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select Category")) {
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
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_State_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select the State")) {
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
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_Disablity_type_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Any Disablity ?")) {
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
        /*awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_District_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select the District")) {
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
        }, R.string.err_tech_stacks);*/


        //awesome validation for education
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_Education_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select Education")) {
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
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_Employer_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select the Employer")) {
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
        awesomeValidation.addValidation(Upica_Registration.this, R.id.input_layout_bankname_Upica, new CustomValidation() {
            @Override
            public boolean compare(ValidationHolder validationHolder) {
                if (((Spinner) validationHolder.getView()).getSelectedItem().toString().equals("Select the Bank")) {
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
        employer.setEnabled(false);

        myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(1);
        myRectPaint.setColor(Color.WHITE);
        myRectPaint.setStyle(Paint.Style.STROKE);

        /*
        employer.setEnabled(false);
        input_jobrole.setEnabled(false);
        input_empid.setEnabled(false);
        input_loc.setEnabled(false);
        input_layout_prefferedlanguage.setEnabled(false);*/
        Bankdetails();
        Statedetails();
        Employerlist();
        banks = new String[]{"Select the Bank"};
        states = new String[]{"Select the State"};
        districts = new String[]{"Select the District"};
        employers = new String[]{"Select the Employer"};
        jobrole = new String[]{"Select the Jobrole"};
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

                /*if(yearobirth.equals("Year")){

                    Toast.makeText(getApplicationContext(),"Year must be selected",Toast.LENGTH_LONG).show();
                }*/
                // else
                if (faces != null && faces.size() == 0) {
                    Snackbar.make(parentv, "Your photo is not in correct format.Click another photo.", Snackbar.LENGTH_SHORT).show();
                }
              /*  else if (gender.equals("Select Gender")){
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


                else if (employer1.equals("Select the Employer")){
                    Toast.makeText(getApplicationContext(),"Employer must be selected",Toast.LENGTH_LONG).show();
                }
                else if (bankname1.equals("Select the Bank")){
                    Toast.makeText(getApplicationContext(),"Bank  name must be selected",Toast.LENGTH_LONG).show();

                }*/
                else if (!new VerhoeffAlgorithm().validateVerhoeff(input_aadhar.getText().toString())) {
                    Snackbar.make(parentv, "This Aadhaar number is invalid.Please input correct aadhaar number.", Snackbar.LENGTH_SHORT).show();
                } else if (!employer1.equals("Select the Employer") && employeridname.equals("4") && (input_empid.getText().toString().matches(""))) {

                    Toast.makeText(Upica_Registration.this, "Employee ID/Seller ID Cannot be empty", Toast.LENGTH_SHORT).show();
                } else if ((!eduction1.equals("Select Education") && eduction1.equals("Other")) && (other_qualification.getText().toString().matches(""))) {
                    Toast.makeText(getApplicationContext(), "Education must be filled", Toast.LENGTH_LONG).show();
                }
               /* else if (disablity_type1.equals("Any Disability ?")){
                    Toast.makeText(getApplicationContext(),"Disability must be Selected",Toast.LENGTH_LONG).show();
                }*/

                else if ((!disablity_type1.equals("Any Disability ?") && disablity_type1.equals("Yes")) && (type_of_disablity1.equals("Select Type of Disability"))) {
                    Toast.makeText(getApplicationContext(), "Disablity type must be selected", Toast.LENGTH_LONG).show();
                }




                else if (!(OtherIdproof1.equals("Other Id Proof")) && (input_Id_no.getText().toString().matches(""))) {
                    Toast.makeText(getApplicationContext(), "Id  must be Filled", Toast.LENGTH_LONG).show();


                }



                else if (!state1.equals("Select the State") && (stateiddd.equals("2") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("3") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("16") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("17") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("18") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("23") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("19") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("26") && (input_pancard.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("10") && (input_pancard.getText().toString().matches("")))) {

                    Toast.makeText(Upica_Registration.this, "PAN Card cannot be empty according to your State", Toast.LENGTH_SHORT).show();
                } else if ((!state1.equals("Select the State") && stateiddd.equals("1") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("4") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("5") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("6") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("7") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("8") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("9") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("11") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("12") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("13") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("14") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("15") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("19") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("20") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("21") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("22") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("24") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("25") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("27") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("28") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("30") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("31") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("32") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("33") && (input_aadhar.getText().toString().matches(""))) ||
                        (!state1.equals("Select the State") && stateiddd.equals("34") && (input_aadhar.getText().toString().matches("")))) {

                    Toast.makeText(Upica_Registration.this, "Aadhar Card Can't be empty according to your state", Toast.LENGTH_SHORT).show();
                } else if (awesomeValidation.validate() && !(gender.equals("Select Gender")) && !state1.equals("Select the State")
                        && !disablity_type1.equals("Any Disability ?")
                        && !((disablity_type1.equals("Yes")) && (type_of_disablity.equals("Select Type of Disability")))
                        && !yearobirth.equals("Year") && !district1.equals("Select the District") && !eduction1.equals("Select Education")
                        && !employer1.equals("Select the Employer") && !(bankname1.equals("Select the Bank"))
                        && !(employeridname.equals("4") && (input_empid.getText().toString().matches("")))
                        && !((eduction1.equals("Other")) && (other_qualification.getText().toString().matches("")))
                        && !(!(OtherIdproof1.equals("Other Id Proof")) && (input_Id_no.getText().toString().matches("")))


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

                         SaveDetail();


//                    Intent ii = new Intent(Upica_Registration.this, Reverify.class);
//                    ii.putExtra("first_namee", input_name.getText().toString());
//                    ii.putExtra("last_namee", input_last_name.getText().toString());
//                    ii.putExtra("mobile", input_mobile_no.getText().toString());
//                    ii.putExtra("aadhar", input_aadhar.getText().toString());
//                    ii.putExtra("pancard", input_pancard.getText().toString());
//                    ii.putExtra("bankaccount", input_bank_ac.getText().toString());
//                    ii.putExtra("doy", yearobirth);
//                    ii.putExtra("dom", monthobirth);
//                    ii.putExtra("dod", dateobirth);
//                    ii.putExtra("gender", gender);
//                    ii.putExtra("bank", bankiddd);
//                    ii.putExtra("state", stateiddd);
//                    ii.putExtra("district", districtiddd);
//                    ii.putExtra("education", eduction1);
//                    //ii.putExtra("employed", employment1);
//                    ii.putExtra("employer", employeridname);
//                    ii.putExtra("sector", sectoridd);
//                    ii.putExtra("addline1", input_address1.getText().toString());
//                    ii.putExtra("addline2", input_address2.getText().toString());
//                    ii.putExtra("pincode", input_pincode.getText().toString());
//                    ii.putExtra("nameasinbank", input_bank_username.getText().toString());
//                    ii.putExtra("ifsccode", input_ifsc_code.getText().toString());
//                    ii.putExtra("jobrole", jobroleeiddd);
//                    ii.putExtra("empid", input_empid.getText().toString());
//                    ii.putExtra("location", input_loc.getText().toString());
//                    ii.putExtra("preflang", preflangiddd);
//                    ii.putExtra("pic", encodedphoto);
//                    ii.putExtra("picaadhar", encodedphotoaadhar);
//                    ii.putExtra("Email", Email.getText().toString());
//                    ii.putExtra("categroy", categoryy);
//                    ii.putExtra("alt_no", alt_no.getText().toString());
//                    ii.putExtra("your_city", your_city.getText().toString());
//                    ii.putExtra("other_qualification", other_qualification.getText().toString());
//                    ii.putExtra("input_id_no", input_Id_no.getText().toString());
//                    ii.putExtra("Any_disability", disablity_type1);
//                    ii.putExtra("type_of_disblity", type_of_disablity1);
//                    ii.putExtra("Any_disability", disablity_type1);
//                    ii.putExtra("other_Id_proof_type", OtherIdproof1);
//                    ii.putExtra("Employment_status", Employment_status1);



                    //startActivity(ii);

                } else {

                    //Toast.makeText(getApplicationContext(), "The form is not filled correctly.Please verify it and submit.", Toast.LENGTH_LONG).show();

                    Snackbar.make(parentv, "The form is not filled correctly.Please verify it and submit.", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        try {


            input_photograph.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
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
        ArrayAdapter<String> myAdapterdisablity_type = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Disablity));
        myAdapterdisablity_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        disablity_type.setAdapter(myAdapterdisablity_type);

        disablity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                disablity_type1 = disablity_type.getSelectedItem().toString();
                if (disablity_type1.equals("Yes")) {
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


        ArrayAdapter<String> myAdapter_type_of_disablity = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type_of_Disablity));
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


        ArrayAdapter<String> myAdapterEmployment_status = new ArrayAdapter<String>(Upica_Registration.this,

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


        ArrayAdapter<String> myAdapterOtherIdproof = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.other_id));
        myAdapterOtherIdproof.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        OtherIdproof.setAdapter(myAdapterOtherIdproof);

        OtherIdproof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                OtherIdproof1 = OtherIdproof.getSelectedItem().toString();

                if (OtherIdproof1.equals("Other Id Proof")) {
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

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Upica_Registration.this,
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

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Upica_Registration.this,
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

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(Upica_Registration.this,
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
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Education));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        education.setAdapter(myAdapter4);

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                eduction1 = education.getSelectedItem().toString();

                if (eduction1.equals("Other")) {
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
        ArrayAdapter<String> myAdapterEmployer = new ArrayAdapter<String>(Upica_Registration.this,
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

        ArrayAdapter<String> myAdaptersector = new ArrayAdapter<String>(Upica_Registration.this,
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


        //empid spinner


        ArrayAdapter<String> myAdaptersectorempid = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1,  getResources().getStringArray(R.array.empid));

        myAdaptersectorempid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empidcumsellerid.setAdapter(myAdaptersectorempid);

        empidcumsellerid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                empidcumsellerid1 = empidcumsellerid.getSelectedItem().toString();
                //  String selectedsectortext = (String) parent.getItemAtPosition(position);


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
        ArrayAdapter<String> categoryadapt = new ArrayAdapter<String>(Upica_Registration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Category));
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

        ArrayAdapter<String> myAdapterBankname = new ArrayAdapter<String>(Upica_Registration.this,
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

        ArrayAdapter<String> myAdapterState = new ArrayAdapter<String>(Upica_Registration.this,
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

    private void funcScanQRCode() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent ii=new Intent(Upica_Registration.this, SimpleScannerActivity.class);
            startActivityForResult(ii, 1);
        }
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
                Snackbar.make(parentv, "Internet Not available", Snackbar.LENGTH_SHORT).show();
            }
        });
        registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //API For Bank
    private void Bankdetails() {


        String serverURL = CommonUtils.url+"get_bank.php";
        show_progressbar();

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
                        Toast.makeText(getApplicationContext(), "Failed to fetch Bank Details", Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // hide_progressbar();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  hide_progressbar();
                Toast.makeText(getApplicationContext(), "Failed to fetch Bank Details", Toast.LENGTH_LONG).show();
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

        //show_progressbar();
        String serverURL = CommonUtils.url+"get_language.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    System.out.println("languages in reg page are" + response);
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
                        ArrayAdapter<String> preflanguage = new ArrayAdapter<String>(Upica_Registration.this,
                                android.R.layout.simple_list_item_1, preflang);
                        preflanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_layout_prefferedlanguage.setAdapter(preflanguage);

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to fetch Language Details", Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

               // hide_progressbar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // hide_progressbar();
                Toast.makeText(getApplicationContext(), "Failed to fetch Language Details", Toast.LENGTH_LONG).show();
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


        String serverURL = CommonUtils.url+"get_state.php";


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
                        Toast.makeText(getApplicationContext(), "Failed to fetch States", Toast.LENGTH_LONG).show();
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


        String serverURL = CommonUtils.url+"get_district.php";
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
                        ArrayAdapter<String> myAdapterDistrict = new ArrayAdapter<String>(Upica_Registration.this,
                                android.R.layout.simple_list_item_1, districtlist);
                        myAdapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        district.setAdapter(myAdapterDistrict);
                        //Toast.makeText(getApplicationContext(),"Success"+districtlist,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to fetch Districts", Toast.LENGTH_LONG).show();
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
        String serverURL = CommonUtils.url+"get_employer.php";

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


                        for (int i=0;i<=employerlist.size()-1;i++){
                            System.out.println("employere"+employerid);
                            System.out.println("employereeee"+employerlist.get(i));




                            if (employerlist.get(i).equals(newString1)){
                                employer.setSelection(i);
                            }
                        }



                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to fetch Employers", Toast.LENGTH_LONG).show();
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
        String serverURL = CommonUtils.url+"get_sector.php";


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
                        Toast.makeText(getApplicationContext(), "Failed to fetch Sector Details", Toast.LENGTH_LONG).show();
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
        //show_progressbar();
        String serverURL = CommonUtils.url+"get_jobrole.php";


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

                        jobroleadapter = new ArrayAdapter<String>(Upica_Registration.this,
                                android.R.layout.simple_list_item_1, jobrolelist);
                        jobroleadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        input_jobrole.setAdapter(jobroleadapter);


                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to fetch Job Roles", Toast.LENGTH_LONG).show();
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
                System.out.println("aaaaaa" + map);
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
                    Toast.makeText(getApplicationContext(), "Click photos using default Camera Of the Device", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            if (resultCode == 2 && requestCode == 1) {
                //do something
                HashMap<String, String> map = new HashMap<>();
                Bundle extras = data.getExtras();
                String ss[] = extras.getStringArray("ss");
                String uid_data=ss[1].replace("encoding=\"UTF-8\"?>\n<PrintLetterBarcodeData ","");
                ss[1] = uid_data;
                for (String s : ss) {
                    String[] sd = s.split("=");
                    Log.d("dataaa", sd[0]);
                    Log.d("dataaa", sd[1]);
                    map.put(sd[0], sd[1]);
                    //map.put("value", sd[1]);
                    //Toast.makeText(this,"result"+ss[0]+" "+ss[1],Toast.LENGTH_LONG).show();
                }
                System.out.println("dataa is" + map);
                System.out.println("name is" + map.get("name"));
                System.out.println("co is" + map.get("co"));
                System.out.println("gender is" + map.get("gender"));
                System.out.println("street is" + map.get("street"));
                System.out.println("dist is" + map.get("dist"));
                System.out.println("lm is" + map.get("lm"));
                System.out.println("subdist is" + map.get("subdist"));
                System.out.println("yob is" + map.get("yob"));

                if (map.get("name")!=null){
                    namefromaadhaar_upica=map.get("name").replace("\"","");
                    String namee[]=namefromaadhaar_upica.split(" ");
                    input_name.setEnabled(false);
                    input_last_name.setEnabled(false);
                    input_name.setText(namee[0]);
                    input_last_name.setText(namee[1]);
                }

                if (map.get("uid")!=null){
                    input_aadhar.setEnabled(false);
                    input_aadhar.setText(map.get("uid").replace("\"",""));

                }


                if(map.get("pc")!=null){
                    input_pincode.setText(map.get("pc").replace("/>","").replace("\"",""));
                    input_pincode.setEnabled(false);
                }
                if(map.get("house")!=null){
                    input_address1.setText(map.get("house").replace("\"",""));
                    input_address1.setEnabled(false);
                }
                if(map.get("lm")!=null){
                    input_address2.setText(map.get("lm").replace("\"",""));
                    input_address2.setEnabled(false);
                }
                if(map.get("subdist")!=null){
                    your_city.setText(map.get("subdist").replace("\"",""));
                    your_city.setEnabled(false);
                }
            }else{
                // Toast.makeText(this,"aaaaa",Toast.LENGTH_LONG).show();
                //do something else
            }}catch (Exception e){
            System.out.println("fffff"+e);
        }


        try {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                if (data.getExtras() == null || (data.getExtras().get("data") == null || !(data.getExtras().get("data") instanceof Bitmap))) {
                    //todo - show error
                    Toast.makeText(getApplicationContext(), "The file picked is invalid.Please use default camera to click Photos", Toast.LENGTH_LONG).show();
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
                    new AlertDialog.Builder(getApplicationContext()).setMessage("Could not set up the face detector!").show();
                    return;
                }

                Frame frame = new Frame.Builder().setBitmap(photo).build();
                faces = faceDetector.detect(frame);
                System.out.println("iiii" + faces.get(1));


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


    private void SaveDetail() {
        String serverURL = CommonUtils.url+"save_student_data.php";
        show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("the responsse is"+response);
                    String status= jobj.getString("status");
                    if (status.equals("1")){
                        Intent iii=new Intent(Upica_Registration.this, Registration_Done.class);
                        startActivity(iii);
                    }
                    else if (status.equals("0")){
                        String msg= jobj.getString("msg");
                        regno1=jobj.getString("registered_number");
                        if (msg.equals("Given Aadhar number is already registered! Try using another Aadhar.")){
                            Toast.makeText(getApplicationContext(),"You have already registered using this aadhar number with "+regno1,Toast.LENGTH_LONG).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent ii=new Intent(Upica_Registration.this,SignInAct.class);
                                    ii.putExtra("key", regno1);
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
                Toast.makeText(getApplicationContext(), "Server Error: Error Saving the details", Toast.LENGTH_LONG).show();
                System.out.println("aa"  +error);


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
                map.put("mobile",input_mobile_no.getText().toString());
                map.put("aadhar",input_aadhar.getText().toString());
                map.put("firstname", input_name.getText().toString());
                map.put("lastname",input_last_name.getText().toString());
                map.put("gender",gender);

                if (categoryy!=null){
                    map.put("category",categoryy);}


                if (Email.getText().toString()!=null){
                    map.put("email",Email.getText().toString());}


                if (alt_no.getText().toString()!=null){
                    map.put("landline",alt_no.getText().toString());}



                if (disablity_type1!=null){
                    map.put("disabilitytype",disablity_type1);}


                System.out.print("typoooo" +disablity_type1);
//
//
                if (type_of_disablity1!=null){
                    map.put("disability",type_of_disablity1);}


                System.out.print("typooooyyy" +type_of_disablity1);

                /* if (OtherIdproof1!=null && OtherIdproof1=="Other Id Proof"){
                     map.put("other_id_type","");

                 }
               else*/ if (OtherIdproof1!=null){
                    map.put("other_id_type",OtherIdproof1);}

                if (monthobirth!=null || dateobirth!=null){
                    map.put("dob",yearobirth+"-"+monthobirth+"-"+dateobirth);}
                else{
                    map.put("year_of_birth",yearobirth);
                }


                map.put("state_id",stateiddd);
                map.put("district_id",districtiddd);


                if (your_city.getText().toString()!=null){
                    map.put("City",your_city.getText().toString());}



                map.put("address1",input_address1.getText().toString());


                if (input_address2.getText().toString()!=null){
                    map.put("address2",input_address2.getText().toString());}

                map.put("pincode",input_pincode.getText().toString());

                map.put("qualification",eduction1);


                if (other_qualification.getText().toString()!=null){
                    map.put("other_qualification",other_qualification.getText().toString());}


                map.put("company_id",employeridname);

                map.put("ssc_id",sectoridd);


                map.put("employee_id",empidcumsellerid1);



                if (input_loc.getText().toString()!=null){
                    map.put("StoreLocation",input_loc.getText().toString());}


                map.put("jobrole_id",jobroleeiddd);
                map.put("language",preflangiddd);

                if (Employment_status1!=null){
                    map.put("employment",Employment_status1);}


                map.put("pan",input_pancard.getText().toString());


                if (input_Id_no.getText().toString()!=null){
                    map.put("other_id_number",input_Id_no.getText().toString());}


                if (encodedphotoaadhar!=null){
                    map.put("aadhar_image",encodedphotoaadhar);}

                if (encodedphoto!=null){
                    map.put("student_image",encodedphoto);
                }
                Log.v("PARAMS", map.toString());


                map.put("ifsc",input_ifsc_code.getText().toString());
                map.put("name_in_bank",input_bank_username.getText().toString());
                map.put("bankac",input_bank_ac.getText().toString());
                map.put("bankname",bankiddd);


                System.out.print("the data for saving is"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Employerlist();
    }
}


