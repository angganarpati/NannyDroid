package com.neti.database.util;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;


public class UserFunctions {
		
    private JSONParser jsonParser;

    //URL of the PHP API
    private static String loginURL = "http://10.0.3.2/nannydroid/";
    private static String registerURL = "http://10.0.3.2/nannydroid/";
    private static String forpassURL = "http://10.0.3.2/nannydroid/";
    private static String chgpassURL = "http://10.0.3.2/nannydroid/";
    private static String profileMatchingURL = "http://10.0.3.2/nannydroid/";


    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String forpass_tag = "forpass";
    private static String chgpass_tag = "chgpass";
    private static String profile_matching_tag = "profile_matching";


    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
        
    }

    /**
     * Function to Login
     **/

    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }

    /**
     * Function to change password
     **/

    public JSONObject chgPass(String newpas, String email){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", chgpass_tag));

        params.add(new BasicNameValuePair("newpas", newpas));
        params.add(new BasicNameValuePair("email", email));
        JSONObject json = jsonParser.getJSONFromUrl(chgpassURL, params);
        return json;
    }


    /**
     * Function to reset the password
     **/

    public JSONObject forPass(String forgotpassword){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", forpass_tag));
        params.add(new BasicNameValuePair("forgotpassword", forgotpassword));
        JSONObject json = jsonParser.getJSONFromUrl(forpassURL, params);
        return json;
    }


     /**
      * Function to  Register
      **/
    public JSONObject registerUser(String name, String ktp, String ttl, String edu, String email, String uname, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("ktp", ktp));
        params.add(new BasicNameValuePair("ttl", ttl));
        params.add(new BasicNameValuePair("edu", ktp));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("uname", uname));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
        return json;
    }
    
    
    /**
     * Function profile matching
     **/
   public JSONObject profileMatching(String name, String ktp, int etika, int gizi, int kebersihan, int kesehatan, int perkembangan, int peralatan, int psikologi){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", profile_matching_tag));
       params.add(new BasicNameValuePair("name", name));
       params.add(new BasicNameValuePair("ktp", ktp));
       params.add(new BasicNameValuePair("etika",Integer.toString(etika)));
       params.add(new BasicNameValuePair("gizi",Integer.toString(gizi)));
       params.add(new BasicNameValuePair("kebersihan",Integer.toString( kebersihan)));
       params.add(new BasicNameValuePair("kesehatan",Integer.toString( kesehatan)));
       params.add(new BasicNameValuePair("perkembangan",Integer.toString( perkembangan)));
       params.add(new BasicNameValuePair("peralatan",Integer.toString( peralatan)));
       params.add(new BasicNameValuePair("psikologi",Integer.toString( psikologi)));
       
       JSONObject json = jsonParser.getJSONFromUrl(profileMatchingURL,params);
       return json;
   }
    
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        
        return true;
    }

}

