package com.example.foodorder.finish_order_fragments;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorder.FinshOrder;
import com.example.foodorder.R;
import com.example.foodorder.cartAdapter;
import com.example.foodorder.model.Cart;
import com.example.foodorder.utils.CartItem;

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
public class deliver extends Fragment {


    public deliver() {
        // Required empty public constructor
    }
    EditText name,city,streat,zip_code,phone;
    String price,user_id;
    OkHttpClient client1;
    String respond;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate (R.layout.fragment_deliver, container, false);
        Button go=view.findViewById (R.id.gotopayment);
        name=view.findViewById (R.id.name_del);
        city=view.findViewById (R.id.city);
        streat=view.findViewById (R.id.Streat);
        zip_code=view.findViewById (R.id.zip_code);
        phone=view.findViewById (R.id.phone_del);

        ///////////////
       SharedPreferences pref = getActivity ().getSharedPreferences("totalPrice",0);
       price = pref.getString("totalprice", null);
        /////////////////////
        SharedPreferences prefs = getActivity ().getSharedPreferences("mypref",0);
        user_id = prefs.getString("id", null);
        client1=new OkHttpClient ();
        ////////////////////
        final TextView delever1,payment1,review1;
        delever1= getActivity ().findViewById (R.id.delever);
        payment1=getActivity ().findViewById (R.id.payment);
        review1=getActivity ().findViewById (R.id.review);
        delever1.setBackgroundColor (getResources ().getColor (R.color.da));
        payment1.setBackgroundColor (getResources ().getColor (R.color.black));
        review1.setBackgroundColor (getResources ().getColor (R.color.black));

        TextView pricetext=view.findViewById (R.id.pricetext);
        pricetext.setText (price+"  ج.م");
        TextView pricetotal=view.findViewById (R.id.total);
        pricetotal.setText (Integer.valueOf (price)+50+"  ج.م");



        final Fragment deleverFrag, paymentFrag,reviewFrag;

        deleverFrag=new deliver ();
        paymentFrag= new payment ();
        reviewFrag=new finalDetails ();
        go.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
               /* FragmentManager fm= getFragmentManager ();
                FragmentTransaction ft=fm.beginTransaction ();
                ft.replace (R.id.containerFrag,paymentFrag);
                ft.addToBackStack (null);
                ft.commit ();
                delever1.setBackgroundColor (getResources ().getColor (R.color.black));
                payment1.setBackgroundColor (getResources ().getColor (R.color.da));
                review1.setBackgroundColor (getResources ().getColor (R.color.black));*/
                okHttpCartConnectio ();
                Toast.makeText (getActivity (),"order compleat",Toast.LENGTH_LONG).show ();
                getActivity ().finish ();

            }
        });


        return view ; }
 public  void OkhttppostOrder(){
     MediaType type= MediaType.parse ("application/json");
     JSONObject order=new JSONObject ();
     try {
         order.put ("price",Integer.valueOf (price)+50);
         order.put ("delevary_fees","15");
         order.put ("shipping_adreess",String.valueOf ("   streat: "+streat.getText ()+"    city:"+city.getText ()+"    zip_code"+zip_code.getText ()));
         order.put ("items",respond);
         order.put ("u_id",user_id);
         order.put ("phone",phone.getText ());

     } catch (JSONException e) {
         e.printStackTrace ( );
     }
     RequestBody body=RequestBody.create (type,order.toString ());
     String url ="http://localhost:3014/orders/creatorder";
     Request request=new Request.Builder().url (url).post (body).build ();
     client1.newCall (request).enqueue (new Callback ( ) {
         @Override
         public void onFailure(@NotNull Call call, @NotNull IOException e) {
             Log.i ("fail",String.valueOf (e));
         }

         @Override
         public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
             Log.i ("json******",response.body ().string ());

         }
     });

 }

    public  void okHttpCartConnectio(){
        String url="http://localhost:3014/cart/getallitems/"+user_id;
        Log.i ("url______",user_id );

        Request request=new Request.Builder ().url (url).build ();
        client1.newCall (request).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("ERR_____msg","fail____"+e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                 respond=response.body ().string ();
                Log.i ("json_-____",respond);
                Log.i ("u_id______",user_id );
                if(respond==null){
                    Toast.makeText (getActivity (),"the cart is empity",Toast.LENGTH_LONG).show ();
                }else {
                    OkhttppostOrder();
                }






            }
        });

    }
    public void okHttpDelet(){
        Log.i ("u_id",user_id);

        final String url="http://localhost:3014/cart/deletcart/"+user_id;
        Request request=new Request.Builder ().url (url).delete ().build ();
        client1.newCall (request).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i ("err________",String.valueOf (e));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i ("response",response.body ().string ());
                Log.i ("url____",url);
            }
        });
    }

}
