package com.example.foodorder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class address_adapter extends RecyclerView.Adapter <address_adapter.viewholder> {


    public class viewholder extends RecyclerView.ViewHolder{

        public viewholder(@NonNull View itemView) {
            super (itemView);
        }
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.address_res,viewGroup,false);
        viewholder vew=new viewholder (view);
        return vew;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
