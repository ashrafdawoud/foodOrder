package com.example.foodorder;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodorder.model.Cart;
import com.example.foodorder.model.Item;
import com.example.foodorder.utils.CartItem;
import com.example.foodorder.utils.ItemParsing;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class cartFragment extends Fragment {


    public cartFragment() {
        // Required empty public constructor
    }
    OkHttpClient client;
    SharedPreferences prefs,pref;
    String user_id,price;
    cartAdapter adapter;
    RecyclerView recyclerView;
     String data1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_cart, container, false);
        // Inflate the layout for this fragment

        client=new OkHttpClient.Builder ().callTimeout (10, TimeUnit.SECONDS).build ();
////////////////////////////////
        prefs = getActivity ().getSharedPreferences("mypref",0);
        user_id = prefs.getString("id", null);
        Log.i ("u_id______",user_id);
        okHttpCartConnection ();

        //////////////////////////////////
        pref = getActivity ().getSharedPreferences("totalPrice",0);
        price = pref.getString("totalprice", null);
        Log.i ("price_________________",price);
        TextView prices=view.findViewById (R.id.prices);
        prices.setText (price);

        /////////////////////////////
         recyclerView=view.findViewById (R.id.carttrcycler);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));

        Button confirm=view.findViewById (R.id.bt);
        confirm.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (getActivity ().getBaseContext (),FinshOrder.class);
                startActivity (intent);



            }
        });
        return view;
    }
    public  void okHttpCartConnection(){
        String url="http://localhost:3014/cart/getallitems/"+user_id;
        Log.i ("url___++___",user_id );

        Request request=new Request.Builder ().url (url).build ();
        client.newCall (request).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("ERR_____msg","fail____"+e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String respond=response.body ().string ();
                Log.i ("json_-____",respond);
                Log.i ("u_id______",user_id );
                try {
                    JSONObject data=new JSONObject (respond);
                    data1=data.optString ("data");
                    Log.i ("json_-__+__",data1);

                } catch (JSONException e) {
                    e.printStackTrace ( );
                }


                  final Cart cart;
                cart= CartItem.cartparsing (respond);
                Log.i ("i_idfragment______",cart.getName ().toString ());

                getActivity ().runOnUiThread (new Runnable ( ) {
                    @Override
                    public void run() {
                        adapter=new cartAdapter (cart.getU_id (),getActivity (),cart.getI_id (),cart.getName (),cart.getPrice (),cart.getImage_path ());
                        recyclerView.setAdapter (adapter);
                        recyclerView.setNestedScrollingEnabled(false);
                        adapter.notifyDataSetChanged();


                    }
                });


            }
        });

    }

}
