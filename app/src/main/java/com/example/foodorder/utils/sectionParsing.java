package com.example.foodorder.utils;

import android.util.Log;

import com.example.foodorder.model.Section;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class sectionParsing {

    private final static String ID_JSON_KEY = "_id";
    private final static String name_JSON_KEY = "s_name";
    private final static String image_JSON_KEY = "image_path";
    private final static String data_JSON_KEY = "data";
    private static ArrayList<String> _id;
    private static ArrayList<String> s_name;
    private static ArrayList<String> image;
    private static ArrayList<String> datalist;





    public  static Section sectionparsing(String json){
        List<JSONObject>item=new ArrayList <> ();

        if(json!=null){
            try {
                JSONArray head=new JSONArray (json);
                for (int i=0;i<head.length ();i++){
                    item.add (head.optJSONObject (i));

                }



                _id=new ArrayList <> ();
                s_name=new ArrayList <> ();
                image=new ArrayList <> ();
                for (int i=0;i<item.size ();i++){
                    JSONObject current_item=item.get (i);
                    _id.add (current_item.optString (ID_JSON_KEY));
                    s_name.add(current_item.optString (name_JSON_KEY));
                    image.add(current_item.optString (image_JSON_KEY));
                    Log.i ("json_id_________",image.get (i));
                }
               /* datalist=new ArrayList <> ();
                for (int i=0;i<image.size ();i++) {
                    JSONObject data=new JSONObject (image.get (i));
                    datalist.add(data.optString (data_JSON_KEY));

                  //  Log.i ("json_iimage_________",datalist.get (i));

                }*/



            } catch (JSONException e) {
                Log.i("parsing null",String.valueOf (e));
            }


        }
        Section section = null;
        section=new Section (_id,s_name,image);





        return section;
    }

}
