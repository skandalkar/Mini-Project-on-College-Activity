package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class FragmentHome extends Fragment {

    Toolbar toolbar;
    TextView userProfileName;
    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = view.findViewById(R.id.home_toolbar);



//        userProfileName = view.findViewById(R.id.user_profile_name);


        String userProfileName = getArguments().getString("username");
        //userProfileName.setText(userProfileName);

        return view;
    }
}