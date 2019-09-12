package com.radiant.rpl.testa.Registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;


public class Activity_Rplintroduction extends AppCompatActivity {

    TextView t1,t2,t3, t4,t5,t6,t7,t8;
    SharedPreferences language_prefs;
    String selected_language;

    Button bb;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rplintroduction);

        t1= findViewById(R.id.inro1);
        t2= findViewById(R.id.inro2);
        t3= findViewById(R.id.inro3);
        t4= findViewById(R.id.inro4);
        t5= findViewById(R.id.inro5);
        t6= findViewById(R.id.inro6);
        t7= findViewById(R.id.inro7);
        t8= findViewById(R.id.inro8);
        bb=findViewById(R.id.button_company);




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




        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Activity_Rplintroduction.this, Company_dropdown.class);
                startActivity(ii);
            }
        });
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();
        t1.setText(resources.getString(R.string.rplintro));
        t2.setText(resources.getString(R.string.rplintro2));
        t3.setText(resources.getString(R.string.benefits2));
        t4.setText(resources.getString(R.string.benefitemp));
        t5.setText(resources.getString(R.string.benefit1));
        t6.setText(resources.getString(R.string.benefit2));
        t7.setText(resources.getString(R.string.benefit3));
        t8.setText(resources.getString(R.string.benefit4));
        bb.setText(resources.getString(R.string.start));

        setTitle(resources.getString(R.string.intro_page));





    }



}
