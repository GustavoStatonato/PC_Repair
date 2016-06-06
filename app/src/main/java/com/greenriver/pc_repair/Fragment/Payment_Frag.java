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
import android.app.FragmentManager;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenriver.pc_repair.MainActivity;
import com.greenriver.pc_repair.R;

import java.io.FileOutputStream;

/**
 * Created by Gustavo e Francisco on 28/02/2016.
 *
 * Class used for creation of payment data, linked to Payment_layout.xml.
 **/
public class Payment_Frag extends DialogFragment implements View.OnClickListener {

    private TextView price;
    private boolean signed = false;
    private boolean payment = false;
    private View rootView;
    private View alert;
    private CheckBox cash;
    private CheckBox check;
    private CheckBox spunkyDiscount;
    private Button apply;
    private Button reset;
    private Button gestureSave;
    private Button gestureClear;
    private EditText receipt;
    private Double discount;
    private Button next;
    private Double updatePrice = 0.00;
    public double DEFAULT_PRICE = 25.00;
    private GestureOverlayView gv; // For signature box
    private boolean isClicked = false; //Used in signature to determine is gesture was pressed
    private String FILENAME = "signature.png";
    private FileOutputStream fos;
    private String paymentType;
    private MainActivity main;

    //SpunkyDiscount Not Implemented
    //public static final int SPUNKYDISCOUNTALERT = 1; //class variable
    //SpunkyDiscountAlert discountSpunky = new SpunkyDiscountAlert(); //intialize call to spunkydiscountalert

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.payment_layout, container, false);
        price = (TextView) rootView.findViewById(R.id.price);
        price.setText(String.format("%.2f", DEFAULT_PRICE));
        apply = (Button) rootView.findViewById(R.id.applyDiscount);
        apply.setOnClickListener(this);
        apply.setEnabled(false);
        next = (Button) rootView.findViewById(R.id.nextButton);
        next.setBackgroundColor(Color.rgb(108, 179, 59));
        next.setOnClickListener(this);
        spunkyDiscount = (CheckBox) rootView.findViewById(R.id.spunky_Discount);
        spunkyDiscount.setOnClickListener(this);
        cash = (CheckBox) rootView.findViewById(R.id.cashPayment);
        cash.setOnClickListener(this);
        check = (CheckBox) rootView.findViewById(R.id.checkPayment);
        check.setOnClickListener(this);

        //Initialize reciept
        receipt = (EditText) rootView.findViewById(R.id.receipt);

        // Initialize gesture for signature
        gv = (GestureOverlayView) rootView.findViewById(R.id.signature);
        gv.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
        gv.setFadeEnabled(true);
        gv.setFadeOffset(5000);
        // Cached Drawing for future export
        gv.setDrawingCacheEnabled(true);

        // Initialize button
        gestureSave = (Button) rootView.findViewById(R.id.btnSave);
        gestureSave.setOnClickListener(this);
        gestureClear = (Button) rootView.findViewById(R.id.btnClear);
        gestureClear.setOnClickListener(this);

        return rootView;
    }

    /**
     * onClick Method:
     * Handles all on click functions within the view using a
     * switch statement.
     * <p/>
     * Case 1:
     * Spunky Discount
     * This method alters the price TextView to apply Spunky
     * discount. It takes the information from the alert and applies
     * the value entered, reducing the price.
     * <p/>
     * Case 2:
     * Apply Discount
     * This method takes the information provided from Spunky Dicount
     * and implements it to TextView
     * <p/>
     * Case 3:
     * Reset Amount
     * Clicking the reset button will put all the values to zero and correct TextView to the
     * default $50.00 amount
     * <p/>
     * Case 4 and 5:
     * Cash or Check Payment
     * When the cash payment checkbox is selected, the case will check
     * if the box is selected or if it has not been. If the box is not
     * checked, it will disable the other checkbox, but will enable both
     * if the checkbox is being unchecked. The same is true visa versa.
     *
     * @param v View of the current fragment
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spunky_Discount:
                //This case is not implemented
                if (spunkyDiscount.isChecked()) {
                    // Alert prompting user to enter an amount.
                    // discountSpunky.setTargetFragment(this, SPUNKYDISCOUNTALERT);
                    // discountSpunky.show(getFragmentManager().beginTransaction(), "alertDialog");
                    // apply.setEnabled(true);
                }
                else {
                    price.setText(String.format("%.2f", DEFAULT_PRICE));
                    apply.setEnabled(false);
                }
                break;
            case R.id.applyDiscount:
                if (updatePrice != 0){
                    price.setText(String.format("%.2f", updatePrice));
                }
                v.invalidate();
                break;
            case R.id.cashPayment:
                if (check.isEnabled()) {
                    paymentType = "cash";
                    check.setEnabled(false);
                    payment = true;
                } else {
                    check.setEnabled(true);
                    payment = false;
                }
                break;
            case R.id.checkPayment:
                if (cash.isEnabled()) {
                    paymentType = "check";
                    cash.setEnabled(false);
                    payment = true;
                } else {
                    cash.setEnabled(true);
                    payment = false;
                }
                break;
            //For some reason, this only can save the first attempt
            case R.id.btnSave:
                Bitmap bm = Bitmap.createBitmap(gv.getDrawingCache());
                main = (MainActivity)getActivity();
                main.setImageData(bm);
                //Allows the user to continue to the next page
                signed = true;
                gestureSave.setEnabled(false);
                gestureClear.setEnabled(false);
                //Empties the Cache
                gv.setDrawingCacheEnabled(false);

                //This section is for saving local copies This case is not implemented
                /*try {
                    gv.setDrawingCacheEnabled(false);
                    Toast.makeText(getActivity(),
                    "I'm in the save button", Toast.LENGTH_LONG).show();
                    fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    //compress to specified format (PNG), quality -
                    //which is ignored for PNG, and out stream
                    bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    a.setImageData(bm);
                    fos.flush();
                    fos.close();
                    Toast.makeText(getActivity(), "Signature Received.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.v("Gestures", e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        fos.flush();
                        fos.close();
                        Toast.makeText(getActivity(), "Signature Saved.", Toast.LENGTH_LONG).show();
                    } catch (Throwable ignore) {
                        Toast.makeText(getActivity(),
                        "Oops! File didn't save.", Toast.LENGTH_LONG).show();
                    }
                }*/
                //resets the cache
                gv.setDrawingCacheEnabled(true);
                gestureSave.setEnabled(true);
                gestureClear.setEnabled(true);
                Toast.makeText(getActivity(), "Signature saved!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnClear:
                signed = false;
                gv.cancelClearAnimation();
                gv.clear(true);
                gv.invalidate();
                break;
            case R.id.nextButton:
                main = (MainActivity)getActivity();
                if (signed == true && payment == true && (receipt.getText() != null)) {
                    main.setPaymentData(receipt.getText().toString() +
                            ":://" + price.getText().toString() + ":://"
                           + paymentType);
                    FragmentManager fn = getFragmentManager();
                    fn.beginTransaction().replace(R.id.content_frame, new Review_Frag()).commit();
                }
                else {
                    Toast.makeText(getActivity(), "" +
                            "You did not fill out the required information!",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

