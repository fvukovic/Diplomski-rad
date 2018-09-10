package com.example.filip.transport.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database.entities.RadniNalog;
import com.example.database.entities.Radnik;
import com.example.filip.transport.R;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.sql.SQLException;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity   {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String[] arraySpinner;
    private Spinner mySpinner;
    public    List<Radnik> sviRadnici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the login form.
        final EditText password = (EditText)findViewById(R.id.lozinka);


        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
        Radnik novi = new Radnik(1,"Filip Vukovic","");
        novi.save();
        int i=0;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        /**
         * punjenje spinnera podacima za ime i prezime
         */
       sviRadnici = Radnik.getAll();
        arraySpinner= new String[sviRadnici.size()];
       for(Radnik radnik : sviRadnici)
       {
           System.out.print("prezime"+radnik.getImeIprezime());
           this.arraySpinner[i] = radnik.getImeIprezime();
           i++;

       }

        /**
         * Listener za prijavu
         */
        RadniNalog novi1 = new RadniNalog(1,"","","","","",1);
        novi1.save();
        RadniNalog novi2 = new RadniNalog(2,"","","","","",2);
        novi2.save();
        RadniNalog novi3 = new RadniNalog(3,"","","","","",3);
        novi3.save();
        final Button button = (Button) findViewById(R.id.button1);
        button. setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent1);

            }
        });
    }
}

