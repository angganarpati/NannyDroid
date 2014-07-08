package com.neti.usermanagement;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;

import com.neti.database.util.DatabaseHandler;
import com.neti.nannydroidalpha.R;

public class Registered extends Activity {



    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        HashMap<String,String> user = new HashMap<String, String>();
        user = db.getUserDetails();

        /**
         * Displays the registration details in Text view
         **/

        final TextView name = (TextView)findViewById(R.id.name);
        final TextView ktp = (TextView)findViewById(R.id.ktp);
        final TextView ttl = (TextView)findViewById(R.id.ttl);
        final TextView edu = (TextView)findViewById(R.id.edu);
        final TextView uname = (TextView)findViewById(R.id.uname);
        final TextView email = (TextView)findViewById(R.id.email);
        final TextView created_at = (TextView)findViewById(R.id.regat);
        name.setText(user.get("name"));
        ktp.setText(user.get("ktp"));
        ttl.setText(user.get("ttl"));
        edu.setText(user.get("edu"));
        uname.setText(user.get("uname"));
        email.setText(user.get("email"));
        created_at.setText(user.get("created_at"));


        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });


    }}
