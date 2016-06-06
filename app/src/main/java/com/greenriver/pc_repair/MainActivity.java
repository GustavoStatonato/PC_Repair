/*
The MIT License (MIT)
Copyright (c) 2015 - 2016 Green River College
Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software
is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */



package com.greenriver.pc_repair;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.greenriver.pc_repair.Fragment.CustomerInfo_Frag;
import com.greenriver.pc_repair.Fragment.Payment_Frag;
import com.greenriver.pc_repair.Fragment.Policy1_Frag;
import com.greenriver.pc_repair.Fragment.Policy2_Frag;
import com.greenriver.pc_repair.Fragment.Review_Frag;
import com.greenriver.pc_repair.Fragment.Warranty_Frag;

/**
 * Created by Gustavo e Francisco on 28/02/2016.
 *
 *Class used for data manipulation, contains methods set and get.
 **/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Fields
    Button warrantyButton, policy1Button;
    private String firstName = "first";
    private String lastName = "last";
    private String receipt = "12584aav";
    public static double DEFAULT_PRICE = 0.0;
    private String price = "50.00";
    private String paymentMethod = "cash";
    private String phoneNumber = "123456789";
    private String email = "email@mail.com";
    private String studentID = "110111011";
    private String problem = "Problem Description";
    private String issueType = "[none]";
    private Bitmap signature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        price = "" + DEFAULT_PRICE;
        FragmentManager fn = getFragmentManager();
        fn.beginTransaction().replace(R.id.content_frame, new Warranty_Frag()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Create a fragment manager to manage the fragments change.
        FragmentManager fn = getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_warranty) {
            fn.beginTransaction().replace(R.id.content_frame, new Warranty_Frag()).commit();
        } else if (id == R.id.nav_policy1) {
            fn.beginTransaction().replace(R.id.content_frame, new Policy1_Frag()).commit();
        } else if (id == R.id.nav_policy2) {
            fn.beginTransaction().replace(R.id.content_frame, new Policy2_Frag()).commit();
        } else if (id == R.id.nav_costumer) {
            fn.beginTransaction().replace(R.id.content_frame, new CustomerInfo_Frag()).commit();
        } else if (id == R.id.nav_payment) {
            fn.beginTransaction().replace(R.id.content_frame, new Payment_Frag()).commit();
        } else if (id == R.id.nav_review) {
            fn.beginTransaction().replace(R.id.content_frame, new Review_Frag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setImageData(Bitmap bm) {
        this.signature = bm;
    }

    public Bitmap getImageData() {
        return this.signature;
    }

    public void setPaymentData(String s) {
        String[] arr = s.split(":://");
        this.receipt = arr[0];
        this.price = arr[1];
        this.paymentMethod = arr[2];
    }

    public void setCustomerInfoData(String s) {
        String[] arr = s.split(":://");
        String[] arr2 = arr[0].split(" ");
        this.firstName = arr2[0];
        this.lastName = arr2[1];
        this.phoneNumber = arr[1];
        this.email = arr[2];
        this.studentID = arr[3];
        this.issueType = arr[4];
        this.problem = arr[5];
    }

    public String getStringData() {
        return (this.firstName + " " + this.lastName + ":://" + this.receipt + ":://" + this.price
                + ":://" + this.paymentMethod + ":://" + this.phoneNumber
                + ":://" + this.email + ":://" + this.studentID + ":://"
                + this.issueType + "\n" + this.problem);
    }
}
