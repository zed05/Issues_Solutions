package com.example.zed.issues_solutions;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout   = findViewById(R.id.drawer);
        nv              = findViewById(R.id.navigationViewID);
        mToggle         = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


//        Load Fragment khi bấm vào mục
        setupDrawerContent(nv);

        VnReviewFragment vnReviewFragment = new VnReviewFragment();
        getFragmentManager().beginTransaction().add(R.id.fragLayout, vnReviewFragment).addToBackStack(null).commit();

    }

    //    Kích hoạt icon Hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    Load Fragment theo từng mục
    public void selectItemDrawer(MenuItem menuItem){
        Fragment fragment = null;
        Class fragClass;

        switch (menuItem.getItemId()){
            case R.id.vnreview:
                fragClass = VnReviewFragment.class;
                break;
            case R.id.theverge:
                fragClass = TheVergeFragment.class;
                break;
            case R.id.solution:
                fragClass = SolutionFragment.class;
                break;
            default:
                fragClass = VnReviewFragment.class;
        }

        try {
            fragment = (Fragment) fragClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragLayout, fragment).addToBackStack(null).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
