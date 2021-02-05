package com.example.attendence;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask< String, Void, String > {
    AlertDialog alertDialog;
    Context context;

    public BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder( context ).create();
        alertDialog.setTitle( "login information...." );
    }

    @Override
    protected String doInBackground(String... params) {


        String method = params[0];
        if (method.equals( "register" )) {
            String NAME = params[1];
            String EMAIL = params[2];
            String PASSWORD = params[3];
            String PHONENUMBER = params[4];
            String DESIGNATION = params[5];
            String CURRENTADDRESS = params[6];
            String PERMANENTADDRESS = params[7];
            String reg_url = "http://192.168.18.58/webapp/Registration.php";


            try {
                URL url = new URL( reg_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( OS, "UTF-8" ) );
                String data = URLEncoder.encode( "name", "UTF-8" ) + "=" + URLEncoder.encode( NAME, "UTF-8" ) + "&" +
                        URLEncoder.encode( "email", "UTF-8" ) + "=" + URLEncoder.encode( EMAIL, "UTF-8" ) + "&" +
                        URLEncoder.encode( "password", "UTF-8" ) + "=" + URLEncoder.encode( PASSWORD, "UTF-8" ) + "&" +
                        URLEncoder.encode( "phone_number", "UTF-8" ) + "=" + URLEncoder.encode( PHONENUMBER, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Designation", "UTF-8" ) + "=" + URLEncoder.encode( DESIGNATION, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Current_Address", "UTF-8" ) + "=" + URLEncoder.encode( CURRENTADDRESS, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Permanent_Address", "UTF-8" ) + "=" + URLEncoder.encode( PERMANENTADDRESS, "UTF-8" );


                bufferedWriter.write( data );
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );

                String response = "";
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append( line ).append( "\n" );
                    response += line;
                }
                return response;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (method.equals( "login" )) {
            String login_email = params[1];
            String login_pass = params[2];
            String login_url = "http://192.168.18.58/webapp/signin.php";
            try {
                URL url = new URL( login_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String data = URLEncoder.encode( "login_email", "UTF-8" ) + "=" + URLEncoder.encode( login_email, "UTF-8" ) + "&" +
                        URLEncoder.encode( "login_pass", "UTF-8" ) + "=" + URLEncoder.encode( login_pass, "UTF-8" );
                bufferedWriter.write( data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (method.equals( "registerr" )) {
            String NAME = params[1];
            String EMAIL = params[2];
            String PHONENUMBER = params[3];
            String DESIGNATION = params[4];
            String CURRENTADDRESS = params[5];
            String PERMANENTADDRESS = params[6];
            String ID = params[7];
            Log.e( "jdjvsd", "doInBackground: " + EMAIL + " " + PHONENUMBER + " " + DESIGNATION + " " + CURRENTADDRESS + " " + PERMANENTADDRESS + " " + ID );
            // String reg_url = "http://192.168.18.58/webapp/update_data.php";


            try {
                URL url = new URL( "http://192.168.18.58/webapp/update_data.php" );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( OS, "UTF-8" ) );
                String data = URLEncoder.encode( "name", "UTF-8" ) + "=" + URLEncoder.encode( NAME, "UTF-8" ) + "&" +
                        URLEncoder.encode( "email", "UTF-8" ) + "=" + URLEncoder.encode( EMAIL, "UTF-8" ) + "&" +
                        URLEncoder.encode( "phone_number", "UTF-8" ) + "=" + URLEncoder.encode( PHONENUMBER, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Designation", "UTF-8" ) + "=" + URLEncoder.encode( DESIGNATION, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Current_Address", "UTF-8" ) + "=" + URLEncoder.encode( CURRENTADDRESS, "UTF-8" ) + "&" +
                        URLEncoder.encode( "Permanent_Address", "UTF-8" ) + "=" + URLEncoder.encode( PERMANENTADDRESS, "UTF-8" ) + "&" +
                        URLEncoder.encode( "ID", "UTF-8" ) + "=" + URLEncoder.encode( ID, "UTF-8" );


                bufferedWriter.write( data );
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );

                String response = "";
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append( line ).append( "\n" );
                    response += line;
                    Log.e( "dsbsb", "doInBackground: " + response );
                }
                return response;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (method.equals( "markattendance" )) {
            String user_id = params[1];
            Log.e( "nbdns", "onPostExecute: " + user_id );
            String login_url = "http://192.168.18.58/webapp/checkin.php";
            try {
                URL url = new URL( login_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String data = URLEncoder.encode( "ID", "UTF-8" ) + "=" + URLEncoder.encode( user_id, "UTF-8" );
                bufferedWriter.write( data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.e( "sdajfnjda", "doInBackground: " + response );
                //  return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "checkIn marked";
        } else if (method.equals( "markattendancee" )) {
            String user_id = params[1];
            Log.e( "nbdns", "onPostExecute: " + user_id );
            String login_url = "http://192.168.18.58/webapp/checkout.php";
            try {
                URL url = new URL( login_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String data = URLEncoder.encode( "ID", "UTF-8" ) + "=" + URLEncoder.encode( user_id, "UTF-8" );
                bufferedWriter.write( data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.e( "response", "doInBackground: " + response );
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return "checkOut Marked";
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute( s );
        Log.e( "nbdndsdss", "onPostExecute: " + s );
        if (s.equals( "Registration Successful" )) {
            Log.e( "bbnvvnbnvvv", "onPostExecute: " + s );
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
            context.startActivity( new Intent( context, MainActivity.class ) );
        } else if (s.equals( "Email Already Exist" )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
            Log.e( "bbnvvnbnv", "onPostExecute: " + s );

        } else if (s.equals( "Login success" )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
            Log.e( "bbnvvnbnv", "onPostExecute: " + s );
            context.startActivity( new Intent( context, Dashboard.class ) );
        } else if (s.equals( "No record found..." )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
            Log.e( "bbnvvnbnv", "onPostExecute: " + s );
        } else if (s.equals( "Record updated successfully" )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
        } else if (s.contains( "CheckIn Marked" )) {
            Log.e( "badmfvnasbd", "onPostExecute: CheckIn Marked" );
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
        } else if (s.contains( "you already marked" )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
        } else if (s.equals( "CheckOut Marked" )) {
            Toast.makeText( context, s, Toast.LENGTH_SHORT ).show();
        }
    }
}



