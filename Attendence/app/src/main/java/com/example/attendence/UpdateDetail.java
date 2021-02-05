package com.example.attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class UpdateDetail extends AppCompatActivity {

EditText pp,nn,ee,ppp,dd,cc,pppp;
String NAM,EMI,PAS,PHO,DES,CUR,PER,usrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_detail );

        pp = findViewById( R.id. profileee);
        nn = findViewById( R.id. ett_namE);
        ee = findViewById( R.id. ett_emaiL);
        ppp = findViewById( R.id. ett_contacT);
        dd = findViewById( R.id. ett_designation);
        cc = findViewById( R.id. ett_Current);
        pppp = findViewById( R.id. ett_Perminent);

        Intent intent = getIntent();
         usrid = intent.getStringExtra("i");
         NAM = intent.getStringExtra("n");
         EMI = intent.getStringExtra("e");
         PAS = intent.getStringExtra("pa");
         PHO = intent.getStringExtra("p");
         DES = intent.getStringExtra("d");
         CUR = intent.getStringExtra("c");
         PER = intent.getStringExtra("paaa");
        Log.e( "cnzxbc", "onCreate: "+ PHO );
        pp.setText( NAM );
        nn.setText( NAM );
        ee.setText( EMI );
        ppp.setText( PHO );
        dd.setText( DES );
        cc.setText( CUR );
        pppp.setText( PER  );

        Log.e( "ncddandda", "onCreate: "+nn);

  }

    public void updatedata(View view) {
        String method ="registerr";
        Log.e( "dsmnncsdm", "updatedata: "+NAM  );
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,nn.getText().toString(),ee.getText().toString(),ppp.getText().toString(),dd.getText().toString(),cc.getText().toString(),pppp.getText().toString(),usrid);
        Log.e( "ncnxzbcn", "updatedata: "+nn  );
        finish();
    }
}
