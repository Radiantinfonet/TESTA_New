package com.radiant.rpl.testa.Registration;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.Initials.MyNetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;


public class SavephotoComparison {


    public static ProctoringcomparisonClass proctoringcomparisonClass;
    Context context;
    private android.app.AlertDialog progressDialog;

//save the context recievied via constructor in a local variable

    public SavephotoComparison(Context context){
        this.context=context;
    }


    public void PhotoApi(final String encodedphoto,final String mobile,final String name) {
        String serverURL = "http://52.35.163.0:8000/restserver/candidate/";
        //show_progressbar();

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("message");
                    Toast.makeText(context,""+status, Toast.LENGTH_LONG);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //hide_progressbar();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hide_progressbar();
                Toast.makeText(context, "Error Saving the details", Toast.LENGTH_LONG).show();
                System.out.println("aa"+error);
                Toast.makeText(context,""+error, Toast.LENGTH_LONG);
            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();

                map.put("Authorization","Token cd04c523e4a20881d43d0598a59645dd102d2571");

                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                if(encodedphoto!= null) {
                    map.put("Cand_Img", encodedphoto);
                }
                map.put("phone",mobile);//input_mobile_no.getText().toString());
                map.put("name",name);//(input_name.getText().toString() + input_last_name.getText().toString()));

                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(context).addToRequestQueue(request);
    }


    public void PhotoApiREc(final String encoded,final String UserMobile,final String name, final Context c) {
        progressDialog = new SpotsDialog(c, R.style.Custom);
        progressDialog.show();
        String serverURL = "http://52.35.163.0:8000/restserver/recognise/";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("message");
                    String status1= jobj.getString("code");

                    String status3 = "";
                    try{
                        status3= jobj.getString("phone");
                    } catch (JSONException e) {
                        System.out.println("aa"+e);
                    }

                    System.out.print("message" +status );
                    Toast.makeText(context,""+status, Toast.LENGTH_LONG);

                    if((status3.equals(UserMobile)) && (status1.equals("1")))
                    {

                      proctoringcomparisonClass.saveatten(1);
                    }
                    else
                    {
                        FaceAlertMessage(name,c);
                        //FaceAlertMessage();

                        //  Toast.makeText(getApplicationContext(),"o o You are not a Valid User",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    System.out.println("aa"+e);
                    e.printStackTrace();
                }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        },

                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(context, "Photo comparison failed", Toast.LENGTH_LONG).show();
                System.out.println("aa"+error);
            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();

                map.put("Authorization","Token cd04c523e4a20881d43d0598a59645dd102d2571");

                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("Check_Img", encoded);
                map.put("phone",UserMobile);
                Log.d("requestPARAM",""+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(context).addToRequestQueue(request);
    }


    public static void aa(ProctoringcomparisonClass ss){
        proctoringcomparisonClass =ss;
    }


    public void PhotoApiREc1(final String encoded,final String UserMobile, final Context c) {

            System.out.print("message photo comparison api ran" );
            String serverURL = "http://52.35.163.0:8000/restserver/recognise/";


            StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jobj = new JSONObject(response);
                        System.out.println("photo comparison status"+response);
                        String status= jobj.getString("message");
                        String status1= jobj.getString("code");
                        // String status2= jobj.getString("email");
                        String status3 = "";
                        try{
                            status3= jobj.getString("phone");
                        } catch (JSONException e) {
                            System.out.println("aa"+e);
                        }

                        System.out.print("message" +status );
                        Toast.makeText(context,""+status, Toast.LENGTH_LONG);


                        if((status3.equals(UserMobile)) && (status1.equals("1")))
                        {
                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();

                        }
                        else if((!status3.equals(UserMobile)) && (status1.equals("1"))) {

                            FaceAlertMessage2(c);

                        }
                        else {

                            FaceAlertMessage2(c);
                        }

                        //
                        //SaveDetail();

                    } catch (JSONException e) {
                        System.out.println("aa"+e);
                        e.printStackTrace();
                        //SaveDetail();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(context, "Error Saving the details", Toast.LENGTH_LONG).show();
                    System.out.println("aa"+error);
                    //SaveDetail();
                    //
                }
            })

            {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    super.getHeaders();
                    Map<String, String> map = new HashMap<>();

                    map.put("Authorization","Token cd04c523e4a20881d43d0598a59645dd102d2571");

                    return map;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    super.getParams();
                    Map<String, String> map = new HashMap<>();
                    map.put("Content-Type", "application/x-www-form-urlencoded");
                    map.put("Check_Img", encoded);
                    map.put("phone",UserMobile);
                    Log.d("request_param",""+map);
                    return map;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyNetwork.getInstance(context).addToRequestQueue(request);
        }


    public void FaceAlertMessage2(Context c){

        AlertDialog alertDialog = new AlertDialog.Builder(c)
                .setMessage("Make Sure You are sitting straight in front of camera")
                .setTitle("Message")
                .setCancelable(true)
                .setNegativeButton("OK",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }}

                ).create();

        alertDialog.show();
    }


    private void FaceAlertMessage(String Namee,Context cc){
        String sourceString = "Please Make Sure  "+"<b>" + Namee  + "</b> " + " is Sitting in front of camera and taking Exam.";
        AlertDialog alertDialog = new AlertDialog.Builder(cc)
                .setMessage(Html.fromHtml(sourceString))
                .setTitle("Message")
                .setCancelable(true)
                .setNegativeButton("OK",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }}

                ).create();

        alertDialog.show();

    }
}
