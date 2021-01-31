package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.viewpager);
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.inflateMenu(R.menu.menu);

        setActionBar(toolbar);
        ArrayList<String>arrayList=new ArrayList<>();
        arrayList.add("CHECKER");
        arrayList.add("HEALTH NEWS");
        prepareViewPager(viewPager,arrayList);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.signout){
            Toast.makeText(getApplicationContext(),"signout",Toast.LENGTH_SHORT).show();

            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(MainActivity.this, login2.class));
                            finish();
                        }
                    });
        }
        return true ;
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {

        MainAdapter adapter=new  MainAdapter(getSupportFragmentManager());
        StratActivity fragment=new StratActivity();

            Bundle bundle=new Bundle();
            bundle.putString("title",arrayList.get(0));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment,arrayList.get(0));
            fragment=new StratActivity();
        viewPager.setAdapter(adapter);
        NewsActivity fragment2=new NewsActivity();

        Bundle bundle1=new Bundle();
            bundle.putString("title",arrayList.get(1));
            fragment2.setArguments(bundle);
            adapter.addFragment(fragment2,arrayList.get(1));
            fragment2=new NewsActivity();

        viewPager.setAdapter(adapter);
    }


    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList=new ArrayList<>();
        List<Fragment> fragmentList=new ArrayList<>();
    public void addFragment(Fragment fragment, String title)
    {
       arrayList.add(title);
       fragmentList.add(fragment);
    }
        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}
