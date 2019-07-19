package com.radiant.rpl.testa.ExamSection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import radiant.rpl.radiantrpl.R;

public class ContactUsActivity extends AppCompatActivity {


   // private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE =1 ;
    TextView call1,call2,call3,call4,call5;
    TextView help1,help2,help3,help4,help5,Helpline22;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


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
