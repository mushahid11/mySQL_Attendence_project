package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.text.DateFormat.getDateTimeInstance;

public class AttendanceReport extends AppCompatActivity {
  TextView currMonth;
    String userEmail,userPassword;
    String json_stringg;
    JSONObject jsonObjectt;
    JSONArray jsonArrayy;
    String resultt;
    String idd,checkin,checkout,IDD,NAme;
   TextView sss;
    RecyclerView recyclerView;
    AdapterHorizontal adapterHorizontal;
    String USerId;


    ArrayList<ModelHorizontal> chatitem = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_attendance_report );

        currMonth = findViewById( R.id.c_month );

//        DateFormat dateFormat = new SimpleDateFormat("MM");
//        Date date = new Date();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String ma=month_date.format(cal.getTime());
        currMonth.setText(ma);


        Intent intent = getIntent();
        IDD = intent.getStringExtra( "id" );
        NAme = intent.getStringExtra( "NAme" );

        recyclerView = findViewById(R.id.recycler);
        Log.e( "nxcbznxznc", "onCreczxate: " + IDD );
       new BackgroundTask().execute();
        sss = findViewById( R.id.ssss );
        sss.setText(NAme);

//        ModelHorizontal modelHorizontal = new ModelHorizontal();
//        modelHorizontal.setCI( "asddds" );
//        modelHorizontal.setCO( "mcvnvnnv" );
//        chatitem.add( modelHorizontal );
//
//        adapterHorizontal = new AdapterHorizontal( chatitem,AttendanceReport.this );
//        recyclerView.setAdapter(adapterHorizontal);

        }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {
        String json_url;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://192.168.18.58/webapp/attendancereport.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("ID","UTF-8") + "=" + URLEncoder.encode(IDD,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String response3 = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response3 += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                resultt = response3;
                Log.e("abcdhfhdsef", "doInBackground: "+resultt );
                return response3;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultt;

        }
        @Override
        protected void onPreExecute() {
           // json_url = "http://192.168.18.58/webapp/attendancereport.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result) {

            json_stringg = result;
            Log.e("bdgkksdjc ", "onCreate: "+json_stringg );
            try {
                jsonObjectt = new JSONObject(json_stringg);
                jsonArrayy = jsonObjectt.getJSONArray("server_response");
                int count = 0;

                while(count < jsonArrayy.length())
                {
                    JSONObject JO = jsonArrayy.getJSONObject(count);
                     USerId = JO.getString("userID");
                    idd = JO.getString("ID");
                    checkin = JO.getString("CheckIn");
                    checkout = JO.getString("CheckOut");
                    Log.e( "dsdsdssdsd", "onPostExecute: "+checkout );
                    Log.e( "ddfnsns", "onPostExecute: "+checkout );

                    ModelHorizontal modelHorizontal = new ModelHorizontal();
                    modelHorizontal.setCI( checkin );
                    modelHorizontal.setCO( checkout );
                    chatitem.add( modelHorizontal );

                    adapterHorizontal = new AdapterHorizontal( chatitem,AttendanceReport.this );
                    recyclerView.setAdapter(adapterHorizontal);
                   count++;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

           // Log.e( "sdfdsdsdk", "onPostExecute: "+modelHorizontal  );
        }


        }

}
