    package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
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


public class Profile extends AppCompatActivity {

    String userEmail,userPassword;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String result;
    String userId,passs,nam,email,phone,disig,curr,per;
    TextView p, n, em, ph, di, cu, pe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        p = findViewById( R.id.user_profile );
        n = findViewById( R.id.ett_namEE );
        em = findViewById( R.id.ett_emaiLL );
        ph = findViewById( R.id.ett_contacTT );
        di = findViewById( R.id.ett_designationn);
        cu = findViewById( R.id.ett_Currentt);
        pe = findViewById( R.id.ett_Perminentt);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( this );
        userEmail = preferences.getString( "userEmail", "" );
        userPassword = preferences.getString( "userPassword", "" );

    }



    class BackgroundTask extends AsyncTask<String, Void, String>
    {
        String json_url;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String data = URLEncoder.encode("user_email","UTF-8") + "=" + URLEncoder.encode(userEmail,"UTF-8") + "&"+
                        URLEncoder.encode("user_pass","UTF-8") + "=" + URLEncoder.encode(userPassword,"UTF-8");
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
                result = response3;
                Log.e("dksafndnvec", "doInBackground: "+result );
                return response3;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;

        }
        @Override
        protected void onPreExecute() {
            json_url = "http://192.168.18.58/webapp/json_attendance.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String result) {

            json_string = result;
            Log.e("bcjknjkksdjc ", "onCreate: "+json_string );
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                //while(count < jsonArray.length())

                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    userId = JO.getString("ID");
                    nam = JO.getString("name");
                    email = JO.getString("email");
                    passs = JO.getString("password");
                    phone = JO.getString("phone_number");
                    disig = JO.getString("Designation");
                    curr = JO.getString("Current_Address");
                    per = JO.getString("Permanent_Address");
                    Log.e("ksdjashdn", "onPostExecute: "+phone );

                    p.setText( nam );
                    n.setText( nam );
                    em.setText( email );
                    ph.setText( phone );
                    di.setText( disig );
                    cu.setText( curr );
                    pe.setText( per );
                    Log.e( "bbcnzxbm", "onPostExecute: "+ pe );
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Profile.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userId",userId);
                    editor.apply();
                    count++;
                }
//                displayUserAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        new BackgroundTask().execute();
    }
    public void moveToUpdate(View view) {
      Intent intent = new Intent(Profile.this,UpdateDetail.class);
      intent.putExtra( "i",userId );
      intent.putExtra( "n",nam );
      intent.putExtra( "e",email );
      intent.putExtra( "pa",passs );
      intent.putExtra( "p",phone );
        Log.e( "ncbxcxznzx", "onPostExecute: "+phone  );
      intent.putExtra( "d",disig );
      intent.putExtra( "c",curr );
      intent.putExtra( "paaa",per );
      startActivity( intent );
    }
}