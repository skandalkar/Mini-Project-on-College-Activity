package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 In this Fragment the collection of user's answer for survey is done then passed to the user's
 respective database created at RealtimeDatabase to improve or enhance the user experience this require #Backend_expense_track

 */

public class FragmentC extends Fragment
{
    RadioGroup radioGroupC;
    RadioButton rC1,rC2,rC3,rC4,rC5;
/*
    public FragmentC()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
*/
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        radioGroupC = view.findViewById(R.id.rCGroup);

        rC1 = view.findViewById(R.id.frag_c_r1);
        rC2 = view.findViewById(R.id.frag_c_r2);
        rC3 = view.findViewById(R.id.frag_c_r3);
        rC4 = view.findViewById(R.id.frag_c_r4);
        rC5 = view.findViewById(R.id.frag_c_r5);

        radioGroupC.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkeState(checkedId);
            }

            private void checkeState(int checkedId)
            {
                if (checkedId ==0)
                {
                    Toast.makeText(getContext(),"Please select anyone.",Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }
}