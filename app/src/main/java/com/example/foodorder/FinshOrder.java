package com.example.foodorder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.foodorder.finish_order_fragments.deliver;
import com.example.foodorder.finish_order_fragments.finalDetails;
import com.example.foodorder.finish_order_fragments.payment;

public class FinshOrder extends AppCompatActivity {
   public TextView delever1,payment1,review1;
    Fragment deleverFrag, paymentFrag,reviewFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_finsh_order);


        delever1=findViewById (R.id.delever);
        payment1=findViewById (R.id.payment);
        review1=findViewById (R.id.review);

        deleverFrag=new deliver ();
        paymentFrag= new payment ();
        reviewFrag=new finalDetails ();

        FragmentManager fm= getFragmentManager ();
        FragmentTransaction ft=fm.beginTransaction ();
        ft.replace (R.id.containerFrag,deleverFrag);
        ft.addToBackStack (null);
        ft.commit ();

        delever1.setBackgroundColor (getResources ().getColor (R.color.da));
        payment1.setBackgroundColor (getResources ().getColor (R.color.black));
        review1.setBackgroundColor (getResources ().getColor (R.color.black));








    }

    public void close(View view) {
        finish ();
    }
}
