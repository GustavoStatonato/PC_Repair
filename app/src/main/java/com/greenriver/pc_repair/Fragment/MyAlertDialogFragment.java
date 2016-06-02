package com.greenriver.pc_repair.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.greenriver.pc_repair.R;

public class MyAlertDialogFragment extends DialogFragment implements View.OnClickListener{
    static MyAlertDialogFragment newInstance() {
        return new MyAlertDialogFragment();
    }
    Button ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.warranty_alert,null);
        // Title of Alert
        getDialog().setTitle("Warning:");

        //setup button listener
        ok = (Button) rootView.findViewById(R.id.btnOk);
        ok.setOnClickListener(this);
        setCancelable(false);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnOk){
            dismiss();

        }
    }
}
