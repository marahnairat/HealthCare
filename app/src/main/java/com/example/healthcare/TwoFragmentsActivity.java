package com.example.healthcare;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
   // List<String> valuesselected = Arrays.asList();
    String specialization_for_listview="";
    ArrayList<String>  valuesselected=new ArrayList<String>();
    ArrayList<String>  list_of_doctors=new ArrayList<String>();
    HashMap<String, String> map = new HashMap<String, String>();
    String result=" ";
    public int res;
    int lat= 0;
    int  lon=0;

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
        getAllSpecializationsFromFireStore(res);


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
                        ArrayList<String> symps=new ArrayList<String>();
                        Map<String, Object> mapsym = chestdoc.getData();
                        for (Map.Entry<String, Object> entry : mapsym.entrySet()) {
                            Log.d("TAG", entry.getKey().toString()+""+ entry.getValue().toString());
                            map.put(entry.getKey().toString(),entry.getValue().toString());
                            symps.add(entry.getKey().toString());
                        }
                        changeTextCheckbox(symps);

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
    public void changeTextCheckbox(ArrayList<String> s) {
        for (int i=0;i<s.size();i++) {

            createChekbox(s.get(i),i);
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







    public void getAllSpecializationsFromFireStore(int res) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("specialisations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                    String spec="";
//                                    Log.d("all documents", document.getId() + " => " + document.getData());
                                    String alldoc=document.getData().values().toString();
                                    if (res == Integer.parseInt(alldoc.substring(1,2))) {
                                        Log.d(" specialisation is: ", document.getId() );
                                        Log.d("specialisation weight ", document.getId() + " => " + res);
                                        spec = document.getId();
                                        specialization_for_listview=spec;
                                        getDoctorstoCalcAlgo(spec);
                                    }
                                }


                        } else {
                            Log.w("all documents", "Error getting documents.", task.getException());
                        }
                    }
                });



    }

    public void getDoctorstoCalcAlgo(String specialize) {
        ArrayList<DataObject> doctors = new ArrayList<DataObject>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("specialisations").document(specialize).collection("Doctors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                        doctors.add(new DataObject((int) document.getGeoPoint("location").getLatitude(),
                                                (int) document.getGeoPoint("location").getLongitude()
                                                ,document.get("name").toString(),document.get("city").toString(),document.get("phone").toString(),document.get("image").toString()));



                            }
                        } else {
                            Log.w("all documents", "Error getting documents.", task.getException());
                        }

                        ArrayList<DataObject> resultKNN = new ArrayList<DataObject>();//doctors
//                        Intent m = new Intent(getBaseContext(),mycurrentlocation.class);
//                       lon =  (int)(m.getSerializableExtra("LONGITUDE"));
//                       lat =  (int)(m.getSerializableExtra("LATITUDE"));

                        DataObject patient = new DataObject(2, 3);// patient
                        resultKNN = kNN(patient, doctors);

                        for(int i=0;i<5;i++)
                        Log.d("result of knnnnnn", resultKNN.get(i) + "\n" );

                        Intent i = new Intent(getBaseContext(),ListViews.class);
                        i.putExtra("array of doctors",resultKNN);
                        i.putExtra("specialization",specialization_for_listview);
                        startActivity(i);


                    }
                });





    }

    public static ArrayList<DataObject> kNN(DataObject o, ArrayList<DataObject> objs) {
        ArrayList<DataObject> record = new ArrayList<DataObject>();//doctors
        DataObject ref ;
        for (int i = 0; i < objs.size(); i++) {
            ref = objs.get(i);
            record.add(new DataObject(Math.sqrt((o.x - ref.x) * (o.x - ref.x) + (o.y - ref.y) * (o.y - ref.y)),ref.name,ref.city,ref.phone,ref.image));
        }

//sorting distance.
        Collections.sort(record, new Comparator<DataObject>(){
            public int compare(DataObject o1, DataObject o2){
                if(o1.getDistance() == o2.getDistance())
                    return 0;
                return o1.getDistance() < o2.getDistance() ? -1 : 1;
            }
        });
        return  record;
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

class DataObject implements Parcelable {
    int x;
    int y;
    double distance;
    String name;
    String city;
    String phone;
    String image;

    public DataObject(int x, int y)
    { this.x = x;
        this.y = y; }

    public DataObject(int x, int y, String name, String  city,String phone,String image)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.image = image;
    }
    public DataObject(double distance, String name, String  city,String phone,String image)
    {
        this.distance = distance;
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.image = image;
    }

    protected DataObject(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        distance = in.readDouble();
        name = in.readString();
        city = in.readString();
        phone = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeDouble(distance);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataObject> CREATOR = new Creator<DataObject>() {
        @Override
        public DataObject createFromParcel(Parcel in) {
            return new DataObject(in);
        }

        @Override
        public DataObject[] newArray(int size) {
            return new DataObject[size];
        }
    };

    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getPhone() {
        return phone;
    }
    public double getDistance() {
        return distance;
    }

    public String toString(){
        return "[" + getDistance() + "," + getName() + "," + getCity() +"," + getPhone() + "]";
    }
}
