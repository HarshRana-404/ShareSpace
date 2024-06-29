package com.harsh.sharespace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.harsh.sharespace.fragments.AccountFragment;
import com.harsh.sharespace.fragments.MyWorkspacesFragment;
import com.harsh.sharespace.fragments.WorkspacesFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnavMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnavMain = findViewById(R.id.bn_main);

        loadFragment(new WorkspacesFragment());
        bnavMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.mi_work_spaces){
                    loadFragment(new WorkspacesFragment());
                }
                else if(item.getItemId()==R.id.mi_my_work_spaces){
                    loadFragment(new MyWorkspacesFragment());
                }
                else if(item.getItemId()==R.id.mi_account){
                    loadFragment(new AccountFragment());
                }
                return true;
            }
        });
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_bn_main, fragment);
        fm.popBackStack();
        ft.commit();
    }
}