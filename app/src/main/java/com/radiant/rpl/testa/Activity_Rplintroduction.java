package com.radiant.rpl.testa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.radiant.rpl.testa.Registration.Company_dropdown;
import radiant.rpl.radiantrpl.R;


public class Activity_Rplintroduction extends AppCompatActivity {

    Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rplintroduction);
        bb=findViewById(R.id.button_company);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(Activity_Rplintroduction.this, Company_dropdown.class);
                startActivity(ii);
            }
        });
    }
}
