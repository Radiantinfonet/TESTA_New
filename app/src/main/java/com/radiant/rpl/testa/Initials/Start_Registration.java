package com.radiant.rpl.testa.Initials;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.radiant.rpl.testa.Common.CommonUtils;
import com.radiant.rpl.testa.LocalDB.DbAutoSave;

import radiant.rpl.radiantrpl.R;

public class Start_Registration extends AppCompatActivity implements UpdateHelper.onUpdateCheckListener{
    SessionManager sessionManager;
    Button b1,b2;
    SharedPreferences prefs;
    Long timereset;
    DbAutoSave dbAutoSave;
    public static String Baseurll= CommonUtils.url+"";
    public static String Testing_Baseurll="https://www.skillassessment.org/sdms/android_connect1/";
    private static final int REQUEST_PHONE_CALL = 1;
    private int permi;
    private int permi1;
    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_start__registration);


        UpdateHelper.with(this)
                .onUpdateCheck((UpdateHelper.onUpdateCheckListener) this)
                .check();

       /* Intent ii=new Intent(Start_Registration.this,DecoderActivity.class);
        startActivity(ii);*/


        sessionManager=new SessionManager();
        String stat=sessionManager.getPreferences(getApplicationContext(),"vipin");
        dbAutoSave = new DbAutoSave(getApplicationContext());
        prefs=getSharedPreferences("prefs", MODE_PRIVATE);

        permi = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //permi1=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        if (permi != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, 7882);

        }


        if (stat.equals("1")){
        }
        else if (stat.equals("0")){
            if (prefs.contains("millisLeft")){
                timereset= prefs.getLong("millisLeft", 1);
                prefs.edit().clear().commit();
            }

        }
        b1=findViewById(R.id.buttonregister);
        b2=findViewById(R.id.loginuser);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Start_Registration.this, Activity_Rplintroduction.class);
                startActivity(ii);
            }
        }
        );
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii=new Intent(Start_Registration.this,SignInAct.class);
                startActivity(ii);
            }
        });

    }

    public  static  String getURL(){
        return  Baseurll;
    }

    public  static  String getTestingURL(){
        return  Testing_Baseurll;
    }

    @Override
    public void onUpdateCheckListener(final String urlApp) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("New Version is available")
                .setCancelable(false)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlApp)));

                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" +urlApp)));

                        }


                        finish();

                    }
                }).create();


        alertDialog.show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();

            moveTaskToBack(true);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

   /* protected void exitByBackKey() {
finish();


    }*/

}
