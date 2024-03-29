package com.neti.usermanagement;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import com.neti.database.util.AlertDialogManager;
import com.neti.database.util.DatabaseHandler;
import com.neti.database.util.UserFunctions;
import com.neti.nannydroidalpha.MainActivity;
import com.neti.nannydroidalpha.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Register extends Activity {

	// Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();

    /**
     *  JSON Response node names.
     **/

    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_KTP = "ktp";
    private static String KEY_TTL = "ttl";
    private static String KEY_EDU = "edu";
    private static String KEY_USERNAME = "uname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_ERROR = "error";

    /**
     * Defining layout items.
     **/

    EditText inputName;
    EditText inputKTP;
    EditText inputTTL;
    EditText inputEDU;
    EditText inputUsername;
    EditText inputEmail;
    EditText inputPassword;
    Button btnRegister;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.register);

    /**
     * Defining all layout items
     **/
        inputName = (EditText) findViewById(R.id.name);
        inputKTP = (EditText) findViewById(R.id.ktp);
        inputTTL = (EditText) findViewById(R.id.ttl);
        inputEDU = (EditText) findViewById(R.id.edu);
        inputUsername = (EditText) findViewById(R.id.uname);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.pword);
        btnRegister = (Button) findViewById(R.id.register);



/**
 * Button which Switches back to the login screen on clicked
 **/

        Button login = (Button) findViewById(R.id.bktologin);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        /**
         * Register Button click event.
         * A Toast is set to alert when the fields are empty.
         * Another toast is set to alert Username must be 5 characters.
         **/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (  ( !inputUsername.getText().toString().equals("")) && ( !inputPassword.getText().toString().equals("")) && ( !inputName.getText().toString().equals("")) && ( !inputKTP.getText().toString().equals("")) && ( !inputTTL.getText().toString().equals("")) && ( !inputEDU.getText().toString().equals("")) && ( !inputEmail.getText().toString().equals("")) )
                {
                    if ( inputUsername.getText().toString().length() > 4 ){
                    NetAsync(view);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
       }
    /**
     * Async Task to check whether internet connection is working
     **/

    private class NetCheck extends AsyncTask<String,String,Boolean>
    {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            nDialog = new ProgressDialog(Register.this);
            nDialog.setMessage("Loading..");
            nDialog.setTitle("Checking Network");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args){
/**
 * Gets current device state and checks for working internet connection by trying Google.
 **/
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://10.0.3.2");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;

        }
        @Override
        protected void onPostExecute(Boolean th){

            if(th == true){
                nDialog.dismiss();
                new ProcessRegister().execute();
            }
            else{
                nDialog.dismiss();
                alert.showAlertDialog(Register.this, "Failed", "Error in Network Connection", false);
            }
        }
    }

    private class ProcessRegister extends AsyncTask<String, String, JSONObject> {

/**
 * Defining Process dialog
 **/
        private ProgressDialog pDialog;

        String email,password,name,ktp,ttl,edu,uname;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inputUsername = (EditText) findViewById(R.id.uname);
            inputPassword = (EditText) findViewById(R.id.pword);
            name = inputName.getText().toString();
            ktp = inputKTP.getText().toString();
            ttl = inputTTL.getText().toString();
            edu = inputTTL.getText().toString();
            email = inputEmail.getText().toString();
            uname= inputUsername.getText().toString();
            password = inputPassword.getText().toString();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Registering ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {


        UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.registerUser(name, ktp, ttl, edu, email, uname, password);

            return json;


        }
       @Override
        protected void onPostExecute(JSONObject json) {
       /**
        * Checks for success message.
        **/
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        String res = json.getString(KEY_SUCCESS);

                        String red = json.getString(KEY_ERROR);

                        if(Integer.parseInt(res) == 1){
                            pDialog.setTitle("Getting Data");
                            pDialog.setMessage("Loading Info");
                            
                            alert.showAlertDialog(Register.this, "Success", "Successfully Registered", true);


                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");

                            /**
                             * Removes all the previous data in the SQlite database
                             **/

                            UserFunctions logout = new UserFunctions();
                            logout.logoutUser(getApplicationContext());
                            db.addUser(json_user.getString(KEY_NAME),json_user.getString(KEY_KTP),json_user.getString(KEY_TTL),json_user.getString(KEY_EDU),json_user.getString(KEY_EMAIL),json_user.getString(KEY_USERNAME),json_user.getString(KEY_UID),json_user.getString(KEY_CREATED_AT));
                            /**
                             * Stores registered data in SQlite Database
                             * Launch Registered screen
                             **/

                            Intent registered = new Intent(getApplicationContext(), Registered.class);

                            /**
                             * Close all views before launching Registered screen
                            **/
                            registered.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            pDialog.dismiss();
                            startActivity(registered);


                              finish();
                        }

                        else if (Integer.parseInt(red) ==2){
                            pDialog.dismiss();
                            alert.showAlertDialog(Register.this, "Failed", "User already exists", false);
                        }
                        else if (Integer.parseInt(red) ==3){
                            pDialog.dismiss();
                            alert.showAlertDialog(Register.this, "Failed", "Invalid Email id", false);
                        }

                    }


                        else{
                        pDialog.dismiss();
                        alert.showAlertDialog(Register.this, "Failed", "Error occured in registration", false);
                        }

                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }}
        public void NetAsync(View view){
            new NetCheck().execute();
        }}


