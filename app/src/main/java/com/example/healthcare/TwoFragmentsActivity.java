package com.example.healthcare;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
   // List<String> valuesselected = Arrays.asList();
    ArrayList<String>  valuesselected=new ArrayList<String>();
   // tring [] valuesselected ;S
    HashMap<String, String> map = new HashMap<String, String>();
    String result=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);


        final Button button = (Button) findViewById(R.id.buttonchange);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onButtonClick();
            }
        });

        final Button but1 = (Button) findViewById(R.id.continueButton);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                continueCheck(v);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.two_fragments, menu);
//        getMenuInflater().inflate(R.menu.one_fragment, menu);
        return true;
    }




    @Override
    public void onButtonClick() {
        TwoFragment textFragment;
        textFragment = (TwoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.two_fragment);
        textFragment.changeTextProperties(" "+result);
        result=" ";
for(int i=0;i<valuesselected.size();i++)
        Log.i("Test", valuesselected.get(i));

    }


    public void continueCheck(View view) {
//        double data[] = new double[valuesselected.size()];
//        for (int m=0;m<valuesselected.size();m++) {
//            data[m]= Double.parseDouble(valuesselected.get(m))*10.0;
//        }
 double data[]={13,15,33,41,14,19,75,16};
        double noofclusters=3;
        double centroid[][]=new double[][]{
                {0,0,0},
                {2,4,30}
        };
        Pair c=getCentroid(data,noofclusters,centroid);

        for (int i=0;i<=c.getArray1().length;i++) {
            Log.d("Document", "marrrrrrrrrrrrrah " + (int) c.getArray1()[1][i]);
//            if ((int)c[1].length)
        }
        int max=(int)c.getArray2()[0];
        int k = 0;
        for (int j=0;j<c.getArray2().length;j++)
        {
            if (c.getArray2()[j]>=max)
            {
                max=(int) c.getArray2()[j];
                k=j;
                Log.d("Document", "max length print k   " +(int)c.getArray1()[1][k]/10 );

            }
        }
        Log.d("Document", "RESUIUUUUUUUUUUUULLLT " +(int)c.getArray1()[1][k]/10 );


//        Intent i = new Intent(getBaseContext(),ListViews.class);
//                startActivity(i);

    }





    @Override
    public void onSpecializationButtonClick(String area) {
        DocumentReference chestData= FirebaseFirestore.getInstance().collection("areas").document( area );
        chestData.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot chestdoc =task.getResult();
                    if (chestdoc.exists())
                    {

                        String[] m= chestdoc.getData().keySet().toString().split(",");
                        System.out.println(chestdoc.getData());
                        System.out.println(chestdoc.getData().getClass().getName());
                        String[] m_value= chestdoc.getData().values().toString().split(",");

                        for (int i=0;i<m.length;i++){
                            map.put(m[i],m_value[i]);
                        }
                        System.out.println(map);
//                        ArrayList<String[]> n=new ArrayList<>();
//                        for(int i=0;i<m.length;i++)
//                           n.add(chestdoc.getData().get(m[i]).toString().split(","));
                        changeTextCheckbox(m);

                      Log.d("Document", chestdoc.getData().values().toString());

                    }
                    else
                    {
                        Log.d("Document","noooooooooo data");
                    }

                }
            }
        });

    }
    public void changeTextCheckbox(String[] s) {
        for (int i=0;i<s.length;i++) {

            createChekbox(s[i],i);
        }


    }
    @SuppressLint("ResourceType")
    private void createChekbox(String s, int i) {

        final LinearLayout ll = (LinearLayout) findViewById(R.id.one_layout);

        CheckBox cb = new CheckBox(this);
        /*
            if(s.startsWith("[")||s.endsWith("]")) {

                s.replace("", "[");
                s.replace("]","");
            }*/
        cb.setText(s);
        cb.setId(i);
        ll.addView(cb);
cb.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if (((CheckBox) v).isChecked()) {

                                  result+=cb.getText()+" , ";
                                  Toast.makeText(TwoFragmentsActivity.this, cb.getText()+"SELECTED" , Toast.LENGTH_SHORT).show();
                                    valuesselected.add(map.get(cb.getText()));

                                 // Log.i("Test", "log infos");
                              } else {
                                  Toast.makeText(TwoFragmentsActivity.this, cb.getText()+"Not selected", Toast.LENGTH_SHORT).show();
                              }
                          }
                      }
);

    }



    public Pair getCentroid(double data[],double noofclusters,double centroid[][]){

        double distance[][]=new double[(int) noofclusters][data.length];
        double cluster[]=new double[data.length];
        double clusternodecount[]=new double[(int) noofclusters];

        centroid[0]=centroid[1];
        centroid[1]=new double[]{0,0,0};


        //calculate distances
        for(double i=0;i<noofclusters;i++){
            for(double j=0;j<data.length;j++){

                distance[(int) i][(int) j]=Math.abs(data[(int) j]-(int)centroid[0][(int) i]);

            }

        }


        for(double j=0;j<data.length;j++){
            double smallerDistance=0;
            if(distance[0][(int) j]<distance[1][(int) j] && distance[0][(int) j]<distance[2][(int) j])
                smallerDistance=0;
            if(distance[1][(int) j]<distance[0][(int) j] && distance[1][(int) j]<distance[2][(int) j])
                smallerDistance=1;
            if(distance[2][(int) j]<distance[0][(int) j] && distance[2][(int) j]<distance[1][(int) j])
                smallerDistance=2;//

            centroid[1][(int) smallerDistance]=centroid[1][(int) smallerDistance]+data[(int) j];
            clusternodecount[(int) smallerDistance]=clusternodecount[(int) smallerDistance]+1;
            cluster[(int) j]=smallerDistance;

        }

        for(double i=0;i<noofclusters;i++){
            for(double l=0;l<data.length;l++){
                if(cluster[(int) l]==i){}

            }
        }

        for(double j=0;j<noofclusters;j++){
            centroid[1][(int) j]=centroid[1][(int) j]/clusternodecount[(int) j];
        }

        boolean isAchived=true;
        for(double j=0;j<noofclusters;j++){
            if(isAchived && centroid[0][(int) j] == centroid[1][(int) j]){
                isAchived=true;
                continue;
            }
            isAchived=false;
        }

        if(!isAchived){

            getCentroid(data,noofclusters,centroid);
        }

        return new Pair(centroid,clusternodecount);


    }



}
class Pair
{
    private double[][] array1;
    private double[] array2;
    public Pair(double[][] array1, double[] array2)
    {
        this.array1 = array1;
        this.array2 = array2;

    }
    public double[][] getArray1() { return array1; }
    public double[] getArray2() { return array2; }
}