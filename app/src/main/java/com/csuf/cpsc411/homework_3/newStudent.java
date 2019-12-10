package com.csuf.cpsc411.homework_3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csuf.cpsc411.homework_3.Model.Student;
import com.csuf.cpsc411.homework_3.Model.StudentDB;
import com.csuf.cpsc411.homework_3.Model.Vehicle;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class newStudent extends AppCompatActivity {

    ArrayList<Vehicle> vehicle =  new ArrayList<Vehicle>();
    protected Student stud;
    protected int studIndx;
    protected View bt,bt1;
    protected Menu detailMenu;
    protected final String TAG = "Detail Screen";
    private LinearLayout vehiclelLL;
    protected EditText eV0,eV1,eV2,eV3;
    protected int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.student_name);

        studIndx = new StudentDB(this).retrieveStudentObjects().size();

        System.out.println(studIndx);

        vehiclelLL = (LinearLayout) findViewById(R.id.vehicle_list_id1);

        TextView tv = findViewById(R.id.display_string_id1);

        int s1=studIndx+1;
        String origStr = (String) tv.getText();
        tv.setText(origStr + s1);
        tv.setTextSize(20);

        EditText editView = findViewById(R.id.first_name_id1);
        editView.setEnabled(true);
        editView = findViewById(R.id.last_name_id1);
        editView.setEnabled(true);
        editView = findViewById(R.id.cwid_id1);
        editView.setEnabled(true);
        bt= findViewById(R.id.btns);
        bt.setVisibility(View.GONE);
        bt1=findViewById(R.id.btns1);
        bt1.setVisibility(View.GONE);

        LayoutInflater inflaters = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflaters.inflate(R.layout.vehicle_rows, null);

        vehiclelLL.addView(rowView, vehiclelLL.getChildCount()-2);


        bt.findViewById(R.id.btns).setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_done).setVisible(true);

        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:


                EditText editView = findViewById(R.id.first_name_id1);
                EditText editView1 = findViewById(R.id.last_name_id1);
                eV3= findViewById(R.id.cwid_id1);

                if(TextUtils.isEmpty(editView.getText())){

                    eV0.setError( "First Name required!,please enter in field" );

                } else if(TextUtils.isEmpty(editView1.getText())) {
                    editView1.setError( "Last name required!,Please enter in field" );


                } else if(TextUtils.isEmpty(eV3.getText())) {
                    eV2.setError( "Cwid required!,Please enter in field" );

                } else {
                    stud = new Student(editView.getText().toString(),editView1.getText().toString(),Integer.valueOf(eV3.getText().toString()));
                    stud.setVehicleid(vehicle);
                    new StudentDB(this).addstudentist(stud);
                    //perform any action;
                    Intent i = new Intent(newStudent.this, SummaryActivity.class);
                    startActivity(i);
                    return true;


                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void addvehicle(View v) {

        eV0 = findViewById(R.id.veh_make_ids);
        eV1 = findViewById(R.id.veh_model_ids);
        eV2 = findViewById(R.id.veh_year_ids);
        eV3= findViewById(R.id.cwid_id1);

        if(TextUtils.isEmpty(eV0.getText())){

            eV0.setError( "Make is required!" );

        } else if(TextUtils.isEmpty(eV1.getText())) {
            eV1.setError( "Model is required!" );


        } else if(TextUtils.isEmpty(eV2.getText())) {
            eV2.setError( "Year is required!" );

        } else {


            vehicle.add(new Vehicle(eV0.getText().toString(), eV1.getText().toString(), Integer.valueOf(eV2.getText().toString()),Integer.valueOf(eV3.getText().toString())));
            bt1.findViewById(R.id.btns1).setVisibility(View.GONE);
            bt.findViewById(R.id.btns).setVisibility(View.VISIBLE);

            detailMenu.findItem(R.id.action_done).setVisible(true);
        }


        for(i=0;i<vehicle.size();i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.vehicle_row, null);

            Vehicle c = vehicle.get(i);
            rowView.setId(new Integer(i));


            EditText ed = rowView.findViewById(R.id.veh_make_id);
            ed.setText(c.getMake());
            ed.setEnabled(false);
            EditText ed1 = rowView.findViewById(R.id.veh_model_id);
            ed1.setText(c.getModel());
            ed1.setEnabled(false);
            EditText ed2 = rowView.findViewById(R.id.veh_year_id);
            ed2.setText(Integer.toString(c.getyear()));
            ed2.setEnabled(false);
            rowView.setTag(ed);

            vehiclelLL.addView(rowView, vehiclelLL.getChildCount() - 2);


        }

        eV0.getText().clear();
        eV1.getText().clear();
        eV2.getText().clear();
        bt1.findViewById(R.id.btns1).setVisibility(View.GONE);
        bt.findViewById(R.id.btns).setVisibility(View.VISIBLE);


    }


}
