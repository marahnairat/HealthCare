package com.example.healthcare;

import java.util.ArrayList;

public class Doctor {

    public String name;
    public String hometown;
    public String phone;

    public Doctor(String name, String hometown, String phone) {
        this.name = name;
        this.hometown = hometown;
        this.phone = phone;
    }


    //retrieve user's name
    public String getName(){
        return name;
    }

    //retrieve users' hometown
    public String getHometown(){
        return hometown;
    }
    public String getPhone(){  return phone; }

    public static ArrayList<Doctor> getDoctors(ArrayList<DataObject> docs) {
        ArrayList<Doctor> users = new ArrayList<Doctor>();
//        users.add(new Doctor("Harry", "San Diego"));
//        users.add(new Doctor("Marla", "San Francisco"));
//        users.add(new Doctor("Sarah", "San Marco"));
        for (int d=0;d<5;d++)
            users.add(new Doctor(docs.get(d).name, docs.get(d).city,docs.get(d).phone));
        return users;
    }
}
