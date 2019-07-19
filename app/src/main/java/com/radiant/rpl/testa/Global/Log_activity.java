package com.radiant.rpl.testa.Global;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.radiant.rpl.testa.MyNetwork;
import com.radiant.rpl.testa.Registration_Done;
import com.radiant.rpl.testa.Reverify;
import com.radiant.rpl.testa.SignInAct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Log_activity {

    Context ctx;

   Log_activity(Log_activity ctx){
        ctx=Log_activity.this;
    }

    private void saveLog(final String fnamee, final String ip, final String activity, final String lat, final String longi,final String cmpid) {
        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/save_logs.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                  /*  String status= jobj.getString("status");
                    if (status.equals("1")){
                        Intent iii=new Intent(ctx, Registration_Done.class);
                        iii.putExtra("sectorid",sectorr);
                        iii.putExtra("mobileeno",mobbb);
                        startActivity(iii);
                    }
                    else if (status.equals("0")){
                        String msg= jobj.getString("msg");
                        regno=jobj.getString("registered_number");
                        if (msg.equals("Given Aadhar number is already registered! Try using another Aadhar.")){
                            Toast.makeText(ctx,"You have already registered using this aadhar number with "+regno,Toast.LENGTH_LONG).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent ii=new Intent(ctx, SignInAct.class);
                                    ii.putExtra("key", regno);
                                    startActivity(ii);
                                    //Do something after 100ms
                                }
                            }, 100);
                        }
                    }
                    else {
                        String msg1= jobj.getString("msg");
                        Toast.makeText(ctx,msg1,Toast.LENGTH_LONG).show();
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Error Saving the details", Toast.LENGTH_LONG).show();
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
                map.put("username",fnamee);
                map.put("ip",ip);
                map.put("company_id",cmpid);
                map.put("activity",activity);
                map.put("lat",lat);
                map.put("longi",longi);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(ctx).addToRequestQueue(request);
    }
}
