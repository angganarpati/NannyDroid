package com.neti.question.activity;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;


import com.neti.database.util.AlertDialogManager;
import com.neti.database.util.SessionManager;
import com.neti.database.util.UserFunctions;
import com.neti.nannydroidalpha.R;
import com.neti.nannydroidalpha.UserMainActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalHasilActivity extends Activity {
	
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	
	
	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Session Manager Class
	SessionManager session;
	
	@Override
	public void onBackPressed() {
		//Disable Back Button
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		// Session class instance
        session = new SessionManager(getApplicationContext());
		
		Button btnNext = (Button) findViewById(R.id.btnNext);
		
		//get text view
		TextView t1=(TextView)findViewById(R.id.textNama);
		TextView t2=(TextView)findViewById(R.id.textKTP);
      	TextView t3=(TextView)findViewById(R.id.textEtika);
      	TextView t4=(TextView)findViewById(R.id.textGizi);
      	TextView t5=(TextView)findViewById(R.id.textKebersihan);
      	TextView t6=(TextView)findViewById(R.id.textKesehatan);
      	TextView t7=(TextView)findViewById(R.id.textPerkembangan);
      	TextView t8=(TextView)findViewById(R.id.textPerlengkapan);
      	TextView t9=(TextView)findViewById(R.id.textPsikologi);
		
      	// get user data from session
        HashMap<String, String> user = session.getUserData();  
        // name
        String name = user.get(SessionManager.KEY_NAME); 
        // email
        String ktp = user.get(SessionManager.KEY_KTP);
        
        // displaying user data
        t1.setText(Html.fromHtml("Nama : <b>" + name + "</b>"));
        t2.setText(Html.fromHtml("KTP : <b>" + ktp + "</b>"));
        
        // get user score from session
        HashMap<String, Integer> score = session.getScore();  
        // score all test
        int etika = score.get(SessionManager.KEY_Nilai_Etika); 
        int gizi = score.get(SessionManager.KEY_Nilai_Gizi);
        int kebersihan = score.get(SessionManager.KEY_Nilai_Kebersihan);
        int kesehatan = score.get(SessionManager.KEY_Nilai_Kesehatan);
        int perkembanganAnak = score.get(SessionManager.KEY_Nilai_PerkembanganAnak);
        int perlengkapan = score.get(SessionManager.KEY_Nilai_Perlengkapan);
        int psikologi = score.get(SessionManager.KEY_Nilai_Psikologi);
        
        t3.setText(Html.fromHtml("Score Etika : <b>"+ etika + "</b"));
        t4.setText(Html.fromHtml("Score Gizi : <b>"+ gizi + "</b"));
        t5.setText(Html.fromHtml("Score Kebersihan : <b>"+ kebersihan + "</b"));
        t6.setText(Html.fromHtml("Score Kesehatan : <b>"+ kesehatan + "</b"));
        t7.setText(Html.fromHtml("Score Perkembangan Anak : <b>"+ perkembanganAnak + "</b"));
        t8.setText(Html.fromHtml("Score Perlengkapan : <b>"+ perlengkapan + "</b"));
        t9.setText(Html.fromHtml("Score Psikologi : <b>"+ psikologi + "</b"));
		
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				NetAsync(view);
             }});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
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
            nDialog = new ProgressDialog(TotalHasilActivity.this);
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
                    URL url = new URL("http://www.google.com");
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
                new ProcessData().execute();
            }
            else{
                nDialog.dismiss();
                alert.showAlertDialog(TotalHasilActivity.this, "Failed..", "Error in Network Connection", false);
            }
        }
    }
    
    private class ProcessData extends AsyncTask<String, String, JSONObject> {

/**
 * Defining Process dialog
 **/
        private ProgressDialog pDialog;

        String name,ktp;
        int etika,gizi,kebersihan,kesehatan,perkembangan,peralatan,psikologi;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HashMap<String, String> user = session.getUserData();  
            name = user.get(SessionManager.KEY_NAME);
            ktp = user.get(SessionManager.KEY_KTP);
            // get user score from session
            HashMap<String, Integer> score = session.getScore();  
            // score all test
            etika = score.get(SessionManager.KEY_Nilai_Etika); 
            gizi = score.get(SessionManager.KEY_Nilai_Gizi);
            kebersihan = score.get(SessionManager.KEY_Nilai_Kebersihan);
            kesehatan = score.get(SessionManager.KEY_Nilai_Kesehatan);
            perkembangan = score.get(SessionManager.KEY_Nilai_PerkembanganAnak);
            peralatan = score.get(SessionManager.KEY_Nilai_Perlengkapan);
            psikologi = score.get(SessionManager.KEY_Nilai_Psikologi);
           
            pDialog = new ProgressDialog(TotalHasilActivity.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Sending data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

        UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.profileMatching(name, ktp, etika, gizi, kebersihan, kesehatan, perkembangan, peralatan, psikologi );
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
                            pDialog.setTitle("Process Data");
                            pDialog.setMessage("Loading");
                            
                            //alert.showAlertDialog(TotalHasilActivity.this, "Success", "Successfully Stored", true);

                            Intent home = new Intent(getApplicationContext(), UserMainActivity.class);

                            /**
                             * Close all views before launching Registered screen
                            **/
                            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            pDialog.dismiss();
                            startActivity(home);
                            finish();
                        }

                        else if (Integer.parseInt(red) == 2){
                            pDialog.dismiss();
                            alert.showAlertDialog(TotalHasilActivity.this, "Failed", "Data already exists", false);
                            Intent home = new Intent(getApplicationContext(), UserMainActivity.class);
                            /**
                             * Close all views before launching Registered screen
                            **/
                            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            pDialog.dismiss();
                            startActivity(home);
                            finish();
                        }
                   
                    }

                        else{
                        pDialog.dismiss();

                        alert.showAlertDialog(TotalHasilActivity.this,"Failed" ,"Error occured", false);
                        }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }}
        public void NetAsync(View view){
            new NetCheck().execute();
        }
}
