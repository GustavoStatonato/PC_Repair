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
 * Created by GustavoB3 on 01/06/2016.
 */
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
