package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arctouch.codechallenge.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        HomeFragment homeFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,homeFragment).commit();

    }

}
