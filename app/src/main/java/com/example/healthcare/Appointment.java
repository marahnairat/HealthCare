package com.example.healthcare;

import java.util.ArrayList;

public class Appointment {

    public String dr_name;
    public String p_name;
    public String day;
    public String hour;
    public String app_id;



    public Appointment(String dr_name,String p_name, String day, String hour,String app_id) {
        this.dr_name = dr_name;
        this.p_name = p_name;
        this.day = day;
        this.hour = hour;
        this.app_id = app_id;

    }

    public Appointment(Appointment appo) {
    }


    //retrieve user's name
    public String getdrName(){
        return dr_name;
    }
    public String getpName(){
        return p_name;
    }
    public String getDay(){  return day; }
    public String getHour(){  return hour; }
    public String getAppid(){  return app_id; }


    public static ArrayList<Appointment> getAppos(ArrayList<DataObject> appos) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        for (int d=0;d<appos.size();d++)
            appointments.add(new Appointment(appos.get(d).dr_name, appos.get(d).p_name, appos.get(d).day,appos.get(d).hour,appos.get(d).app_id));
        return appointments;
    }
}


