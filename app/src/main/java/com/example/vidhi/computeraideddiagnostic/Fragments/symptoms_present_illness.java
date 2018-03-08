package com.example.vidhi.computeraideddiagnostic.Fragments;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vidhi.computeraideddiagnostic.DatabaseHelper;
import com.example.vidhi.computeraideddiagnostic.R;


public class symptoms_present_illness extends Fragment {

    DatabaseHelper myDb;
    CheckBox dia,thy,hyp;
    int dia1=0,thy1=0,hyp1=0;
    Button btn;
    Button btn_vieww;
    int flag=0;
    ImageView img9;

    public symptoms_present_illness() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myDb = new DatabaseHelper(getContext());
        View v=inflater.inflate(R.layout.fragment_symptoms_present_illness, container, false);
        dia=v.findViewById(R.id.dia_present);
        thy=v.findViewById(R.id.thy_present);
        hyp=v.findViewById(R.id.hyp_present);
        btn=v.findViewById(R.id.btn_present);
      //  btn_vieww=v.findViewById(R.id.btn_present_view);
        img9=v.findViewById(R.id.nextimg9);
        present();
        //vieww();
        return v;
    }

    public void present()
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    if (dia.isChecked()) {
                        dia1 = 1;
                    }
                    if (thy.isChecked()) {
                        thy1 = 1;
                    }
                    if (hyp.isChecked()) {
                        hyp1 = 1;
                    }

                    boolean isInserted = myDb.insert7(dia1, thy1, hyp1);

                    if (isInserted == true) {
                        Toast.makeText(getContext(), "Successfully entered ", Toast.LENGTH_SHORT).show();
                        flag=1;
                        btn.setBackgroundColor(Color.BLUE);
                        btn.setTextColor(Color.WHITE);
                        btn.setText("Data is SAVED");
                        img9.setVisibility(View.VISIBLE);
                        img9.setFocusable(true);
                    } else {
                        Toast.makeText(getContext(), "unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "One User can submit details only 1 time \n For another details, try with different login.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void vieww()
//    {
//        btn_vieww.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Cursor res = myDb.getalldata_present();
//
//                if (res.getCount() == 0) {
//                    Toast.makeText(getContext(), "No data found!!", Toast.LENGTH_SHORT).show();
//                }
//
//                StringBuffer buffer = new StringBuffer();
//
//                while (res.moveToNext()) {
//                    buffer.append("Diabetes:" + res.getInt(1) + "\n");
//                    buffer.append("Thyroid:" + res.getInt(2) + "\n");
//                    buffer.append("Hypertension:" + res.getInt(3) + "\n\n\n");
//
//                }
//
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setCancelable(true);
//                builder.setTitle("PRESENT ILLNESS:");
//                builder.setMessage(buffer);
//                builder.show();
//
//            }
//
//
//
//        });
//    }
}

