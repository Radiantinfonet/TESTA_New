package com.radiant.rpl.testa.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import radiant.rpl.radiantrpl.R;

public class Registration_Done extends AppCompatActivity {
TextView tvv,tvv1;
    String sectorrr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__done);
        tvv=findViewById(R.id.clickhere);
        tvv1=findViewById(R.id.clickheree);
        Intent iiii=getIntent();
        //if ()
        if (iiii.hasExtra("sectorid")) {
            sectorrr= iiii.getStringExtra("sectorid");
        }
        String mobbbbb=iiii.getStringExtra("mobileeno");

        String styledText = getResources().getString(R.string.Reverify_Click);
       // if (sectorrr!="" && sectorrr=="40") {
            tvv.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
       // }
       // else {
        //    tvv.setVisibility(View.GONE);
      //  }
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skillassessment.org/retail/";

             Intent ii=new Intent(Registration_Done.this, Start_Registration.class);
                   startActivity(ii);
            }
        });

        String styledText1 = getResources().getString(R.string.Start_Exam)+getResources().getString(R.string.Start_Exam_msg);

            tvv1.setText(Html.fromHtml(styledText1), TextView.BufferType.SPANNABLE);

        tvv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skillassessment.org/retail/";
                Intent ii=new Intent(Registration_Done.this, Start_Registration.class);
                startActivity(ii);

            }
        });

    }
}
