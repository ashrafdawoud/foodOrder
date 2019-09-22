package com.example.foodorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.Viewholder> {


    List<String>_id,image,s_name;
    Activity context;
    int posithon;

    public recyclerAdapter(List <String> _id,List <String> image, List <String> s_name, Activity context) {
        this._id = _id;
        this.image = image;
        this.s_name = s_name;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View view= LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.recycler_item,viewGroup,false);
        Viewholder vh=new Viewholder (view);



        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {
       // Bitmap bitmap =  BitmapFactory.decodeStream (Integer.parseInt (image.get (i)));
       // BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);


        viewholder.sectionname.setText (s_name.get (i));
      //  if(context!=null) {
        Picasso.with (context).load (image.get (i)).error (R.drawable.back2).into (viewholder.imageView);

        //دا عشان نبدا الاكتيفيتي بتاعة التفاصيل ونبعت كمان ال position
        viewholder.imageView.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                posithon=i;
                Intent intent=new Intent (v.getContext (),detail_activity.class);
                intent.putExtra ("s_id",String.valueOf (_id.get (posithon)));
                intent.putExtra ("s_image",String.valueOf (image.get (i)));
                v.getContext ().startActivity (intent);
            }
        });



        }




    @Override
    public int getItemCount() {
        return image.size ();
    }



    public class Viewholder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        TextView sectionname ;
        LinearLayout homelayout;

        public Viewholder(@NonNull View itemView) {
            super (itemView);
            imageView=itemView.findViewById (R.id.backgroundcontainer);
            sectionname=itemView.findViewById (R.id.sectionname);
            homelayout=itemView.findViewById (R.id.homelayout);

        }




    }
}
