package com.example.healthcare;


import android.annotation.SuppressLint;
import android.content.ClipData;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
   // List<String> valuesselected = Arrays.asList();
    ArrayList<String>  valuesselected=new ArrayList<String>();
   // tring [] valuesselected ;S
    HashMap<String, String> map = new HashMap<String, String>();
    String result=" ";
    public int res;

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
        int data[] = new int[valuesselected.size()];
        for (int m=0;m<valuesselected.size();m++) {
            data[m]= (int) Double.parseDouble(valuesselected.get(m))*10;
        }
//        int data[]={13,15,33,41,14,19,75,16};
        int noofclusters=3;
        double centroid[][]=new double[][]{
                {0,0,0},
                {getMinValue(data),(getMinValue(data)+getMaxValue(data))/2,getMaxValue(data)}
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
                max= c.getArray2()[j];
                k=j;

            }

        }
        Log.d("Document", "max length print k   " +k );
        Log.d("Document", "RESUIUUUUUUUUUUUULLLT " +(int)c.getArray1()[1][k] );

int clustersdata[]= {10,30,50,70,90,110,130};
         res=getNearestValue(clustersdata,(int)c.getArray1()[1][k]);
        Log.d("Document", "RESUIUUUUUUUUUUUULLLT " +res);
        res=res/10;
        Log.d("Document", "RESUIUUUUUUUUUUUULLLT " +res );
       geAllFromFireStore(res);










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


    public Pair getCentroid(int data[],int noofclusters,double centroid[][]){

        int distance[][]=new int[noofclusters][data.length];
        int cluster[]=new int[data.length];
        int clusternodecount[]=new int[ noofclusters];

        centroid[0]=centroid[1];
        centroid[1]=new double[]{0,0,0};

        //calculate distances
        for(int i=0;i<noofclusters;i++){
            for(int j=0;j<data.length;j++){

                distance[i][ j]=Math.abs(data[ j]-(int)centroid[0][i]);

            }

        }


        for(int j=0;j<data.length;j++){
            int smallerDistance=0;
            if(distance[0][j]<distance[1][ j] && distance[0][ j]<distance[2][ j])
                smallerDistance=0;
            if(distance[1][ j]<distance[0][j] && distance[1][ j]<distance[2][ j])
                smallerDistance=1;
            if(distance[2][j]<distance[0][j] && distance[2][ j]<distance[1][j])
                smallerDistance=2;//

            centroid[1][smallerDistance]=centroid[1][smallerDistance]+data[ j];
            clusternodecount[ smallerDistance]=clusternodecount[ smallerDistance]+1;
            cluster[(int) j]=smallerDistance;

        }

        for(int i=0;i<noofclusters;i++){
            for(int l=0;l<data.length;l++){
                if(cluster[l]==i){}

            }
        }

        for(int j=0;j<noofclusters;j++){
            centroid[1][ j]=(centroid[1][j]/clusternodecount[j]);
        }

        boolean isAchived=true;
        for(int j=0;j<noofclusters;j++){
            if(isAchived && centroid[0][ j] == centroid[1][j]){
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



    public static int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }
    public static int getMinValue(int[] numbers){
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }
    public static int getNearestValue(int[] numbers,int myNumber){

        int distance = Math.abs(numbers[0] - myNumber);
        int idx = 0;
        for(int c = 1; c < numbers.length; c++){
            int cdistance = Math.abs(numbers[c] - myNumber);
            if(cdistance < distance){
                idx = c;
                distance = cdistance;
            }
        }
        return numbers[idx];
    }







    public  void geAllFromFireStore(int res) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<ClipData.Item> allItems = new ArrayList<>();

        db.collection("specialisations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                {
//                                    Log.d("all documents", document.getId() + " => " + document.getData());
                                    String alldoc=document.getData().values().toString();

                                    if (res == Integer.parseInt(alldoc.substring(1,2))) {
                                        Log.d(" specialisation is: ", document.getId() );
                                        Log.d("specialisation weight ", document.getId() + " => " + res);
                                    }
                                }

                            }
                        } else {
                            Log.w("all documents", "Error getting documents.", task.getException());
                        }
                    }
                });

    }









}
class Pair
{
    private double[][] array1;
    private int[] array2;
    public Pair(double[][] array1, int[] array2)
    {
        this.array1 = array1;
        this.array2 = array2;

    }
    public double[][] getArray1() { return array1; }
    public int[] getArray2() { return array2; }
}