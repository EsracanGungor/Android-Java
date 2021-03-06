package com.esragungor.gyk3011;

import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_kaydet, btn_durdur, btn_cal;
    MediaRecorder recorder;
    String kayitYolu=Environment.getExternalStorageDirectory().getPath()+"/kayit.3gp";

    int izinKodu=99;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_kaydet = findViewById(R.id.btn_seskaydet_kaydet);
        btn_durdur = findViewById(R.id.btn_seskaydet_durdur);
        btn_cal = findViewById(R.id.btn_seskaydet_cal);
        btn_kaydet.setOnClickListener(this);
        btn_durdur.setOnClickListener(this);
        btn_cal.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == btn_kaydet) {
            if (IzinleriKontrol()){
            KaydiBaslat();}
            else {
                IzinAl();
            }
        } else if (v == btn_cal) {
            SesiCal();
        } else if (v == btn_durdur) {
            SesiDurdur();
        }
    }


    private void SesiDurdur() {
    if(recorder!=null){
    recorder.stop();
    recorder.reset();
    recorder.release();
    recorder=null;
        Toast.makeText(getApplicationContext(),"Kayit durduruldu",Toast.LENGTH_SHORT).show();
        }}


    private void SesiCal() {



    }

    private void KaydiBaslat() {
        if(recorder!=null){   Toast.makeText(getApplicationContext(),"Kayit islemi devam ediyor "
                ,Toast.LENGTH_SHORT).show();
        return;
        }
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(kayitYolu);
        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(),"Kayit basladi",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean IzinleriKontrol(){
        int izin1=ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int izin2=ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);

        return izin1==PackageManager.PERMISSION_GRANTED&&izin2==PackageManager.PERMISSION_GRANTED;

    }
    private void IzinAl() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{RECORD_AUDIO,WRITE_EXTERNAL_STORAGE},
                izinKodu);
    }

}