package com.csuf.cpsc411.homework_3.Model;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Vehicle extends PersistentObject{
    protected int mYear;
    protected String mMake;
    protected String mModel;
    protected int mcwid;

    public Vehicle(){}

    public Vehicle(String mk, String mdl, int y, int cwid) {

        mMake = mk;
        mModel = mdl;
        mYear = y;
        mcwid=cwid;
    }

    public int getyear() {
        return mYear;
    }

    public void setyear(int year) {
        mYear = year;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = make;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("Make", mMake);
        vals.put("Model", mModel);
        vals.put("Year", mYear);
        vals.put("CWID", mcwid);
        db.insert("VEHICLE", null, vals);
    }

    @Override
    public void initFrom(Cursor c, SQLiteDatabase db) {
        mMake = c.getString(c.getColumnIndex("Make"));
        mModel = c.getString(c.getColumnIndex("Model"));
        mYear = c.getInt(c.getColumnIndex("Year"));

    }

}
