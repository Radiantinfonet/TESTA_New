package com.radiant.rpl.testa.ExamSection;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.radiant.rpl.testa.LocaleHelper;
import com.radiant.rpl.testa.Start_Registration;

import io.paperdb.Paper;
import radiant.rpl.radiantrpl.R;

public class Thankspage extends AppCompatActivity {


    Button ratebutton;
    TextView tvvv;
    SharedPreferences language_prefs;
    String selected_language;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankspage);
        ratebutton =  findViewById(R.id.RateButton);
        tvvv=findViewById(R.id.skipTextView1);

        language_prefs = getSharedPreferences("language_prefs", MODE_PRIVATE);
        if (language_prefs.contains("languagee")) {
            selected_language = language_prefs.getString("languagee", "");
        }


        if (selected_language!=null && selected_language.equals("1")) {

            Paper.book().write("language", "hi");
            updateView((String) Paper.book().read("language")); }
        else if(selected_language!=null && selected_language.equals("0")) {

            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));


        }





        ratebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.radiant.rpl.testa");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        tvvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Thankspage.this, Start_Registration.class);
                startActivity(ii);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void updateView(String lang) {

        Context context = LocaleHelper.setLocale(this,lang);
        final Resources resources = context.getResources();


        ratebutton.setText(resources.getString(R.string.rate_us_on_play_store));
        tvvv.setText(resources.getString(R.string.sign_up1));


        setTitle(resources.getString(R.string.Thanks));

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //exitByBackKey();

            moveTaskToBack(true);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


}
