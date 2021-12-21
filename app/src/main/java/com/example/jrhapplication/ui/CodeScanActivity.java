package com.example.jrhapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.jrhapplication.R;
import com.example.jrhapplication.film.ui.AffirmActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodeScanActivity extends Activity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_scan);
        scannerView = findViewById(R.id.scannerView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
        startActivity(new Intent(this, AffirmActivity.class));
    }

    public void startCamera(){
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        if (result==null){
            result = rawResult.getText();

        }

        Log.v("--------", rawResult.getText()); // Pr
        scannerView.resumeCameraPreview(this);
    }
}