package com.example.vidhi.computeraideddiagnostic.Fragments;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vidhi.computeraideddiagnostic.DatabaseHelper;
import com.example.vidhi.computeraideddiagnostic.R;


public class chief_complaints extends Fragment {

    DatabaseHelper myDb;
    RadioButton low_pain, upp_pain, none_pain, yes_fever, no_fever, yes_cold, no_cold, high_bp, low_bp, normal_bp,fever_duration_more,fever_duration_less;
    int low_pain1=0, upp_pain1=0, none_pain1=0, yes_fever1=0, no_fever1=0, yes_cold1=0, no_cold1=0, high_bp1=0, low_bp1=0, normal_bp1=0,fever_duration_more11=0,fever_duration_less11=0;
    Button btn;
    Button btn_view;
    RadioGroup fever_duration;
    int flag=0;
    ImageView img1;

    public chief_complaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myDb = new DatabaseHelper(getContext());
        View v = inflater.inflate(R.layout.fragment_chief_complaints, container, false);
        low_pain = v.findViewById(R.id.lower_abdomen);
        upp_pain = v.findViewById(R.id.upper_abdomen);
        none_pain = v.findViewById(R.id.none_pain);
        yes_fever = v.findViewById(R.id.yes_fever);
        no_fever = v.findViewById(R.id.no_fever);
        yes_cold = v.findViewById(R.id.yes_cold);
        no_cold = v.findViewById(R.id.no_cold);
        high_bp = v.findViewById(R.id.high_bp);
        low_bp = v.findViewById(R.id.low_bp);
        normal_bp = v.findViewById(R.id.normal_bp);
        btn = v.findViewById(R.id.btn_chief);
        fever_duration_less=v.findViewById(R.id.yes_fever_lessthan1week);
        fever_duration_more=v.findViewById(R.id.yes_fever_morethan1week);
     //   btn_view = v.findViewById(R.id.btn_chief_view);
        fever_duration=(RadioGroup)v.findViewById(R.id.fever_duration);
        img1=v.findViewById(R.id.nextimg1);
        chief();
      //  viewall();
        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        yes_fever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(yes_fever.isChecked()) {
                    fever_duration.setVisibility(View.VISIBLE);
                }
                else {
                    fever_duration.setVisibility(View.GONE);
                }
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    public void chief() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    if (low_pain.isChecked()) {
                        low_pain1 = 1;
                    }
                    if (upp_pain.isChecked()) {
                        upp_pain1 = 1;
                    }
                    if (none_pain.isChecked()) {
                        none_pain1 = 1;
                    }
                    if (yes_fever.isChecked()) {
                        yes_fever1 = 1;
                    }
                    if (no_fever.isChecked()) {
                        no_fever1 = 1;
                    }
                    if (yes_cold.isChecked()) {
                        yes_cold1 = 1;
                    }
                    if (no_cold.isChecked()) {
                        no_cold1 = 1;
                    }
                    if (high_bp.isChecked()) {
                        high_bp1 = 1;
                    }
                    if (low_bp.isChecked()) {
                        low_bp1 = 1;
                    }
                    if (normal_bp.isChecked()) {
                        normal_bp1 = 1;
                    }
                    if (fever_duration_less.isChecked()) {
                        fever_duration_less11 = 1;
                    }
                    if (fever_duration_more.isChecked()) {
                        fever_duration_more11 = 1;
                    }
                    Log.e("TAG", "before inserting");
                    boolean isInserted = myDb.insert8(low_pain1, upp_pain1, none_pain1, yes_fever1, no_fever1, yes_cold1, no_cold1, high_bp1, low_bp1, normal_bp1, fever_duration_less11, fever_duration_more11);
                    Log.e("TAG", "after inserting");
                    if (isInserted == true) {
                        Toast.makeText(getContext(), "Successfully entered", Toast.LENGTH_SHORT).show();
                        btn.setBackgroundColor(Color.BLUE);
                        btn.setTextColor(Color.WHITE);
                        btn.setText("Data is SAVED");
                        flag=1;
                        img1.setVisibility(View.VISIBLE);
                        img1.setFocusable(true);
                    } else {
                        Toast.makeText(getContext(), "data not inserted", Toast.LENGTH_SHORT).show();
                    }
                    low_bp1 = 0;
                    low_pain1 = 0;
                    upp_pain1 = 0;
                    none_pain1 = 0;
                    yes_cold1 = 0;
                    yes_fever1 = 0;
                    none_pain1 = 0;
                    no_cold1 = 0;
                    no_fever1 = 0;
                    normal_bp1 = 0;
                    fever_duration_less11 = 0;
                    fever_duration_more11 = 0;
                } else {
                    Toast.makeText(getContext(), "One User can submit details only 1 time \n For another details, try with different login.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void viewall() {
//        btn_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor res = myDb.getalldata_chief();
//
//                if (res.getCount() == 0) {
//                    Toast.makeText(getContext(), "No data found!!", Toast.LENGTH_SHORT).show();
//                }
//
//                StringBuffer buffer1 = new StringBuffer();
//
//                while (res.moveToNext()) {
//                    buffer1.append("Pain_lower abdomen:" + res.getInt(1) + "\n");
//                    buffer1.append("Pain_upper abdomen:" + res.getInt(2) + "\n");
//                    buffer1.append("Pain_none:" + res.getInt(3) + "\n");
//                    buffer1.append("Fever_yes:" + res.getInt(4) + "\n");
//                    buffer1.append("Fever_no:" + res.getInt(5) + "\n");
//                    buffer1.append("cold_yes:" + res.getInt(6) + "\n");
//                    buffer1.append("cold_no:" + res.getInt(7) + "\n");
//                    buffer1.append("BP_high:" + res.getInt(8) + "\n");
//                    buffer1.append("BP_low:" + res.getInt(9) + "\n");
//                    buffer1.append("BP_normal:" + res.getInt(10) + "\n");
//                    buffer1.append("Fever_more than 1 week:" + res.getInt(11) + "\n");
//                    buffer1.append("Fever_less than 1 week:" + res.getInt(12) + "\n\n\n");
//
//                }
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setCancelable(true);
//                builder.setTitle("CHIEF COMPLAINTS:");
//                builder.setMessage(buffer1);
//                builder.show();
//
//            }
//
//        });
//
// }

}
