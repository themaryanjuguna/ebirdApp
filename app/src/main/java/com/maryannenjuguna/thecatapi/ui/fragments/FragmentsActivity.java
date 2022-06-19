package com.maryannenjuguna.thecatapi.ui.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.maryannenjuguna.thecatapi.R;
import com.maryannenjuguna.thecatapi.ui.fragments.HomeFragment;
import com.maryannenjuguna.thecatapi.ui.fragments.NotificationFragment;
import com.maryannenjuguna.thecatapi.ui.fragments.SettingsFragment;

public class FragmentsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);


        bottomNavigationView = findViewById(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        BadgeDrawable badgeDrawable =bottomNavigationView.getOrCreateBadge(R.id.notifications);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;

                    case R.id.notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                        return true;

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsFragment).commit();
                    return true;
                }

                return false;
            }
        });

    }
}