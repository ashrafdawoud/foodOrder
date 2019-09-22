package com.example.foodorder.utils;

import android.util.Log;

import com.example.foodorder.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemParsing {
    private final static String ID_JSON_KEY = "_id";
    private final static String name_JSON_KEY = "name";
    private final static String size_JSON_KEY = "size";
    private final static String discription_JSON_KEY = "discription";
    private final static String discoun_JSON_KEY = "discount";
    private final static String price_JSON_KEY = "price";
    private final static String image_url_JSON_KEY = "image_url";
    static List<String>_id;
    static List<String> name;
    static List<String> size;
    static List<String> discount;
    static List<String> price;
    static List<String> discription;
    static List<String> image_url;
    public static Item itemparsing(String json){

        if(json!=null){
            try {
                List <JSONObject> itemOpjects=new ArrayList <> ();
                JSONArray jsonopject=new JSONArray (json);
                for (int i=0;i<jsonopject.length ();i++){
                    itemOpjects.add (jsonopject.optJSONObject (i));

                }
                _id=new ArrayList <> ();
                name=new ArrayList <> ();
                size=new ArrayList <> ();
                discription=new ArrayList <> ();
                discount=new ArrayList <> ();
                price=new ArrayList <> ();
                image_url=new ArrayList <> ();
                for (int i=0;i<jsonopject.length ();i++){
                    JSONObject singelItem=itemOpjects.get (i);
                    name.add (singelItem.optString (name_JSON_KEY));
                    size.add (singelItem.optString (size_JSON_KEY));
                    discription.add (singelItem.optString (discription_JSON_KEY));
                    discount.add (singelItem.optString (discoun_JSON_KEY));
                    price.add (singelItem.optString (price_JSON_KEY));
                    image_url.add (singelItem.optString (image_url_JSON_KEY));
                    _id.add (singelItem.optString (ID_JSON_KEY));
                  //  Log.i("_id",String.valueOf (_id));

                }


            } catch (JSONException e) {
                Log.i("errparsing",String.valueOf (e));
            }
        }
        Item item=null;
        item=new Item ( _id ,  name,  size,  discount,  price,  discription,  image_url);
        Log.i("_id",String.valueOf (_id));



        return item;
    }

}
