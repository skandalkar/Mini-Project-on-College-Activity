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
    TextView userProfile_Name;
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
       /* toolbar = view.findViewById(R.id.home_toolbar);
        userProfile_Name = view.findViewById(R.id.user_profile_name);
        String userProfileName = getArguments().getString("username");
        userProfile_Name.setText(userProfileName);
*/
        return view;
    }
}