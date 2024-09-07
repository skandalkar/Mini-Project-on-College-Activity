package com.example.expensetracker;

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

public class FragmentA extends Fragment
{

    RadioGroup radioGroupA;
    RadioButton r1,r2,r3,r4,r5;

 /*

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        radioGroupA = view.findViewById(R.id.rAGroup);

        r1 = view.findViewById(R.id.frag_a_r1);
        r2 = view.findViewById(R.id.frag_a_r2);
        r3 = view.findViewById(R.id.frag_a_r3);
        r4 = view.findViewById(R.id.frag_a_r4);
        r5 = view.findViewById(R.id.frag_a_r5);

        radioGroupA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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