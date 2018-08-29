package com.quoctrungdhqn.android_bottom_navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fragmentContainer;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Bottom Navigation");
        }

        fragmentContainer = findViewById(R.id.frame_container);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        setUpBottomNavigation(bottomNavigation);

        loadFragment(new StoreFragment());

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    public void setUpBottomNavigation(AHBottomNavigation bottomNavigation) {
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this,
                R.menu.navigation);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#F2F2F2"));
        bottomNavigation.setBehaviorTranslationEnabled(true);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#FF4081"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            Fragment fragment;

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        fragment = new StoreFragment();
                        loadFragment(fragment);
                        break;
                    case 1:
                        fragment = new GiftsFragment();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = new CartFragment();
                        loadFragment(fragment);
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    case 4:
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }

}
