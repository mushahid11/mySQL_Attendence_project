package com.example.attendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registration extends AppCompatActivity {

    EditText etName,etEmail,etPassword,etPhoneNumber,etDesignation,etCurrentAddress,etPermanentAddress;
    String Name,Email,Password,PhoneNumber,Designation,CurrentAddress,PermanentAddress;
    Button btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        etName = findViewById( R.id.et_regName);
        etEmail= findViewById( R.id.et_regEmail );
        etPassword = findViewById( R.id.et_regPassword );
        etPhoneNumber = findViewById( R.id.et_regPhone);
        etDesignation = findViewById( R.id.et_regDesignation );
        etCurrentAddress = findViewById( R.id.et_regCurrentAddress);
        etPermanentAddress = findViewById( R.id.et_regPerminentAddress);

       btn_Register = findViewById( R.id.btn_register);

        btn_Register.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               userReg();
           }
       } );

    }

    public void userReg() {
        Name= etName.getText().toString();
        Email = etEmail.getText().toString();
        Password= etPassword.getText().toString();
        PhoneNumber= etPhoneNumber.getText().toString();
        Designation= etDesignation.getText().toString();
        CurrentAddress= etCurrentAddress.getText().toString();
        PermanentAddress= etPermanentAddress.getText().toString();
        Log.e( "cbcnbcnxz", "onCreate: "+Name+Email+Password+PhoneNumber+Designation+CurrentAddress+PermanentAddress );

        Log.e( "bcxbcbczxcxmnczxzc", "userReg: "+Name  );


        String method ="register";

        if( TextUtils.isEmpty( PermanentAddress )){
            Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
             if(TextUtils.isEmpty( Name )){

             }else if(TextUtils.isEmpty( Password )){
                 Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
            }else if(TextUtils.isEmpty( Email)){
                 Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
            }else if(TextUtils.isEmpty( Designation )){
                 Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
            }else if(TextUtils.isEmpty( CurrentAddress )){
                 Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
            }else if(TextUtils.isEmpty( PhoneNumber )){
                 Toast.makeText( this, "All fields are required!!!", Toast.LENGTH_SHORT ).show();
             }else{

             }
        }else{

            if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
            {
                etEmail.setError("Enter Valid Email Address");
                etEmail.requestFocus();
            }else {
                  if(checkemail(Email)==true){
                      BackgroundTask backgroundTask = new BackgroundTask( this );
                      backgroundTask.execute( method, Name, Email, Password, PhoneNumber, Designation, CurrentAddress, PermanentAddress );
                  }else{
                      Toast.makeText( this, "enter right email", Toast.LENGTH_SHORT ).show();
                  }



            }
        }}


    public boolean checkemail(String email)
    {

        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
    }












