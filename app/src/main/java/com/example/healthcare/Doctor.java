package com.example.healthcare;

import java.util.ArrayList;

public class Doctor {

    public String name;
    public String hometown;
    public String phone;
    public String image;
    public String specialization;


    public Doctor(String name, String hometown, String phone ,String image) {
        this.name = name;
        this.hometown = hometown;
        this.phone = phone;
        this.image = image;
    }

    public Doctor(Doctor doctor) {
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
    public String getImage(){  return image; }

    public static ArrayList<Doctor> getDoctors(ArrayList<DataObject> docs) {
        ArrayList<Doctor> users = new ArrayList<Doctor>();
        for (int d=0;d<5;d++)
            users.add(new Doctor(docs.get(d).name, docs.get(d).city,docs.get(d).phone,docs.get(d).image));
        return users;
    }
}
