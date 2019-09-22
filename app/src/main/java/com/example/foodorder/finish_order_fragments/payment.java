package com.example.foodorder.finish_order_fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.foodorder.FinshOrder;
import com.example.foodorder.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class payment extends Fragment {


    public payment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =inflater.inflate (R.layout.fragment_payment, container, false);
        final RadioButton cridet=view.findViewById (R.id.cridetCheck);
         final RadioButton delever=view.findViewById (R.id.deleverCheck);
        RadioGroup radioGroup=view.findViewById (R.id.radiogroup);
        Button go=view.findViewById (R.id.gotoreview);

        final TextView delever1,payment1,review1;
       delever1= getActivity ().findViewById (R.id.delever);
        payment1=getActivity ().findViewById (R.id.payment);
        review1=getActivity ().findViewById (R.id.review);
        delever1.setBackgroundColor (getResources ().getColor (R.color.black));
        payment1.setBackgroundColor (getResources ().getColor (R.color.da));
        review1.setBackgroundColor (getResources ().getColor (R.color.black));


        final Fragment deleverFrag, paymentFrag,reviewFrag;

        deleverFrag=new deliver ();
        paymentFrag= new payment ();
        reviewFrag=new finalDetails ();
        go.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getFragmentManager ();
                FragmentTransaction ft=fm.beginTransaction ();
                ft.replace (R.id.containerFrag,reviewFrag);
                ft.addToBackStack (null);
                ft.commit ();
                delever1.setBackgroundColor (getResources ().getColor (R.color.black));
                payment1.setBackgroundColor (getResources ().getColor (R.color.black));
                review1.setBackgroundColor (getResources ().getColor (R.color.da));
            }
        });






  return view;  }



}
