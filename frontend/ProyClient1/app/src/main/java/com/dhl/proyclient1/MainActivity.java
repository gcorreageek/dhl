package com.dhl.proyclient1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(new CardContentFragment(), null);
        adapter.addFragment(new CardContentFragment(), null);
        adapter.addFragment(new CardContentFragment(), null);
        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);


        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setIcon(R.drawable.ic_grain_black_24dp);
        tabs.getTabAt(1).setIcon(R.drawable.ic_thumbs_up_down_black_24dp);
        tabs.getTabAt(2).setIcon(R.drawable.ic_favorite_border_black_24dp);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        View headerview = navigationView.getHeaderView(0);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.linearlayout_header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity","onClickonClickonClickonClickonClick!!");
                Context context = MainActivity.this;
                Intent intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });




        navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.i("MainActivity","siiiiiiiiii!!");
                item.setChecked(true);
                int id = item.getItemId();
                if(id==R.id.nav_home){
                    Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                }else if(id==R.id.nav_edit){
                    Log.i("MainActivity","nav_edit!!");
                    Context context = MainActivity.this;
                    Intent intent = new Intent(context, EditActivity.class);
                    context.startActivity(intent);
                }else if(id==R.id.nav_close){
                    Log.i("MainActivity","nav_close!!");
                    Context context = MainActivity.this;
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }else if(id==R.id.nav_config){
                    Log.i("MainActivity","nav_config!!");
                    Context context = MainActivity.this;
                    Intent intent = new Intent(context, ConfigActivity.class);
                    context.startActivity(intent);
                }else if(id==R.id.nav_about){
                    Log.i("MainActivity","nav_about!!");
                    Context context = MainActivity.this;
                    Intent intent = new Intent(context, AboutActivity.class);
                    context.startActivity(intent);
                }else if(id==R.id.nav_help){
                    Log.i("MainActivity","nav_help!!");
                    Context context = MainActivity.this;
                    Intent intent = new Intent(context, HelpActivity.class);
                    context.startActivity(intent);
                }else if(id==R.id.nav_web){
                    String url = "https://github.com/gcorreageek/dhl";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Intent intent = new Intent(context, NewArticleActivity.class);
                context.startActivity(intent);

                Snackbar.make(v, "Hello Snackbar!",Snackbar.LENGTH_LONG).show();
            }
        });


    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Log.i("MainActivity","ENTRAA!!"+id);
        if(id==R.id.nav_edit){
            Log.i("MainActivity","Hola1!!"+id);
            Toast.makeText(this,"Hola!!",Toast.LENGTH_SHORT).show();
        }
        Log.i("MainActivity","Hola2!!"+mDrawerLayout.toString());
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }*/

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("MainActivity","nav_edit!!");
        //noinspection SimplifiableIfStatement


//        if (id == R.id.nav_profile) {
//            Context context = MainActivity.this;
//            Intent intent = new Intent(context, ProfileActivity.class);
//            context.startActivity(intent);
//            return true;
//        } else
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}
