package com.example.healthcare;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class ThreeFragment extends Fragment {

    ThreeFragmentListener activityCallback;
    //Listener for onButtonClick UI
    public interface ThreeFragmentListener {
        public void onSpecializationButtonClick();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ThreeFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TreeFragmentListener");
        }
    }

    //We get the reference to the editText and the button setUp the OnClickListener
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.three_fragment, container, false);

        final Button button = (Button) view.findViewById(R.id.chestandback);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v);
            }
        });

        return view;
    }

//    //Set the activityCallback to onButtonClick passing the text in the mEditText
//    public void buttonClicked(View view) {
//      //  activityCallback.onSpecialButtonClick();
//
//    }



    public void clickedButton(View view)
    {

        activityCallback.onSpecializationButtonClick();


    }



}