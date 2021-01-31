package com.example.healthcare;

import java.util.ArrayList;

public class Appointment {

    public String name;
    public String day;
    public String hour;
    public String app_id;



    public Appointment(String name, String day, String hour,String app_id) {
        this.name = name;
        this.day = day;
        this.hour = hour;
        this.app_id = app_id;

    }

    public Appointment(Appointment appo) {
    }


    //retrieve user's name
    public String getName(){
        return name;
    }
    public String getDay(){  return day; }
    public String getHour(){  return hour; }
    public String getAppid(){  return app_id; }


    public static ArrayList<Appointment> getAppos(ArrayList<DataObject> appos) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        for (int d=0;d<appos.size();d++)
            appointments.add(new Appointment(appos.get(d).name, appos.get(d).day,appos.get(d).hour,appos.get(d).app_id));
        return appointments;
    }
}


