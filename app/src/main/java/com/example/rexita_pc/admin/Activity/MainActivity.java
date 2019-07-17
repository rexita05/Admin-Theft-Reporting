package com.example.rexita_pc.admin.Activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.rexita_pc.admin.Fragment.BobotFragment;
import com.example.rexita_pc.admin.Fragment.DashboardFragment;
import com.example.rexita_pc.admin.Fragment.KecamatanFragment;
import com.example.rexita_pc.admin.Fragment.LogoutFragment;
import com.example.rexita_pc.admin.R;
import com.google.firebase.iid.FirebaseInstanceId;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

//    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d("TAG", "tokennn: "+ FirebaseInstanceId.getInstance().getToken());
        Fragment fragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_area, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

//        notifikasi();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                fragment = new DashboardFragment();
            }
            else if (id == R.id.navigation_bobot) {
                fragment = new BobotFragment();
            }
            else if (id == R.id.navigation_plus) {
                fragment = new KecamatanFragment();
            }
            else if (id == R.id.navigation_account) {
                fragment = new LogoutFragment();
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                ft.replace(R.id.layout_area, fragment);
                ft.commit();
            }
            return true;
        }
    };
}
