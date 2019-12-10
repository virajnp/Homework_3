package com.csuf.cpsc411.homework_3;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.csuf.cpsc411.homework_3.Adapter.Summaryadapter;
import com.csuf.cpsc411.homework_3.Model.StudentDB;

import androidx.appcompat.app.AppCompatActivity;


public class SummaryActivity extends AppCompatActivity {
    protected ListView mSummaryView;
    protected final String TAG = "Summary Screen";
    protected Summaryadapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_name_lv);
        mSummaryView = findViewById(R.id.summary_list_id);
        new StudentDB(this).retrieveStudentObjects();
        ad = new Summaryadapter(this);
        mSummaryView.setAdapter(ad);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");

        ad.notifyDataSetChanged();
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
}


