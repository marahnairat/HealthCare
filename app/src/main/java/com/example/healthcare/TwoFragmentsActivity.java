package com.example.healthcare;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.LocationRequest;
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


public class TwoFragmentsActivity extends FragmentActivity implements LocationListener ,
      ThreeFragment.ThreeFragmentListener {
   // List<String> valuesselected = Arrays.asList();
    String specialization_for_listview="";
    ArrayList<String>  valuesselected=new ArrayList<String>();
    ArrayList<String>  list_of_doctors=new ArrayList<String>();
    HashMap<String, String> map = new HashMap<String, String>();
    String result=" ";
    public int res;
   int  lat;
   int lon;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(TwoFragmentsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TwoFragmentsActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        getLocation();

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
        return true;
    }



//
//    @Override
//    public void onButtonClick() {
//for(int i=0;i<valuesselected.size();i++)
//        Log.i("Test", valuesselected.get(i));
//
//    }


    public void continueCheck(View view) {
        result=" ";

        int data[] = new int[valuesselected.size()];
        for (int m=0;m<valuesselected.size();m++) {
            data[m]= (int) (Double.parseDouble(valuesselected.get(m))*10);
            Log.d("valuesselected is ", valuesselected.get(m) );

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
                                  valuesselected.remove(map.get(cb.getText()));
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
            if (clusternodecount[j]==0)
                continue;
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
                                    int alldoc=document.getLong("index").intValue();
                                    if (res == alldoc) {
                                        Log.d(" specialisation is: ", document.getId() );
                                        Log.d("specialisation weight ", document.getId() + " => " + res);
                                        spec = document.getId();
                                        specialization_for_listview=spec;
                                        getDoctorstoCalcAlgo(spec);
                                        break;
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
        String s=specialize;
        Log.d("ssssssssssssssssspppp",s);
        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        db1.collection("specialisations").document(specialize).collection("Doctors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult
                                    ()) {
                                        doctors.add(new DataObject( document.getGeoPoint("location").getLatitude(),
                                                 document.getGeoPoint("location").getLongitude(),document.get("name").toString(),
                                                document.get("city").toString(),
                                                document.get("phone").toString()
                                                ,document.get("image").toString(),
                                                document.get("uid").toString(),specialize));



                            }
                        } else {
                            Log.w("all documents", "Error getting documents.", task.getException());
                        }

                        ArrayList<DataObject> resultKNN = new ArrayList<DataObject>();//doctors

                        DataObject patient = new DataObject(lat, lon);// patient
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
            record.add(new DataObject(Math.sqrt((o.x - ref.x) * (o.x - ref.x) + (o.y - ref.y) * (o.y - ref.y)),ref.dr_name,ref.city,ref.phone,ref.image,ref.dr_id,ref.spec));
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
    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,TwoFragmentsActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        lat=(int)location.getLatitude();
        lon=(int)location.getLongitude();
        Toast.makeText(this, ""+lat+","+lon, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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
    double x;
    double y;
    double distance;
    String dr_name;
    String p_name;
    String city;
    String phone;
    String dr_id;
    String image;
    String day;
    String hour;
    String app_id;
    String spec;

    public DataObject(double x, double y)
    { this.x = x;
        this.y = y; }

    public DataObject(double x, double y, String dr_name, String  city,String phone ,String image,String dr_id,String spec)
    {
        this.x = x;
        this.y = y;
        this.dr_name = dr_name;
        this.city = city;
        this.phone = phone;
        this.dr_id = dr_id;
        this.image = image;
        this.spec = spec;

    }
    public DataObject(double distance, String dr_name, String  city,String phone,String image, String dr_id,String spec)
    {
        this.distance = distance;
        this.dr_name = dr_name;
        this.city = city;
        this.phone = phone;
        this.dr_id = dr_id;
        this.image = image;
        this.spec = spec;
    }
    public DataObject( String dr_name,String p_name, String  day,String hour,String app_id)
    {
        this.dr_name = dr_name;
        this.p_name = p_name;
        this.day = day;
        this.hour = hour;
        this.app_id = app_id;

    }

    protected DataObject(Parcel in) {
        x = in.readDouble();
        y = in.readDouble();
        distance = in.readDouble();
        dr_name = in.readString();
        p_name = in.readString();
        city = in.readString();
        phone = in.readString();
        dr_id = in.readString();
        image = in.readString();
        day = in.readString();
        hour = in.readString();
        app_id = in.readString();
        spec = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(x);
        dest.writeDouble(y);
        dest.writeDouble(distance);
        dest.writeString(dr_name);
        dest.writeString(p_name);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(dr_id);
        dest.writeString(image);
        dest.writeString(day);
        dest.writeString(hour);
        dest.writeString(app_id);
        dest.writeString(spec);
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

    public String getdrName() {
        return dr_name;
    }
    public String getpName() {
        return p_name;
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
    public String getHour() {
        return hour;
    }
    public String getDay() {
        return day;
    }
    public String getApp_id() {
        return app_id;
    }
    public String getSpec() {
        return spec;
    }

    public String toString(){
        return "[" + getDistance() + "," + getdrName() + "," + getCity() +"," + getPhone() + "]";
    }
}
