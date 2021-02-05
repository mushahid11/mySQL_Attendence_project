package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   EditText  ET_NAME,ET_PASS;
    String login_email,login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ET_NAME = findViewById( R.id.et_Email );
        ET_PASS = findViewById( R.id.et_Password);

     }



    public void login(View view) {
        login_email = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();

        if(login_email.isEmpty() && login_pass.isEmpty()){
            Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
        }else {
            String method = "login";
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userEmail",login_email);
            editor.putString("userPassword",login_pass);
            editor.apply();
            BackgroundTask backgroundTask = new BackgroundTask( this );
            AsyncTask< String, Void, String > a=  backgroundTask.execute( method, login_email, login_pass );
//            Intent intent = new Intent(MainActivity.this, Dashboard.class);
//            intent.putExtra("data", login_email );
//            Log.e( "zxcxzcnxzjmn", "login: "+login_email  );
//            startActivity(intent);
            // finish();
        }
    }



    public void moveToRegistrationScreen(View view) {
        startActivity( new Intent(MainActivity.this,Registration.class) );
    }
}