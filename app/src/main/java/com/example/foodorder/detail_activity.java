package com.example.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.foodorder.model.Item;
import com.example.foodorder.utils.ItemParsing;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class detail_activity extends AppCompatActivity {

    OkHttpClient client;
    String s_id,s_image;
    mealDetailAdapter adapter;
    RecyclerView recy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_activity);


        final ImageButton imageView=findViewById (R.id.asd);
        imageView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
        //onbackpressed دس بترجع خطوه

                       onBackPressed();
            }
        });
        Intent intent=getIntent ();
         s_id=intent.getStringExtra ("s_id");
         s_image=intent.getStringExtra ("s_image");
        Log.i ("section_id________",s_id);
        ImageView cover=findViewById (R.id.detailcover);
        Picasso.with (this).load (s_image).into (cover);

        /*دا خاص بال recycler */
         recy=findViewById (R.id.meal_detail_recy);
        recy.setHasFixedSize (true);
        recy.setLayoutManager (new LinearLayoutManager (this));
        client=new OkHttpClient.Builder ().callTimeout (10, TimeUnit.SECONDS).build ();
        itemOkHttpCOnnection ();
    }
    public void itemOkHttpCOnnection(){
         String url="http://localhost:3014/item/allitem/get/"+s_id;
        final Request request=new Request .Builder ().url (url).build ();
        client.newCall (request).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("err","fails in details connection");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful ( )) {

                    String respons = response.body ( ).string ( );
                    Item item = ItemParsing.itemparsing (respons);
                    if (item != null) {
                        adapter=new mealDetailAdapter (item.get_id (), item.getName (), item.getSize (), item.getDiscount (), item.getPrice (), item.getDiscription (), item.getImage_url (), detail_activity.this);
                        runOnUiThread(new Runnable ( ) {
                            @Override
                            public void run() {

                                recy.setAdapter (adapter);
                                recy.setNestedScrollingEnabled(false);

                            }
                        });
                    }
                }
            } });

    }
}
