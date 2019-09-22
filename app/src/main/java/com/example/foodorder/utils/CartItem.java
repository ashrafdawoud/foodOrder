package com.example.foodorder.utils;

import android.util.Log;

import com.example.foodorder.model.Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartItem {
    private final static String itemID_JSON_KEY = "i_id";
    private final static String name_JSON_KEY = "name";
    private final static String price_JSON_KEY = "price";
    private final static String image_JSON_KEY = "image";
    private final static String size_JSON_KEY = "size";
    private final static String image_url_JSON_KEY = "image_path";
    static ArrayList<String>i_id,name,price,image_path;
    static String u_id;

    public  static Cart cartparsing(String jason){

        i_id=new ArrayList <> ();
        name=new ArrayList <> ();
        price=new ArrayList <> ();
        image_path=new ArrayList <> ();


        try {
            JSONObject cartelement=new JSONObject (jason);
            JSONArray result=cartelement.optJSONArray ("result");
            JSONObject resultopject=result.optJSONObject (0);
            u_id=resultopject.optString ("u_id");
            JSONArray items=resultopject.optJSONArray ("items");
            for (int i=0;i<items.length ();i++){
                JSONObject step=items.getJSONObject (i);
                i_id.add (step.optString (itemID_JSON_KEY));
                name.add (step.optString (name_JSON_KEY));
                price.add (step.optString (price_JSON_KEY));
                image_path.add (step.optString (image_url_JSON_KEY));
                Log.i ("cart_____",price.toString ());


            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
        Cart cart=null;
        cart=new Cart (u_id,i_id,name,price,image_path);

        return cart;
    }
}
