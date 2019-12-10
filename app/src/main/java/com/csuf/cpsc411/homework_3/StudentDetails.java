package com.csuf.cpsc411.homework_3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class StudentDetails extends AppCompatActivity {
    protected Student s;
    ArrayList<Vehicle> vehicle;
    protected int studentIndx;
    protected View bt,bt1;
    protected Menu detailMenu;
    protected final String TAG = "Detail Screen";
    private LinearLayout vehiclelinearlayout;
    protected int i;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.student_details_add);


        studentIndx = getIntent().getIntExtra("StudentIndex", 0);
        s =new  StudentDB(this).retrieveStudentObjects().get(studentIndx);

        vehiclelinearlayout = (LinearLayout) findViewById(R.id.vehicle_list_id);

        TextView tv = findViewById(R.id.display_string_id);

        int s1=studentIndx+1;
        String origStr = (String) tv.getText();
        tv.setText(origStr + s1);
        tv.setTextSize(20);

        EditText editView = findViewById(R.id.first_name_id);
        editView.setText(s.getFirstname());
        editView.setEnabled(false);
        editView = findViewById(R.id.last_name_id);
        editView.setText(s.getLastname());
        editView.setEnabled(false);
        editView = findViewById(R.id.cwid_id);
        editView.setText(Integer.toString(s.getCwid()));
        editView.setEnabled(false);
        bt= findViewById(R.id.btn);
        bt.setVisibility(View.GONE);
        bt1=findViewById(R.id.btn1);
        bt1.setVisibility(View.GONE);


        for(i=0;i<s.getVehicleid().size();i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.vehicle_row, null);

            Vehicle c = s.getVehicleid().get(i);
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

            vehiclelinearlayout.addView(rowView, vehiclelinearlayout.getChildCount() - 1);


        }









    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);

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
            case R.id.action_add:
                //perform any action;
                Intent i = new Intent(StudentDetails.this, newStudent.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
