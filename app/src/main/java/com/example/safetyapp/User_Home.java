package com.example.safetyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class User_Home extends AppCompatActivity {
  androidx.viewpager.widget.ViewPager vp1;


    Toolbar toolbar1;
    TabLayout tabs1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        vp1=findViewById(R.id.vp1);
        toolbar1=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        tabs1=findViewById(R.id.tabs1);
        // replace toolbar in place of actionbar



        tabs1.addTab( tabs1.newTab().setText("ONE") );
        tabs1.addTab( tabs1.newTab().setText("TWO") );
        tabs1.addTab( tabs1.newTab().setText("THREE") );
        mypageradapter myad = new mypageradapter (getSupportFragmentManager());
        vp1.setAdapter(myad);


        // Now Connect Tabs With ViewPager
        tabs1.setupWithViewPager(vp1);

       //ActionBar actionBar = getSupportActionBar();
       // actionBar.setTitle("Safety App");
    }
    //This method is used to load menu from xml file
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return true;
    }
    //Assigning functions to menu options. This method is used to define logic of menu items
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
        }


    private void setAdapter(mypageradapter myad) {
    }

    public void user_logout(MenuItem item) {
//        Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("myapp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("mobileno");
        editor.commit();

        Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
    }

    public void user_share(MenuItem item) {
        /*Create an ACTION_SEND Intent*/
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        /*This will be the actual content you wish you share.*/
        String shareBody = "Download the app now \n https://firebasestorage.googleapis.com/v0/b/safetyapp-253d1.appspot.com/o/app-release.apk?alt=media&token=a80f5685-cf09-4c4d-aac5-4a96325d5455";
        /*The type of the content is text, obviously.*/
        intent.setType("text/plain");
        /*Applying information Subject and Body.*/
         intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        /*Fire!*/
        startActivity(Intent.createChooser(intent, shareBody));
    }

    class mypageradapter extends FragmentPagerAdapter
    {

        mypageradapter(FragmentManager frm)
        {
            super(frm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0)
                return new Emergency();
            else if(position==1)
                return new ManageFriends();
            else
                return new Profile();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
                return "Emergency";
            else if(position==1)
                return "Manage Friends";
            else
                return "Chnage Password";
        }
    }
    ////////////////
}