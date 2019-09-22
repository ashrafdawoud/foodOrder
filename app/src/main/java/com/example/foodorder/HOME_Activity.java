package com.example.foodorder;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class HOME_Activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    ImageView bnbutton;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home_);




       //الكود الخاص بال navigation and it tooggle
        drawerLayout=findViewById (R.id.drawerlayout);
        toolbar=findViewById (R.id.bar);
        drawerToggle=new ActionBarDrawerToggle (this,drawerLayout,toolbar,R.string.open,R.string.end);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

       /* drawerToggle.getDrawerArrowDrawable().setColor(R.color.colorPrimary);
        drawerToggle.syncState ();
       */
        //الكود الخاص بالاختفاء لما نحدد منها عنصر
        //كود فتح اي عنصر من الnavigation
        NavigationView nave=findViewById (R.id.nav_view);
        nave.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener ( ) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Intent intent=new Intent (HOME_Activity.this,NavigationActivity.class);
                Intent intent1=new Intent (HOME_Activity.this,MainActivity.class);

                int id=menuItem.getItemId ();
                switch (id)
                {
                    case R.id.favourit:
                        intent.putExtra ("favourite" ,"favourit");
                        startActivity (intent);


                        break;
                    case R.id.lasorder:
                        intent.putExtra ("favourite" ,"lastorder");
                        startActivity (intent);

                        break;
                    case R.id.setting:
                        intent.putExtra ("favourite" ,"setting");
                        startActivity (intent);

                        break;
                    case R.id.logout:
                        finish ();
                        break;


                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        // كود فتح سلة المشتريات
       bnbutton=findViewById (R.id.pinImage);
        bnbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment fragment=new cartFragment ();
                FragmentManager fm=getFragmentManager ();
                FragmentTransaction ft=fm.beginTransaction ();
                ft.replace (R.id.homeFragment,fragment);
                ft.addToBackStack(null);
                ft.commit ();

            }
        });







}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.icon, menu);
        return true;
    }


}
