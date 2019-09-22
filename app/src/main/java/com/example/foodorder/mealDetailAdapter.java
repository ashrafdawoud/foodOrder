package com.example.foodorder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class mealDetailAdapter extends RecyclerView.Adapter<mealDetailAdapter.myViewHolder> {

     List <String> _id;
     List<String> name;
     List<String> size;
     List<String> discount;
     List<String> price;
     List<String> discription;
     List<String> image_url;
     Context context;

    public mealDetailAdapter(List <String> _id, List <String> name, List <String> size, List <String> discount, List <String> price, List <String> discription, List <String> image_url, Context context) {
        this._id = _id;
        this.name = name;
        this.size = size;
        this.discount = discount;
        this.price = price;
        this.discription = discription;
        this.image_url = image_url;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.meal_details_recy_item,parent,false);
        final myViewHolder vh=new myViewHolder (view);
        vh.layout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (parent.getContext (),finallMealDetail.class);
                intent.putExtra ("asas","asasa");
                parent.getContext ().startActivity (intent);

            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        Picasso.with (context).load (image_url.get (i)).into ( myViewHolder.photo);
        myViewHolder.mealprice.setText (price.get (i)+" ج.م");
        myViewHolder.mealname.setText (name.get (i));
        myViewHolder.mealdisc.setText (discription.get (i));
        myViewHolder.layout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (v.getContext (),finallMealDetail.class);
                intent.putExtra ("_id",_id.get (i));
                intent.putExtra ("name",name.get (i));
                intent.putExtra ("size",size.get (i));
                intent.putExtra ("price",price.get (i));
                intent.putExtra ("discount",discount.get (i));
                intent.putExtra ("image_url",image_url.get (i));
                intent.putExtra ("discription",discription.get (i));


                v.getContext ().startActivity (intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return _id.size ();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
    ImageView photo;
    TextView mealdisc, mealprice,  mealname;
    LinearLayout layout;

        public myViewHolder(@NonNull View itemView) {
            super (itemView);
            photo =itemView.findViewById (R.id.mealphoto);
           mealdisc=itemView.findViewById (R.id.mealdiscription);
            mealname=itemView.findViewById (R.id.mealname);
            mealprice=itemView.findViewById (R.id.mealprice);
            layout=itemView.findViewById (R.id.layout);

        }
    }

}
