package com.radiant.rpl.testa.Barcode_d;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;


import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import radiant.rpl.radiantrpl.R;

public class SimpleScannerActivity extends BaseScannerActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void handleResult(Result rawResult) {
        try {
            String str = rawResult.getContents();
            String[] ss = str.split("\" ");
            Log.d("ffff", ss[0] + " " + ss[1]);
            if (ss != null) {
                Intent intent = new Intent();
                intent.putExtra("ss", ss);
                setResult(2, intent); //Here 2 result code
                //startActivity(intent);
                finish();
            }
        }catch (Exception e){
            Intent intent = new Intent();
           // intent.putExtra("ss", ss);
            Toast.makeText(getApplicationContext(),"Unable to fetch data from your aadhaar Card.Please fill manually.",Toast.LENGTH_LONG).show();
            setResult(2, intent); //Here 2 result code
            //startActivity(intent);
            finish();
        }


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
            }
        }, 2000);
    }


}
