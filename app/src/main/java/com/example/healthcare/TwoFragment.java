
package com.example.healthcare;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TwoFragment extends Fragment {

    private static TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.two_fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.textChanged);
        return view;
    }

    public void changeTextProperties(String text) {
        mTextView.setText(text);
    }

}