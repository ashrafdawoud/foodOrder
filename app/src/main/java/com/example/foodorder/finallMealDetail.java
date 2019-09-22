package com.example.foodorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
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

public class finallMealDetail extends AppCompatActivity {
    OkHttpClient client;
    SharedPreferences prefs;
    String user_id;
    TextView name,discription,price;
    String cover0,name0,discription0,price0,id0;
    String str;
boolean connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_finall_meal_detail);
        ImageView cover=findViewById (R.id.image_cover);
         name=findViewById (R.id.meal_name);
         discription=findViewById (R.id.mealdiscription);
         price=findViewById (R.id.mealprice);

        Intent intent=getIntent ();
        cover0=intent.getStringExtra ("image_url");
        name0=intent.getStringExtra ("name");
        discription0=intent.getStringExtra ("discription");
        price0=intent.getStringExtra ("price");
        id0=intent.getStringExtra ("_id");
        Picasso.with (this).load (cover0).into (cover);
        name.setText (name0);
        discription.setText (discription0);
        price.setText (price0+"ج.م");
        RadioButton small=findViewById (R.id.small);
        final RadioButton mediam=findViewById (R.id.mediam);
        RadioButton big=findViewById (R.id.big);

        client=new OkHttpClient ();
         prefs = this.getSharedPreferences("mypref",0);
         user_id = prefs.getString("id", null);
        Log.i ("idprefranse_____",user_id);
        Button add_cart=findViewById (R.id.add_cart);
        final RadioGroup rgroup=findViewById (R.id.RGroup);
        add_cart.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                //rediobutton
                //boolean checked = ((RadioButton) v).isChecked();
               // Log.i("clicked____",String.valueOf (checked));
                str="";
                // Check which radio button was clicked
              /*  switch(v.getId()) {
                    case R.id.small:
                        if(checked)
                            str = "small";
                        break;
                    case R.id.big:
                        if(checked)
                            str = "big";
                        break;
                    case R.id.mediam:
                        if(checked)
                            str = "mediam";
                        break;

                }*/
              if(rgroup.getCheckedRadioButtonId ()==-1){
                  Toast.makeText (finallMealDetail.this,"you should select one",Toast.LENGTH_LONG).show ();
              }else {
                  switch(rgroup.getCheckedRadioButtonId ()) {
                      case R.id.small:
                              str = "small";
                          break;
                      case R.id.big:
                              str = "big";
                          break;
                      case R.id.mediam:
                              str = "mediam";
                          break;
              }
                  okhttpadditem();

              }





            }
        });



    }




    public void click(View view) {
        finish ();
    }
   /* public void onRadioButtonClicked(View view) {

    }*/

public  void okhttpadditem(){
    MediaType media=MediaType.parse("application/json");
    JSONObject item=new JSONObject ();
    try {
        item.put ("i_id",id0);
        item.put ("name",name0);
        item.put ("size",str);
        item.put ("price",price0);
        item.put ("image_path",cover0);


    } catch (JSONException e) {
        e.printStackTrace ( );
    }
String url="http://localhost:3014/cart/additemtocart/"+user_id;
    RequestBody body = RequestBody.Companion.create(media, item.toString ());
    Request requist=new Request.Builder ().url (url).put (body)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json").build ();
    client.newCall (requist).enqueue (new Callback ( ) {
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
            runOnUiThread (new Runnable ( ) {
                @Override
                public void run() {
                    Toast.makeText (finallMealDetail.this,"added to cart ",Toast.LENGTH_LONG).show ();
                }
            });
        }
    });



}
}
