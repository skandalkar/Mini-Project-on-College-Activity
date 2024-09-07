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
 respective database created at RealtimeDatabase to improve or enhance the user experience this
 require #Backend_expense_track

 */

public class FragmentB extends Fragment
{

    RadioGroup radioGroupB;
    RadioButton rB1,rB2,rB3,rB4,rB5;

/*
    public FragmentB() {
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
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        radioGroupB = view.findViewById(R.id.rBGroup);

        rB1 = view.findViewById(R.id.frag_b_r1);
        rB2 = view.findViewById(R.id.frag_b_r2);
        rB3 = view.findViewById(R.id.frag_b_r3);
        rB4 = view.findViewById(R.id.frag_b_r4);
        rB5 = view.findViewById(R.id.frag_b_r5);

        radioGroupB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
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