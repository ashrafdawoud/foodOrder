package com.example.foodorder;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Fragment fragment1,fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        fragment1= new sign_infragment ();
        FragmentManager fm=getFragmentManager ();
        FragmentTransaction ft=fm.beginTransaction ();
        ft.replace (R.id.fragment,fragment1);
        ft.commit ();








    }
}
