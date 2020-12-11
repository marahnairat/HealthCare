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
        public void onSpecializationButtonClick(String area);

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

        final Button button1 = (Button) view.findViewById(R.id.chestandback);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"Chest and back");
            }
        });


        final Button button2 = (Button) view.findViewById(R.id.headandneck);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"Head and neck");
            }
        });


        final Button button3 = (Button) view.findViewById(R.id.abdomenandpelvis);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"Abdomen and pelvis");
            }
        });


        final Button button4 = (Button) view.findViewById(R.id.noseearthroat);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"nose ear throat ");
            }
        });


        final Button button5 = (Button) view.findViewById(R.id.skin);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"skin");
            }
        });



        final Button button6 = (Button) view.findViewById(R.id.bonesandjoints);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"Bones and joints");
            }
        });



        final Button button7 = (Button) view.findViewById(R.id.elsee);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                clickedButton(v,"Else ");
            }
        });





        return view;
    }




    public void clickedButton(View view,String area)
    {

        activityCallback.onSpecializationButtonClick(area);


    }



}