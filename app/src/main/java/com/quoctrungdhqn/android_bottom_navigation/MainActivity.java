package com.quoctrungdhqn.android_bottom_navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
    private FrameLayout mFragmentContainer;
    private AHBottomNavigation mBottomNavigation;
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppBarLayout = findViewById(R.id.app_bar_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Bottom Navigation");
        }

        mFragmentContainer = findViewById(R.id.frame_container);

        mBottomNavigation = findViewById(R.id.bottom_navigation);

        setUpBottomNavigation(mBottomNavigation);

        loadFragment(new StoreFragment());

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        loadAnimation(mFragmentContainer);
    }

    private void loadAnimation(FrameLayout fragmentContainer) {
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
        bottomNavigation.setBehaviorTranslationEnabled(false);

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
                        mAppBarLayout.setExpanded(true);
                        if (wasSelected) {
                            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                            if (fragment instanceof StoreFragment) {
                                ((StoreFragment) fragment).refresh();
                            }
                        } else {
                            fragment = new StoreFragment();
                            loadFragment(fragment);
                        }
                        break;
                    case 1:
                        mAppBarLayout.setExpanded(true);
                        if (!wasSelected) {
                            fragment = new GiftsFragment();
                            loadFragment(fragment);
                        }
                        break;
                    case 2:
                        mAppBarLayout.setExpanded(true);
                        if (!wasSelected) {
                            fragment = new CartFragment();
                            loadFragment(fragment);
                        }

                        break;
                    case 3:
                        mAppBarLayout.setExpanded(true);
                        if (!wasSelected) {
                            fragment = new ProfileFragment();
                            loadFragment(fragment);
                        }
                        break;
                    case 4:
                        mAppBarLayout.setExpanded(true);
                        if (!wasSelected) {
                            fragment = new SettingFragment();
                            loadFragment(fragment);
                        }
                        break;
                }
                return true;
            }
        });
    }

}
