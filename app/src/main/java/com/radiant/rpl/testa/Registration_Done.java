package com.radiant.rpl.testa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import radiant.rpl.radiantrpl.R;

public class Registration_Done extends AppCompatActivity {
TextView tvv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__done);
        tvv=findViewById(R.id.clickhere);
        Intent iiii=getIntent();
       String sectorrr= iiii.getStringExtra("sectorid");
       String mobbbbb=iiii.getStringExtra("mobileeno");

        String styledText = "<u><font color='red'>Click Here</font></u> to proceed for Assessment";
        if (sectorrr.equals("40")) {
            tvv.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        }
        else {
            tvv.setVisibility(View.GONE);
        }
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.skillassessment.org/retail/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}
