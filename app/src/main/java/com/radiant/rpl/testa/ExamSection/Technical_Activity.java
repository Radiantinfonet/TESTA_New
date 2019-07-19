package com.radiant.rpl.testa.ExamSection;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import radiant.rpl.radiantrpl.R;

public class Technical_Activity extends AppCompatActivity {

    private static final int PICK_IMAGE1 =100 ;
    private static final int PICK_IMAGE2 =1 ;
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;



    private static final int PICK_IMAGE3 =2 ;
    private static final int MY_CAMERA_PERMISSION_CODE =1000 ;

    CardView cardView1,cardView2,cardView3;
    Uri imageUri1,imageUri2,imageUri3;
    ImageView imageView1,imageView2,imageView3;
    Button submit;
    String screenshot1,screenshot2,screenshot3,getproblem;
    ProgressDialog pd;
    EditText problemexp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_);



        cardView1 = findViewById(R.id.card1);
        cardView2 = findViewById(R.id.card2);
        cardView3 = findViewById(R.id.card3);
        imageView1 = findViewById(R.id.img11);
        imageView2 = findViewById(R.id.img22);
        imageView3 = findViewById(R.id.img33);
        submit = findViewById(R.id.submitbtn);
        problemexp = findViewById(R.id.etproblem);


        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("userid")) {
            sharedpreferences.getString("userid", "");
        }




        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                }
                else {


                    Opengallery1();
                }
            }

            private void Opengallery1() {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE1);
            }
        });




        imageView2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {


                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                }

                else{

                    Opengallery2();
                }
            }

            private void Opengallery2() {

                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE2);
            }
        });




        imageView3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                }







                else{


                    Opengallery3();
                }
            }

            private void Opengallery3() {

                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE3);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDetail();
            }


        });





    }


    private void SaveDetail() {

        String serverURL = "https://www.skillassessment.org/sdms/android_connect1/save_help_data.php";
        pd = new ProgressDialog(Technical_Activity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        pd.show();



        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("status");
                    Toast.makeText(getApplicationContext(),"We have Received your query will update soon",Toast.LENGTH_LONG).show();
                    /*if (status.equals("1")){
                        Intent iii=new Intent(Technical_Activity.this,Technical_Activity .class);

                        startActivity(iii);
                    }

                    else if (status.equals("0")){
                        Toast.makeText(getApplicationContext(),jobj.getString("msg"),Toast.LENGTH_LONG).show();
                        Log.d("Response",response);
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (pd.isShowing()) {
                    pd.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "We Encounterd an Error. Please try again later"+error, Toast.LENGTH_LONG).show();
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

                map.put("help_text",problemexp.getText().toString());

                map.put("user_name",sharedpreferences.getString("userid","") );
                if (screenshot1!=null){
                map.put("image_one",screenshot1 );}
                if (screenshot2!=null){
                    map.put("image_two",screenshot2 );}
                if (screenshot3!=null){
                    map.put("image_three",screenshot3 );}
                System.out.println("data"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }











    @Override

    protected  void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        super.onActivityResult(requestcode,resultcode,data);

        if(resultcode == RESULT_OK && requestcode == PICK_IMAGE1)
        {
            imageUri1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                screenshot1 = Base64.encodeToString(byteArray, Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView1.setImageURI(imageUri1);
        }



        if(resultcode == RESULT_OK && requestcode == PICK_IMAGE2)
        {
            imageUri2 = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                screenshot2 = Base64.encodeToString(byteArray, Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView2.setImageURI(imageUri2);
        }


        if(resultcode == RESULT_OK && requestcode == PICK_IMAGE3)
        {
            imageUri3 = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri3);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                screenshot3 = Base64.encodeToString(byteArray, Base64.DEFAULT);


            } catch (IOException e) {
                e.printStackTrace();
            }

            imageView3.setImageURI(imageUri3);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            //ends the activity
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
