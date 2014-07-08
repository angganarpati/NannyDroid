package com.neti.database.util;

import java.util.HashMap;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREF_NAME = "NannydroidPref";
	
	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";
	
	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";
	
	// User KTP (make variable public to access from outside)
	public static final String KEY_KTP = "ktp";
	
	// User Score (make variable public to access from outside)
	public static final String KEY_Nilai_Etika = "nilai_etika";
	public static final String KEY_Nilai_Gizi = "nilai_gizi";
	public static final String KEY_Nilai_Kebersihan ="nilai_kebersihan";
	public static final String KEY_Nilai_Kesehatan ="nilai_kesehatan";
	public static final String KEY_Nilai_PerkembanganAnak ="nilai_perkembangan_anak";
	public static final String KEY_Nilai_Perlengkapan ="nilai_perlengkapan";
	public static final String KEY_Nilai_Psikologi ="nilai_psikologi";
	
	
	// Constructor
	
	public SessionManager(Context context) {
		// TODO Auto-generated constructor stub
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	/**
	 * Create login session
	 * */
	public void createLoginSession(String name, String ktp){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);
		
		// Storing name in pref
		editor.putString(KEY_NAME, name);
		
		// Storing email in pref
		editor.putString(KEY_KTP, ktp);
		
		// commit changes
		editor.commit();
	}	
	
	public void createScoreEtika(int etika){
		editor.putInt(KEY_Nilai_Etika, etika);	
		editor.commit();
	}
	
	public void createScoreGizi(int gizi){
		editor.putInt(KEY_Nilai_Gizi, gizi);
		editor.commit();
		
	}
	public void createScoreKebersihan(int kebersihan){
		editor.putInt(KEY_Nilai_Kebersihan, kebersihan);
		editor.commit();
	}
	public void createScoreKesehatan(int kesehatan){
		editor.putInt(KEY_Nilai_Kesehatan, kesehatan);
		editor.commit();
	}
	public void createScorePerkembanganAnak (int perkembanganAnak){
		editor.putInt(KEY_Nilai_PerkembanganAnak, perkembanganAnak);
		editor.commit();
	}
	public void createScorePerlengkapan (int perlengkapan){
		editor.putInt(KEY_Nilai_Perlengkapan, perlengkapan);
		editor.commit();
	}
	public void createScorePsikologi (int psikologi){
		editor.putInt(KEY_Nilai_Psikologi, psikologi);
		editor.commit();
	}
	

	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserData(){
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		// user ktp
		user.put(KEY_KTP, pref.getString(KEY_KTP, null));
		
		// return user
		return user;
	}
	
	public HashMap<String, Integer> getScore(){
		HashMap<String, Integer> score = new HashMap<String, Integer>();
		
		// user score
		
		score.put(KEY_Nilai_Etika, pref.getInt(KEY_Nilai_Etika, 0));
		score.put(KEY_Nilai_Gizi, pref.getInt(KEY_Nilai_Gizi, 0));
		score.put(KEY_Nilai_Kebersihan, pref.getInt(KEY_Nilai_Kebersihan, 0));
		score.put(KEY_Nilai_Kesehatan, pref.getInt(KEY_Nilai_Kesehatan, 0));
		score.put(KEY_Nilai_PerkembanganAnak, pref.getInt(KEY_Nilai_PerkembanganAnak, 0));
		score.put(KEY_Nilai_Perlengkapan, pref.getInt(KEY_Nilai_Perlengkapan, 0));
		score.put(KEY_Nilai_Psikologi, pref.getInt(KEY_Nilai_Psikologi, 0));
		
		return score;
	}
	
	/**
	 * Clear session details
	 * @return 
	 * */
	

	public void clearData() {
		// TODO Auto-generated method stub
		
		editor.clear();
		editor.commit();	
		
	}
	
}
