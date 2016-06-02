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
 * Created by GustavoB3 on 01/06/2016.
 */
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