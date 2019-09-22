package com.example.foodorder.utils;

import com.example.foodorder.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class userParsing {

    private final static String ID_JSON_KEY = "_id";
    private final static String FIRSTNAME_JSON_KEY = "firstName";
    private final static String SECOUNDNAME_JSON_KEY = "secoundName";
    private final static String EMAIL_JSON_KEY = "email";
    private final static String PASSWORD_JSON_KEY = "password";
    private final static String PHONE_JSON_KEY = "phone";

    public static User parseSandwichJson(String json) {
        User user=null;
        String id,first_name,secound_name,email,password,phone;

        id=new String ();
        first_name=new String ();
        secound_name=new String ();
        email=new String ();
        password=new String ();
        phone=new String ();



        if(json!=null&&!json.isEmpty ()){
            try {
                JSONObject userjason=new JSONObject (json);
                 id=userjason.optString (ID_JSON_KEY);
                 first_name=userjason.optString (FIRSTNAME_JSON_KEY);
                 secound_name=userjason.optString (SECOUNDNAME_JSON_KEY);
                 email=userjason.optString (EMAIL_JSON_KEY);
                 password=userjason.optString (PASSWORD_JSON_KEY);
                 phone=userjason.optString (PHONE_JSON_KEY);


            } catch (JSONException e) {
                e.printStackTrace ( );
            }
        }
        user=new User (id,first_name,secound_name,email,phone,password);



   return user; }}
