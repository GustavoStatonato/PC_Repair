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

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.greenriver.pc_repair.R;

/**
 * Created by Gustavo on 28/02/2016.
 *Class used to alert when the manufacturer's warranty is valid, linked to Warranty_alert.xml.
 **/

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
