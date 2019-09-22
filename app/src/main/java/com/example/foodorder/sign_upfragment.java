package com.example.foodorder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorder.model.User;
import com.example.foodorder.utils.userParsing;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class sign_upfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public sign_upfragment() {
        // Required empty public constructor
    }
    public OkHttpClient okhttpclient;
    TextView firstnametext , lastnametext,emailtext ,phonetext ,passwordtext;
    EditText firstnamehint , lastnamehint,emailhint,phonehint,passwordhint;
    String   firstname="first name" ,lastname="last name";
    TextView err;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate (R.layout.fragment_sign_upfragment, container, false);

        // first edit text
       firstnamehint=view.findViewById (R.id.firstnameegit);
       firstnametext=view.findViewById (R.id.firstname);
        firstnamehint .setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                { firstnametext.setText ("FIRST NAME");
                    firstnamehint.setHint("");}
                else
                {  firstnametext.setText ("");
                    firstnamehint.setHint("First name");}
            }
        });
        //end
        //lastname edit text
       lastnamehint=view.findViewById (R.id.lastnameedit);
        lastnametext=view.findViewById (R.id.lastname);
        lastnamehint .setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {   lastnametext.setText ("Last name");
                    lastnamehint.setHint("");}
                else
                {   lastnametext.setText ("");
                    lastnamehint.setHint("Last name");}
            }
        });
        //end
        //email edit text
        emailhint=view.findViewById (R.id.emailedit);
        emailtext=view.findViewById (R.id.emailtext);
        emailhint .setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {   emailtext.setText ("Email");
                    emailhint.setHint("");}
                else
                {   emailtext.setText ("");
                    emailhint.setHint("Email");}
            }
        });
        //end
        //phone edit text
        phonehint=view.findViewById (R.id.phoneedit);
        phonetext=view.findViewById (R.id.phone);
        phonehint .setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {   phonetext.setText ("phone");
                    phonehint.setHint("");}
                else
                {   phonetext.setText ("");
                    phonehint.setHint("phone");}
            }
        });

        //password
        passwordhint=view.findViewById (R.id.passwordedit);
        passwordtext=view.findViewById (R.id.password);
        passwordhint .setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {   passwordtext.setText ("password");
                    passwordhint.setHint("");}
                else
                {   passwordtext.setText ("");
                    passwordhint.setHint("password");}

            }
        });

        TextView button=view.findViewById (R.id.logbut);
        button.setOnClickListener (new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Fragment fragment=new sign_infragment ();
                FragmentManager fm=getFragmentManager ();
                FragmentTransaction ft=fm.beginTransaction ();
                ft.replace (R.id.fragment,fragment);
                ft.commit ();

            }
        });

        okhttpclient=new OkHttpClient.Builder ().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        ;
        Button sign_up=view.findViewById (R.id.sign_up);
        err=view.findViewById (R.id.err);
        sign_up.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                err.setText ("");
                if(firstnamehint.getText().length() != 0 &&lastnamehint.getText().length() != 0 &&emailhint.getText().length() != 0
                        &&phonehint.getText().length() != 0&&passwordhint.getText().length() != 0)
                {  //to verify user
                verify_existing_account();
                   // make_cart();


                }
                else {
                err.setText ("complete all fields ");}


            }
        });


        return view;
    }

    public void verify_existing_account(){
        String userEmail=emailhint.getText ().toString ();
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url="http://localhost:3014/user/"+userEmail;
        Request requist=new Request.Builder ().url (url).build ();
        okhttpclient.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("verify_______________","err");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if(response.body ().string ().equals ("null")){
                    okhttprespond ();
                    //to back
                    Fragment fragment=new sign_infragment ();
                    FragmentManager fm=getFragmentManager ();
                    FragmentTransaction ft=fm.beginTransaction ();
                    ft.replace (R.id.fragment,fragment);
                    ft.commit ();
                  }

                else {
                    Log.i ("___________","exist");


                }



            }
        });




    }


    /* قبل ما اعمل ال post  حليت جزء المشكله بملف ال xml وال         android:networkSecurityConfig="@xml/security"
     الي ضيفتها في الالبليكيشن في ال mainfist*/
    public  void okhttprespond(){
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("firstName", firstnamehint.getText ());
            postdata.put("secoundName", lastnamehint.getText ());
            postdata.put("email", emailhint.getText ());
            postdata.put("password", passwordhint.getText ());
            postdata.put("phone",phonehint.getText ());
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url="http://localhost:3014/user/creatuser";
        RequestBody body = RequestBody.Companion.create(MEDIA_TYPE, postdata.toString());

        Request requist=new Request.Builder ().url (url).post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json").build ();
        okhttpclient.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("err","fial_________________");
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e("msg________________", mMessage);
                make_cart();

            }
        });
    }


    public void make_cart(){
        String email= emailhint.getText ().toString ();

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url="http://localhost:3014/user/"+email;
        Request requist=new Request.Builder ().url (url).build ();
        okhttpclient.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("login_______________",String.valueOf (e));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //respond.body.srting بتتنادي مره واحد بس
                String json=response.body ().string ();

                Log.i ("jason",json);
                    User user;
                    user= userParsing.parseSandwichJson (json );

                    id=user.get_id ();
                Log.i ("idchok___",id);
                okHttpAddTOcart();









            }
        });




    }

    public  void okHttpAddTOcart(){
        MediaType media=MediaType.parse("application/json");
        JSONObject cart=new JSONObject ();
        JSONArray item=new JSONArray ();
        try {
            cart.put ("u_id",id);
            cart.put ("items",item);
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
        String url="http://localhost:3014/cart/creatcart";
        RequestBody body = RequestBody.Companion.create(media, cart.toString ());
        Request requist=new Request.Builder ().url (url).post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json").build ();
        okhttpclient.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("err","fial_________________");
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e("msg________________", mMessage);


            }
        });



    }
}

