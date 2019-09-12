package com.radiant.rpl.testa.ExamSection;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.radiant.rpl.testa.Registration.LocaleHelper;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class ContactUsActivity extends AppCompatActivity {


   // private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE =1 ;
    TextView call1,call2,call3,call4,call5;
    TextView help1,help2,help3,help4,help5,Helpline22;
    SharedPreferences language_prefs;
    String selected_language;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }



        if (  selected_language!=null && selected_language.equals("1")) {

            Paper.book().write("language", "hi");
            updateView((String) Paper.book().read("language"));
        }
        else if(selected_language!=null && selected_language.equals("0")) {

            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));


        }



        //Add back button


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        call1 = findViewById(R.id.Helpline222);
        call2 = findViewById(R.id.Helpline2222);
        call3 = findViewById(R.id.Helpline22222);
        call4 = findViewById(R.id.Helpline222222);
        call5 = findViewById(R.id.Helpline2222222);


        help1 = findViewById(R.id.no1);
        help2 = findViewById(R.id.no2);
        help3 = findViewById(R.id.no3);
        help4 = findViewById(R.id.no4);
        help5 = findViewById(R.id.no5);
        Helpline22 = findViewById(R.id.Helpline22);



        call1.setText("7303507548");
        call2.setText("7303507553");
        call3.setText("7303216198");
        call4.setText("7303216193");
        call5.setText("7303507547");



        Helpline22.setPaintFlags(Helpline22.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call1.setPaintFlags(call1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call2.setPaintFlags(call2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call3.setPaintFlags(call3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call4.setPaintFlags(call4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call5.setPaintFlags(call5.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();
        setTitle(resources.getString(R.string.Contact_us));


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
