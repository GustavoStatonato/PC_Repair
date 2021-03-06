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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenriver.pc_repair.MainActivity;
import com.greenriver.pc_repair.R;

/**
 * Created by Gustavo e Francisco on 28/02/2016.
 *
 *Class used for confirmation of data and send the data to
 *server and database,  linked to Review_layout.xml.
 **/
public class Review_Frag extends Fragment implements View.OnClickListener {
    String[] array;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.review_layout, container, false);
        MainActivity main = (MainActivity)getActivity();

        //Grabs the data from main
        String test = main.getStringData();
        //Splits test into an array
        array =  test.split(":://");

        //Assigns the displays with the data
        TextView nameDisp = (TextView) rootView.findViewById(R.id.nameDisplay);
        nameDisp.setText(array[0]);

        TextView receiptDisp = (TextView) rootView.findViewById(R.id.receiptDisplay);
        receiptDisp.setText(array[1]);

        TextView priceDisp = (TextView) rootView.findViewById(R.id.costDisplay);
        priceDisp.setText(array[2]);

        TextView paymentMethod = (TextView) rootView.findViewById(R.id.paymentDisplay);
        paymentMethod.setText(array[3]);

        TextView phoneNumber = (TextView) rootView.findViewById(R.id.phoneDisplay);
        phoneNumber.setText(array[4]);

        TextView emailDisp = (TextView) rootView.findViewById(R.id.emailDisplay);
        emailDisp.setText(array[5]);

        TextView studentID = (TextView) rootView.findViewById(R.id.sidDisplay);
        studentID.setText(array[6]);

        TextView problem = (TextView) rootView.findViewById(R.id.issueDisplay);
        problem.setText(array[7]);

        ImageView signature = (ImageView) rootView.findViewById(R.id.signatureImage);
        Bitmap bm = main.getImageData();
        signature.setImageBitmap(bm);
        signature.setMaxWidth(400);

        Button btnReview = (Button) rootView.findViewById(R.id.menuButton);
        btnReview.setOnClickListener(this);
        btnReview.setBackgroundColor(Color.rgb(108, 179, 59));

        return rootView;
    }

    @Override
    public void onClick(View view) {
        String url = "http://flonghini.greenrivertech.net/pcrepair/index.php?nameDisp="
                + this.array[0] + "&receiptDisp=" + this.array[1] + "&priceDisp=" +
                this.array[2] + "&paymentMethod=" + this.array[3] + "&phoneNumber=" +
                this.array[4] + "&emailDisp=" + this.array[5] + "&studentID=" +this.array[6] +
                "&problem=" + this.array[0];
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
