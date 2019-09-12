package com.radiant.rpl.testa.Registration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.radiant.rpl.testa.LocalDB.DbAutoSave;
import com.radiant.rpl.testa.Registration.First_Activity;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Start_Registration extends AppCompatActivity implements UpdateHelper.onUpdateCheckListener{
    SessionManager sessionManager;
    Button b1,b2;
    SharedPreferences prefs,language_prefs;
    Long timereset;
    DbAutoSave dbAutoSave;
    String selected_language;
    public static String Baseurll="https://www.skillassessment.org/sdms/android_connect/";
    public static String Testing_Baseurll="https://www.skillassessment.org/sdms/android_connect11/";
    private static final int REQUEST_PHONE_CALL = 1;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_start__registration);


        b1=findViewById(R.id.buttonregister);
        b2=findViewById(R.id.loginuser);


        UpdateHelper.with(this)
                .onUpdateCheck((UpdateHelper.onUpdateCheckListener) this)
                .check();

        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);

        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }

        //changed
        Paper.init(this);
        String language = Paper.book().read("language");
        if(language == null) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
        }

        sessionManager=new SessionManager();
        String stat=sessionManager.getPreferences(getApplicationContext(),"vipin");
        dbAutoSave = new DbAutoSave(getApplicationContext());
        prefs=getSharedPreferences("prefs", MODE_PRIVATE);




        if (selected_language==null) {
            First_Activity.show(Start_Registration.this);
        }


        if (selected_language!=null &&   selected_language!=null && selected_language.equals("1")) {

                Paper.book().write("language", "hi");
                updateView((String) Paper.book().read("language"));
                System.out.println("ideide" + language_prefs.getString("hindi", ""));
        }
          else if(selected_language!=null && selected_language!=null && selected_language.equals("0")) {

            Paper.book().write("language", "en");
                updateView((String) Paper.book().read("language"));
                System.out.println("ideide" + language_prefs.getString("english", ""));


        }


        if (stat.equals("1")){
        }
        else if (stat.equals("0")){
            if (prefs.contains("millisLeft")){
                timereset= prefs.getLong("millisLeft", 1);
                prefs.edit().clear().commit();
            }

        }


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






//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        boolean firstStart = prefs.getBoolean("firstStart", true);

//        if (firstStart) {
//            showStartDialog();
//        }



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



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();
       b1.setText(resources.getString(R.string.register_for_rpl4));
       b2.setText(resources.getString(R.string.take_exam));


    }


//       private void showStartDialog() {
//     new AlertDialog.Builder(this)
//     .setTitle("choose your language")
//     .setMessage("This should only be shown once")
//     .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//
//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//    dialog.dismiss();
//    }
//    })
//     .create().show();
//
//     SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//     SharedPreferences.Editor editor = prefs.edit();
//     editor.putBoolean("firstStart", false);
//     editor.apply();
//     }




}
