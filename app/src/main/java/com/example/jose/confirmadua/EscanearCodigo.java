package com.example.jose.confirmadua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class EscanearCodigo extends AppCompatActivity {
    EditText codigo;
    Button escanear;
    TextView textView;

    private ZXingScannerView vistaescaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_codigo);
        textView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            String user = (String) bundle.get("usr");
            String pass = (String) bundle.get("pass");
            String cadenass = user + " la pass es = " + pass;

            textView.setText(cadenass);
        }
    }

    public void Escanear(View view){
        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(new zxingscanner());
        setContentView(vistaescaner);
        vistaescaner.startCamera();
    }

    class zxingscanner implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {
            String dato = result.getText();
            int length = dato.length();
            int largo = 9;
            int inicio = length - largo;
            String dua = "";
            dua = dato.substring(inicio,length);

            setContentView(R.layout.activity_escanear_codigo);
            vistaescaner.stopCamera();
            codigo = (EditText) findViewById(R.id.edtCodigo);
            codigo.setText(dua);
        }
    }
}
