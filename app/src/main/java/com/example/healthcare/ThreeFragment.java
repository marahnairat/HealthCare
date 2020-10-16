package com.example.healthcare;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ThreeFragment extends Fragment {

    ThreeFragmentListener activityCallback;

    //Listener for onButtonClick UI
    public interface ThreeFragmentListener {
      //  public void onSpecialButtonClick();

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
//
//        final Button button = (Button) view.findViewById(R.id.buttonchange);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                buttonClicked(v);
//            }
//        });

        return view;
    }

//    //Set the activityCallback to onButtonClick passing the text in the mEditText
//    public void buttonClicked(View view) {
//      //  activityCallback.onSpecialButtonClick();
//
//    }


}