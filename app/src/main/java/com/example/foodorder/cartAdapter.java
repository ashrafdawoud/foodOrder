package com.example.foodorder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.viewholder> {
    ArrayList <String> i_id,name,price,image_path;
    Activity activity;
    String i_idD,u_id;
    OkHttpClient client=new OkHttpClient ();
    Integer prices=0;



    public cartAdapter(String u_id,Activity activity,ArrayList <String> i_id, ArrayList <String> name, ArrayList <String> price, ArrayList <String> image_path) {
        this.u_id=u_id;
        this.activity=activity;
        this.i_id = i_id;
        this.name = name;
        this.price = price;
        this.image_path = image_path;
    }


    public class viewholder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView  name,price,delet;

        public viewholder(@NonNull View itemView) {
            super (itemView);
              poster=itemView.findViewById (R.id.itemImage);
              name=itemView.findViewById (R.id.itemName);
              price=itemView.findViewById (R.id.itemPrice);
              delet=itemView.findViewById (R.id.delet);

        }

    }

    @NonNull
    @Override
    public cartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.carttem,viewGroup,false);
        viewholder vh=new viewholder (view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.viewholder viewholder,final int i) {
        Picasso.with (activity).load (image_path.get (i)).into (viewholder.poster);
        viewholder.name.setText (name.get (i));
        viewholder.price.setText (price.get (i)+" ج.م");
        prices=prices+Integer.valueOf (price.get (i));

                viewholder.delet.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                i_idD=String.valueOf (i_id.get (i));
                Log.i ("+_____",i_idD);
                okHttpDelet ();
                notifyDataSetChanged();
                Toast.makeText (activity,"deleted",Toast.LENGTH_LONG).show ();
                image_path.remove (i);
                name.remove (i);
                price.remove (i);
                notifyDataSetChanged();

            }
        });


        Log.i ("price___+",prices.toString ());
        share();



    }

    @Override
    public int getItemCount() {

        return image_path.size ();
      //  cartAdapter.notifyDataSetChanged();

    }


    public void okHttpDelet(){
        Log.i ("u_id",u_id);
        Log.i ("i_id",i_idD);

        final String url="http://localhost:3014/cart/deleateitemcart/"+u_id+"/"+i_idD;
        Request request=new Request.Builder ().url (url).delete ().build ();
        client.newCall (request).enqueue (new Callback ( ) {
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

    public void share(){
        SharedPreferences user_pref=activity.getSharedPreferences ("totalPrice",activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=user_pref.edit ();
        editor.putString ("totalprice",prices.toString ());
        editor.commit();
        Log.i ("price___",prices.toString ());
    }
}
