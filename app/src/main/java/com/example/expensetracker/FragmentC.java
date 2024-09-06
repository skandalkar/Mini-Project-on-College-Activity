package com.example.expensetracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 In this Fragment the collection of user's answer for survey is done then passed to the user's
 respective database created at RealtimeDatabase to improve or enhance the user experience this require #Backend_expense_track

 */

public class FragmentC extends Fragment
{

    public FragmentC()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_c, container, false);
    }
}