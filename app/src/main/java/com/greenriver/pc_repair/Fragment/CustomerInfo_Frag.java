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

package com.greenriver.pc_repair.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.greenriver.pc_repair.MainActivity;
import com.greenriver.pc_repair.R;

/**
 * Created by Gustavo on 28/02/2016.
 *
 * Class used for creation of User and data linked to Customer_layout.xml.
 */

public class CustomerInfo_Frag extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phone;
    private EditText sid;
    private CheckBox slow;
    private CheckBox malware;
    private CheckBox hardware;
    private CheckBox software;
    private CheckBox other;
    private EditText description;
    private Button btnNext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.customer_layout, container, false);

        //defines button
        btnNext = (Button) rootView.findViewById(R.id.nextButton);
        btnNext.setBackgroundColor(Color.rgb(108, 179, 59));


        firstName = (EditText) rootView.findViewById(R.id.firstName);

        lastName = (EditText) rootView.findViewById(R.id.lastName);

        email = (EditText) rootView.findViewById(R.id.email);

        phone = (EditText) rootView.findViewById(R.id.phone);

        sid = (EditText) rootView.findViewById(R.id.sid);

        slow = (CheckBox) rootView.findViewById(R.id.slowBox);

        malware = (CheckBox) rootView.findViewById(R.id.malwareBox);

        hardware = (CheckBox) rootView.findViewById(R.id.hardwareBox);

        software= (CheckBox) rootView.findViewById(R.id.softwareBox);

        other= (CheckBox) rootView.findViewById(R.id.otherBox);

        description = (EditText) rootView.findViewById(R.id.issue);

        /*
        MainActivity main = (MainActivity)getActivity();
        main.changeWarrantyButtonOff();
        main.changePolicy1ButtonOff();
        main.changePolicy2ButtonOff();
        main.changeCustomerInfoButtonOn();
        main.changePaymentButtonOff();
        main.changeReviewButtonOff();
        */

        if( firstName.getText().toString().length() == 0 )
            firstName.setError( "First name is required!" );
        if( lastName.getText().toString().length() == 0 )
            lastName.setError( "Last name is required!" );
        if( email.getText().toString().length() == 0 )
            email.setError( "Email is required!" );
        if( phone.getText().toString().length() == 0 )
            phone.setError( "Phone is required!" );
        if( description.getText().toString().length() == 0)
            description.setError( "Description is required!");


        firstName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if( firstName.getText().toString().length() == 0 )
                    firstName.setError( "First name is required!" );
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if( lastName.getText().toString().length() == 0 )
                    lastName.setError( "Last name is required!" );
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if( email.getText().toString().length() == 0 )
                    email.setError( "Email is required!" );
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if( phone.getText().toString().length() == 0 )
                    phone.setError( "Phone is required!" );


            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        //Inflate the next Policy1
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmailValid(email.getText().toString())) {

                    if ( phone.getText().toString().length() == 10) {

                        if(description.getText().length() > 0) {

                            MainActivity main = (MainActivity) getActivity();
                            String s = "";
                            if (slow.isChecked()) {
                                s += "[Slow] ";
                            }
                            if (malware.isChecked()) {
                                s += "[Malware] ";
                            }
                            if (hardware.isChecked()) {
                                s += "[Hardware] ";
                            }
                            if (software.isChecked()) {
                                s += "[Software] ";
                            }
                            if (other.isChecked()) {
                                s += "[Other] ";
                            }
                            else
                            {
                                s += "[Other] ";
                            }


                            main.setCustomerInfoData(firstName.getText().toString() + " " + lastName.getText().toString()
                                    + ":://" + phone.getText().toString() + ":://" + email.getText().toString()
                                    + ":://" + sid.getText().toString() + ":://" + s + ":://" + description.getText().toString());

                            FragmentManager fn = getFragmentManager();
                            fn.beginTransaction().replace(R.id.content_frame, new Payment_Frag()).commit();
                        }
                        else
                        {
                            description.setError("Please give a description of your issue!");
                        }

                    }
                    else
                    {
                        phone.setError( "A 10 digit phone number is required." );
                    }
                }
                else
                {
                    email.setError( "Please check the email format and try again!" );
                }
            }
        });




        return rootView;
    }

    //checks email address is a valid format
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }






}

