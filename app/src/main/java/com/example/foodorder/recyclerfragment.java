package com.example.foodorder;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorder.model.Section;
import com.example.foodorder.model.User;
import com.example.foodorder.utils.sectionParsing;

import org.jetbrains.annotations.NotNull;

import java.io.IOError;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class recyclerfragment extends Fragment {
    RecyclerView recyclerView;
    recyclerAdapter adapter;
    OkHttpClient section_Client;

    public recyclerfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_recyclerfragment, container, false);
        // Inflate the layout for this fragment
        section_Client=new OkHttpClient.Builder ().callTimeout (10, TimeUnit.SECONDS).build ();
        section_Connection();
        //
        recyclerView=view.findViewById (R.id.recycler);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (getActivity ()));
        recyclerView.setAdapter (adapter);
        recyclerView.setNestedScrollingEnabled(false);


        //okhttp

        return view;
    }
    public  void section_Connection(){
        String url="http://localhost:3014/Section/getsection";
        Request requist=new Request.Builder ().url (url).build ();
        section_Client.newCall (requist).enqueue (new Callback ( ) {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("msg_____________","fails");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException  {
                if(response.isSuccessful ()){
                    String res=response.body ().string ();
                    if(!res.isEmpty ()){
                        Log.i("json_____",res);
                        Section section=null;
                        section= sectionParsing.sectionparsing (res );
                        try{
                        if(section!=null) {
                            //Log.i ("class____________", String.valueOf (section.get_id ( )));
                            adapter = new recyclerAdapter (section.get_id ( ), section.getImage ( ), section.getName ( ), getActivity ( ));
                            //بنعمل الميثود بتاعة runonuithread عشان نعمل التغير في ال ريسيكلر فيو من غيرها هيحصل ايررور
                        }
                        else {                Log.i ("class____________", "value is null");
                        }}catch ( Exception e){
                            Log.i("err__________", String.valueOf (e));

                        }
                       getActivity ().runOnUiThread(new Runnable ( ) {
                            @Override
                            public void run() {

                                recyclerView.setAdapter (adapter);
                                recyclerView.setNestedScrollingEnabled(false);

                            }
                        });






                    }
                }
            }
        });

    }

}
