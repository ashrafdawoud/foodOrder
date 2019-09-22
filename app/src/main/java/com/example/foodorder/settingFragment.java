package com.example.foodorder;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class settingFragment extends Fragment {

OkHttpClient client;
SharedPreferences prefs;
String user_id;
    public settingFragment() {
        // Required empty public constructor
    }
    EditText firstName,secoundName,phone,email,password;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_setting, container, false);
        client=new OkHttpClient ();
        // Inflate the layout for this fragment
        firstName= view.findViewById (R.id.firstnameSetting);
        secoundName= view.findViewById (R.id.lastnameSetting);
        email= view.findViewById (R.id.emailsetting);
        phone= view.findViewById (R.id.phonesetting);
        password= view.findViewById (R.id.Newpassword);
        prefs = getActivity ().getSharedPreferences("mypref",0);
        user_id = prefs.getString("id", null);
        btn=view.findViewById (R.id.save);
        btn.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                okHttpUpdateUser ();
                Toast.makeText (getActivity (),"updated",Toast.LENGTH_LONG).show ();

            }
        });



        return view;
    }
    public void okHttpUpdateUser(){
        MediaType type=MediaType.parse ("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("firstName", firstName.getText ());
            postdata.put("secoundName", secoundName.getText ());
            postdata.put("email", email.getText ());
            postdata.put("password", password.getText ());
            postdata.put("phone",phone.getText ());
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url="http://localhost:3014/user/"+user_id+"/userupdate";
        RequestBody body = RequestBody.Companion.create(type, postdata.toString());
        Log.i ("url++++++",url);

        Request requist=new Request.Builder ().url (url).put (body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json").build ();
        client.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("err",String.valueOf (e));
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

