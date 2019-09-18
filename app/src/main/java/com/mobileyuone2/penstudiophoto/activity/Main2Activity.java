package com.mobileyuone2.penstudiophoto.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobileyuone2.penstudiophoto.Fragment.FragmentBeranda;
import com.mobileyuone2.penstudiophoto.Fragment.FragmentFilter;
import com.mobileyuone2.penstudiophoto.Fragment.FragmentTampilanList;
import com.mobileyuone2.penstudiophoto.Fragment.FragmentTampilanPeta;
import com.mobileyuone2.penstudiophoto.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        final FragmentBeranda fragmentBeranda = new FragmentBeranda();
        final FragmentTampilanList fragmentTampilanList = new FragmentTampilanList();;
        final FragmentFilter fragmentFilter = new FragmentFilter();
        final FragmentTampilanPeta fragmentTampilanPeta = new FragmentTampilanPeta();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.beranda){
                    setFragment(fragmentBeranda);
                    return true ;
                }else if(id == R.id.tampilanlist){
                    setFragment(fragmentTampilanList);
                    return true;
                }else if(id == R.id.filter){
                    setFragment(fragmentFilter);
                    return true;
                }else if (id == R.id.tampilanpeta){
                    setFragment(fragmentTampilanPeta);
                    return true;
                }
                return false;
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();


    }
}

