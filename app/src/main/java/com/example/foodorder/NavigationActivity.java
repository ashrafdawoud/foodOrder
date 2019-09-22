package com.example.foodorder;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_navigation);
        Fragment adresses, lastorders, setting;


        Intent intent = getIntent ( );
        String id = intent.getStringExtra ("favourite");
        TextView title = findViewById (R.id.titlebar);

        if (id.equals ("favourit")) {
            adresses = new lastorderFragment ( );
            FragmentManager fm = getFragmentManager ( );
            FragmentTransaction ft = fm.beginTransaction ( );
            ft.replace (R.id.Navigationfragment, adresses);
            ft.commit ( );
            title.setText ("عناويني");
        }


            if (id.equals ("lastorder")) {
                lastorders = new lastorderFragment ( );
                FragmentManager fm = getFragmentManager ( );
                FragmentTransaction ft = fm.beginTransaction ( );
                ft.replace (R.id.Navigationfragment, lastorders);
                ft.commit ( );
                title.setText ("طلباتي");

            }
            if (id.equals ("setting")) {
                setting = new settingFragment ( );
                FragmentManager fm = getFragmentManager ( );
                FragmentTransaction ft = fm.beginTransaction ( );
                ft.replace (R.id.Navigationfragment, setting);
                ft.commit ( );
                title.setText ("الاعدادات ");
                Toast.makeText (this, "hello", Toast.LENGTH_LONG).show ( );

            }


            ImageView back = findViewById (R.id.back);
            back.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    finish ();
                }
            });


        }
    }

