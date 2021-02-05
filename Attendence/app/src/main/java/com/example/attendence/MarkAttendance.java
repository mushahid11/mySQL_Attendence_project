package com.example.attendence;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MarkAttendance extends AppCompatActivity {
    String IDD, NAme;
    TextView tvv;
    String uiD;

    String json_stringg;
    JSONObject jsonObjectt;
    JSONArray jsonArrayy;
    String resultt;
    String idd,checkin,checkout,USerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_markattendance );

        tvv = findViewById( R.id.tv_timeee );

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd", Locale.getDefault() );
        String currentDateandTime = sdf.format( new Date() );

        tvv.setText( currentDateandTime );
        Intent intent = getIntent();
        IDD = intent.getStringExtra( "id" );
        NAme = intent.getStringExtra( "NAme" );
        Log.e( "nxcbznxzcxznc", "onCreczxate: " + uiD );
        TextView User = findViewById( R.id.user );

        User.setText( NAme );

        CheckBox cb1 = (CheckBox) findViewById( R.id.checkbox1 );
        CheckBox cb2 = (CheckBox) findViewById( R.id.checkbox2 );

//        BackgroundTask backgroundTaskk = new BackgroundTask();
//        backgroundTaskk.execute(  );
//        finish();


        cb1.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb1.isChecked()) {


                    String method = "markattendance";
                    BackgroundTask backgroundTask = new BackgroundTask( MarkAttendance.this );
                    backgroundTask.execute( method, IDD );
                    //finish();

                }
            }


        } );


        cb2.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb2.isChecked()) {

                        String method = "markattendancee";
                        BackgroundTask backgroundTask = new BackgroundTask(MarkAttendance.this);
                        backgroundTask.execute( method, IDD );
                        //finish();
                }
            }

        } );


    }


}



