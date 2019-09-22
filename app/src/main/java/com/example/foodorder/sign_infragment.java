package com.example.foodorder;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorder.model.User;
import com.example.foodorder.utils.userParsing;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class sign_infragment extends Fragment {

    OkHttpClient client;
    EditText mypassword;
    EditText myemail;
    public sign_infragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_sign_infragment, container, false);


         client=new OkHttpClient();
        //for text of email and password
        final TextView textView=view.findViewById (R.id.emailtext);
        myemail =view.findViewById (R.id.email);
        TextView signup =view.findViewById (R.id.signup);


        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment=new sign_upfragment ();
                FragmentManager fm=getFragmentManager ();
                FragmentTransaction ft=fm.beginTransaction ();
                ft.replace (R.id.fragment,fragment);
                ft.commit ();
            }
        });
        myemail.setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                { textView.setText ("Email");
                    myemail.setHint("");}
                else
                {  textView.setText ("");
                    myemail.setHint("Email");}
            }
        });
        final TextView textView1= (TextView) view.findViewById (R.id.passwordtext);
          mypassword =view.findViewById (R.id.password);

        mypassword.setOnFocusChangeListener(new View.OnFocusChangeListener ( ) {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                { textView1.setText ("password");
                    mypassword.setHint("");}
                else
                {  textView1.setText ("");
                    mypassword.setHint("password");}
            }
        });
        // end of text
        Button button=view.findViewById (R.id.enterbutton);
        button.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View v) {
                /**/
                if(myemail.getText ().length ()!=0&&mypassword.getText ().length ()!=0)
                {  verify_existing_account ();}
            }
        });

        return view;
    }
    public void verify_existing_account(){
        String email= myemail.getText ().toString ();

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url="http://localhost:3014/user/"+email;
        Request requist=new Request.Builder ().url (url).build ();
        client.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("login_______________",String.valueOf (e));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //respond.body.srting بتتنادي مره واحد بس
                String json=response.body ().string ();
                if(json.equals ("null")){
                    getActivity ().runOnUiThread (new Runnable ( ) {
                        @Override
                        public void run() {
                            Toast.makeText (getActivity (),"user not exist",Toast.LENGTH_LONG).show ();

                        }
                    });
                }

                else {
                    User user=null;
                    user= userParsing.parseSandwichJson (json );
                    Log.i ("000000",user.getPassword ());
                    Log.i ("000000", String.valueOf (mypassword.getText ()));


                    if(mypassword.getText ().toString ().equals (user.getPassword ())){
                        Intent intent=new Intent (getActivity ().getBaseContext (),HOME_Activity.class);
                        //عشان نحط ال يوزر اي دي في البريفرانس عشان نستدعيه وقت ما نحب
                        SharedPreferences user_pref=getActivity ().getSharedPreferences ("mypref",getActivity ().MODE_PRIVATE);
                        SharedPreferences.Editor editor=user_pref.edit ();
                        editor.putString ("id",user.get_id ().toString ());
                        editor.commit();

                        getActivity ().startActivity (intent);
                    }

                }



            }
        });




    }

}
