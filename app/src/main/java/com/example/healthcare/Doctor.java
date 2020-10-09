package com.example.healthcare;

import java.util.ArrayList;

public class Doctor {

    public String name;
    public String hometown;

    public Doctor(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }


    //retrieve user's name
    public String getName(){
        return name;
    }

    //retrieve users' hometown
    public String getHometown(){
        return hometown;
    }

    public static ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> users = new ArrayList<Doctor>();
        users.add(new Doctor("Harry", "San Diego"));
        users.add(new Doctor("Marla", "San Francisco"));
        users.add(new Doctor("Sarah", "San Marco"));
        return users;
    }
}
