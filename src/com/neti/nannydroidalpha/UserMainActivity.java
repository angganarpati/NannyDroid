package com.neti.nannydroidalpha;


import java.util.HashMap;

import com.neti.database.util.DatabaseHandler;
import com.neti.database.util.SessionManager;
import com.neti.database.util.UserFunctions;
import com.neti.question.activity.EtikaActivity;
import com.neti.usermanagement.ChangePassword;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class UserMainActivity extends Activity {
	
		@Override
		public void onBackPressed() {
			//Disable Back Button
		}
	
		// action bar
		private ActionBar actionBar;	
		
		SessionManager session;
		
	    /**
	     * Called when the activity is first created.
	     */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_user);
	        
	        session = new SessionManager (UserMainActivity.this);
	        
	        Button btnMulai = (Button) findViewById(R.id.mulai);
	        actionBar = getActionBar();

			// Hide the action bar title
			actionBar.setDisplayShowTitleEnabled(false);

	        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

	        /**
	         * Hashmap to load data from the Sqlite database
	         **/
	         HashMap<String,String> user = new HashMap<String, String>();
	         user = db.getUserDetails();
	         
			/**
			 * Sets user first name and last name in text view.
			 **/
	        final TextView login = (TextView) findViewById(R.id.textwelcome);
	        login.setText("Selamat Datang "+user.get("name")+" No KTP : "+user.get("ktp"));
	        
	        String name = user.get("name");
	        String ktp = user.get("ktp");
	        
	        // save session into shared preferences
	        session.createLoginSession(name, ktp);
	        
	        btnMulai.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                Intent myIntent = new Intent(view.getContext(), EtikaActivity.class);
	                startActivityForResult(myIntent, 0);
	                finish();
	             }});

	    }
	    
	    public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.activity_user, menu);
			return super.onCreateOptionsMenu(menu);
		}
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Take appropriate action for each action item click
			switch (item.getItemId()) {
			case R.id.menu_chng_pass:
				// search action
				chng_pass();
				return true;
			case R.id.menu_logout:
				// menu logout
				logout();	
				return true;
			default:
				return super.onOptionsItemSelected(item);
			}
		}
	    
	    private void chng_pass(){
	    	Intent chgpass = new Intent(getApplicationContext(), ChangePassword.class);
            startActivity(chgpass);
	    }
	    
	    private void logout (){
	    	session.clearData();
	    	UserFunctions logout = new UserFunctions();
            logout.logoutUser(getApplicationContext());            
            Intent login = new Intent(getApplicationContext(), MainActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            finish();
	    }

		
			
}

