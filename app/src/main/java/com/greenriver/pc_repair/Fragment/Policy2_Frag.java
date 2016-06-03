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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.greenriver.pc_repair.MainActivity;
import com.greenriver.pc_repair.R;

/**
 * Created by Gustavo on 28/02/2016.
 *Class used for confirmation of policy 2, linked to Policy2_layout.xml.
 **/
public class Policy2_Frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.policy2_layout, container, false);



        final CheckBox Check = (CheckBox) rootView.findViewById(R.id.checkedPolicy2);
        final Button btnNext = (Button) rootView.findViewById(R.id.nextButton);
        final MainActivity main = (MainActivity)getActivity();


        // predefines button as not enabled.
        btnNext.setEnabled(false);

        //hides Next button until warranty set to no
        btnNext.setVisibility(View.INVISIBLE);


        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                if (Check.isChecked()) {

                    //Enables and un-hides the Next button
                    btnNext.setEnabled(true);
                    btnNext.setVisibility(View.VISIBLE);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                FragmentManager fn = getFragmentManager();
                fn.beginTransaction().replace(R.id.content_frame, new CustomerInfo_Frag() {
                }).commit();
            }
        });

        return rootView;
    }
}