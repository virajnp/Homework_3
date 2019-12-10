package com.csuf.cpsc411.homework_3.Model;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersistentObject{
    protected String Firstname;
    protected String Lastname;
    protected int cwid;

    
    protected ArrayList<com.csuf.cpsc411.homework_3.Model.Vehicle> mvehicle;

    public Student(){

    }

    public Student(String fname,String lname, int id){
        Firstname = fname;
        Lastname = lname;
        cwid = id;
    }

    public String getFirstname(){
        return Firstname;
    }

    public String getLastname(){
        return Lastname;
    }

    public int getCwid(){
        return cwid;
    }


    public void Setfirstname(String fname){
        Firstname=fname;
    }

    public void Setlastname(String lname){
        Lastname=lname;
    }

    public void SetCwid(int cid){
        cwid=cid;
    }


    public void setVehicleid(ArrayList<Vehicle>  veh){ mvehicle = veh;
    }

    public ArrayList<Vehicle> getVehicleid(){
        return mvehicle;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        //
        ContentValues vals = new ContentValues();
        vals.put("FirstName", Firstname);
        vals.put("LastName", Lastname);
        vals.put("CWID", cwid);



        db.insert("Student", null, vals);

        // Insert the vehicle objects
        for (int i=0; i<mvehicle.size(); i++) {
            mvehicle.get(i).insert(db);
        }
    }


    @Override
    public void initFrom(Cursor c, SQLiteDatabase db) {
        Firstname = c.getString(c.getColumnIndex("FirstName"));
        Lastname = c.getString(c.getColumnIndex("LastName"));
        cwid = c.getInt(c.getColumnIndex("CWID"));


        mvehicle = new ArrayList<Vehicle>();
        Cursor cursor = db.query("VEHICLE", null, "CWID=?", new String[]{new Integer(cwid).toString()},null,null,null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Vehicle vObj = new Vehicle();
                vObj.initFrom(cursor, db);
                mvehicle.add(vObj);
            }
        }
    }




}
